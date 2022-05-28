import { Request, Response, Router } from 'express';
import { StatusCodes } from 'http-status-codes';

const statusRoute = Router();

statusRoute.get('/status', (_req: Request, res: Response) => {
  res.status(StatusCodes.OK).send({ status: 'online' });
});

export { statusRoute };
