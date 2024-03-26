import { useForm } from 'react-hook-form';
import Button from '../../components/Button';
import Input from '../../components/Input';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';

import { Column, Container, LoginContainer, Spacing, Title } from './styles';

export interface IFormLogin {
  email: string;
  password: string;
}

export const defaultValues: IFormLogin = {
  email: '',
  password: ''
};

const Login = () => {
  const schema = yup
    .object({
      email: yup.string()
        .email('E-mail inválido')
        .required('Campo obrigatório'),
      password: yup
        .string()
        .min(6, 'No mínimo 6 caracteres')
        .required('Campo obrigatório'),
    })
    .required();

  const {
    control,
    formState: { errors, isValid },
  } = useForm<IFormLogin>({
    resolver: yupResolver(schema),
    mode: 'onBlur',
    defaultValues,
    reValidateMode: 'onChange',
  });

  const login = () => {
    console.log('login');
  };

  return (
    <Container>
      <LoginContainer>
        <Column>
          <Title>Login</Title>
          <Spacing/>
          <Input
            name="email"
            placeholder="Email"
            control={control}
            errorMessage={errors?.email?.message}
          />
          <Spacing/>
          <Input
            name="password"
            type="password"
            placeholder="Senha"
            control={control}
            errorMessage={errors?.password?.message}
          />
          <Spacing/>
          <Button title="Entrar" disabled={!isValid} onClick={login}/>
        </Column>
      </LoginContainer>
    </Container>
  );
};

export default Login;
