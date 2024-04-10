# frozen_string_literal: true

puts Math.sqrt(16)

puts Math.log10(100)

puts radian = 30 * (Math::PI / 180)
puts Math.sin(radian)

puts Math::E

time = Time.now

puts time.year
puts time.month
puts time.day

puts time.strftime('%d/%m/%Y %H:%M:%S')

puts time.friday?

time2 = Time.now

puts time == time2
puts time - time2
