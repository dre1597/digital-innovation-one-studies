begin
  # 1 / 0
  # 1 + "d"
  # raise "I'm an exception!"
  1 + "1".to_i
rescue ZeroDivisionError
  puts "You can't divide by zero!"
rescue TypeError
  puts "You can only add numbers!"
rescue Exception => e
  puts "Exception raised: #{e}"
else
  puts "No exceptions were raised!"
ensure
  puts "I'm always executed!"
end


