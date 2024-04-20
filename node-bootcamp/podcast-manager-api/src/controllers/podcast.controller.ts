import { IncomingMessage, ServerResponse } from 'http';

import { listEpisodesService } from '../services/list-episodes.service';
import { filterEpisodesService } from '../services/filter-episodes.service';
import { ContentType } from '../utils/content-type';
import { PodcastTransfer } from '../models/podcast-transfer.model';

const defaultContent = { 'Content-Type': ContentType.JSON };

export const getListEpisodes = async (
  req: IncomingMessage,
  res: ServerResponse
) => {
  const content: PodcastTransfer = await listEpisodesService();

  res.writeHead(content.statusCode, defaultContent);
  res.write(JSON.stringify(content.body));

  res.end();
};

export const getFilterEpisodes = async (
  req: IncomingMessage,
  res: ServerResponse
) => {
  const content: PodcastTransfer = await filterEpisodesService(req.url);

  res.writeHead(content.statusCode, defaultContent);
  res.write(JSON.stringify(content.body));

  res.end();
};
