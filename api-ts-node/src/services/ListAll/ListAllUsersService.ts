import { getRepository } from 'typeorm';
import { User } from '../../entities/User';

export class ListAllUsersService {
  async execute() {
    return getRepository(User).createQueryBuilder('users').select().getMany();
  }
}
