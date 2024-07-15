arr0 = []
arr1 = [1, 2, 3, 4, 5]
arr2 = Array.new # empty array
arr3 = Array.new(3) # [nil, nil, nil]
arr4 = Array.new(3, true) # [true, true, true]
arr5 = Array.new(3, "oi") # ["oi", "oi", "oi"]
puts "-----------------------"

puts arr0.inspect
puts arr1.inspect
puts arr2.inspect
puts arr3.inspect
puts arr4.inspect
puts arr5.inspect
puts "-----------------------"


arr = [1, 2, 3, 4, 5]
puts arr[0] # => 1
puts arr[2] # => 3
puts arr[-1] # => 5, negative index starts from the end
puts arr.first # => 1
puts arr.last # => 5

puts "-----------------------"

arr = [1, 2, 3]
puts arr.inspect
arr << 4 # Add to the end
puts arr.inspect
arr.push(5) # Add to the end
puts arr.inspect
arr.unshift(0) # Add to the beginning
puts arr.inspect
arr.pop # remove from the end
puts arr.inspect
arr.shift # remove from the beginning
puts arr.inspect


puts "-----------------------"
arr1 = [1, 2, 3]
arr2 = [4, 5, 6]
puts (arr1 + arr2).inspect # => [1, 2, 3, 4, 5, 6]
puts (arr1 * 3).inspect # => [1, 2, 3, 1, 2, 3, 1, 2, 3]


puts "-----------------------"
arr = [1, 2, 3, 4, 5]
arr.each do |item|
  puts item
end


puts "-----------------------"
arr = [1, 2, 3, 4, 5]
puts arr.length # => 5
puts arr.reverse.inspect # => [5, 4, 3, 2, 1]
puts arr.include?(3) # => true
puts arr.map { |x| x * 2 }.inspect # => [2, 4, 6, 8, 10]

puts "-----------------------"
str = "a,b,c,d"
arr = str.split(",") # => ["a", "b", "c", "d"]
puts arr.inspect
puts arr.join("-") # => "a-b-c-d"


puts "-----------------------"
multi = [[1, 2], [3, 4], [5, 6]]
puts multi[0][1] # => 2
puts multi[1][0] # => 3
puts multi[2][1] # => 6

puts "-----------------------"

puts (1..5).to_a.inspect # => [1, 2, 3, 4, 5]

puts "-----------------------"
arr = [1, 2, 3, 4, 5]
puts arr.select { |x| x > 2 }.inspect # => [3, 4, 5]
puts arr.reject { |x| x > 2 }.inspect # => [1, 2]

puts "-----------------------"
arr = [1, 2, 3, 4, 5]
puts arr.find { |x| x == 4 } # => 4

puts "-----------------------"
arr = [
  { name: "danilo", age: 20},
  { name: "LaNA", age: 40},
  { name: "Fabiana", age: 18}
]
puts arr.find { |x| x[:name] == "LaNA" }.inspect # => {name: "LaNA", age: 40}
puts arr.select { |x| x[:name].downcase.include? "NA".downcase }.inspect # => {name: "Lana", age: 40}
