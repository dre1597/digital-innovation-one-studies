# frozen_string_literal: true

module Utils
  LOCAL_DATABASE = 'tmp/local.sqlite'

  def self.connect
    puts "Connecting to #{LOCAL_DATABASE}"
  end
end

Utils.connect
