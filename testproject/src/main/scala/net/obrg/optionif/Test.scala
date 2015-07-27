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
    val o: Option[Int] = y
    val o2 = y.map(_ * 3)
    val asdf = None.map(x => "nothing")
    val asdf2 = None.map(x => 3)
    val asdf3: Option[Int] = None
    asdf3.map(x => 3)
    val asdf4: Option[_] = None
    asdf4.map(x => 3)
    val noneval = None
    noneval.map(x => "noneval")
    val foo = if (o.isEmpty) None else Some(o.get * 3)
    // Inferred Option.map
    val z = List(Some(1), None, None, Some(4)).map(_.map(_ * 2))
    // Transitive inferred Option map
    val zsum = z.flatMap(_.map(_ * -1)).sum
    println(x.sum + y.sum + zsum)
  }
}
