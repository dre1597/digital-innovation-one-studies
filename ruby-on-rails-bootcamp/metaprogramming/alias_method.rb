# frozen_string_literal: true

class Supplier
  def my_method
    puts 'my_method'
  end
end

Supplier.class_eval do
  alias_method :original_my_method, :my_method

  def my_method
    puts 'my_method'
  end
end

f = Supplier.new

f.my_method
f.original_my_method
