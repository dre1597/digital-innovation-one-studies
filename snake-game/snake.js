const startBtn = document.getElementById('start-game');
const board = document.getElementById('snake');
const context = board.getContext('2d');
const scoreValue = document.getElementById('score');
const gameOver = document.getElementById('game-over');

const boardBorder = 'green';
const boardBackground = 'lightgreen';
const snakeColor = 'green';
const snakeBorder = 'darkgreen';
const foodColor = 'red';
const foodBorder = 'darkred';

let snake = [
  { x: 200, y: 200 },
  { x: 190, y: 200 },
  { x: 180, y: 200 },
];

let score = 0;
let changingDirection = false;
const box = 20;

let foodX;
let foodY;

let dx = box;
let dy = 0;

startBtn.addEventListener('click', () => {
  gameOver.textContent = '';
  start();
});
generateFood();

document.addEventListener('keydown', changeDirection);

function start() {
  if (hasGameEnded()) {
    gameOver.textContent = `Game Over! You got ${score} points!`;
    return;
  }
  changingDirection = false;
  setTimeout(() => {
    clearBoard();
    drawFood();
    moveSnake();
    drawSnake();
    start();
  }, 100);
}

function hasGameEnded() {
  for (let i = 4; i < snake.length; i++) {
    if (snake[i].x === snake[0].x && snake[i].y === snake[0].y) return true;
  }
  const hitLeftWall = snake[0].x < 0;
  const hitRightWall = snake[0].x > board.width - box;
  const hitTopWall = snake[0].y < 0;
  const hitBottomWall = snake[0].y > board.height - box;

  return hitLeftWall || hitRightWall || hitTopWall || hitBottomWall;
}

function generateFood() {
  foodX = randomFood(0, board.width - box);
  foodY = randomFood(0, board.height - box);

  snake.forEach(function hasSnakeEatenFood(part) {
    const hasEaten = part.x == foodX && part.y == foodY;
    if (hasEaten) generateFood();
  });
}

function randomFood(min, max) {
  return Math.round((Math.random() * (max - min) + min) / box) * box;
}
function clearBoard() {
  context.fillStyle = boardBackground;
  context.strokeStyle = boardBorder;
  context.fillRect(0, 0, board.width, board.height);
  context.strokeRect(0, 0, board.width, board.height);
}

function drawFood() {
  context.fillStyle = foodColor;
  context.strokeStyle = foodBorder;
  context.fillRect(foodX, foodY, box, box);
  context.strokeRect(foodX, foodY, box, box);
}

function moveSnake() {
  const head = { x: snake[0].x + dx, y: snake[0].y + dy };
  snake.unshift(head);

  const hasEatenFood = snake[0].x === foodX && snake[0].y === foodY;

  if (hasEatenFood) {
    score += 10;
    scoreValue.innerHTML = score;
    generateFood();
  } else snake.pop();
}

function drawSnake() {
  snake.forEach(drawSnakePart);
}

function drawSnakePart(snakePart) {
  context.fillStyle = snakeColor;
  context.strokeStyle = snakeBorder;
  context.fillRect(snakePart.x, snakePart.y, box, box);
  context.strokeRect(snakePart.x, snakePart.y, box, box);
}

function changeDirection(event) {
  const LEFT_KEY = 37;
  const RIGHT_KEY = 39;
  const UP_KEY = 38;
  const DOWN_KEY = 40;

  if (changingDirection) return;

  changingDirection = true;
  const keyPressed = event.keyCode;
  const goingUp = dy === -box;
  const goingDown = dy === box;
  const goingRight = dx === box;
  const goingLeft = dx === -box;

  if (keyPressed === LEFT_KEY && !goingRight) {
    dx = -box;
    dy = 0;
  }
  if (keyPressed === UP_KEY && !goingDown) {
    dx = 0;
    dy = -box;
  }
  if (keyPressed === RIGHT_KEY && !goingLeft) {
    dx = box;
    dy = 0;
  }
  if (keyPressed === DOWN_KEY && !goingUp) {
    dx = 0;
    dy = box;
  }
}
