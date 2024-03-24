import { login } from './login.tsx';

describe('login', () => {
  const mockAlert = jest.fn();

  window.alert = mockAlert;

  it('should call alert', () => {
    login();
    expect(mockAlert).toHaveBeenCalledWith('Welcome');
  });
});
