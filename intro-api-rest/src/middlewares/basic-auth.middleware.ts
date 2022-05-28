import { NextFunction, Request, Response } from 'express';
import { ForbiddenError } from '../models/errors';
import { userRepository } from '../repositories';

export const basicAuthMiddleware = async (
  req: Request,
  _res: Response,
  next: NextFunction
) => {
  try {
    const authHeader = req.headers['authorization'];

    if (!authHeader) {
      throw new ForbiddenError('You shall not pass');
    }

    const [authType, token] = authHeader.split(' ');

    if (authType !== 'Basic' || !token) {
      throw new ForbiddenError('You shall not pass');
    }

    const tokenContent = Buffer.from(token, 'base64').toString('utf-8');

    const [username, password] = tokenContent.split(':');

    if (!username || !password) {
      throw new ForbiddenError('You shall not pass');
    }

    const user = await userRepository.findByUsernameAndPassword(
      username,
      password
    );

    if (!user) {
      throw new ForbiddenError('You shall not pass');
    }

    req.user = user;
    next();
  } catch (error) {
    next(error);
  }
};
