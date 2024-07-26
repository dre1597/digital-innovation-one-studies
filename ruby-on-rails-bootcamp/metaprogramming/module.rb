# frozen_string_literal: true

module Attributes
  def self.included(base)

    base.class_eval do
      def xxx
        puts 'sssss'
      end
    end

    base.instance_eval do
      def xxx_class
        puts 'sssss'
      end
    end

    puts "Was included in class - #{base}"
  end

  attr_accessor :id, :name, :document

  def show_data
    puts "#{id} - #{name} - #{document}"
  end
end

module ClassMethods
  def total_method
    puts 'Hiiii ... '
  end
end

class Customer3
  include Attributes
  extend ClassMethods
end

class Supplier
  include Attributes
  extend ClassMethods
end

c = Customer3.new
f = Supplier.new
c.id = 1
c.name = 'Danilo'
c.document = '12222'

puts '===== client instance ====='
puts c.methods - Class.methods
puts '===== supplier instance ====='
puts f.methods - Class.methods

puts '===== client class ====='
puts Customer3.methods - Class.methods
puts '===== supplier class ====='
puts Supplier.methods - Class.methods

Customer.total_method
