# frozen_string_literal: true
require_relative '../lib/json_orm'

class Supplier
  include JsonOrm
  json_file('json/suppliers.json')
end
