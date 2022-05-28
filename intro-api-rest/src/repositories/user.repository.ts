import { User } from '../models';
import { db } from '../db';
import { DatabaseError } from '../models/errors';

class UserRepository {
  async findAll(): Promise<User[]> {
    const query = `
      SELECT id, user 
      FROM application_user
    `;

    const { rows } = await db.query<User>(query);

    return rows || [];
  }

  async findById(id: string): Promise<User> {
    try {
      const query = `
      SELECT id, user 
      FROM application_user
      WHERE id = $1
    `;

      const values = [id];
      const { rows } = await db.query<User>(query, values);
      const [user] = rows;

      return user;
    } catch (error) {
      throw new DatabaseError('error on find by id', error);
    }
  }

  async create(user: User): Promise<string> {
    const script = `
      INSERT INTO application_user(
        username,
        password
      )
      VALUES ($1, crypt($2, 'my_salt'))
      RETURNING id
    `;

    const values = [user.username, user.password];

    const { rows } = await db.query<{ id: string }>(script, values);
    const [newUser] = rows;
    return newUser.id;
  }

  async update(user: User, id: string): Promise<void> {
    const script = `
      UPDATE application_user
      SET 
        username = $1
      WHERE id = $2
    `;

    const values = [user.username, id];

    await db.query(script, values);
  }

  async remove(id: string): Promise<void> {
    const script = `
    DELETE 
    FROM application_user
    where id = $1
    `;

    const values = [id];

    await db.query(script, values);
  }

  async findByUsernameAndPassword(
    username: string,
    password: string
  ): Promise<User | null> {
    try {
      const query = `
      SELECT username, password
      FROM application_user
      WHERE username = $1
      AND password = crypt($2, 'my_salt')
    `;

      const values = [username, password];

      const { rows } = await db.query<User>(query, values);

      const [user] = rows;

      return user || null;
    } catch (error) {
      throw new DatabaseError('Error on find by username and password', error);
    }
  }
}

export const userRepository = new UserRepository();
