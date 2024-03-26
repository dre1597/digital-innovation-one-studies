import React from 'react';
import ReactDOM from 'react-dom/client';

import GlobalStyles from './global.ts';
import Login from './pages/Login';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <GlobalStyles/>
    <Login/>
  </React.StrictMode>,
);
