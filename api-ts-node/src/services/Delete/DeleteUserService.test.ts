import { getConnection } from 'typeorm';
import createConnection from '../../database';
import { CreateOneUserFactory } from '../../utils/factories/CreateOneUserFactory';
import { DeleteUserService } from './DeleteUserService';

describe('DeleteUserService', () => {
  beforeAll(async () => {
    const connection = await createConnection();
    await connection.runMigrations();
  });

  afterAll(async () => {
    const connection = getConnection();
    await connection.query('DELETE FROM users');
    await connection.close();
  });

  it('Should return an empty array on success', async () => {
    const user = await new CreateOneUserFactory().execute();

    const deleteUserService = new DeleteUserService();

    const result = await deleteUserService.execute({ id: user.id });

    expect(result).toHaveLength(0);
  });
});
