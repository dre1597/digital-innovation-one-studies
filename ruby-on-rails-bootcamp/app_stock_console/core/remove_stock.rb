# frozen_string_literal: true

require 'terminal-table'

def remove_stock(products)
  show_table(products)

  id = ask_id

  product = find_product(products, id)
  unless product
    clear_screen
    show_red_message("Product with (#{id}) not found", false, false)
    show_yellow_message('Do you want to try again? (y/n)', false, false)
    option = gets.chomp.downcase
    clear_screen

    remove_stock(products) if %w[y yes].include?(option)

    return
  end

  removed_quantity = ask_quantity_to_remove(product)
  product[:quantity] = product[:quantity] - removed_quantity

  show_green_message('The product was successfully removed!', true, true, 3)
  list_products(products)
end

def show_table(products)
  clear_screen

  show_yellow_message('====== Choose one of the products below ========', false, false)

  table = Terminal::Table.new do |t|
    t.headings = %w[ID Name Quantity]
    products.each do |row|
      t.add_row [row[:id], row[:name], row[:quantity]]
    end
  end

  puts table
end

def ask_id
  show_blue_message('ID:', false, false)
  gets.to_i
end

def find_product(products, id)
  products.find { |p| p[:id] == id }
end

def ask_quantity_to_remove(product)
  clear_screen
  show_blue_message("Type the quantity you want to remove: #{add_yellow(product[:name])}", false, false)
  show_green_message("Current quantity: #{add_yellow(product[:quantity])}", false, false)
  gets.to_i
end
