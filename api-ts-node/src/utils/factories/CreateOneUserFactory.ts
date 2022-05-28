import { CreateUserService } from '../../services';

export class CreateOneUserFactory {
  async execute() {
    const createUserService = new CreateUserService();

    return createUserService.execute({
      id: 'any_id1',
      name: 'any_user1',
      email: 'anyemail@email.com',
    });
  }
}
