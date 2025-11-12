package helper

import cats.effect.*
import cats.syntax.all.*
import scala.concurrent.Future

object ContextImpl {
  import scala.concurrent.ExecutionContext.Implicits.global

  val asyncFuture: Async[[x] =>> () => Future[x]] = new Async[[x] =>> () => Future[x]] {
    override def pure[A](x: A): () => scala.concurrent.Future[A] = () => Future.successful(x)

    override def handleErrorWith[A](fa: () => scala.concurrent.Future[A])(
      f: Throwable => () => scala.concurrent.Future[A]
    ): () => scala.concurrent.Future[A] = () => fa().recoverWith(ue => f(ue)())

    override def raiseError[A](e: Throwable): () => scala.concurrent.Future[A] = () => Future.failed(e)

    override def cont[K, R](
      body: cats.effect.kernel.Cont[[x] =>> () => scala.concurrent.Future[x], K, R]
    ): () => scala.concurrent.Future[R] = ???

    override def evalOn[A](fa: () => scala.concurrent.Future[A], ec: scala.concurrent.ExecutionContext): () => scala.concurrent.Future[A] =
      fa

    override def executionContext: () => scala.concurrent.Future[scala.concurrent.ExecutionContext] = () => Future.successful(implicitly)

    override def monotonic: () => scala.concurrent.Future[scala.concurrent.duration.FiniteDuration] = () => ???

    override def realTime: () => scala.concurrent.Future[scala.concurrent.duration.FiniteDuration] = ???

    override def flatMap[A, B](fa: () => scala.concurrent.Future[A])(
      f: A => () => scala.concurrent.Future[B]
    ): () => scala.concurrent.Future[B] = () => fa().flatMap(u => f(u)())

    override def tailRecM[A, B](a: A)(f: A => () => scala.concurrent.Future[Either[A, B]]): () => scala.concurrent.Future[B] = ???

    override def deferred[A]: () => scala.concurrent.Future[cats.effect.kernel.Deferred[[x] =>> () => scala.concurrent.Future[x], A]] = ???

    override def ref[A](a: A): () => scala.concurrent.Future[cats.effect.kernel.Ref[[x] =>> () => scala.concurrent.Future[x], A]] = ???

    override def cede: () => scala.concurrent.Future[Unit] = ???

    override def start[A](
      fa: () => scala.concurrent.Future[A]
    ): () => scala.concurrent.Future[cats.effect.kernel.Fiber[[x] =>> () => scala.concurrent.Future[x], Throwable, A]] = ???

    override protected def sleep(time: scala.concurrent.duration.FiniteDuration): () => scala.concurrent.Future[Unit] = ???

    override def canceled: () => scala.concurrent.Future[Unit] = ???

    override def forceR[A, B](fa: () => scala.concurrent.Future[A])(
      fb: () => scala.concurrent.Future[B]
    ): () => scala.concurrent.Future[B] = ???

    override def onCancel[A](
      fa: () => scala.concurrent.Future[A],
      fin: () => scala.concurrent.Future[Unit]
    ): () => scala.concurrent.Future[A] = ???

    override def uncancelable[A](
      body: cats.effect.kernel.Poll[[x] =>> () => scala.concurrent.Future[x]] => () => scala.concurrent.Future[A]
    ): () => scala.concurrent.Future[A] = ???

    override def suspend[A](hint: cats.effect.kernel.Sync.Type)(thunk: => A): () => scala.concurrent.Future[A] = ???
  }
}
