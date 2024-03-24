import { api } from '../api';

export const login = async (email: string, password: string): Promise<boolean> => {
  const data = await api;

  return email === data.email && password === data.password;
};
