# frozen_string_literal: true

class Person
  def initialize(person_hash = {})
    @name = person_hash[:name]
    @surname = person_hash[:surname]
    @age = person_hash[:age]
  end

  attr_accessor :name, :surname, :age

  def to_s
    "#{@name} #{@surname} #{@age}"
  end
end

puts Person.new({ name: 'John', surname: 'Doe', age: 25 }).to_s
