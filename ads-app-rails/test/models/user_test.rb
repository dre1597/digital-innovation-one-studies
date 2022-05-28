require 'test_helper'

class UserTest < ActiveSupport::TestCase
    test "name is required" do
        user = User.new(name: nil)
        user.save
        assert user.errors[:name].any?
    end

    test "email is required and need a valid format" do
        user = User.new(name: nil)
        user.save

        assert user.errors[:email].any?

        user = User.new(email: "invalid")
        user.save

        assert user.errors[:email].any?

        user = User.new(email: "valid@email.com")
        user.save
        
        refute user.errors[:email].any?
    end

    test "email is unique" do
        user = User.create!(name:"User1", email:"user1@email.com", password: "12345", password_confirmation: "12345")
        other_user = User.create(name:"User2", email:"user1@email.com", password: "12345", password_confirmation: "12345")

        assert other_user.errors[:email].any?
    end
end
