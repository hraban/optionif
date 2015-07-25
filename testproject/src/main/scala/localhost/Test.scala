package localhost

/**
 * Simple test program that should trigger the DivByZero plugin, if loaded
 */
object Test {
  val five = 5
  val amount = five / 0
  def main(args: Array[String]) {
    println(amount)
  }
}
