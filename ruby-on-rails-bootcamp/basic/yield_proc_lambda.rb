def my_method
  yield if block_given?
end

my_method { puts "Block being executed!" }
my_method do
  puts "Block being executed!"
end


say_hello = Proc.new { puts "Hello, world!" }
say_hello.call


def execute_proc(p)
  p.call
end

my_proc = Proc.new { puts "Proc being executed!" }
execute_proc(my_proc)


multiply = Proc.new do |a, b|
  a * b if a && b
end

# multiply = lambda do |a, b|
#     a * b if a && b
# end

result = multiply.call # or multiply[6, 7]
result = multiply.call(6, 7) # or multiply[6, 7]
puts result # => 42


say_goodbye = lambda { puts "Bye, world!" }
say_goodbye.call


say_goodbye = -> { puts "Bye, world!" }
say_goodbye.call


# Lambda with two parameters
adder = lambda do |a, b|
  a + b
end

# Arrow syntax
# adder = ->(a, b) { a + b }

# sum = adder.call()

# Lambda with parameters
sum = adder.call(5, 8) # or adder[5, 8]
puts sum # => 13
