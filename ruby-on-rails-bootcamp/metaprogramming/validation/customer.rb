# frozen_string_literal: true

require_relative 'validation'

class CustomerForValidation
  include Validation

  attr_accessor :name, :email

  validate_empty :name, :email
end

c = CustomerForValidation.new

puts c.methods - Class.methods

puts CustomerForValidation.methods - Class.methods

c.name = 'bob'
c.email = 'bob@email.com'
c.validate!
