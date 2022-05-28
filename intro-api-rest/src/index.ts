import express from 'express';
import 'dotenv/config';

import { usersRoute, statusRoute, authRoute } from './routes';
import { bearerAuthMiddleware, errorHandler } from './middlewares';

const app = express();

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use(statusRoute);
app.use(bearerAuthMiddleware, usersRoute);
app.use(authRoute);

app.use(errorHandler);

app.listen(3000, () => {
  console.log(`Running on http:localhost:3000`);
});
