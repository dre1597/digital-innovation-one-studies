# frozen_string_literal: true

class Configuration
  def self.configure
    config = new
    yield(config)
    config
  end
end

mysql_config = Configuration.configure do |config|
  def config.database
    'metaprogramming_db'
  end

  def config.user
    'root'
  end

  def config.password
    '123456'
  end
end

puts mysql_config.database
puts mysql_config.user
puts mysql_config.password
