# frozen_string_literal: true

require_relative './models/product'
require_relative './models/supplier'
require 'terminal-table'

p = Product.new

puts p.methods - Class.methods

puts Product.methods - Class.methods

p.title = 'foo'
puts p.inspect

products = Product.all

table = Terminal::Table.new do |t|
  t.headings = %w[ID Name Description Price Stock Supplier ID]

  products.each do |product|
    t << [product.id, product.title, product.description, format('%.2f', product.price), product.stock, product.supplier_id]
  end
end
puts table

suppliers = Supplier.all

table = Terminal::Table.new do |t|
  t.headings = %w[ID Name CNPJ]

  suppliers.each do |supplier|
    t << [supplier.id, supplier.name, supplier.cnpj]
  end
end

puts table
