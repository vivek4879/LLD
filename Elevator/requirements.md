"""
Design an elevator control system for a building. The system should handle multiple elevators, floor requests, and move elevators efficiently to service requests.

1. Primary capabilities
    1.1 How many elevators, how many floors? fixed or configurable?
    1.2 Hall calls just up and down or do they choose floor?
    1.3 Can passengers select multiple destination floors inside the elevator?
    1.4 What deos move efficiently mean in the problem?

2. Error Handling:
    2.1 What about invalid floor requests?
    2.2 What if somebody requests the floor they are alrady on?
3. scope boundaries
    3.1 Capacity, weight limits, door mechanics, emergency stops?
    3.2 Simulation (step/tick) or actual hardware control software?


Requirements:
1. System manages 3 elevators serving 10 floors (0-9)
2. Users can request an elevator from any floor(hall call).System decides which elevator to dispatch.
3. Once inside, users can select one or more destination floors.
4. Simulation runs in discrete time steps (a step() call advances time)
5. Elevator stops come in 2 types:
    - Hall calls: Request from a floor wiht direction (Up or Down)
    - Destination: Request from inside elevator (no direction specified)
6. System handles multiple concurrent pickup requests across floors
7. Invalid requests should be rejected (return false)
    -non existent floor numbers
8. Requests for the current floor are treated as a no op.


Entities

-ElevatorController (class)
    Elevators
    Floors

- Elevator (class, needs to maintain state and also enforce rules)
    floor
    id

-Floor (number)
-Request (class, need direction and potentially the floor)


whats the state this needs to maintain and whats the methods/functions/behaviors ir needs to be able to do?
class ElevatorController:
- elevators: List<Elevators>

+ ElevatorController()
+ requestElevator(floor, requsetType) -> boolean
+ steps() -> void


class Elevator
- CurrentfFloor: int
- direction: Direction
- requests: Set<Requests>  //here equality will bebased on the floor+RequestType

enum Direction:
    UP
    DOWN
    IDLE
+ Elevator()
+ addRequest(request) -> boolean
+ step() -> void
+ getFloor() -> int
+ getDirection() -> Direction
+ move(floor, direction) -> boolean




class Request:
 """
 1. hall call:floor + direction
 2. destination: floor
 """
- floor: int
- type: RequestType

+ Request(floor, type)
+ getFloor() -> int
+ getType() -> RequestType


enum RequestType:
    PICKUP_UP
    PICKUP_DOWN
    DESTINATION



"""


