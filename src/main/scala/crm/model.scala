package crm

case class Customer(
  name  : String,
  phone : String,
  id    : Option[Int] = None
)

sealed trait Subscription
object Subscriptions {
  case object Never extends Subscription
  case object Active extends Subscription
  case object Lapsed extends Subscription
  //case object Free extends Subscription
}

sealed trait Frequency
object Frequencies {
  case object Immediate extends Frequency
  case object Monthly extends Frequency
  //case object Weekly extends Frequency
  case object Never extends Frequency
}

