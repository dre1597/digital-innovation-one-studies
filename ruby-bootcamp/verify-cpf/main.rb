# frozen_string_literal: true

require 'cpf_cnpj'

puts 'Enter your CPF: '
cpf = gets.chomp
valid = CPF.valid?(cpf)

if valid
  puts 'Valid'
else
  puts 'Invalid'
end
