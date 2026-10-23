"Design a movie ticket booking system similar to BookMyShow that allows users to browse movies, select theaters and showtimes, book tickets, and manage reservations."

Requirements:
1. Users can search for movies by title
2. Users can browse movies playing at a given theater
3. Theaters have multiple screens; all screens share the same seat layout (rows A-Z, seats 0-20)
4. Users can view available seats for a showtime and select specific ones
5. Users can book multiple seats in a single reservation; booking returns a confirmation ID
6. Concurrent booking of the same seat: exactly one succeeds
7. Users can cancel a reservation by confirmation ID, releasing the seats

Out of Scope:
- Payment processing (assume payment always succeeds)
- Variable seat layouts or seat types (all seats identical)
- Rescheduling (cancel and rebook instead)
- UI / rendering



BookingSystem
<!-- "search for movies" / "browse movies at a theater"
"book seats" / "cancel reservation" -->


List<TheaterId> Theaters



getTheaters() -> List<Theater>
searchMovies(title: string) -> List<ScreenTime>
getShowTimesAtTheater(Theater) -> List<ScreenTime>
bookSeats(showTimeId:string, seatIds List<String>) -> Reservation
cancelResrevation( Reservation: String) -> boolean





Theater
<!-- "browse movies at a theater"
"Theaters have multiple screens" -->
String TheaterId
String name
List<ScreenTime> screenList


Theater()
getShowTime() -> List<ShowTime>
getScreenTimeForMovie(Movie) -> List<ScreenTime>







ScreenTime
<!-- "view available seats for a showtime"
"book multiple seats in a single reservation"
"cancel a reservation, releasing the seats" -->

String  screenId
DateTime DateTime
String movieName
Theater theater
List<reservations> reservations

getAvailablSeats -> List<SeatIds>
isSeatAvailable(SeatId) -> boolean
makeReservation(Reservation) -> Reservation
cancelReservation(Reeservation) -> boolean




Reservation
String reservationId
List<String> seatIds
String screenId
DateTime DateTime
String movieName
String Theater

Reservation(String confirmationID, List<String> seatIds, String screenId, DateTime, MovieName)
getSeatIds -> List<String>
getDateTime
getScreenId
getTheater



Movie
String id
String name






