# frozen_string_literal: true

require 'json'

class JsonRepository
  def self.read(file)
    unless File.exist?(file)
      puts 'File not found'
      nil
    end

    begin
      JSON.parse(File.read(file))
    rescue JSON::ParserError => e
      puts "Error in file #{file}: #{e.message}"
      nil
    end
  end

  def self.write(file, data)
    File.open(file, 'w') do |f|
      f.puts JSON.pretty_generate(data)
    end
  end

  def self.add(file, obj)
    data = read(file) || []

    data << obj

    write(file, data)
  end
end
