package crm

case class Customer(
  name  : String,
  phone : String,
  id    : Option[Int] = None
)

