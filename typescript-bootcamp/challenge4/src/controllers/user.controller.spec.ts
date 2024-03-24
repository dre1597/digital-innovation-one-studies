import { Request } from 'express';

import { UserController } from './user.controller';
import { UserService } from '../services/user.service';
import { makeMockResponse } from '../__mocks__/mock-response.mock';

describe('UserController', () => {
  const mockUserService: Partial<UserService> = {
    createUser: jest.fn(),
    getAllUsers: jest.fn(),
    deleteUser: jest.fn(),
  };

  const userController = new UserController(mockUserService as UserService);

  it('should add an new user', () => {
    const mockRequest = {
      body: {
        name: 'any_name',
        email: 'any_email@email.com'
      }
    } as Request;
    const mockResponse = makeMockResponse();
    userController.createUser(mockRequest, mockResponse);
    expect(mockResponse.state.status).toBe(201);
    expect(mockResponse.state.json).toMatchObject({ message: 'User created' });
  });

  it('should throw an error if name is missing', () => {
    const mockRequest = {
      body: {
        email: 'any_email@email.com'
      }
    } as Request;
    const mockResponse = makeMockResponse();
    userController.createUser(mockRequest, mockResponse);
    expect(mockResponse.state.status).toBe(400);
    expect(mockResponse.state.json).toMatchObject({ message: 'Bad request! Missing name' });
  });

  it('should throw an error if email is missing', () => {
    const mockRequest = {
      body: {
        name: 'any_name'
      }
    } as Request;
    const mockResponse = makeMockResponse();
    userController.createUser(mockRequest, mockResponse);
    expect(mockResponse.state.status).toBe(400);
    expect(mockResponse.state.json).toMatchObject({ message: 'Bad request! Missing email' });
  });

  it('should get all users', () => {
    const mockRequest = {} as Request;
    const mockResponse = makeMockResponse();
    userController.getAllUsers(mockRequest, mockResponse);

    const spy = jest.spyOn(mockUserService, 'getAllUsers').mockReturnValue(mockUserService.getAllUsers!());
    expect(mockResponse.state.status).toBe(200);
    expect(spy).toHaveBeenCalled();
  });

  it('should delete a user', () => {
    const mockRequest = {
      body: {
        email: 'any_email@email.com'
      }
    } as Request;
    const mockResponse = makeMockResponse();
    userController.deleteUser(mockRequest, mockResponse);
    expect(mockResponse.state.status).toBe(200);
    expect(mockResponse.state.json).toMatchObject({ message: 'User deleted' });
  });

  it('should not delete a user if the user does not exist', () => {
    const mockRequest = {
      body: {
        email: 'invalid_email@email.com'
      }
    } as Request;
    const mockResponse = makeMockResponse();
    userController.deleteUser(mockRequest, mockResponse);
    expect(mockResponse.state.status).toBe(200);
  });
});
