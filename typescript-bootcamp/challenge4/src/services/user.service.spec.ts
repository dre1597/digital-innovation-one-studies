import { User, UserService } from './user.service';

describe('UserService', () => {
  const mockDb: User[] = []
  const userService = new UserService(mockDb);

  it('Should add a new user', () => {
    const mockConsole = jest.spyOn(global.console, 'log')
    userService.createUser('any_name', 'any_email@email.com');
    expect(mockConsole).toHaveBeenCalledWith('DB updated', mockDb)
  })
})
