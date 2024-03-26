import { ButtonHTMLAttributes, MouseEventHandler } from 'react';
import { ButtonContainer } from './styles.ts';

interface Props extends ButtonHTMLAttributes<HTMLButtonElement> {
  title: string;
  onClick?: MouseEventHandler<HTMLButtonElement>;
}

const Button = ({ title, onClick, ...props }: Props) => {
  return (
    <ButtonContainer onClick={onClick} {...props}>{title}</ButtonContainer>
  );
};

export default Button;
