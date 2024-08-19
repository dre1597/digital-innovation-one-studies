# frozen_string_literal: true

require_relative "cpf_utils/version"

module CpfUtils
  class Error < StandardError; end

  def self.format(cpf)
    cpf.gsub(/\D/, "").gsub(/(\d{3})(\d{3})(\d{3})(\d{2})/, '\1.\2.\3-\4')
  end

  def self.valid?(cpf)
    cpf = cpf.to_s.scan(/\d/).map(&:to_i)

    return false unless cpf.length == 11
    return false if cpf.uniq.length == 1

    sum1 = (0..8).inject(0) { |sum, i| sum + cpf[i] * (10 - i) }
    sum2 = (0..9).inject(0) { |sum, i| sum + cpf[i] * (11 - i) }

    dv1 = sum1 % 11
    dv1 = dv1 < 2 ? 0 : 11 - dv1

    dv2 = sum2 % 11
    dv2 = dv2 < 2 ? 0 : 11 - dv2

    (dv1 == cpf[9]) && (dv2 == cpf[10])
  end
end
