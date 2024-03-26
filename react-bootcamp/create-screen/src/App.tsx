import { BrowserRouter as Router, Route, Routes, } from 'react-router-dom';

import { Feed } from './pages/Feed';
import { Home } from './pages/Home';
import { Login } from './pages/Login';
import { Register } from './pages/Register';
import { GlobalStyle } from './styles/global';

function App() {
  return (
    <Router>
      <GlobalStyle/>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/login" element={<Login/>}/>
        <Route path="/feed" element={<Feed/>}/>
        <Route path="/register" element={<Register/>}/>
      </Routes>
    </Router>
  );
}

export default App;
