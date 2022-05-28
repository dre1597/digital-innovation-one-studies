import { UpdateUserService } from '../../services';
import { Request, Response } from 'express';

export class UpdateUserController {
  async handle(request: Request, response: Response) {
    const updateUserService = new UpdateUserService();

    const { id, name, email } = request.body;

    if (id.length === 0) {
      return response.status(400).json({ message: 'ID field is required' });
    }

    if (name.length === 0) {
      return response.status(400).json({ message: 'Name field is required' });
    }

    await updateUserService.execute({ id, name, email });

    return response.status(204).send();
  }
}
