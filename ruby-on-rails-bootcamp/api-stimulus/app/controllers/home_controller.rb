class HomeController < ApplicationController
  def index
    render json: {
      message: 'Hello, Stimulus!',
      endpoints: [
        '/students'
      ]
    }
  end
end
