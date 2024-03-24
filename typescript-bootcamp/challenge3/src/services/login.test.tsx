import { login } from './login';

describe('login', () => {

  const mockEmail = 'any_email@email.com';
  const mockPassword = '123456';
  it('should open an alert with welcome message if email and password are valid', async () => {
    const response = await login(mockEmail, mockPassword);
    expect(response).toBeTruthy();
  });

  it('should throw an error if email is invalid', async () => {
    const response = await login('invalid_email@email.com', mockPassword);
    expect(response).toBeFalsy();
  });

  it('should throw an error if password is invalid', async () => {
    const response = await login(mockEmail, 'invalid_password');
    expect(response).toBeFalsy();
  });

  it('should throw an error if email and password are invalid', async () => {
    const response = await login('invalid_email@email.com', 'invalid_password');
    expect(response).toBeFalsy();
  });
});
