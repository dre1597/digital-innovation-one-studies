import { Podcast } from './podcast.model';

export interface PodcastTransfer {
  statusCode: number;
  body: Podcast[];
}
