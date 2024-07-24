# frozen_string_literal: true

class Example
end

a = Example.new

def a.a_method
  puts 'Hello from a_method'
end

a.a_method

b = Example.new
b.a_method # raises NoMethodError
