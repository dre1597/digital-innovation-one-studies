def function_without_parameters
  puts "Function without parameters"
end

function_without_parameters

def function_with_parameters(nome)
  puts "Function with parameters - #{nome}"
end

function_with_parameters("Name 1")
function_with_parameters "Name 2"

def function_with_parameters(name, age)
  puts "Function with parameters #{name} - #{age}"
end

function_with_parameters "Liah", 1
function_with_parameters("Liah", 1)

def function_wht_parameters_and_default_value(name = "Lana")
  puts "Function with parameters#{name}"
end

function_wht_parameters_and_default_value
function_wht_parameters_and_default_value("Sheila")

def function_with_array(array = [])
  puts "Function with array - " + array.inspect
end

function_with_array
function_with_array([1,2,3])
function_with_array [4,5,6]

def function_with_hash(hash = {})
  puts "Function with hash - " + hash.inspect
end

function_with_hash
function_with_hash({name: "test"})
function_with_hash({name: "test", age: 27})

def function_with_any_parameters(*parameters)
  puts "Function with any parameters - " + parameters.inspect
end

function_with_any_parameters
function_with_any_parameters 1,2,4
function_with_any_parameters("1", 2.5, 4)

def sum(a, b)
  return a + b # optional return
end

def sum_without_return(a, b)
  a + b # without return the last statement will be returned
end

x = sum(1,2)
y = sum_without_return(1,2)

puts "The result of x=#{x} and y=#{y}"

def sum_plus_5(a, b)
  c = a + b
  return 10 if c > 5

  c * 20
end

puts "The result is: " + sum_plus_5(1,2).to_s
puts "The result is: " + sum_plus_5(5,2).to_s

def method_with_block
  yield if block_given?
end

method_with_block

method_with_block { puts "Block being executed!" }
method_with_block do
  puts "Block 2 being executed!"
  puts "Block 3 being executed!"
  puts "Block 4 being executed!"
  puts "Block 5 being executed!"
end
