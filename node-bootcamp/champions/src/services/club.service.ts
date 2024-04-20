import * as HttpResponse from '../utils/http-helper';
import * as repository from '../repositories/club.repository';
import { Club } from '../models/club.model';

export const getClubService = async () => {
  const data = await repository.findAllClubs();
  return HttpResponse.ok<Club[]>(data);
};
