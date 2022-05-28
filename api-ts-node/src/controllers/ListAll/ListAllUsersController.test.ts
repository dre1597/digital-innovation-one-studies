import { getConnection } from 'typeorm';
import createConnection from '../../database';
import { CreateManyUsersFactory } from '../../utils/factories/CreateManyUsersFactory';
import { makeMockRequest } from '../../utils/mocks/mockRequest';
import { makeMockResponse } from '../../utils/mocks/mockResponse';
import { ListAllUsersController } from './ListAllUsersController';

describe('ListAllUsersController', () => {
  beforeAll(async () => {
    const connection = await createConnection();
    await connection.runMigrations();
  });

  afterAll(async () => {
    const connection = getConnection();
    await connection.query('DELETE FROM users');
    await connection.close();
  });

  it('Should return 200 on success', async () => {
    await new CreateManyUsersFactory().execute();

    const listAllUsersController = new ListAllUsersController();

    const request = makeMockRequest({});

    const response = makeMockResponse();

    await listAllUsersController.handle(request, response);

    expect(response.state.status).toBe(200);
  });
});
