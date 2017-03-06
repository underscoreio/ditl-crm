package presentation

/*
 * This is building up an example JSON output function using a type-class.
 * It's the example from the presentation.
 */
object JsonTypeClassExample {

  trait JsonFormat[T] {
    def format(value: T): String
  }

  implicit val intFormat = new JsonFormat[Int] {
    def format(value: Int): String = value.toString
  }

  implicit val strFormat = new JsonFormat[String] {
    def format(value: String): String =
      "\"" + value + "\""
  }

  def outputJson[T](value: T)(implicit jf: JsonFormat[T]) =
    jf.format(value)

  implicit def listFormat[T](implicit jf: JsonFormat[T]) =
    new JsonFormat[List[T]] {
      def format(values: List[T]): String = {
        val formattedValues = for {
           v <- values
         } yield jf.format(v)
       "[" + formattedValues.mkString(",") + "]"
      }
  }
}
