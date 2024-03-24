import { Box } from '@chakra-ui/react';
import { ReactNode } from 'react';

import { Header } from './Header';

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
