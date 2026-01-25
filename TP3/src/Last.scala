
class Last {
  def lastRecursive[A](list: List[A]): A = {
    if (list.isEmpty) throw new NoSuchElementException("last of empty list")
    if (list.tail.isEmpty) list.head  // Si la queue est vide, le premier élément est le last.
    else lastRecursive(list.tail)     // Si la queue n'est pas vide on récursive sur la suite de la liste.
  }

  def lastPattern[A](list: List[A]): A = list match {
    case Nil => throw new NoSuchElementException("last of empty list")
    case x :: Nil => x
    case x :: tail =>  lastPattern(tail)
  }

  def lastList[A](list: List[A]): A = list.last
}
