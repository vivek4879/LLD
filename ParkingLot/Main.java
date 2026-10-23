import java.util.*;

enum VehicleType {CAR, BIKE, TRUCK}
enum SpotType {CAR, BIKE, TRUCK}

interface PricingStrategy{
    double calculateFee(Ticket, xitTime )
}

class ParkingLot{
    private final List<Spot> spots;
    private final Set<String> takenSpots;
    private final long hourlyRateCents;
    private final Map<String, Ticket> activeTickets;

    ParkingLot(List<Spots> spots, long hourlyRateCents){
        if(spots == null || spots.isEmpty()){
            throw new IllegalArgumentException("");
        }
        if(hourlyRateCents <= 0){
            throw new IllegalArgumentException("");
        }
        //adding a defensive copy so that the caller cannot later modify the list from outside.
        //we can add methods in our clas to modify this if need be.
        this.spots = new ArrayList<>(spots);;
        this.hourlyRateCents = hourlyRateCents;
        this.activeTickets = new HashMap<>();
        this.takenSpots = new HashSet<>();
    }

    public Ticket enter(VehicleType type){
        Spot availableSpot = findAvailableSpot(type);
        if(availableSpot == null) {
            throw new RuntimeException("No Available spot for vehicle type " + type);
        }
        takenSpots.add(availableSpot.getId());
        String ticketId = //will create randomStirng
        long currentTime =  //will get current time from system
        Ticket newTicket = new Ticket(ticketId,availableSpot.getId(), currentTime, type);
        activeTickets.put(ticketId, newTicket);
        return newTicket;
    }

    private Spot findAvailableSpot(VehicleType type){
        SpotType spotType = mapVehicleTypeToSpotType(type);
        for(Spot spot : spots){
            if(!takenSpots.contains(spot.getId()) &&  spot.getSpotType() == spotType){
                return spot;
            }
        }
        return null;
    }

    private SpotType mapVehicleTypeToSpotType(VehicleType type){
        if(type == VehicleType.CAR)return SpotType.CAR;
        else if(type == VehicleType.BIKE)return SpotType.BIKE;
        else if(type == VehicleType.TRUCK)return SpotType.TRUCK;
        throw new RuntimeException("Unknown vehicle type");
    }
    public long exit(String ticketId){
        if(ticketId == null || ticketId.isEmpty()){
            Throw new RuntimeException("");
        }
        Ticket ticket = activeTickets.get(ticketId);
        if(ticket == null){
            throw new IllegalArgumentException("Invallid Ticket");
        }
        String spotId = ticket.getSpotId();
        long exitTime = //take curent time from system
        activeTickets.remove(ticketId);
        takenSpots.remove(spotId);
        long totlCost = calculateFee(ticket.getInTime(), exitTime);
        return totalCost;
    }

    private long computeFee(long inTime, long outTime){
        long durationMillis = outTime - inTime;
        long durationHours = durationMillis / (1000 * 60 * 60);

        if(durationMillis % (1000 * 60 * 60) != 0){
            durationHours++;
        }
        return durationHours * hourlyRateCents;
    } 
}

