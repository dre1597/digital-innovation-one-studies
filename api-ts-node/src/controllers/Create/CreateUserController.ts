import { Request, Response } from 'express';
import { v4 as uuid } from 'uuid';
import { CreateUserService } from '../../services';

export class CreateUserController {
  async handle(request: Request, response: Response) {
    const createUserService = new CreateUserService();

    const { name, email } = request.body;

    const id = uuid();

    if (!name || name.length === 0) {
      return response.status(422).json({ message: 'Name is required' });
    }

    const user = await createUserService.execute({ id, name, email });

    return response.status(201).json(user);
  }
}
