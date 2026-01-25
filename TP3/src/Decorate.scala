class Decorate {
  def decorate(left: String, s: String, right: String): String = left + s + right

//  def decorateQ(s: String): String = decorate("\"", s, "\"")
//  val decorateQ: String => String = (s: String) => decorate("\"", s, "\"")
//  val decorateQ: String => String = s => decorate("\"", s, "\"")
//  val decorateQ = decorate("\"", _: String, "\"")
//  val decorateQ: String => String = decorate("\"", _, "\"")


  def decorateC(s: String)(using decorator: String): String = decorator + s + decorator

//  given String = "\""
//  decorateC("foo")  // res: String = "foo"
//
//  given String = "'"
//  decorateC("foo") // res: String = 'foo'

  def decorateC(s: String)(using decorators: (String, String)): String = {
    val (left, right) = decorators
    left + s + right
  }

//  given (String, String) = ("(", ")")
//  decorateC("foo") // res: String = (foo)
}
