import { Container, NameText, Progress, UserPicture } from './styles';

interface Props {
  nome: string;
  image: string;
  percentual: number;
}

const UserInfo = ({ nome, image, percentual }: Props) => {
  return (
    <Container>
      <UserPicture src={image}/>
      <div>
        <NameText>{nome}</NameText>
        <Progress percentual={percentual}/>
      </div>
    </Container>
  );
};

export { UserInfo };
