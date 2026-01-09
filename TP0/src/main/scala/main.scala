@main
def main(): Unit = {
  println("Hello world!")
}

object Hello extends App {
  println("Hello from Hello object!")
}

object HelloPlus :
  def main(args: Array[String]): Unit =
    println(s"Hello ${args(0)} from HelloPlus object!")