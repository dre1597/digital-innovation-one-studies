class Student < ApplicationRecord
  validates :name, :phone, :code, presence: true
end
