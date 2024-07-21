# frozen_string_literal: true

class Vehicle
  attr_accessor :id, :name, :description

  def on
    raise NotImplementedError
  end
end

class Car < Vehicle
  def initialize(name)
    @name = name
  end

  def on
    puts "Car #{@name} is on"
  end
end

a = Car.new('Toyota')
a.on
