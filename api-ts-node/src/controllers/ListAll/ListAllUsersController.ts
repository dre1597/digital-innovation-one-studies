import { Request, Response } from 'express';
import { ListAllUsersService } from '../../services';

export class ListAllUsersController {
  async handle(request: Request, response: Response) {
    const listAllUsersService = new ListAllUsersService();

    const users = await listAllUsersService.execute();

    return response.status(200).json(users);
  }
}
