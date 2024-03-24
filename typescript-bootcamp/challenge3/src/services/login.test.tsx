import { login } from './login';

describe('login', () => {

  const mockEmail = 'any_email@email.com';
  it('should open an alert with welcome message if email is valid', async () => {
    const response = await login(mockEmail);
    expect(response).toBeTruthy();
  });

  it('should throw an error if email is invalid', async () => {
    const response = await login('invalid_email@email.com');
    expect(response).toBeFalsy();
  });
});
