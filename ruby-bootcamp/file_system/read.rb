# frozen_string_literal: true

file = File.open('list.txt', 'r')

file.each_line do |line|
  puts line
end

file.close
