# frozen_string_literal: true

class Fish
  def swim
    puts "I'm swimming"
  end
end

class Bird
  def fly
    puts "I'm flying"
  end

  def method_missing(method_name)
    puts "I'm missing #{method_name}"
  end
end

fish = Fish.new
bird = Bird.new

fish.swim
bird.fly
bird.kick
