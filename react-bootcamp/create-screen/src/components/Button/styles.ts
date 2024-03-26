import styled, { css } from 'styled-components';

interface ButtonProps {
  variant: 'primary' | 'secondary';
  width?: string;
}

export const ButtonContainer = styled.button<ButtonProps>`
  background: #565656;
  border-radius: 22px;
  position: relative;
  color: #FFFFFF;
  padding: 6px 12px;
  width: 120px;
  margin-right: 8px;
  border: 0;

  width: ${({ variant }) => variant !== 'primary' && css`
    min-width: 120px;
    height: 33px;
    background: #E4105D;
    width: 170px;

    &::after {
      content: '';
      position: absolute;
      border: 1px solid #E4105D;
      top: -5px;
      left: -6px;
      width: calc(100% + 10px);
      height: calc(100% + 10px);
      border-radius: 22px;
    }
  `}
`;

