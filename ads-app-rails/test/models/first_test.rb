require "test_helper"

class FirstTest < ActiveSupport::TestCase
    test "this is my first test" do
        variable = "Test"
        assert_equal "Test", variable
    end
end