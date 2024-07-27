# frozen_string_literal: true

module Models
  class Customer
    @@table_name = 'customers'
    include ORM::DIO
  end
end
