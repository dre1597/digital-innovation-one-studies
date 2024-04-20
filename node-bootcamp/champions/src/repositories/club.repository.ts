import fs from 'fs/promises';

import { Club } from '../models/club.model';

export const findAllClubs = async (): Promise<Club[]> => {
  const data = await fs.readFile('./src/data/clubs.json', 'utf-8');
  return JSON.parse(data);
};
