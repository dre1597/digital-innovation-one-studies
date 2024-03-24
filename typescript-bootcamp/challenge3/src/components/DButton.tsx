import { Button } from '@chakra-ui/react';
import { MouseEventHandler } from 'react';

interface Props {
  onClick: MouseEventHandler;
}

export const DButton = ({ onClick }: Props) => {
  return (
    <Button
      onClick={onClick}
      colorScheme="teal"
      size="sm"
      width="100%"
      marginTop="5px"
    >
      Entrar
    </Button>
  );
};

export default DButton;
