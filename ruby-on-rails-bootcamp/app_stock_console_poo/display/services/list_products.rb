# frozen_string_literal: true

require 'terminal-table'

def list_products
  clear_screen

  show_yellow_message('====== Products List ========', false, false)

  table = Terminal::Table.new do |t|
    t.headings = %w[ID Name Description Price Quantity]
    repository = ProductService.new(JsonRepository, 'db/products.json')
    # repository = ProductService.new(CsvRepository, 'db/products.csv')

    repository.all.each do |p|
      t.add_row [p.id, p.name, p.description, p.price, p.quantity]
    end
  end

  puts table

  show_yellow_message('Press enter to continue ...', false, false)
  gets
  clear_screen
end
