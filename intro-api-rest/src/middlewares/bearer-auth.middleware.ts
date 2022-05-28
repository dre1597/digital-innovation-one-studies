import { NextFunction, Request, Response } from 'express';
import { ForbiddenError } from '../models/errors';
import { verify } from 'jsonwebtoken';

export const bearerAuthMiddleware = async (
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

    if (authType !== 'Bearer' || !token) {
      throw new ForbiddenError('You shall not pass');
    }
    try {
      const tokenPayload = verify(token, String(process.env.JWT_SECRET));

      if (typeof tokenPayload !== 'object' || !tokenPayload.sub) {
        throw new ForbiddenError('You shall not pass');
      }

      const user = {
        id: tokenPayload.sub,
        username: tokenPayload.username,
      };

      req.user = user;
      next();
    } catch (error) {
      throw new ForbiddenError('You shall not pass');
    }
  } catch (error) {
    next(error);
  }
};
