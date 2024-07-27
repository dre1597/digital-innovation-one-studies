# frozen_string_literal: true

require_relative '../lib/json_orm'

class Product
  include JsonOrm
  json_file('json/products.json')
end
