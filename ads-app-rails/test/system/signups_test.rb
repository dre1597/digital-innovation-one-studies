require "application_system_test_case"

class SignupsTest < ApplicationSystemTestCase
    test "valid user signed up" do
        visit "/"
        click_on "Sign Up"

        fill_in "Name", with: "user1"
        fill_in "Email", with: "user1@email.com"
        fill_in "Password", with: "123456"
        fill_in "Confirm password", with: "123456"

        click_on "Create"

        assert_text "Account created sucessfully!"
    end

    test "invalid user signed up" do
        visit "/"
        click_on "Sign Up"
        click_on "Create"

        assert_text "Error on create account"
    end
end
