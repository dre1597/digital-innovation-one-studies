import { Card } from '../../components/Card';
import { UserInfo } from '../../components/UserInfo';
import { Header } from '../../components/Header';
import { Column, Container, Title, TitleHighlight } from './styles';

const Feed = () => {
  return (
    <>
      <Header autenticado={true}/>
      <Container>
        <Column flex={3}>
          <Title>Feed</Title>
          <Card/>
          <Card/>
          <Card/>
          <Card/>
          <Card/>
          <Card/>
          <Card/>
          <Card/>
          <Card/>
          <Card/>
        </Column>
        <Column flex={1}>
          <TitleHighlight> # RANKING 5 TOP DA SEMANA </TitleHighlight>
          <UserInfo
            nome="André Pereira"
            image="https://avatars.githubusercontent.com/u/74803456?s=400&u=736b043e800681b2f2f93d44d4188b2c6431264e&v=4"
            percentual={100}
          />
          <UserInfo
            nome="André Pereira"
            image="https://avatars.githubusercontent.com/u/74803456?s=400&u=736b043e800681b2f2f93d44d4188b2c6431264e&v=4"
            percentual={72}
          />
          <UserInfo
            nome="André Pereira"
            image="https://avatars.githubusercontent.com/u/74803456?s=400&u=736b043e800681b2f2f93d44d4188b2c6431264e&v=4"
            percentual={65}
          />
          <UserInfo
            nome="André Pereira"
            image="https://avatars.githubusercontent.com/u/74803456?s=400&u=736b043e800681b2f2f93d44d4188b2c6431264e&v=4"
            percentual={45}
          />
          <UserInfo
            nome="André Pereira"
            image="https://avatars.githubusercontent.com/u/74803456?s=400&u=736b043e800681b2f2f93d44d4188b2c6431264e&v=4"
            percentual={25}
          />
        </Column>
      </Container>
    </>
  );
};

export { Feed };
