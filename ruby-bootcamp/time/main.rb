time = Time.now

puts time.year
puts time.month
puts time.day

puts time.strftime('%d/%m/%Y %H:%M:%S')

puts time.friday?

time2 = Time.now

puts time == time2
puts time - time2
