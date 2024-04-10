class Post < ApplicationRecord
  validates :title, :author, :description, presence: true
  validates :score, presence: true, numericality: { only_integer: true, greater_than_or_equal_to: 0, less_than_or_equal_to: 10 }
  validates :title, length: { maximum: 255 }
  validates :author, length: { maximum: 100 }
  validates :description, length: { maximum: 500 }
end
