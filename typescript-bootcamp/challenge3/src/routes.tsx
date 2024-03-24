import { useContext } from 'react';
import { Route, Routes } from 'react-router-dom';
import { AppContext } from './components/AppContext';
import Account from './pages/Account';
import AccountInfo from './pages/AccountInfo';
import Home from './pages/Home';

const MainRoutes = () => {
  const { isLoggedIn } = useContext(AppContext);

  return (
    <Routes>
      <Route path="/" element={<Home/>}/>
      <Route path="/account/:id" element={isLoggedIn ? <Account/> : <Home/>}/>
      <Route path="/info" element={<AccountInfo/>}/>
    </Routes>
  );
};

export default MainRoutes;
