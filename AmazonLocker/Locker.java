package AmazonLocker;
import java.util.Map;
import java.util.HashMap;


class Locker{
    private Compartment[] compartments;
    private Map<Integer,AccessToken> codes;
    private Map<Integer, Compartment> compartmentById;
    // Map<String,Queue<Compartments>> compartments;

    public Locker(int size){
        this.compartments = new Compartment[size];
        this.codes = new HashMap<>();
        this.compartmentById = new HashMap<>();
        for(int i = 0; i < size; i++){
            compartments[i] = new Compartment("SMALL", i);
            compartmentById.put(i,compartments[i]);
        }
    }

//in case requirement changes to if small not available medium fine etc, then what we cn do is 
//we can pass a list of acceptable sizes to nextAvailableCompartment and the go through compartments and 
//return the first size that fits.
    private Compartment nextAvailableCompartment(String size){
        for(Compartment compartment:compartments){
            if(compartment.getStatus() == Compartment.Status.RESERVED && compartment.isReservationExpired(System.currentTimeMillis())){
                compartment.markAvailable();
            }
            if(compartment.getStatus() == Compartment.Status.AVAILABLE && compartment.getSize().equals(size))return compartment;
        }
        return null;
    }

//we should make this a synchronized method to avoid race condition. But synchronized on a method 
//locks the entire locker object.Pickups via get package will also be blocked while deposit is in progress.
    public int reserveCompartment(String size){
        Compartment compartment = nextAvailableCompartment(size);
        if(compartment == null){
            throw new NoSpaceException();
        }
        return compartment.markReserved(System.currentTimeMillis());

    }

    public void confirmDeposit(int id){
        if(compartment.getStatus() != Compartment.Status.RESERVED){
            throw new IllegalStateException("Compartment not resreved");
        }
        if(compartment.isReservationExpired(System.currentTimeMillis())){
            compartment.markAvailable();
            throw new ReservationExpiredException();
        }
        Compartment compartment = compartmentById.get(id);
        AccessToken accessToken = new AccessToken(compartment, System.currentTimeMillis());
        codes.put(accessToken.getCode(), accessToken);
        compartment.markOccupied();

    }

    public boolean getPackage(int code){
        if(!codes.containsKey(code))return false;
        AccessToken accessToken= codes.get(code);
        if(!accessToken.isValid(System.currentTimeMillis()))return false;
        Compartment compartment = accessToken.getCompartment();
        compartment.open();
        compartment.markAvailable();
        accessToken.markUsed();
        codes.remove(code);
        return true;
        
    }
}