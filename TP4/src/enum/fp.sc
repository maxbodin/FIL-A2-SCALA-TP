import Direction.{East, North, South, West}

sealed class Direction(val ordinal: Int):
  def invert = this match
    case North => South
    case East => West
    case South => North
    case West => East

object Direction:
  val North = new Direction(1)
  val East = new Direction(2)
  val South = new Direction(3)
  val West = new Direction(4)
  def values:Array[Direction] =
    Array(North, East, South, West)
  def valueOf(s: String) = s match
    case "North" => North
    case "East" => East
    case "South" => South
    case "West" => West