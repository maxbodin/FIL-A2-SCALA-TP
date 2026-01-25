class TracedFact extends Fact {
  override def _fact(n: BigInt, soFar: BigInt): BigInt = {
    println(s"CALL _fact($n, $soFar)")
    val r = super._fact(n, soFar)
    println(s"RETURN $r")
    r
  }
}

object TracedFact extends App :
  (new TracedFact)._fact(4,1)