require "application_system_test_case"

class LoginsTest < ApplicationSystemTestCase

    def setup
        @user = FactoryBot.create(:user, password: "12345", password_confirmation: "12345")
    end
    test "valid login" do

        login_as(@user)

        assert_text "Welcome!"
    end

    test "invalid login" do 

        login_as(@user, "invalidpassword")

        assert_equal new_session_path, current_path
        assert_text "Invalid email or password"
    end
end
