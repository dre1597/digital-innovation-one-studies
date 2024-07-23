# frozen_string_literal: true

class Product
  def initialize(product_hash = {})
    self.id = product_hash['id']
    self.name = product_hash['name']
    self.description = product_hash['description']
    self.price = product_hash['price']
    self.quantity = product_hash['quantity']
  end

  attr_accessor :id, :name, :description, :price, :quantity
end
