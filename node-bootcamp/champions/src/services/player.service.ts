import { Player } from '../models/player.model';
import { Statistics } from '../models/statistics.model';
import * as PlayerRepository from '../repositories/player.repository';
import * as HttpResponse from '../utils/http-helper';

export const getPlayerService = async () => {
  const data = await PlayerRepository.findAllPlayers();

  if (!data) {
    return await HttpResponse.noContent();
  }

  return await HttpResponse.ok(data);
};

export const getPlayerByIdService = async (id: number) => {
  const data = await PlayerRepository.findPlayerById(id);

  if (!data) {
    return await HttpResponse.noContent();
  }

  return await HttpResponse.ok<Player | undefined>(data);
};

export const createPlayerService = async (player: Player) => {
  if (Object.keys(player).length === 0) {
    return await HttpResponse.badRequest();
  }

  await PlayerRepository.insertPlayer(player);
  return await HttpResponse.created();
};

export const deletePlayerService = async (id: number) => {
  const isDeleted = await PlayerRepository.deleteOnePlayer(id);

  if (!isDeleted) {
    return await HttpResponse.badRequest();
  }

  return await HttpResponse.ok({ message: 'deleted' });
};

export const updatePlayerService = async (
  id: number,
  statistics: Statistics
) => {
  const data = await PlayerRepository.findAndModifyPlayer(id, statistics);

  if (Object.keys(data).length === 0) {
    return await HttpResponse.badRequest();
  }

  return await HttpResponse.ok(data);
};
