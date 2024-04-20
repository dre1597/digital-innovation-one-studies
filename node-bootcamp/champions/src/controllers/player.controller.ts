import { Request, Response } from 'express';
import * as playerService from '../services/player.service';
import { Statistics } from '../models/statistics.model';

export const getPlayer = async (req: Request, res: Response) => {
  const httpResponse = await playerService.getPlayerService();
  res.status(httpResponse.statusCode).json(httpResponse.body);
};

export const getPlayerById = async (req: Request, res: Response) => {
  const id = parseInt(req.params.id);
  const httpResponse = await playerService.getPlayerByIdService(id);
  res.status(httpResponse.statusCode).json(httpResponse.body);
};

export const postPlayer = async (req: Request, res: Response) => {
  const bodyValue = req.body;
  const httpResponse = await playerService.createPlayerService(bodyValue);

  if (httpResponse) {
    res.status(httpResponse.statusCode).json(httpResponse.body);
  }
};

export const deletePlayer = async (req: Request, res: Response) => {
  const id = parseInt(req.params.id);
  const httpResponse = await playerService.deletePlayerService(id);

  res.status(httpResponse.statusCode).json(httpResponse.body);
};

export const updatePlayer = async (req: Request, res: Response) => {
  const id = parseInt(req.params.id);
  const bodyValue: Statistics = req.body;
  const httpResponse = await playerService.updatePlayerService(id, bodyValue);
  res.status(httpResponse.statusCode).json(httpResponse.body);
};
