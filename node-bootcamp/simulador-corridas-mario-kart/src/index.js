const player1 = {
  NOME: 'Mario',
  VELOCIDADE: 4,
  MANOBRABILIDADE: 3,
  PODER: 3,
  PONTOS: 0,
};

const player2 = {
  NOME: 'Luigi',
  VELOCIDADE: 3,
  MANOBRABILIDADE: 4,
  PODER: 4,
  PONTOS: 0,
};

async function rollDice() {
  return Math.floor(Math.random() * 6) + 1;
}

async function getRandomBlock() {
  let random = Math.random();

  switch (true) {
    case random < 0.33:
      return 'RETA';
    case random < 0.66:
      return 'CURVA';
    default:
      return 'CONFRONTO';
  }
}

async function logRollResult(characterName, block, diceResult, attribute) {
  console.log(
    `${characterName} 🎲 rolou um dado de ${block} ${diceResult} + ${attribute} = ${
      diceResult + attribute
    }`
  );
}

async function playRaceEngine(firstCharacter, secondCharacter) {
  for (let round = 1; round <= 5; round++) {
    console.log(`🏁 Rodada ${round}`);

    let block = await getRandomBlock();
    console.log(`Bloco: ${block}`);

    let firstDiceResult = await rollDice();
    let secondDiceResult = await rollDice();

    let firstTotalTestSkill = 0;
    let secondTotalTestSkill = 0;

    if (block === 'RETA') {
      firstTotalTestSkill = firstDiceResult + firstCharacter.VELOCIDADE;
      secondTotalTestSkill = secondDiceResult + secondCharacter.VELOCIDADE;

      await logRollResult(
        firstCharacter.NOME,
        'velocidade',
        firstDiceResult,
        firstCharacter.VELOCIDADE
      );

      await logRollResult(
        secondCharacter.NOME,
        'velocidade',
        secondDiceResult,
        secondCharacter.VELOCIDADE
      );
    }

    if (block === 'CURVA') {
      firstTotalTestSkill = firstDiceResult + firstCharacter.MANOBRABILIDADE;
      secondTotalTestSkill = secondDiceResult + secondCharacter.MANOBRABILIDADE;

      await logRollResult(
        firstCharacter.NOME,
        'manobrabilidade',
        firstDiceResult,
        firstCharacter.MANOBRABILIDADE
      );

      await logRollResult(
        secondCharacter.NOME,
        'manobrabilidade',
        secondDiceResult,
        secondCharacter.MANOBRABILIDADE
      );
    }

    if (block === 'CONFRONTO') {
      let firstPowerResult = firstDiceResult + firstCharacter.PODER;
      let secondPowerResult = secondDiceResult + secondCharacter.PODER;
      const randomBonus = Math.random();
      const randomDano = Math.random();

      console.log(`${firstCharacter.NOME} confrontou com ${secondCharacter.NOME}! 🥊`);

      await logRollResult(
        firstCharacter.NOME,
        'poder',
        firstDiceResult,
        firstCharacter.PODER
      );

      await logRollResult(
        secondCharacter.NOME,
        'poder',
        secondDiceResult,
        secondCharacter.PODER
      );

      if (firstPowerResult > secondPowerResult && secondCharacter.PONTOS > 0) {
        if (randomDano > 0.5) {
          console.log(
            `${firstCharacter.NOME} venceu o confronto! ${secondCharacter.NOME} perdeu 2 pontos 💣`
          );
          secondCharacter.PONTOS -= 2;
        } else {
          console.log(
            `${firstCharacter.NOME} venceu o confronto! ${secondCharacter.NOME} perdeu 1 ponto 🐢`
          );
          secondCharacter.PONTOS--;
        }

        if (randomBonus > 0.5) {
          firstCharacter.PONTOS++;
          console.log(`Turbo! ${firstCharacter.NOME} ganhou um ponto!`);
        }
      }

      if (secondPowerResult > firstPowerResult && firstCharacter.PONTOS > 0) {
        if (randomDano > 0.5) {
          console.log(
            `${secondCharacter.NOME} venceu o confronto! ${firstCharacter.NOME} perdeu 2 pontos 💣`
          );
          firstCharacter.PONTOS -= 2;
        } else {
          console.log(
            `${secondCharacter.NOME} venceu o confronto! ${firstCharacter.NOME} perdeu 1 ponto 🐢`
          );
          firstCharacter.PONTOS--;
        }

        if (randomBonus > 0.5) {
          secondCharacter.PONTOS++;
          console.log(`Turbo! ${secondCharacter.NOME} ganhou um ponto!`);
        }
      }

      console.log(
        secondPowerResult === firstPowerResult
          ? 'Confronto empatado! Nenhum ponto foi perdido'
          : ''
      );
    }

    if (firstTotalTestSkill > secondTotalTestSkill) {
      console.log(`${firstCharacter.NOME} marcou um ponto!`);
      firstCharacter.PONTOS++;
    } else if (secondTotalTestSkill > firstTotalTestSkill) {
      console.log(`${secondCharacter.NOME} marcou um ponto!`);
      secondCharacter.PONTOS++;
    }

    console.log('-----------------------------');
  }
}

async function declareWinner(firstCharacter, secondCharacter) {
  console.log('Resultado final:');
  console.log(`${firstCharacter.NOME}: ${firstCharacter.PONTOS} ponto(s)`);
  console.log(`${secondCharacter.NOME}: ${secondCharacter.PONTOS} ponto(s)`);

  if (firstCharacter.PONTOS > secondCharacter.PONTOS) {
    console.log(`\n${firstCharacter.NOME} venceu a corrida! Parabéns! 🏆`);
  } else if (secondCharacter.PONTOS > firstCharacter.PONTOS) {
    console.log(`\n${secondCharacter.NOME} venceu a corrida! Parabéns! 🏆`);
  } else {
    console.log('A corrida terminou em empate');
  }
}

(async function main() {
  console.log(
    `🏁🚨 Corrida entre ${player1.NOME} e ${player2.NOME} começando...\n`
  );

  await playRaceEngine(player1, player2);
  await declareWinner(player1, player2);
})();
