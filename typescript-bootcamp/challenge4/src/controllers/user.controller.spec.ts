import { Request } from 'express';

import { UserController } from './user.controller';
import { UserService } from '../services/user.service';
import { makeMockResponse } from '../__mocks__/mock-response.mock';

describe('UserController', () => {
  const mockUserService: Partial<UserService> = {
    createUser: jest.fn()
  }

  const userController = new UserController(mockUserService as UserService);

  it('should add an new user', () => {
    const mockRequest = {
      body: {
        name: 'any_name',
        email: 'any_email@email.com'
      }
    } as Request
    const mockResponse = makeMockResponse()
    userController.createUser(mockRequest, mockResponse)
    expect(mockResponse.state.status).toBe(201)
    expect(mockResponse.state.json).toMatchObject({ message: 'User created' })
  })
})
