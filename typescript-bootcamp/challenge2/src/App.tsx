import './App.css';
import { Box, Button, Center, Input } from '@chakra-ui/react';
import { login } from './services/login.tsx';

function App() {
  return (
    <Box minHeight="100vh" backgroundColor="#9413dc" padding="25px">
      <Box backgroundColor="#FFFFFF" borderRadius="25px" padding="15px">
        <Center>
          <h1>Login</h1>
        </Center>
        <Input placeholder="email"/>
        <Input placeholder="password"/>
        <Center>
          <Button onClick={login} colorScheme="teal" size="sm" width="100%" marginTop="5px">
            Login
          </Button>
        </Center>
      </Box>
    </Box>
  );
}

export default App;
