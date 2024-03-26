import { ButtonHTMLAttributes } from 'react';

import { ButtonContainer } from './styles';

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  title: string;
  variant?: 'primary' | 'secondary';
  onClick?: () => void;
}

const Button = ({ title, variant = 'primary', onClick }: ButtonProps) => {
  return (
    <ButtonContainer variant={variant} onClick={onClick}>{title}</ButtonContainer>
  );
};

export { Button };
