# frozen_string_literal: true

module DecoratePrint
  def print(text)
    decoration = '#' * 30
    puts decoration
    puts text
    puts decoration
  end
end
