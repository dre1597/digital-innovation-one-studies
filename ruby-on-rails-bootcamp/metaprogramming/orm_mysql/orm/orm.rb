# frozen_string_literal: true

require 'json'
require 'byebug'
require_relative '../infra/db'

module ORM
  module DIO
    class << self
      def table(obj)
        "#{obj.to_s.split('::').last.downcase}s"
      end
    end

    def self.included(base)
      base.extend(ClassMethodsAttributes)
      base.include(InstanceMethodsAttributes)

      base.class_variable_set(:@@table_name, DIO.table(base)) unless base.class_variable_defined?(:@@table_name)
      table = base.class_variable_get(:@@table_name)
      data = Infra::Db.new.execute("desc #{table}")
      attributes = data.map { |elem| elem['Field'] }
      attributes.each do |attribute|
        base.define_method("#{attribute}=") do |valor|
          instance_variable_set("@#{attribute}", valor)
        end

        base.define_method(attribute.to_s) do
          instance_variable_get("@#{attribute}")
        end
      end
    end
  end

  module InstanceMethodsAttributes
    def include
      unless self.class.class_variable_defined?(:@@table_name)
        self.class.class_variable_set(:@@table_name, DIO.table(self.class))
      end
      table = self.class.class_variable_get(:@@table_name)

      columns = []
      methods.each do |method|
        if method.to_s.end_with?('=')
          key = method.to_s.chomp('=')
          columns << key if key.scan(/id|=|!|\?/).empty?
        end
      end

      sql = "insert into #{table}(#{columns.join(', ')})values(#{columns.map { |_c| '?' }.join(', ')})"
      params = []
      columns.each do |column|
        params << instance_variable_get("@#{column}")
      end

      Infra::Db.new.execute(sql, params)
    end
  end

  module ClassMethodsAttributes
    def all
      class_variable_set(:@@table_name, DIO.table(self)) unless class_variable_defined?(:@@table_name)
      table = class_variable_get(:@@table_name)

      data = Infra::Db.new.execute("SELECT * FROM #{table}")
      customer = []
      data.each do |elem|
        obj = new
        obj.methods.each do |method|
          if method.to_s.end_with?('=')
            key = method.to_s.chomp('=')
            obj.send("#{key}=", elem[key])
          end
        end
        customer << obj
      end

      customer
    end
  end
end
