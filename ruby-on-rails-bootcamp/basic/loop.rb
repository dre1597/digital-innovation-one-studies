# frozen_string_literal: true
require 'byebug'

loop do
    puts "This will be repeated until you stop it"
    puts "Type 1 to exit"
    quit = gets.to_i
    break if quit == 1
end

count = 0
while count < 5 do
    puts count
    count += 1
end

count = 0
until count == 5 do
  puts count
  count += 1
end

for i in 0..4 do
    puts i
end

5.times do |i|
    puts i
end

5.times { |i| puts i }

[1, 2, 3, 4, 5].each do |num|
    puts num
end

[{ name: 'test'}, { name: 'test2'}].each do |item|
    item.each do |key, value|
        puts key
        puts value
    end
    puts "---------"
end

1.upto(5) do |i|
    puts i
end

puts "---------"

5.downto(1) do |i|
    puts i
end

(1..5).each do |i|
    next if i.even?
    puts i
end

(1..5).each do |i|
    puts i
    redo if i.even?
end

(1..5).each do |i|
  break if i.even?
  puts i
end
