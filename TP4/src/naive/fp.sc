sealed trait Direction
case object North extends Direction
case object East extends Direction
case object South extends Direction
case object West extends Direction

object Direction:
  def invert(d: Direction) = d match
    case North => South
    case East => West
    case South => North
    case West => East
