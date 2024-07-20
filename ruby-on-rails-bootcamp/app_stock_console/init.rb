# frozen_string_literal: true

require_relative 'display/menu'
require_relative 'display/operations'
require_relative 'core/add_product'
require_relative 'core/list_products'
require_relative 'core/remove_stock'

products = [
  {
    id: 1,
    name: 'Apple',
    description: 'A red Apple',
    price: 2.5,
    quantity: 20
  },
  {
    id: 2,
    name: 'Banana',
    description: 'A yellow Banana',
    price: '1.5',
    quantity: 30
  }
]

init_menu(products)
