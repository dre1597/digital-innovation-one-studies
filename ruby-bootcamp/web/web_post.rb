# frozen_string_literal: true

require 'net/http'

request = Net::HTTP::Post.new('/api/users')

request.set_form_data({ name: 'Jane', job: 'QA' })

response = Net::HTTP.start('reqres.in', use_ssl: true) do |https|
  https.request(request)
end

puts response.code
puts response.message
puts response.body
