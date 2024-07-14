puts 'Enter a number: '
number = gets.to_i

if number.positive?
  puts 'Your number is positive'
elsif number.negative?
  puts 'Your number is negative'
else
  puts 'Your number is zero'
end

puts 'Enter a number: '
number = gets.to_i

puts 'You got the number correct' if number == 42

unless number == 42
  puts "You did not get the number #{number}"
else
  puts 'You got the number: ' + number.to_s
end

case number
when 1
  puts "Found the first #{number}"
when 2
  puts "Found the second: #{number}"
else
  puts "Found something else: #{number}"
end
