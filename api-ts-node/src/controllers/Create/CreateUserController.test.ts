import { Request } from 'express';
import { getConnection } from 'typeorm';
import createConnection from '../../database';
import { makeMockResponse } from '../../utils/mocks/mockResponse';
import { CreateUserController } from './CreateUserController';

describe('CreateUserController', () => {
  beforeAll(async () => {
    const connection = await createConnection();
    await connection.runMigrations();
  });

  afterAll(async () => {
    const connection = getConnection();
    await connection.query('DELETE FROM users');
    await connection.close();
  });

  const createUserController = new CreateUserController();

  const response = makeMockResponse();
  it('Should return 201 on success', async () => {
    const request = {
      body: {
        name: 'any_user',
        email: 'any_email@email.com',
      },
    } as Request;

    await createUserController.handle(request, response);

    expect(response.state.status).toBe(201);
  });

  it('Should return 422 if no name is provided', async () => {
    const request = {
      body: {
        name: '',
        email: 'any_email@email.com',
      },
    } as Request;

    await createUserController.handle(request, response);

    expect(response.state.status).toBe(422);
  });

  it('Should return 201 if no email is provided', async () => {
    const request = {
      body: {
        name: 'any_user',
        email: '',
      },
    } as Request;

    await createUserController.handle(request, response);

    expect(response.state.status).toBe(201);
  });
});
