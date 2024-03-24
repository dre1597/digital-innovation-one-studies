import { User, UserService } from './user.service';

describe('UserService', () => {
  const mockDb: User[] = [];
  const userService = new UserService(mockDb);

  it('Should add a new user', () => {
    const mockConsole = jest.spyOn(global.console, 'log');
    userService.createUser('any_name', 'any_email@email.com');
    expect(mockConsole).toHaveBeenCalledWith('DB updated', mockDb);
  });

  it('Should throw an error if name is missing', () => {
    expect(() => userService.createUser('', 'any_email@email.com')).toThrow();
  });

  it('Should throw an error if email is missing', () => {
    expect(() => userService.createUser('any_name', '')).toThrow();
  });

  it('Should get a user by email', () => {
    const user = userService.getUserByEmail('any_email@email.com');
    expect(user?.name).toBe('any_name');
  });

  it(`Should not get a user if the user does not exist`, () => {
    const user = userService.getUserByEmail('invalid_email@email.com');
    expect(user).toBeUndefined();
  });

  it('Should get all users', () => {
    const users = userService.getAllUsers();
    expect(users.length).toBe(1);
  });

  it('Should not delete a user if the user does not exist', () => {
    let users = userService.getAllUsers();
    expect(users.length).toBe(1);
    userService.deleteUser('invalid_email@email.com');
    users = userService.getAllUsers();
    expect(users.length).toBe(1);
  });

  it('Should delete a user', () => {
    let users = userService.getAllUsers();
    expect(users.length).toBe(1);
    userService.deleteUser('any_email@email.com');
    users = userService.getAllUsers();
    expect(users.length).toBe(0);
  });
});
