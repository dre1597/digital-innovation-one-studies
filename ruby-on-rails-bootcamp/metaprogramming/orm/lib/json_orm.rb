# frozen_string_literal: true

require 'json'
require 'byebug'

module JsonOrm
  def self.included(base)
    base.extend(ClassMethodsAttributes)
    base.include(InstanceMethodsAttributes)
  end

  module ClassMethodsAttributes
    def all
      data = read_json
      objs = []
      data.each do |row|
        obj = new
        row.each_key do |key|
          obj.send("#{key}=", row[key])
        end
        objs << obj
      end

      objs
    end

    protected

    def json_file(path)
      @path = path
      define_attributes
    end

    private

    def read_json
      return unless File.exist?(@path)

      file = File.read(@path)
      JSON.parse(file)
    end

    def define_attributes
      data = read_json
      puts data.first.keys
      attributes = data.first.keys

      attributes.each do |attribute|
        define_method("#{attribute}=") do |value|
          instance_variable_set("@#{attribute}", value)
        end

        define_method(attribute.to_s) do
          instance_variable_get("@#{attribute}")
        end
      end
    end
  end

  module InstanceMethodsAttributes
    def validate_name
      raise 'Name is required' if name.nil? || name == ''
    end
  end
end
