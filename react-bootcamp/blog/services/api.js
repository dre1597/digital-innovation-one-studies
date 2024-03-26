import { createClient } from '@supabase/supabase-js';

// export const api = axios.create({
//   baseURL: process.env.SUPABASE_URL,
//   headers: {
//     apikey: process.env.SUPABASE_KEY,
//     authorization: `Bearer ${process.env.SUPABASE_KEY}`,
//   }
// });

export const api = createClient(process.env.SUPABASE_URL, process.env.SUPABASE_KEY);
