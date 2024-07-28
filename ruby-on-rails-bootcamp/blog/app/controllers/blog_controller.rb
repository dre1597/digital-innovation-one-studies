class BlogController < ApplicationController
  layout 'application_blank'

  def index
    @post = Post.where('publish_date < ?', Time.zone.now).order('publish_date DESC').first
  end
end
