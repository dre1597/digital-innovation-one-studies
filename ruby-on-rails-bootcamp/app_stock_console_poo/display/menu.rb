# frozen_string_literal: true

def init_menu
  loop do
    show_menu

    option = gets.to_i

    case option
    when 1
      add_product
    when 2
      list_products
    when 3
      remove_stock
    when 4
      clear_screen
      exit
    else
      show_message 'Invalid option, please try again'
    end
  end
end

def show_menu
  show_yellow_message('======= MENU =========', false, false)
  show_blue_message("#{add_red('1')} - Add product", false, false)
  show_blue_message("#{add_red('2')} - List products", false, false)
  show_blue_message("#{add_red('3')} - Remove stock", false, false)
  show_blue_message("#{add_red('4')} - Exit", false, false)
end
