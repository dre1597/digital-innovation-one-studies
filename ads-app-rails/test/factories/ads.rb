FactoryBot.define do
  factory :ad do
    title { "MyString" }
    description { "MyText" }
    price { 1 }
    user { nil }
  end
end
