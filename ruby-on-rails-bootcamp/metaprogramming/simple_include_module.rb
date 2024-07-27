# frozen_string_literal: true

module Attributes
  attr_accessor :id, :name, :document
end

class NaturalPerson
  include Attributes
end

class LegalPerson
  include Attributes
end

puts NaturalPerson.new.methods - Class.methods
puts LegalPerson.new.methods - Class.methods
