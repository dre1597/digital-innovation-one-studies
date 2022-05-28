import 'reflect-metadata';
import express from 'express';
import { router } from './routes';
import createConnection from './database';

createConnection();
const app = express();

app.use(express.json());

app.use(router);

const PORT = 5000;

app.listen(PORT, () => {
  console.log(`Server up on http://localhost:${PORT}`);
});
