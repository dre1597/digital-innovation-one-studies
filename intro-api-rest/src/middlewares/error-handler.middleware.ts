import { Request, Response } from 'express';
import { StatusCodes } from 'http-status-codes';
import { DatabaseError, ForbiddenError } from '../models/errors';

export const errorHandler = (error: any, _req: Request, res: Response) => {
  if (error instanceof DatabaseError) {
    res.sendStatus(StatusCodes.BAD_REQUEST);
  } else if (error instanceof ForbiddenError) {
    res.sendStatus(StatusCodes.FORBIDDEN);
  } else {
    res.sendStatus(StatusCodes.INTERNAL_SERVER_ERROR);
  }
};
