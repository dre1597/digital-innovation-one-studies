# frozen_string_literal: true

puts 'Type a valid phone'
phone = gets.to_s

type_green = "\e[32m"
type_red = "\e[31m"
reset = "\e[0m"

if phone =~ /^\(\d{2}\) 9\d{4}-\d{4}$/
  puts "#{type_green}Phone is valid #{phone} #{reset}"
else
  puts "#{type_red}Phone is invalid #{phone} #{reset}"
end

puts 'Type a valid name'
name = gets.to_s

require 'byebug'
debugger

if name =~ /[V|v]alid .*?[N|n]name/
  puts "#{type_green}Valid #{name}"
else
  puts "#{type_red}Invalid #{name}"
end
