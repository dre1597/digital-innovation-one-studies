const readline = require('readline');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

const personagens = [
  {
    NOME: 'Mario',
    VELOCIDADE: 4,
    MANOBRABILIDADE: 3,
    PODER: 3,
  },
  {
    NOME: 'Luigi',
    VELOCIDADE: 3,
    MANOBRABILIDADE: 4,
    PODER: 4,
  },
  {
    NOME: 'Peach',
    VELOCIDADE: 3,
    MANOBRABILIDADE: 4,
    PODER: 2,
  },
  {
    NOME: 'Yoshi',
    VELOCIDADE: 2,
    MANOBRABILIDADE: 4,
    PODER: 3
  },
  {
    NOME: 'Bowser',
    VELOCIDADE: 5,
    MANOBRABILIDADE: 2,
    PODER: 5
  },
  {
    NOME: 'Donkey Kong',
    VELOCIDADE: 2,
    MANOBRABILIDADE: 2,
    PODER: 5
  }
];

function showMenu() {
  const options = personagens.map((personagem, index) => {
    return `${index + 1} - ${personagem.NOME} : ${personagem.VELOCIDADE} velocidade, ${personagem.MANOBRABILIDADE} manobrabilidade, ${personagem.PODER} poder`;
  });

  console.log('Selecione um personagem:');
  console.log(options.join('\n'));
}

function promptCharacterAndStartGame() {
  rl.question('Selecione um personagem: ', async answer => {

    const index = parseFloat(answer);

    if (isNaN(index)) {
      console.log('Por favor, digite um valor numérico');
      return promptCharacterAndStartGame();
    }

    if (index < 1 || index > 6) {
      console.log('Por favor, digite um valor entre 1 e 6');
      return promptCharacterAndStartGame();
    }

    const playerOne = personagens[answer - 1];
    console.log(`O personagem selecionado foi: ${playerOne.NOME}`);

    const playerTwo = selectRandomPlayerTwo();
    console.log(`O segundo personagem selecionado foi: ${playerTwo.NOME}`);


    console.log(
      `🏁🚨 Corrida entre ${playerOne.NOME} e ${playerTwo.NOME} começando...\n`
    );

    playerOne.PONTOS = 0;
    playerTwo.PONTOS = 0;

    await playRaceEngine(playerOne, playerTwo);
    await declareWinner(playerOne, playerTwo);

    rl.close();
  });
}

function selectRandomPlayerTwo() {
  const index = Math.floor(Math.random() * 6);
  return personagens[index];
}

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
  showMenu();

  promptCharacterAndStartGame();
})();
