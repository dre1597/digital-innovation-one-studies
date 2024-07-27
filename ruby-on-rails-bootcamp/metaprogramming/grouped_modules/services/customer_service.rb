# frozen_string_literal: true

module Services
  class CustomerService
    def self.save(customer)
      puts "Saving customer #{customer.name}"
    end
  end
end
