# frozen_string_literal: true

first_number = 'Digite o primeiro número'
second_number = 'Digite o segundo número'

def show_menu
  puts 'Selecione uma opção'
  puts '1 - Somar'
  puts '2 - Subtrair'
  puts '3 - Multiplicar'
  puts '4 - Dividir'
  puts '0 - Sair'
  puts 'Digite o número da opção desejada'
end

def calculate(num1, num2, operation)
  case operation
  when 1
    "#{num1} + #{num2} = #{num1 + num2}"
  when 2
    "#{num1} - #{num2} = #{num1 - num2}"
  when 3
    "#{num1} * #{num2} = #{num1 * num2}"
  when 4
    "#{num1} / #{num2} = #{num1 / num2}"
  else
    puts 'Opcão inválida'
  end
end

loop do
  show_menu

  option = gets.chomp.to_i

  break if option.zero?

  if option < 1 || option > 4
    puts 'Opcão inválida'
    next
  end

  puts first_number
  num1 = gets.chomp.to_i
  puts second_number
  num2 = gets.chomp.to_i

  result = calculate(num1, num2, option)

  puts result
end

system 'clear'
