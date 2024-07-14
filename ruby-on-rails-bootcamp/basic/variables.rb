# frozen_string_literal: true

a = 1
puts a.class
puts 1.class
b = 1.1
puts b.class

# c = a + "8" # error because a is an integer and "8" is a string
c = a + '8'.to_i
puts c

puts true.class
puts false.class
puts ''.class
puts :test.class
hash = { test: 1 }
puts hash.class
puts [].class
puts (1..10).class
puts nil.class
