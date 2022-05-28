import createConnection from '../../database';
import { getConnection } from 'typeorm';
import { CreateOneUserFactory } from '../../utils/factories/CreateOneUserFactory';
import { DeleteUserController } from './DeleteUserController';
import { makeMockRequest } from '../../utils/mocks/mockRequest';
import { makeMockResponse } from '../../utils/mocks/mockResponse';

describe('DeleteUserController', () => {
  beforeAll(async () => {
    const connection = await createConnection();
    await connection.runMigrations();
  });

  afterAll(async () => {
    const connection = getConnection();
    await connection.query('DELETE FROM users');
    await connection.close();
  });

  it('Should return 204 on success', async () => {
    const user = await new CreateOneUserFactory().execute();

    const deleteUserController = new DeleteUserController();

    const request = makeMockRequest({
      params: {
        id: user.id,
      },
    });

    const response = makeMockResponse();

    await deleteUserController.handle(request, response);

    expect(response.state.status).toBe(204);
  });
});
