import { useNavigate } from 'react-router-dom';
import { MdEmail, MdLock, MdPerson } from 'react-icons/md';
import { useForm } from 'react-hook-form';

import { Button } from '../../components/Button';
import { Header } from '../../components/Header';
import { Input } from '../../components/Input';

import { api } from '../../services/api';


import {
  Column,
  Container,
  CriarText,
  IHaveAccountText,
  Row,
  SubtitleLogin,
  TermsText,
  Title,
  TitleLogin,
  Wrapper
} from './styles';

const Register = () => {

  const navigate = useNavigate();

  const { control, handleSubmit, formState: { errors } } = useForm({
    reValidateMode: 'onChange',
    mode: 'onChange',
  });

  const onSubmit = async (formData: any) => {
    try {
      await api.post(`/users`, formData);
      navigate('/login');
      return;
    } catch (e) {
      console.error(e);
    }
  };

  console.log('errors', errors);

  return (<>
    <Header/>
    <Container>
      <Column>
        <Title>A plataforma para você aprender com experts, dominar as principais tecnologias
          e entrar mais rápido nas empresas mais desejadas.</Title>
      </Column>
      <Column>
        <Wrapper>
          <TitleLogin>Faça seu cadastro</TitleLogin>
          <SubtitleLogin>Crie sua conta e make the change._</SubtitleLogin>
          <form onSubmit={handleSubmit(onSubmit)}>
            <Input placeholder="Nome" leftIcon={<MdPerson/>} name="name" control={control}/>
            {errors.email && <span>Nome é obrigatório</span>}
            <Input placeholder="E-mail" leftIcon={<MdEmail/>} name="email" control={control}/>
            {errors.email && <span>E-mail é obrigatório</span>}
            <Input type="password" placeholder="Senha" leftIcon={<MdLock/>} name="senha" control={control}/>
            {errors.senha && <span>Senha é obrigatório</span>}
            <Button title="Criar minha conta" variant="secondary" type="submit"/>
          </form>
          <TermsText>
            Ao clicar em "criar minha conta", declaro que aceito as Políticas de Privacidade e os Termos de
            Uso da DIO.
          </TermsText>
          <Row>
            <IHaveAccountText>Já tenho conta.</IHaveAccountText>
            <CriarText>Fazer login</CriarText>
          </Row>
        </Wrapper>
      </Column>
    </Container>
  </>);
};

export { Register };
