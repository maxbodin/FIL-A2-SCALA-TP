import FermeturesEclair.{adjustToPair, largest, largestAt, values}
import NouvellesStructures.unless
import Range.{printRange, stringify}

import scala.annotation.tailrec

@main
def main(): Unit = {
  println("Hello Fact!")

  val result = Factorial.fact2(100)
  println(result)
  println(result.toString.length)

  println(FunctionalFactorial.fact3_fixed(20000).toString.length)


  val range = 1 to 10
  printRange(range)   // expected : 12345678910
  stringify(range)    // expected : String = 012345678910
  println()

  println(values(x => x * x, -2, 2)) // expected : scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((-2,4), (-1,1), (0,0), (1,1), (2,4))

  println(largest(x => 10 * x - x * x, 1 to 10)) // expected : Int = 25

  println(largestAt(x => 10 * x - x * x, 1 to 10)) // expected : Int = 5

  val pairs = (1 to 5) zip (6 to 10)
  val sumPair = adjustToPair((a, b) => a + b)
  val sums = pairs.map(sumPair)
  println(sums) // expected : Seq[Int] = Vector(7, 9, 11, 13, 15)

  // val sumPair = ((a: Int, b: Int) => a + b).tupled

  var i = 0
  val a = Array(1, 2, 3)
  unless(i >= a.length) {     // La condition 'i >= a.length' est passée par nom.
    a(i) = a(i) + 1           // Le bloc est aussi passé par nom.
    i = i + 1
  }
}

object Factorial:
  def fact(n: BigInt): BigInt =
    println("calling fac("+n+")")
    if n <= 1 then 1
    else n * fact(n-1)

  def fact2(n: BigInt): BigInt = {
    @tailrec
    def _fact(n: BigInt, accumulator: BigInt): BigInt = {
      if (n <= 1) accumulator
      else _fact(n - 1, accumulator * n)
    }

    _fact(n, 1)
  }

  def factRevisite(n: Int): BigInt = {
    if n == 0 then 1
    else (1 to n).reduce(_*_)
  }


object Range:
  def printRange(r: Range): Unit = r.foreach(print)
  def stringify(r: Range): String = r.map(_.toString).reduce(_+_)


object FermeturesEclair:
  def values(f: (Int) => Int, low: Int, high: Int): IndexedSeq[(Int, Int)] = {
    val inputs = low to high
    val outputs = inputs.map(f)
    inputs.zip(outputs)
  }

//  def largest(f: (Int) => Int, inputs: Seq[Int]): Int = {
//    val outputs = inputs.map(f)
//    outputs.max
//  }

  def largest(f: Int => Int, inputs: Seq[Int]): Int = inputs.map(f).max

  def largestReduce(f: Int => Int, inputs: Seq[Int]): Int = inputs.map(f).reduce((a, b) => if (a > b) a else b)
  // ou avec Math.max : inputs.map(f).reduce(Math.max)

  def largestAt(f: Int => Int, inputs: Seq[Int]): Int = {
    inputs.zip(inputs.map(f)) // 1. Crée les paires (input, output)
      .reduce((pair1, pair2) => if (pair1._2 > pair2._2) pair1 else pair2) // 2. Trouve la meilleure paire
      ._1 // 3. Extrait l'input de cette paire
  }

  def adjustToPair(f: (Int, Int) => Int): ((Int, Int)) => Int = {
    (pair: (Int, Int)) => f(pair._1, pair._2)
  }


object NouvellesStructures:
  def unless(condition: => Boolean)(block: => Unit): Unit = {
    while (!condition) {
      block
    }
  }