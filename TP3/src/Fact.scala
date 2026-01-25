import scala.annotation.tailrec

class Fact {
  // @tailrec
  def _fact(n: BigInt, soFar: BigInt): BigInt = {
    if (n <= 1) soFar
    else _fact(n - 1, n * soFar)
  }
}


object FunctionalFactorial {

  // fact3 est une valeur de type fonction (BigInt => BigInt)
  val fact3: BigInt => BigInt = (n: BigInt) => {

    // _fact est aussi une valeur de type fonction.
    // Elle est récursive, elle doit donc être déclarée 'lazy' pour
    // éviter un problème d'initialisation (elle s'appellerait elle-même
    // avant d'être complètement définie).
    lazy val _fact: (BigInt, BigInt) => BigInt = (currentN, accumulator) => {
      if (currentN <= 1) accumulator
      else _fact(currentN - 1, accumulator * currentN)
    }

    _fact(n, 1)
  }

  // La structure externe est une fonction, mais l'implémentation interne
  // utilise une méthode pour bénéficier de l'optimisation.
  val fact3_fixed: BigInt => BigInt = (n: BigInt) => {
    @tailrec
    def _fact(currentN: BigInt, accumulator: BigInt): BigInt = {
      if (currentN <= 1) accumulator
      else _fact(currentN - 1, accumulator * currentN)
    }

    _fact(n, 1)
  }
}