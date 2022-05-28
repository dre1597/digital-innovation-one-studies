import { Router, Request, Response } from 'express';
import { CreateUserController, ListAllUsersController } from './controllers';
import { UpdateUserController } from './controllers/Update/UpdateUserController';

const listAllUsersController = new ListAllUsersController();
const createUserController = new CreateUserController();
const updateUserController = new UpdateUserController();

export const router = Router();

router.get('/', (request: Request, response: Response) => {
  return response.json({ message: 'Hello world' });
});

router.get('/users', listAllUsersController.handle);

router.post('/users', createUserController.handle);

router.put('/users', updateUserController.handle);
