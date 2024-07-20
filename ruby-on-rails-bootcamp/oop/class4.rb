# frozen_string_literal: true

class Person
  def initialize(person_hash = {})
    @name = person_hash[:name]
    @age = person_hash[:age]
    @surname = person_hash[:surname]
  end

  attr_accessor :name, :age, :height

  private

  def show_full_name_private
    "#{@name} #{@surname} #{@age}"
  end

  public

  def show_full_name
    show_full_name_private
  end

  def outro
    show_full_name
  end

  def to_override
    'to override'
  end

  protected

  def use_protected_method
    'use protected method'
  end
end

class Student < Person
  def initialize(name, surname, age)
    @name = name
    @surname = surname
    @age = age
    super({ name:, surname:, age: })
  end

  def show_action
    "#{@name} Action"
  end

  def to_override
    "#{super} - #{@name}"
  end
end

p = Student.new('Student', '1', 20)
puts p.to_override
