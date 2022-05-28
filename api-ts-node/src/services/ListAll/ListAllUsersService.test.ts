import { getConnection } from 'typeorm';
import createConnection from '../../database';
import { CreateManyUsersFactory } from '../../utils/factories/CreateManyUsersFactory';
import { ListAllUsersService } from './ListAllUsersService';

describe('ListAllUsersService', () => {
  beforeAll(async () => {
    const connection = await createConnection();
    await connection.runMigrations();
  });

  afterAll(async () => {
    const connection = getConnection();
    await connection.query('DELETE FROM users');
    await connection.close();
  });

  it('Should return all created users', async () => {
    await new CreateManyUsersFactory().execute();

    const expectedResponse = [
      {
        name: 'any_user1',
        email: 'anyemail@email.com',
      },
      {
        name: 'any_user2',
        email: '',
      },
    ];

    const listAllUsersService = new ListAllUsersService();

    const result = await listAllUsersService.execute();

    expect(result).toMatchObject(expectedResponse);
  });
});
