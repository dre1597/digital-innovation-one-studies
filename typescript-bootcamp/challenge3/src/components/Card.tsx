import { Box } from '@chakra-ui/react';
import { ReactNode } from 'react';

interface Props {
  children: ReactNode;
}

export const Card = ({ children }: Props) => {
  return (
    <Box backgroundColor="#FFFFFF" borderRadius="25px" padding="15px">
      {children}
    </Box>
  );
};
