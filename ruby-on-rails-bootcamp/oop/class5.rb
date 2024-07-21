# frozen_string_literal: true

class Car
  attr_accessor :id, :name, :description

  def on
    puts "Car #{@name} is on"
  end

  def name?
    @name != '' && !@name.nil?
  end

  def name_uppercase!
    @name = @name.upcase if name?
    @name
  end
end

a = Car.new
a.name = 'Toyota'
a.name_uppercase!

puts a.name
