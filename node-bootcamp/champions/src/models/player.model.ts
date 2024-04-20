import { Statistics } from './statistics.model';

export type Player = {
  id: number;
  name: string;
  club: string;
  nationality: string;
  position: string;
  statistics: Statistics;
}
