# frozen_string_literal: true

module Models
  class Supplier
    @@table_name = 'suppliers'
    include ORM::DIO
  end
end
