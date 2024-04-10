# frozen_string_literal: true

module DecoratePrint
  def print(text)
    decoration = '#' * 30
    puts decoration
    puts text
    puts decoration
  end
end

module Legs
  include DecoratePrint

  def frontal_kick
    print 'Frontal Kick'
  end

  def side_kick
    print 'Side Kick'
  end
end

module Arms
  include DecoratePrint

  def punch
    print 'Punch'
  end
end

class RobotX
  include Legs
  include Arms
end

class RobotY
  include Legs
end

x = RobotX.new
x.frontal_kick
x.punch
y = RobotY.new
y.side_kick
