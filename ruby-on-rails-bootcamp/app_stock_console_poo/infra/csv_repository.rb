# frozen_string_literal: true

require 'csv'

class CsvRepository
  def self.read(file)
    unless File.exist?(file)
      puts 'File not found'
      nil
    end

    begin
      CSV.read(file, headers: true)
    rescue CSV::MalformedCSVError => e
      puts "Error in file #{file}: #{e.message}"
      nil
    end
  end

  def self.write(file, data)
    CSV.open(file, 'w', headers: true) do |csv|
      csv << data.headers if data.headers
      data.each { |row| csv << row }
    end
  end

  def self.add(file, obj)
    data = read(file)

    return unless data

    data << obj
    write(file, data)
  end
end
