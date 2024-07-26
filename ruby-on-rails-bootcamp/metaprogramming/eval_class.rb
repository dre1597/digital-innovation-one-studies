# frozen_string_literal: true

class Person
end

Person.class_eval do
  %i[speak scream].each do |action|
    define_method action do
      if action.to_s == 'scream'
        puts 'Hello World!'.upcase
      else
        puts 'Hello, World!'
      end
    end
  end
end

pessoa = Person.new
pessoa.scream
pessoa.speak

class Calculator
  define_method :sum do |a, b|
    a + b
  end

  # def sum(a, b)
  #   a + b
  # end
end

calc = Calculator.new
puts calc.sum(5, 3)

class Product
  attributes = %i[name price code]

  attributes.each do |attribute|
    define_method(attribute.to_s) do
      instance_variable_get("@#{attribute}")
    end

    define_method("#{attribute}=") do |value|
      instance_variable_set("@#{attribute}", value)
    end
  end
end

product = Product.new

puts product.methods - Class.methods

product.name = 'Pen'
product.price = 1.50
puts product.name
puts product.price

class Customer
  def initialize(hash_array)
    hash_array.each do |hash|
      hash.each_key do |key|
        self.class.define_method(key.to_s) do
          instance_variable_get("@#{key}")
        end

        self.class.define_method("#{key}=") do |value|
          instance_variable_set("@#{key}", value)
        end
      end
      break
    end
  end
end

hash_array = [
  { name: 'João', phone: '1234-5678', cpf: '111.222.333-44' },
  { name: 'João', phone: '1234-5678', cpf: '111.222.333-44' },
  { name: 'João', phone: '1234-5678', cpf: '111.222.333-44' },
  { name: 'João', phone: '1234-5678', cpf: '111.222.333-44' }
]

customer = Customer.new(hash_array)

customer.name = 'ssss'
puts customer.name

puts customer.methods - Class.methods

colors = {
  red: "\e[31m",
  green: "\e[32m",
  blue: "\e[34m",
  yellow: "\e[33m",
  reset: "\e[0m"
}

def to_color(text, color)
  "#{color}#{text}\e[0m"
end

puts to_color('This text will be red.', colors[:red])
puts to_color('This text will be green.', colors[:green])
puts to_color('This text will be blue.', colors[:blue])
puts to_color('This text will be yellow.', colors[:yellow])

String.class_eval do
  colors = {
    red: "\e[31m",
    green: "\e[32m",
    blue: "\e[34m",
    yellow: "\e[33m",
    white: "\e[0m"
  }

  colors.each do |key, color|
    define_method("print_in_#{key}") do
      puts "#{color}#{self}\e[0m"
    end
  end
end

class Customer2
  attr_accessor :name, :phone
end

c = Customer2.new
c.name = 'Danilo'

c.name.print_in_blue

'Text to print'.print_in_red
'Text to print'.print_in_green
'Text to print'.print_in_blue
'Text to print'.print_in_yellow
'Text to print'.print_in_white
