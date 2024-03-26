import logo from '../../assets/logo-dio.png';
import { BuscarInputContainer, Container, Input, Menu, MenuRight, Row, UserPicture, Wrapper } from './styles';
import { ButtonLink } from '../ButtonLink';

interface Props {
  autenticado?: boolean;
}

const Header = ({ autenticado }: Props) => {
  return (
    <Wrapper>
      <Container>
        <Row>
          <img src={logo} alt="Logo da dio"/>
          {autenticado ? (
            <>
              <BuscarInputContainer>
                <Input placeholder="Buscar..."/>
              </BuscarInputContainer>
              <Menu>Live Code</Menu>
              <Menu>Global</Menu>
            </>
          ) : null}
        </Row>
        <Row>
          {autenticado ? (
            <UserPicture
              src="https://avatars.githubusercontent.com/u/74803456?s=400&u=736b043e800681b2f2f93d44d4188b2c6431264e&v=4"/>
          ) : (
            <>
              <MenuRight to="/">Home</MenuRight>
              <ButtonLink to="/login" title="Entrar"/>
              <ButtonLink to="/register" title="Cadastrar"/>
            </>)}
        </Row>
      </Container>
    </Wrapper>
  );
};

export { Header };
