import { PodcastTransfer } from '../models/podcast-transfer.model';
import { podcastRepository } from '../repositories/podcast.repository';
import { StatusCode } from '../utils/status-code';

export const filterEpisodesService = async (
  podcastName: string | undefined
): Promise<PodcastTransfer> => {
  let responseFormat: PodcastTransfer = {
    statusCode: 0,
    body: [],
  };

  const queryString = podcastName?.split('?p=')[1] || '';
  const data = await podcastRepository(queryString);

  responseFormat = {
    statusCode: data.length !== 0 ? StatusCode.OK : StatusCode.NO_CONTENT,
    body: data,
  };

  return responseFormat;
};
