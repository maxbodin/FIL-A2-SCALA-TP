//case class Rational(n_in: Int, d_in: Int) {
//  require(d_in != 0, "Denominator cannot be zero")
//
//  var n: Int = n_in
//  private var _d: Int = d_in
//  def d: Int = _d
//  def d_=(newValue: Int): Unit = {
//    require(newValue != 0, "Denominator cannot be zero")
//    _d = newValue
//  }
//
//  def this(n: Int) = this(n, 1)
//
//  def +(that: Rational): Rational = {
//    new Rational(
//      n * that.d + that.n * d,
//      d * that.d
//    )
//  }
//
//  override def toString: String = s"$n/$d"
//}

case class Rational(n: Int, d: Int) {
  require(d != 0, "Denominator cannot be zero")

  def this(n: Int) = this(n, 1)

  def +(that: Rational): Rational =
    Rational(n * that.d + that.n * d, d * that.d)

  def *(that: Rational): Rational =
    Rational(n * that.n, d * that.d)

  override def toString: String = {
    // La simplification se fait maintenant via la méthode 'apply' du compagnon
    val simplified = Rational.simplify(this)
    if (simplified.d == 1) s"${simplified.n}"
    else s"${simplified.n}/${simplified.d}"
  }
}

// Objet compagnon pour les static et factories
object Rational {

  // Constantes utiles
  val ZERO = new Rational(0, 1)
  val ONE = new Rational(1, 1)

  def apply(n: Int, d: Int): Rational = {
    require(d != 0, "Denominator cannot be zero")

    def gcd(a: Int, b: Int): Int = if (b == 0) a.abs else gcd(b, a % b)

    val g = gcd(n, d)
    val sign = if (d < 0) -1 else 1

    new Rational(sign * n / g, sign * d / g)
  }

  def apply(n: Int): Rational = apply(n, 1)

  def simplify(r: Rational): Rational = apply(r.n, r.d)
}