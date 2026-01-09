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

  override def toString: String = s"$n/$d"
}