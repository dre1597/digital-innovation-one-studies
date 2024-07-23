# frozen_string_literal: true

require 'terminal-table'

def remove_stock
  show_table

  id = ask_id

  product = find_product(id)
  puts product.inspect
  unless product
    # clear_screen
    show_red_message("Product with ID (#{id}) not found", false, false)
    show_yellow_message('Do you want to try again? (y/n)', false, false)
    option = gets.chomp.downcase
    clear_screen

    remove_stock if %w[y yes].include?(option)

    return
  end

  removed_quantity = ask_quantity_to_remove(product)
  product.quantity = product.quantity.to_i - removed_quantity

  ProductService.new(JsonRepository, 'db/products.json').update(product)
  # ProductService.new(CsvRepository, 'db/products.csv').update(product)

  show_green_message('The product was successfully removed!', true, true, 3)
  list_products
end

def show_table
  clear_screen

  show_yellow_message('====== Choose one of the products below ========', false, false)

  table = Terminal::Table.new do |t|
    t.headings = %w[ID Name Quantity]

    repository = ProductService.new(JsonRepository, 'db/products.json')
    # repository = ProductService.new(CsvRepository, 'db/products.csv')
    repository.all.each do |p|
      t.add_row [p.id, p.name, p.quantity]
    end
  end

  puts table
end

def ask_id
  show_blue_message('ID:', false, false)
  gets.to_i
end

def find_product(id)
  ProductService.new(JsonRepository, "db/products.json").all.find { |p| p.id == id }
  # ProductService.new(CsvRepository, "db/products.csv").all.find { |p| p.id.to_i == id }
end

def ask_quantity_to_remove(product)
  clear_screen
  show_blue_message("Type the quantity you want to remove: #{add_yellow(product.name)}", false, false)
  show_green_message("Current quantity: #{add_yellow(product.quantity)}", false, false)
  gets.to_i
end
