import { Request, Response } from 'express';

import { UserService } from '../services/user.service';

export class UserController {
  userService: UserService;
  createUser = (request: Request, response: Response): Response => {
    const user = request.body;

    if (!user.name) {
      return response.status(400).json({ message: 'Bad request! Missing name' });
    }

    if (!user.email) {
      return response.status(400).json({ message: 'Bad request! Missing email' });
    }

    this.userService.createUser(user.name, user.email);
    return response.status(201).json({ message: 'User created' });
  };
  getAllUsers = (request: Request, response: Response) => {
    const users = this.userService.getAllUsers();
    return response.status(200).json(users);
  };

  constructor(
    userService = new UserService()
  ) {
    this.userService = userService;
  }
}
