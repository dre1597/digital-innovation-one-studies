require_relative 'common/person'
require_relative 'common/legal_person'
require_relative 'common/natural_person'

class Employee
  include Common::Person
  include Common::NaturalPerson
end

puts Employee.new.methods - Class.methods

class Supplier
  include Common::Person
  include Common::LegalPerson
end

puts Supplier.new.methods - Class.methods
