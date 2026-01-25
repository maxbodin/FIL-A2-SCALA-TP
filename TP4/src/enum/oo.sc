enum Direction:
  case North, East, South, West

  def invert = this match
    case North => South
    case East => West
    case South => North
    case West => East


  enum Direction(val degrees: Int):
    case North extends Direction(0)
    case East extends Direction(90)
    case South extends Direction(180)
    case West extends Direction(270)