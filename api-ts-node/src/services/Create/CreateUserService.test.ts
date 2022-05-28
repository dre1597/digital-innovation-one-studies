import { getConnection } from 'typeorm';
import createConnection from '../../database';
import { CreateUserService } from './CreateUserService';

describe('CreateUserService', () => {
  beforeAll(async () => {
    const connection = await createConnection();
    await connection.runMigrations();
  });

  afterAll(async () => {
    const connection = getConnection();
    await connection.query('DELETE FROM users');
    await connection.close();
  });

  it('Should return an id on success', async () => {
    const createUserService = new CreateUserService();

    const result = await createUserService.execute({
      id: 'any_id',
      name: 'any_name',
      email: ' any_email@email.com',
    });

    expect(result).toHaveProperty('id');
  });
});
