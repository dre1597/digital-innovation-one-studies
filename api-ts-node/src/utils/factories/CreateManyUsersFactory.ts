import { CreateUserService } from '../../services';

export class CreateManyUsersFactory {
  async execute() {
    const createUserService = new CreateUserService();

    await createUserService.execute({
      id: 'any_id1',
      name: 'any_user1',
      email: 'anyemail@email.com',
    });

    await createUserService.execute({
      id: 'any_id2',
      name: 'any_user2',
      email: '',
    });
  }
}
