import cats.effect.*
import cats.syntax.all.*
import com.comcast.ip4s.*
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDslBinCompat
import org.http4s.implicits.*
import org.http4s.ember.server.*
import org.http4s.server.Router
import org.http4s.server.staticcontent.WebjarServiceBuilder
import org.http4s.server.staticcontent.*

class BBIO[EF[_]: Async] {
  val dslasync: Http4sDslBinCompat[EF] = new Http4sDslBinCompat[EF] {
    //
  }
  import dslasync._

  def isJsAsset(asset: WebjarServiceBuilder.WebjarAsset): Boolean = true

  val webjars: HttpRoutes[EF] = webjarServiceBuilder[EF].withWebjarAssetFilter(isJsAsset).toRoutes

  val webjars2 = Router("resources" -> webjars)

  val helloWorldService: HttpRoutes[EF] = HttpRoutes.of[EF] { case GET -> Root / "hello" / name =>
    Ok.apply(s"Hello, $name.")
  }

  val routes: HttpRoutes[EF] = helloWorldService <+> webjars2

  def runAction(args: List[String]): EF[ExitCode] = EmberServerBuilder
    .default[EF]
    .withHost(ipv4"0.0.0.0")
    .withPort(port"8080")
    .withHttpApp(routes.orNotFound)
    .build
    .use(_ => Sync[EF].never)
    .as(ExitCode.Success)

}
