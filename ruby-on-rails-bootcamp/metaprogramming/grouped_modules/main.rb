require_relative 'entities/customer'
require_relative 'entities/supplier'
require_relative 'services/customer_service'

c = Entities::Customer.new
c.id = 1
c.name = 'John Doe'

s = Entities::Supplier.new
s.id = 1
s.name = 'Acme Inc.'

Services::CustomerService.save(c)
