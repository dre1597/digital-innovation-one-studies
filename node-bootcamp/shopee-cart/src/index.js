import * as cartService from './services/cart.js';
import * as favService from './services/fav.js';
import createItem from './services/item.js';

const myCart = [];
const favItems = [];

console.log('Welcome to the your Shopee Cart!');

const item1 = await createItem('item 1', 20.99, 1);
const item2 = await createItem('item 2', 39.99, 3);

await cartService.addItem(myCart, item1);
await cartService.addItem(myCart, item2);

await cartService.decreaseItem(myCart, item2);
await cartService.decreaseItem(myCart, item2);
await cartService.decreaseItem(myCart, item2);

await cartService.displayCart(myCart);
await cartService.deleteItem(myCart, item2.name);
await cartService.deleteItem(myCart, item1.name);
await cartService.evaluateCart(myCart);

await favService.listItems(favItems);
await favService.addItem(favItems, item1);
await favService.addItem(favItems, item2);
await favService.deleteItem(favItems, item2.name);
await favService.listItems(favItems);
