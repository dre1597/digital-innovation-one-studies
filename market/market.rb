class Market
  def initialize(product, price)
    @product = product
    @price = price
  end

  def buy
    puts "You bought #{@product} for #{@price}."
  end
end
