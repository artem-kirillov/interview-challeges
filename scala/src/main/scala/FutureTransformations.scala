import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}

object FutureTransformations extends App {
  implicit val ec: ExecutionContextExecutor = ExecutionContext.global

  val talk: Seq[Future[String]] = Seq(
    Future.successful("я"),
    Future.failed(new RuntimeException(".")),
    Future.successful("немного"),
    Future.failed(new RuntimeException("src/test")),
    Future.successful("заикаюсь"),
    Future.failed(new RuntimeException("..."))
  )

  // Future[(Seq[String], Seq[Throwable])]

  val res: Future[(Seq[String], Seq[Throwable])] = Future.
    sequence(talk.map { f => f.map(Success(_)).recover { case e: Throwable => Failure(e) } }).
    map(seq => seq.partition(_.isSuccess) match {
      case (first: Seq[Success[String]], second: Seq[Failure[String]]) => first.map(_.get) -> second.map(_.exception) })
  val done = Await.result(res, 1 second)
}
