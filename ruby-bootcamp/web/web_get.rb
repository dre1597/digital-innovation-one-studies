# frozen_string_literal: true

require 'net/http'

example = Net::HTTP.get('example.com', '/index.html')

File.open('example.html', 'w') do |f|
  f.write(example)
end
