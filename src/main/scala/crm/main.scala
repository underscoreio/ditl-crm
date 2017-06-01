package crm

import fs2.{Stream, Task}
import org.http4s.server.Server
import org.http4s.util.StreamApp
import org.http4s.server.blaze.BlazeBuilder
import scala.concurrent.duration._

object BlazeServer extends StreamApp {
  override def stream(args: List[String]): Stream[Task, Nothing] = {
    val db = DoobieDatabase.createFreshDatabase.unsafeRunFor(5.seconds)
    BlazeBuilder
      .bindHttp(8080, "0.0.0.0")
      .mountService(new CustomerAPI(db).service, "/")
      .serve
  }
}
