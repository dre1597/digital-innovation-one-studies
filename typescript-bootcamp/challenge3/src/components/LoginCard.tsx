import { Box, Center, Input } from '@chakra-ui/react';
import { CustomButton } from './CustomButton.tsx';
import { login } from '../services/login.tsx';


export const LoginCard = () => {
  return (
    <Box backgroundColor="#FFFFFF" borderRadius="25px" padding="15px">
      <Center>
        <h1>Login</h1>
      </Center>
      <Input placeholder="email"/>
      <Input placeholder="password"/>
      <Center>
        <CustomButton onClick={login}>
          Login
        </CustomButton>
      </Center>
    </Box>
  );
};
