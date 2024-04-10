# frozen_string_literal: true

def verify_phone(phone)
  phone_regex = /^\(\d{2}\) \d{5}-\d{4}$/

  phone.match?(phone_regex)
end

puts verify_phone('(99) 99999-9999')
puts verify_phone('(99)99999999')
