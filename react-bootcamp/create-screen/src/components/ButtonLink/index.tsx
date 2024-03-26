import { ButtonLinkContainer } from './styles.ts';

interface ButtonLink {
  title: string;
  to: string;
}

const ButtonLink = ({ title, to }: ButtonLink) => {
  return (
    <ButtonLinkContainer to={to}>{title}</ButtonLinkContainer>
  );
};

export { ButtonLink };
