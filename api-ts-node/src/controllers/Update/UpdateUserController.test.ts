import { getConnection } from 'typeorm';
import createConnection from '../../database';
import { CreateOneUserFactory } from '../../utils/factories/CreateOneUserFactory';
import { UpdateUserController } from './UpdateUserController';
import { Request } from 'express';
import { makeMockResponse } from '../../utils/mocks/mockResponse';

describe('UpdateUserController', () => {
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

    const updateUserController = new UpdateUserController();

    const request = {
      body: {
        id: user.id,
        name: 'another_name',
        email: 'another_email@email.com.br',
      },
    } as Request;

    const response = makeMockResponse();

    await updateUserController.handle(request, response);

    expect(response.state.status).toBe(204);
  });
});
