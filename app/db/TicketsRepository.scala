package db

import models.Booking
import anorm._
import play.api.db.DB


object TicketsRepository {

  def bookForEvent(eventId: Int, count: Int): Seq[Booking] = {
    import play.api.Play.current

    DB.withTransaction { implicit c =>

      val numAvailableTickets: Int = SQL(
        "SELECT num_free FROM tickets WHERE event_id={eventId}").
        on("eventId" -> eventId).
        as(SqlParser.int("num_free").single)

      val bookings = (0 to count).map { _ =>
        val bookingId: Option[Long] = SQL(
          "INSERT INTO bookings(event_id)" +
              " VALUES({eventId})").on('eventId -> eventId).executeInsert()

        Booking(bookingId, eventId)
      }

      bookings
    }

  }
}
