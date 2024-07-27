# frozen_string_literal: true

require_relative 'models/customer'
require_relative 'models/supplier'
require 'terminal-table'

customer = Models::Customer.new
customer.name = "Danilo - #{Time.now.to_i}"
customer.phone = '1111111111'
customer.cpf = '1111111111'

puts customer.methods - Class.methods

customer.include

customers = Models::Customer.all

rows = customers.map do |c|
  [c.id, c.name, c.phone, c.cpf]
end

table = Terminal::Table.new(
  headings: %w[ID Name Phone CPF],
  rows:
)

puts table

puts '-----------------'

suppler = Models::Supplier.new
suppler.name = "Company DIO ORM - #{Time.now.to_i}"
suppler.phone = '111111-1111'
suppler.cnpj = Time.now.to_i
suppler.address = "Street #{Time.now.to_i} - City - UF"
suppler.include

suppliers = Models::Supplier.all

rows = suppliers.map do |s|
  [s.id, s.name, s.phone, s.cnpj, s.address]
end

table = Terminal::Table.new(
  headings: %w[ID Name Phone CNPJ Address],
  rows:
)

puts table
