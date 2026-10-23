// BookingSystem
// <!-- "search for movies" / "browse movies at a theater"
// "book seats" / "cancel reservation" -->


// List<TheaterId> Theaters



// getTheaters() -> List<Theater>
// searchMovies(title: string) -> List<ScreenTime>
// getShowTimesAtTheater(Theater) -> List<ScreenTime>
// bookSeats(showTimeId:string, seatIds List<String>) -> Reservation
// cancelResrevation( Reservation: String) -> boolean

class BookingSystem{
    private final List<String> theaters;
    private final Map<String,Movie> moviesById; //mapping each movie to a movie object
    private final Map<String,List<ScreenTime>> showTimesbyMovieId;

    BookingSystem(Theaters){
        this.theaters = theaters;
        this.moviesById = new HashMap<>();
        this.showTimesbyMovieId = new HashMap<>();

        for(Theater theater:theaters){
            List<ScreenTime> curScreens = theater.getShowTimesAtTheater(theater);
            for(curScreen:curScreens){
                movie = curScreen.getMovie();
                moviesById.put(movie.getId(),movie);
                showTimesbyMovieId,computeIfAbsent(movie.getId(), h-> new ArrayList<>()).add(curScreen);
            }
        }
    }
    public List<String> getTheaters(){
        return new ArrayList<>(theaters);
    }

    public List<ScreenTime> searchMovies(String title){
        res = new ArrayList<>();
        Stirng smallTitle = title.toLower();
        for(movie: moviesById.values()){
            if(movie.getTitle().toLower().contains(smallTitle)){
                for(showTime: showTimesbyMovieId.get(movie.getId())){
                    if(showTime > curTime){
                        res.add(showTime);
                    }
                }
            }
        }
        return res;
    }

    public boolean book(showTimeId, List<SeatId> seats){
        //input vlaidation -> throw

        newReservation = new reservation(
            showTimeId,
            generateReservationId(),
            seats,
            
        )
        showTime.book(newReservation)
        reservationById.put(newReservation.getId(), newReservation)
        return reservation
    }

    public cancelResrevation(String reservationId){
        //input validaoitn
        Reservation reservation = reservationById.get(reservationId);
        showTIme showTIme = reservation.getShowTImeID();
        showTime.cancel(reservation)
        reservationById.remove(reservationById)
    }


}