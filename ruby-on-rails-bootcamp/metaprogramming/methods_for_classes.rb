# frozen_string_literal: true

class Example
  def a_method
    puts 'Hello from a_method'
  end
end

class Example
  def another_method
    puts 'Hello from another_method'
  end
end

a = Example.new
a.a_method
a.another_method

class String
  def new_method
    puts 'Hello from new_method'
  end

  def uppercase
    upcase
  end

  def lowercase
    downcase
  end
end

s = 'Hello'

s.new_method
''.new_method

puts s.uppercase
puts s.lowercase

class Integer
  def new_method
    puts 'Hello from new_method'
  end
end

1.new_method
2.new_method
