# frozen_string_literal: true

class Person
  def initialize(person_hash = {})
    @name = person_hash[:name]
    @surname = person_hash[:surname]
    @age = person_hash[:age]
  end

  attr_accessor :name, :surname, :age

  private

  def to_string_private
    "#{@name} #{@surname} #{@age}"
  end

  public

  def to_s
    to_string_private
  end
end
