package controllers

import db.TicketsRepository
import models.Booking
import play.api.mvc._

object Tickets extends Controller {

  def book(eventId: Int, count: Int) = Action {
    val bookings: Seq[Booking] =
      TicketsRepository.bookForEvent(eventId, count)

    Ok(s"Thank you! These are your bookings for event $eventId" +
      bookings.mkString("\n"))
  }
}
