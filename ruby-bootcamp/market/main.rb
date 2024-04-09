# frozen_string_literal: true

require_relative 'product'
require_relative 'market'

product = Product.new
product.name = 'Tomato'
product.price = 2.50

Market.new(product.name, product.price).buy
