import { PodcastTransfer } from '../models/podcast-transfer.model';
import { podcastRepository } from '../repositories/podcast.repository';
import { StatusCode } from '../utils/status-code';

export const listEpisodesService = async (): Promise<PodcastTransfer> => {
  let responseFormat: PodcastTransfer = {
    statusCode: 0,
    body: [],
  };

  const data = await podcastRepository();

  responseFormat = {
    statusCode: data.length !== 0 ? StatusCode.OK : StatusCode.NO_CONTENT,
    body: data,
  };

  return responseFormat;
};
