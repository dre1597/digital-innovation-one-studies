import { ButtonContainer } from './styles';

interface Props {
  onClick: () => void;
}

function Button({ onClick }: Props) {
  return (
    <ButtonContainer onClick={onClick}>
      Buscar
    </ButtonContainer>
  );
}

export default Button;
