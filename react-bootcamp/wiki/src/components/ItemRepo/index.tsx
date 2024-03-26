import { ItemContainer } from './styles';

export interface Repo {
  id: number;
  name: string;
  full_name: string;
  html_url: string;
}

interface Props {
  repo: Repo;
  handleRemoveRepo: (id: number) => void;
}

function ItemRepo({ repo, handleRemoveRepo }: Props) {
  const handleRemove = () => {
    handleRemoveRepo(repo.id);
  };

  return (
    <ItemContainer onClick={handleRemove}>
      <h3>{repo.name}</h3>
      <p>{repo.full_name}</p>
      <a href={repo.html_url} rel="noreferrer" target="_blank">Ver repositório</a><br/>
      <a href="#" rel="noreferrer" className="to-remove">Remover</a>
      <hr/>
    </ItemContainer>
  );
}

export default ItemRepo;
