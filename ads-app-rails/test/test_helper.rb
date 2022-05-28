ENV['RAILS_ENV'] ||= 'test'
require_relative "../config/environment"
require "rails/test_help"

class ActiveSupport::TestCase
    # Run tests in parallel with specified workers
    parallelize(workers: :number_of_processors)

    # Setup all fixtures in test/fixtures/*.yml for all tests in alphabetical order.
    fixtures :all

    # Add more helper methods to be used by all tests here...

    def login_as(user, password = "12345")
        visit root_path
        click_link "Sign In"
        
        fill_in "Email", with: user.email
        fill_in "Password", with: password

        click_button "Login"
    end
end
