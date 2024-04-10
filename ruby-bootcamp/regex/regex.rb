# frozen_string_literal: true

regex1 = /abcdef/
regex2 = %r{abcdef}
regex3 = Regexp.new('abcdef')

puts regex1.class
puts regex2.class
puts regex3.class

puts((/by/ =~ 'ruby'))
puts((/by/ =~ 'rails'))

phrase = 'Ruby is fun'
match_data = /fun/.match(phrase)

puts match_data

puts match_data.pre_match
puts match_data.post_match
