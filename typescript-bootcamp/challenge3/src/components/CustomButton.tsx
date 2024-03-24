import { Button } from '@chakra-ui/react';
import { ReactNode } from 'react';

interface ICustomButton {
  onClick: () => void;
  children: ReactNode;
}

export const CustomButton = ({ onClick, children }: ICustomButton) => {
  return (
    <Button onClick={onClick} colorScheme="teal" size="sm" width="100%" marginTop="5px">
      {children}
    </Button>
  );
};
