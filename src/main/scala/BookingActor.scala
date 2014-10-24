import akka.actor.Actor
import db.TicketsRepository
import models.Booking

class BookingActor extends Actor {
  override def receive = {
    case NewBookingCommand(eventId, count) =>
      val bookings: Seq[Booking] =
        TicketsRepository.bookForEvent(eventId, count)
      sender ! BookingSucceeded(eventId, bookings)
  }
}
