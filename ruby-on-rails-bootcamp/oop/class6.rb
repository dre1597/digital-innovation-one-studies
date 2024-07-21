# frozen_string_literal: true

class Car
  attr_accessor :id, :name, :description

  def on
    puts "Car #{@name} is on #{self}"
  end

  def self.on
    puts "Car #{@name} is on #{self}"
  end
end

a = Car.new
a.name = 'Toyota'
puts a.on

puts Car.on
