import { NextFunction, Request, Response, Router } from 'express';
import { StatusCodes } from 'http-status-codes';
import { sign } from 'jsonwebtoken';
import { basicAuthMiddleware, bearerAuthMiddleware } from '../middlewares';
import { ForbiddenError } from '../models/errors';

const authRoute = Router();

authRoute.post(
  '/token',
  basicAuthMiddleware,
  async (req: Request, res: Response, next: NextFunction) => {
    try {
      const user = req.user;

      if (!user) {
        throw new ForbiddenError('You shall not pass');
      }

      const jwt_payload = { username: user.username };
      const jwt_secret = String(process.env.JWT_SECRET);
      const jwt_options = {
        subject: String(user.id),
      };

      const jwt_token = sign(jwt_payload, jwt_secret, jwt_options);

      res.status(StatusCodes.OK).json({ access_token: jwt_token });
    } catch (error) {
      next(error);
    }
  }
);

authRoute.post(
  '/token/validate',
  bearerAuthMiddleware,
  async (_req: Request, res: Response) => {
    res.sendStatus(StatusCodes.OK);
  }
);

export { authRoute };
