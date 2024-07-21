# frozen_string_literal: true

class Email
  attr_accessor :to, :from, :subject, :body

  def send
    puts "To: #{@to}"
  end

  private_class_method :new

  def self.instance
    @@instance ||= new
    @@instance
  end
end

Email.instance.to = 'vDqPm@example.com'
Email.instance.send
puts Email.instance

email = Email.instance
Email.instance.to = 'vDqPm@example.com'
Email.instance.send
puts Email.instance
