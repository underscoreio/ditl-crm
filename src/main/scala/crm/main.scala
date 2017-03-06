package crm

import fs2.Task
import org.http4s.server.{Server, ServerApp}
import org.http4s.server.blaze.BlazeBuilder
import scala.concurrent.duration._

object BlazeServer extends ServerApp {
  override def server(args: List[String]): Task[Server] = {
    val db = DoobieDatabase.createFreshDatabase.unsafeRunFor(5.seconds)
    BlazeBuilder
      .bindHttp(8080, "0.0.0.0")
      .mountService(new CustomerAPI(db).service, "/")
      .start
  }
}
