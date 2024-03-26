import styled from 'styled-components';

export const Container = styled.main`
  width: 100%;
  max-width: 80%;
  margin: 120px auto 0;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
`;

export const Title = styled.h3`
  font-family: 'Open Sans', serif;
  font-style: normal;
  font-weight: 700;
  font-size: 18px;
  line-height: 25px;
  color: #FFFFFF;
  margin-bottom: 24px;
`;

export const TitleHighlight = styled.h3`
  font-family: 'Open Sans', serif;
  font-style: normal;
  font-weight: 700;
  font-size: 18px;
  line-height: 25px;
  color: #FFFFFF70;
  margin-bottom: 24px;
`;

interface ColumnProps {
  flex: number;
}

export const Column = styled.div<ColumnProps>`
  flex: ${({ flex }) => flex};
  padding-right: 24px;
`;


