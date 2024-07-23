# frozen_string_literal: true

class ProductService
  def initialize(repository, file)
    @repository = repository
    @file = file
  end

  def all
    data = @repository.read(@file)
    products = []

    data.each do |p|
      products << Product.new(p)
    end

    products
  end

  def add(product)
    product_hash = to_hash(product)
    @repository.add(@file, product_hash)
  end

  def update(product)
    data = @repository.read(@file)
    product_hash = data.find { |p| p['id'] == product.id }

    product_hash['name'] = product.name
    product_hash['description'] = product.description
    product_hash['price'] = product.price
    product_hash['quantity'] = product.quantity

    product_hash.inspect

    @repository.write(@file, data)
  end

  private

  def to_hash(product)
    product.instance_variables.each_with_object({}) do |var, hash|
      hash[var.to_s.delete('@')] = product.instance_variable_get(var)
    end
  end
end
