# frozen_string_literal: true

module Validation
  def self.included(base)
    base.extend(ClassMethods)
    base.include(InstanceMethods)
  end

  module ClassMethods
    def validate_empty(*attributes)
      attributes.each do |attribute|
        define_method("#{attribute}_validate!") do
          value = instance_variable_get("@#{attribute}")
          raise "THe #{attribute} cannot be empty" if value.nil? || value.empty?
        end
      end
    end
  end

  module InstanceMethods
    def validate
      methods.grep(/validate/).each { |method| send(method) }
    end
  end
end
