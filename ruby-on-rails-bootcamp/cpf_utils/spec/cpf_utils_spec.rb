# frozen_string_literal: true

RSpec.describe CpfUtils do
  it "has a version number" do
    expect(CpfUtils::VERSION).not_to be nil
  end

  it "should format a valid cpf" do
    expect(CpfUtils.format("111.111.111-11")).to eq("111.111.111-11")
    expect(CpfUtils.format("111111.111-11")).to eq("111.111.111-11")
    expect(CpfUtils.format("11111111111")).to eq("111.111.111-11")
  end

  it "should validate a valid cpf" do
    expect(CpfUtils.valid?("471.590.910-35")).to eq(true)
    expect(CpfUtils.valid?("471590.910-35")).to eq(true)
    expect(CpfUtils.valid?("47159091035")).to eq(true)
    expect(CpfUtils.valid?("111.111.111-11")).to eq(false)
    expect(CpfUtils.valid?("111111.111-11")).to eq(false)
    expect(CpfUtils.valid?("11111111111")).to eq(false)
  end
end
