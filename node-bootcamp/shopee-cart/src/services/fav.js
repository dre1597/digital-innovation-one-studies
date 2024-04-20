async function addItem(userFav, item) {
  userFav.push(item);
}

async function deleteItem(userFav, name) {
  const index = userFav.findIndex((item) => item.name === name);

  if (index !== -1) {
    userFav.splice(index, 1);
  }
}

async function listItems(userFav) {
  userFav.forEach((item, index) => {
    console.log(
      `${index + 1}. ${item.name} - R$ ${item.price} | ${item.quantity}x | Subtotal = ${item.subtotal()}`
    );
  });
}

export { addItem, deleteItem, listItems };

