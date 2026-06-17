package AmazonLocker;


public class Compartment{
    public enum Status{
        AVAILABLE,
        RESERVED,
        OCCUPIED
    }

    private final int compId;
    private Status status;
    private long  reservationExpiry;
    private final String size;



    public int getCompId(){
        return compId;
    }

    public Status getStatus(){
        return status;
    }

    public String getSize(){
        return size;
    }

    public boolean isReservationExpired(long curTime){
        return reservationExpiry < curTime;
    }


    public Compartment(String size, int id){
        this.compId = id;
        this.status = Status.AVAILABLE;
        this.size = size;
    }

    public void markOccupied(){
        this.status = Status.OCCUPIED;
    }

    public int markReserved(long curTime){
        this.status = Status.RESERVED;
        this.reservationExpiry = curTime + 10;
        return this.compId;
    }

    public void markAvailable(){
        this.status = Status.AVAILABLE;
        this.reservationExpiry = 0;
    }

    public boolean isAvailable(){
        return status == Status.AVAILABLE;
    }

    public void open(){

    }
}

