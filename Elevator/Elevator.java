package Elevator;

public class Elevator{


    public void step(){
        """
        Core Logic:
        1. If idle with requests, pick direction toward the nearest request.
        2. Check if we should stop at the current floor(matches a hall call or a destination).
        3. If no requests ahead of us (and other requests pending) reverse.
        4. Move one floor in current direction.

        Edge Cases:
        1. no requests -> set IDLE and return
        2. Stop and move and reverse and move cant happen in same tick .
        """

        // case 1:nothing to do
        if(requests.isEmpty()){
            direction = IDLE;
            return;
        }

        //case 2: Pick direction if idle
        if (direction == IDLE){
            Request nearest = findNearestRequest();
            Direction direction = nearest.getFloor() > elevator.floor? UP:DOWN;
        }

        //Case 3: Stop at the current floor?
        type = direction== UP? PICKUP_UP:PICKUP_DOWN;
        hallCallRequest = Request(floor, type);
        destinationRequest = Request(floor,destination);


        if(requests.contains(destinationRequest) || requests.contains(hallCallRequest)){
            requests.remove(destinationRequest);
            requests.remove(hallCallRequest);
            openDoors();
            return;
        }

        //Case 4: reverse if nothing ahead;
        if (!hasRequestAhead(direction)){
            direction = directoin == UP? DOWN:UP;
        }

        //Case 5: Move

        if( directoin == UP){
            floor++;
        }
        else if(direction == DOWN){
            floor--;
        }
    }

    private Request findNearestRequest(int floor){
        """
        Core logic:
        1. Scan all requests, find closest by floor distance

        Edge cases:
        - ties broken arbitrarily
        """
        if(floor < 0 || floor > 9){
            throw Error('Invalid floor number');
        }
        int minDistance = MAX_VALUE;
        nearest = null;
        for(Request request:requests){
            int curDist = Math.abs(request.getFloor() - floor);
            if(curDist<minDistance){
                minDist = curDist;
                nearest = request;
            }
        }
        return nearest;
    }

    private boolean hasRequestAhead(Direction direction){
        """
        Core logic:
        1. Check if any request exist ahead in the given direction
        2. Regardless fo type, we travel toward all requests, only stop for matching requests

        Edge cases:
        - None, naturally floor boundaries.
        """

        for(Request request:requests){
            if(direction == UP && request.getFloor()> currentFloor){
                return true;
            }
            if(direction == DOWN && request.getFloor() < currentFloor){
                return true;
            }

            return false;
        }
    }
}