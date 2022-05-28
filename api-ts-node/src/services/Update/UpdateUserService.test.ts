import { getConnection } from 'typeorm';
import createConnection from '../../database';
import { CreateOneUserFactory } from '../../utils/factories/CreateOneUserFactory';
import { UpdateUserService } from '../index';

describe('UpdateUserService', () => {
  beforeAll(async () => {
    const connection = await createConnection();
    await connection.runMigrations();
  });

  afterAll(async () => {
    const connection = getConnection();
    await connection.query('DELETE FROM users');
    await connection.close();
  });

  it('Should edit the user name', async () => {
    const updateUserService = new UpdateUserService();

    const user = await new CreateOneUserFactory().execute();

    const result = await updateUserService.execute({
      id: user.id,
      name: 'another_user',
    });

    expect(result).toHaveLength(0);
  });
});
