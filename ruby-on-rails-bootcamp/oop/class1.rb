# frozen_string_literal: true

class Person
  attr_accessor :name, :surname

  # def name=(name)
  #   @name = name
  # end
  #
  # def name
  #   @name
  # end
  #
  # def surname=(surname)
  #   @surname = surname
  # end
  #
  # def surname
  #   @surname
  # end

  def to_s
    "#{@name} #{@surname}"
  end
end

p = Person.new
p.name = 'John'
p.surname = 'Doe'
puts p.to_s
