# frozen_string_literal: true

puts "What's your name?"
name = gets.chomp.to_s

puts "What's your surname?"
surname = gets.chomp.to_s

puts "What's your age?"
age = gets.chomp.to_i

puts "Hello, #{name} #{surname}! You are #{age} years old."
