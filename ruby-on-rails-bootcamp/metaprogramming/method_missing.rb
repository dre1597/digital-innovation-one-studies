# frozen_string_literal: true

class Configuration2
  def self.configure
    config = new
    yield(config)
    config
  end

  def method_missing(name, *args, &block)
    if name.to_s.end_with?('=')
      instance_variable_set("@#{name.to_s.chomp('=')}", args.first)
    else
      instance_variable_get("@#{name}")
    end
  end

  def respond_to_missing?(name, include_private = false)
    name.to_s.end_with?('=') || instance_variable_defined?("@#{name}") || super
  end
end

mysql_config = Configuration2.configure do |config|
  config.database = 'metaprogramming_database'
  config.user = 'root'
  config.password = '123456'
end

puts mysql_config.database
puts mysql_config.user
puts mysql_config.password
