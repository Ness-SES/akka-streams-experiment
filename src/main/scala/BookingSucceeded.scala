import models.Booking

case class BookingSucceeded(
    eventId: Int,
    bookings: Seq[Booking])