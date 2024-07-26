# frozen_string_literal: true

class MyClass
  def self.my_method
    puts 'hello'
  end
end

MyClass.my_method

def MyClass.dynamic_method
  puts 'hello dynamic'
end

MyClass.dynamic_method

class MyClass
  def self.dynamic_method2
    puts 'hello again'
  end
end

MyClass.dynamic_method2

class MyClass
  class << self
    def another_class_method
      puts 'hello from another_class_method'
    end

    def another_class_method2
      puts 'hello from another_class_method2'
    end
  end
end

puts MyClass.methods - Class.methods
