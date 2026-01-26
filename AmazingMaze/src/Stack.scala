import scala.collection.mutable.ListBuffer

class Stack[A]:
  private val buffer = new ListBuffer[A]

  def push(item: A): Unit = buffer.append(item)

  def pop(): A = buffer.remove(buffer.size - 1)

  def isEmpty: Boolean = buffer.isEmpty