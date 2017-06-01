package crm

import org.http4s._
import org.http4s.server._
import org.http4s.dsl._

import io.circe._
import io.circe.syntax._
import io.circe.generic.auto._
import org.http4s.circe._

class CustomerAPI(db: Database) {
 
  // Typeclass instances to convert between htt4s response/request decoded
  // and the Circe JSON encoder/decoder.
  implicit def circeJsonDecoder[A](implicit decoder: Decoder[A]) = jsonOf[A]
  implicit def circeJsonEncoder[A](implicit encoder: Encoder[A]) = jsonEncoderOf[A]

  val service = HttpService {

    case GET -> Root / "customers" =>
      Ok(db.list.map(_.asJson))

    case GET -> Root / "customers" / IntVar(id) =>
      db.find(id).flatMap {
        case Some(customer) => Ok(customer)
        case None           => NotFound()
      }

    case req @ POST -> Root / "customers" =>
      req.decode[Customer] { c => Ok(db.insert(c)) }

    case req @ PATCH -> Root / "customers" / IntVar(id) =>
      req.decode[CustomerPatch] { patch =>
        db.find(id).flatMap {
          case None           => NotFound()
          case Some(customer) => 
            val updated = merge(customer, patch)
            Ok(db.update(updated))
        }
      }
  }

  val merge = bulletin.AutoMerge[Customer, CustomerPatch]

  case class CustomerPatch(
    name  : Option[String],
    phone : Option[String]
  )
}
