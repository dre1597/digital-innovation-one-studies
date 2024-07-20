# frozen_string_literal: true

require 'terminal-table'

def list_products(products)
  clear_screen

  show_yellow_message('====== Products List ========', false, false)

  table = make_table(products)

  puts table

  show_yellow_message('Press enter to continue ...', false, false)
  gets
  clear_screen
end

def make_table(products)
  Terminal::Table.new do |t|
    t.headings = %w[ID Name Description Price Quantity]
    products.each do |row|
      t.add_row [row[:id], row[:name], row[:description], row[:price], row[:quantity]]
    end
  end
end
