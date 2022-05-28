import { NextFunction, Request, Response, Router } from 'express';
import { StatusCodes } from 'http-status-codes';
import { userRepository } from '../repositories';

const usersRoute = Router();

usersRoute.get('/users', async (_req: Request, res: Response) => {
  const users = await userRepository.findAll();
  res.status(StatusCodes.OK).send(users);
});

usersRoute.get(
  '/users/:id',
  async (req: Request<{ id: string }>, res: Response, next: NextFunction) => {
    try {
      const id = req.params.id;
      const user = await userRepository.findById(id);
      res.status(StatusCodes.OK).send(user);
    } catch (error) {
      next(error);
    }
  }
);

usersRoute.post('/users', async (req: Request, res: Response) => {
  const newUser = req.body;

  const id = await userRepository.create(newUser);

  res.status(StatusCodes.CREATED).send(id);
});

usersRoute.put(
  '/users/:id',
  async (req: Request<{ id: string }>, res: Response) => {
    const id = req.params.id;
    const modifiedUser = req.body;

    await userRepository.update(modifiedUser, id);

    res.status(StatusCodes.NO_CONTENT).send();
  }
);

usersRoute.delete(
  '/users/:id',
  async (req: Request<{ id: string }>, res: Response) => {
    const id = req.params.id;
    await userRepository.remove(id);

    res.status(StatusCodes.NO_CONTENT).send();
  }
);

export { usersRoute };
