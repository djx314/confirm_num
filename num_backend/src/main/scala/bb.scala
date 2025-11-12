import cats.effect.*
import cats.syntax.all.*
import com.comcast.ip4s.*
import org.http4s.HttpRoutes
import org.http4s.dsl.io.*
import org.http4s.implicits.*
import org.http4s.ember.server.*
import org.http4s.server.Router
import org.http4s.server.staticcontent.WebjarServiceBuilder
import org.http4s.server.staticcontent.*

object Main extends IOApp {

  def isJsAsset(asset: WebjarServiceBuilder.WebjarAsset): Boolean = true

  val webjars: HttpRoutes[IO] = webjarServiceBuilder[IO].withWebjarAssetFilter(isJsAsset).toRoutes

  val webjars2 = Router("resources" -> webjars)

  val helloWorldService: HttpRoutes[IO] = HttpRoutes.of[IO] { case GET -> Root / "hello" / name =>
    Ok(s"Hello, $name.")
  }

  val routes: HttpRoutes[IO] = helloWorldService <+> webjars2

  override def run(args: List[String]): IO[ExitCode] = EmberServerBuilder
    .default[IO]
    .withHost(ipv4"0.0.0.0")
    .withPort(port"8080")
    .withHttpApp(routes.orNotFound)
    .build
    .use(_ => IO.never)
    .as(ExitCode.Success)

}
