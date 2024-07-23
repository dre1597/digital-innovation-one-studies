# frozen_string_literal: true

def show_message(msg, should_clear_screen = true, use_timer = true, timer = 5)
  clear_screen if should_clear_screen
  puts msg
  sleep(timer) if use_timer
  clear_screen if should_clear_screen
end

def show_green_message(msg, should_clear_screen = true, use_timer = true, timer = 5)
  msg = add_green(msg)
  show_message(msg, should_clear_screen, use_timer, timer)
end

def show_red_message(msg, should_clear_screen = true, use_timer = true, timer = 5)
  msg = add_red(msg)
  show_message(msg, should_clear_screen, use_timer, timer)
end

def show_yellow_message(msg, should_clear_screen = true, use_timer = true, timer = 5)
  msg = add_yellow(msg)
  show_message(msg, should_clear_screen, use_timer, timer)
end

def show_blue_message(msg, should_clear_screen = true, use_timer = true, timer = 5)
  msg = add_blue(msg)
  show_message(msg, should_clear_screen, use_timer, timer)
end

def add_green(msg)
  "\e[32m#{msg}\e[0m"
end

def add_red(msg)
  "\e[31m#{msg}\e[0m"
end

def add_yellow(msg)
  "\e[33m#{msg}\e[0m"
end

def add_blue(msg)
  "\e[34m#{msg}\e[0m"
end

def clear_screen
  if Gem.win_platform?
    system('cls') # On Windows
  else
    system('clear') # On Unix/Linux Systems
  end
end
