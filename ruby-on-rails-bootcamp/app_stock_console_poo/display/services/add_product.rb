# frozen_string_literal: true

def add_product
  name = ask_name
  description = ask_description(name)
  price = ask_price(name)
  quantity = ask_quantity(name)

  product = Product.new(
    {
      'id' => Time.now.to_i,
      'name' => name,
      'description' => description,
      'price' => price,
      'quantity' => quantity
    }
  )

  product.inspect

  ProductService.new(JsonRepository, 'db/products.json').add(product)
  # ProductService.new(CsvRepository, 'db/products.csv').add(product)

  show_message("The product #{add_yellow(name)} was added!", true, true, 3)
end

def ask_name
  show_message('Adding new product ...', true, true, 1.5)
  show_blue_message('Name: ', false, false)
  name = gets.chomp
  clear_screen
  name
end

def ask_description(name)
  show_blue_message("Description (#{add_green(name)}): ", false, false)
  description = gets.chomp
  clear_screen
  description
end

def ask_price(name)
  show_blue_message("Price (#{add_green(name)}): ", false, false)
  price = gets.to_f
  clear_screen
  price
end

def ask_quantity(name)
  show_blue_message("Quantity (#{add_green(name)}): ", false, false)
  quantity = gets.to_i
  clear_screen
  quantity
end
