import Exploration.UnExplored
import Maze.{Branch, Leaf}

import scala.collection.mutable.ListBuffer

@main
def main(): Unit = {
  println("Hello world!")

  println("--- Test de la version simple de explore ---")
  val maze5: Leaf = Maze.Leaf("5")
  val maze3: Maze = Maze.Branch("3", Maze.Leaf("4"), maze5, UnExplored)
  val mainMaze: Maze = Maze.Branch("0",
    Maze.Branch("1", Maze.Leaf("2"), maze3, UnExplored),
    Maze.Branch("6", maze3,
      Maze.Branch("7", maze5, Maze.Leaf("8"), UnExplored), UnExplored),
    UnExplored)

  val mainMazeTrace: List[String] = mainMaze.explore()
  println(mainMazeTrace)
  println(mainMazeTrace == List(0, 1, 2, 3, 4, 5, 6, 3, 7, 5, 8).map(_.toString()))

  println("--- Test de la version avec ListBuffer de explore ---")
  mainMaze.reset()

  val trace: ListBuffer[String] = new ListBuffer[String]
  mainMaze.explore(trace)
  println(trace)
  println(trace.toList == List(0, 1, 2, 3, 4, 5, 6, 3, 7, 5, 8).map(_.toString()))

  println("--- Test de la version concurrente avec ListBuffer de explore ---")
  mainMaze.reset()

  val work = new Stack[Maze]()
  val concurrentTrace = new ListBuffer[String]()

  work.push(mainMaze)

  while !work.isEmpty do
    val node = work.pop()
    node.concurrentExplore(work, concurrentTrace)
    println(s"trace so far $concurrentTrace")

  println(s"Final trace: ${trace.toList}")

  println(trace.toList == List("0", "1", "2", "3", "4", "5", "6", "3", "7", "5", "8"))

}



enum Exploration:
  case Explored, PartiallyExplored, UnExplored

enum Maze:
  case Branch(label: String, left: Maze, right: Maze, var status: Exploration)

  case Leaf(label: String)

  def reset(): Unit = this match {
    case Leaf(_) =>
    case b@Branch(_, left, right, _) =>
      if (b.status == Exploration.Explored) {
        b.status = Exploration.UnExplored
        left.reset()
        right.reset()
      }
  }

  def explore(): List[String] = this match {
    case Leaf(label) => List(label)

    case b@Branch(label, left, right, _) =>
      val traceTotale = List(label)

      if (b.status == Exploration.UnExplored) {
        b.status = Exploration.Explored

        val traceGauche = left.explore()
        val traceDroite = right.explore()

        traceTotale ++ traceGauche ++ traceDroite

      } else {
        traceTotale
      }
  }

  def explore(trace: ListBuffer[String]): Unit = this match {
    case Leaf(label) => trace += label

    case b@Branch(label, left, right, _) =>
      trace += label

      if (b.status == Exploration.UnExplored) {
        b.status = Exploration.Explored

        left.explore(trace)
        right.explore(trace)
      }
  }

  def concurrentExplore(work: Stack[Maze], trace: ListBuffer[String]): Unit = this match {
    case Leaf(label) => trace += label

    case b@Branch(label, left, right, _) =>
      b.status match {
        case Exploration.UnExplored =>
          trace += label
          b.status = Exploration.PartiallyExplored
          work.push(b)
          work.push(left)

        case Exploration.PartiallyExplored =>
          b.status = Exploration.Explored
          work.push(right)

        case Exploration.Explored =>
          trace += label
      }
  }