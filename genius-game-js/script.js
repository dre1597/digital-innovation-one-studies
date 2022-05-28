const $green = document.querySelector('.green');
const $red = document.querySelector('.red');
const $yellow = document.querySelector('.yellow');
const $blue = document.querySelector('.blue');

let order = [];
let clickedOrder = [];
let score = 0;

const checkOrder = () => {
  for (const color in clickedOrder) {
    if (clickedOrder[color] !== order[color]) {
      gameOver();
      break;
    }
  }
  if (clickedOrder.length === order.length) {
    alert(`Score: ${score}\nNice! Starting next level!`);
    nextLevel();
  }
};

$green.onclick = () => click(0);
$red.onclick = () => click(1);
$yellow.onclick = () => click(2);
$blue.onclick = () => click(3);

const click = (color) => {
  clickedOrder[clickedOrder.length] = color;
  createColorElement(color).classList.add('selected');

  setTimeout(() => {
    createColorElement(color).classList.remove('selected');
    checkOrder();
  }, 250);
};

const gameOver = () => {
  alert(`Score: ${score}!\nYou lose!\nPlay again?`);
  order = [];
  clickedOrder = [];

  playGame();
};

const createColorElement = (color) => {
  switch (color) {
    case 0:
      return $green;
    case 1:
      return $red;
    case 2:
      return $yellow;
    case 3:
      return $blue;
    default:
      return;
  }
};

const lightNextColor = (element, time) => {
  time = time * 500;
  setTimeout(() => {
    element.classList.add('selected');
  }, time - 250);
  setTimeout(() => {
    element.classList.remove('selected');
  }, time + 250);
};

const shuffleOrder = () => {
  const colorOrder = Math.floor(Math.random() * 4);
  order[order.length] = colorOrder;
  clickedOrder = [];

  for (const i in order) {
    const elementColor = createColorElement(order[i]);
    lightNextColor(elementColor, Number(i) + 1);
  }
};

const nextLevel = () => {
  score++;
  shuffleOrder();
};

const playGame = () => {
  alert('Welcome to Genius game!');
  score = 0;

  nextLevel();
};

playGame();
