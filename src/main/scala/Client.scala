import akka.actor.{ActorRef, Actor, Props, ActorSystem}

case class ClientActor(bookingActor: ActorRef) extends Actor {

  def receive = {

    case StartCommand =>
      bookingActor ! NewBookingCommand(1, 2)

    case BookingSucceeded(eventId, bookings) =>
      println(s"Thank you! These are your bookings for event $eventId" +
          bookings.mkString("\n"))
  }
}

case object StartCommand

object Main extends App {
  val actorSystem = ActorSystem()
  val bookingActor = actorSystem.actorOf(
    Props(classOf[BookingActor]))
  val clientActor = actorSystem.actorOf(
    Props(classOf[ClientActor], bookingActor))
  clientActor ! StartCommand
}