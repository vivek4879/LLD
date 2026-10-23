"Design a parking lot system where different types of vehicles can park, and the system manages spot assignment and calculates fees upon exit."

class ParkingLot
    -List<Spot> parkingLot
    -long hourlyRate
    -Map<String,Ticket> activeTickets
    

    +parkingLot(spots, hourlyRate)
    +getSpot(carType) -> spot
    +enter(vehicleType) ->ticketId
    +exit(ticketId) -> cost

class Spot
    -String id
    -SpotType spotType

    +Spot(id,type)
    +getSpotType() -> SpotType
    +getId() ->String

class Ticket

    -String id
    -String spotId
    -int inTime
    -CarType carType

    +Ticket(id,spotId,inTime,carType)
    +getId() -> String
    +getSpotId() -> String
    +getinTime() -> inTime
    +getcarType() -> carType

enum SpotType
    Motorcycle
    car
    truck


enum CarType
    Motorcycle
    car
    truck









