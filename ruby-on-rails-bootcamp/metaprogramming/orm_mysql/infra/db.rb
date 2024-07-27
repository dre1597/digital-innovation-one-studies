# frozen_string_literal: true

require 'mysql2'
require 'yaml'
require 'erb'
require 'byebug'

module Infra
  class Db
    def initialize
      env = ENV[`APP_ENV`] || 'development'
      yaml_content = ERB.new(File.read('config/database.yml')).result
      db_config = Psych.safe_load(yaml_content, aliases: true)
      config = db_config[env]

      @client = get_client(config)
    end

    def execute(sql, params = [])
      statement = @client.prepare(sql)
      result = statement.execute(*params)
      result.to_a
    ensure
      statement.close
    end

    private

    def get_client(config)
      Mysql2::Client.new(
        host: config['host'],
        port: config['port'],
        username: config['username'],
        password: config['password'],
        database: config['database']
      )
    end
  end
end
