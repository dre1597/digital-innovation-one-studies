# frozen_string_literal: true

first_lambda = -> { puts 'Hello World!' }
first_lambda.call

# first_lambda = lambda { puts 'Hello World!' }
# first_lambda.call

lambda_with_params = ->(names) { names.each { |name| puts "Hello, #{name}!" } }
lambda_with_params.call(%w[John Jane])

lambda_with_multiple_lines = lambda do |names|
  names.each do |name|
    puts "Hello, #{name}!"
  end
end

lambda_with_multiple_lines.call(%w[John Jane])

# lambda_with_multiple_lines = ->(names) {
#   names.each do |name|
#     puts "Hello, #{name}!"
#   end
# }
# lambda_with_multiple_lines.call(%w[John Jane])

def test(lambda1, lambda2)
  lambda1.call
  lambda2.call
end

lambda1 = -> { puts 'Hello World!' }
lambda2 = -> { puts 'Goodbye World!' }
test(lambda1, lambda2)
