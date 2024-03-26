import { ChangeEvent } from 'react';

import { InputContainer } from './styles';

interface Props {
  value: string;
  onChange: (event: ChangeEvent<HTMLInputElement>) => void;
}

function Input({ value, onChange }: Props) {
  return (
    <InputContainer>
      <input value={value} onChange={onChange}/>
    </InputContainer>
  );
}

export default Input;
