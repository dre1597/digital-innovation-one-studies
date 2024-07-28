Rails.application.routes.draw do

  scope '/admin' do
    get '/', to: 'posts#index'
    resources :posts
  end

  get 'up' => 'rails/health#show', as: :rails_health_check

  root 'blog#index'
end
