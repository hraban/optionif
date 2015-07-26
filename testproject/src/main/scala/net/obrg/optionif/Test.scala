package net.obrg.optionif

/**
 * Simple test program that should trigger the DivByZero plugin, if loaded
 */
object Test {
  def main(args: Array[String]) {
    // Not an Option.map
    val x = List(1, 2, 3, 4).map(_ * 2)
    // Option.map
    val y = Some(4).map(_ * 2)
    // Inferred Option.map
    val z = List(Some(1), None, None, Some(4)).map(_.map(_ * 2))
    // Transitive inferred Option map
    val zsum = z.flatMap(x => x).sum
    println(x.sum + y.sum + zsum)
  }
}
