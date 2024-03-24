import { Box } from '@chakra-ui/react';
import { LoginCard } from './components/LoginCard.tsx';
import { Header } from './components/Header.tsx';

function App() {
  return (
    <>
      <Header/>
      <Box minHeight="100vh" backgroundColor="#9413dc" padding="25px">
        <LoginCard/>
      </Box>
    </>
  );
}

export default App;
