import akka.actor.ActorSystem
import akka.stream.scaladsl2._
import db.TicketsRepository
import akka.stream.scaladsl2.FlowGraphImplicits._

class StreamsClient {

  val in = ThunkTap[NewBookingCommand] { () =>
    Some(NewBookingCommand(1, 2))
  }

  val bookingFlow =
    Flow[NewBookingCommand].map { command =>
      val bookings = TicketsRepository.bookForEvent(command.eventId, command.count)
      BookingSucceeded(command.eventId, bookings)
    }

  val out = ForeachDrain[BookingSucceeded] {
    case BookingSucceeded(eventId, bookings) =>
      println(s"Thank you! These are your bookings for event $eventId" +
          bookings.mkString("\n"))
  }

  implicit val actorSystem = ActorSystem()
  implicit val materializer = FlowMaterializer()

  FlowGraph { implicit b =>
    in ~> bookingFlow ~> out
  }.run()
}


object StreamsMain extends App {
  new StreamsClient
}