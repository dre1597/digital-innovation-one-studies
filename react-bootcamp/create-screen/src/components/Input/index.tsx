import { Controller } from 'react-hook-form';
import { InputHTMLAttributes, ReactNode } from 'react';

import { IconContainer, InputContainer, InputText } from './styles';

interface Props extends InputHTMLAttributes<HTMLInputElement> {
  leftIcon?: ReactNode;
  name: string;
  control: any;
}

const Input = ({ leftIcon, name, control, ...rest }: Props) => {
  return (
    <InputContainer>
      {leftIcon ? (<IconContainer>{leftIcon}</IconContainer>) : null}
      <Controller
        name={name}
        control={control}
        render={({ field }) => <InputText {...field} {...rest} />}
      />
    </InputContainer>
  );
};

export { Input };
