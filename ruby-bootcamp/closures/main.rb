# frozen_string_literal: true

# block1

6.times { puts 'Hello World!' }

# block2

sum = 0
numbers = [2, 4, 6]
numbers.each { |n| sum += n }
puts sum

# block3

hash = { 2 => 3, 4 => 5, 6 => 3 }

hash.each do |key, value|
  puts "Key: #{key}"
  puts "Value: #{value}"
  puts "Key * Value: #{key * value}"
  puts
end

# block4

def test
  yield 1
  yield
end

test { puts 'Hello World!' }

#block5

def test_optional
  if block_given?
    yield
  else
    puts 'No block given'
  end
end

test_optional

test_optional { puts 'Hello World!' }

#block6

def test_params2(name, &block)
  @name = name
  block.call
end

test_params2('John') { puts "Hello, #{@name}!" }

#block7

def test_params3(numbers, &block)
  return unless block_given?

  numbers.each do |key, value|
    block.call(key, value)
  end

end

test_params3(2 => 3, 4 => 5, 6 => 3) do |key, value|
  puts "Key: #{key}"
  puts "Value: #{value}"
  puts "Key * Value: #{key * value}"
  puts
end
