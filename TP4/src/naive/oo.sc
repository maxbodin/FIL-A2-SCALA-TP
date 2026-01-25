sealed trait Direction:
  def invert: Direction
case object North extends Direction:
  def invert = South
case object East extends Direction:
  def invert = West
case object South extends Direction:
  def invert = North
case object West extends Direction:
  def invert = East