import { api } from '../services/api';

export const getPosts = async () => {
  const { data } = await api.from('posts').select('*');

  if (data) {
    return data;
  }

  return [];
};

export const getPostBySlug = async (id) => {
  return api.from('posts').select('*').eq('id', id);
};
