# frozen_string_literal: true

module Vehicle
  def on
    raise NotImplementedError
  end
end

class Car
  attr_accessor :id, :name, :description

  include Vehicle

  def on
    puts "Car #{@name} is on"
  end
end

a = Car.new
a.name = 'Toyota'
puts a.on
