# frozen_string_literal: true

numbers = []

3.times do |number|
  print 'Enter a number: '
  numbers[number] = gets.chomp.to_i
end

puts "You entered: #{numbers}"
puts "Your numbers powered by 3: #{numbers.map { |number| number**3 }}"
