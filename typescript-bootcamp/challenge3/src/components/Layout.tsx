import { Box } from '@chakra-ui/react';
import { Header } from './Header';
import { ReactNode } from 'react';

interface Props {
  children: ReactNode;
}

export const Layout = ({ children }: Props) => {
  return (
    <Box minHeight="100vh" backgroundColor="#9413dc">
      <Header/>
      {children}
    </Box>
  );
};
