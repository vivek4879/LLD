package Elevator;

public class ElevatorController{
    List<Elevators> elevators;

    public ElevatorController(){
        elevators = new ArrayList<>();
    }

    public step(){
        for(Elevator e: elevators){
            e.step();
        }
    }

    public boolean requestElevator(int floor, RequestType type: PICKUP_UP | PICKUP_DOWN){
        
        """
        core logic:
        1. find the best elevator to handle this request
        2. send that request to the elevator
        """

        if(floor <0 || floor >9){
            throw Error ('Not a valid floor');
        }
        
        Request request = new Requset(floor, type);
        Elevator best = selectBestElevator(request);
        return best.addRequest(request);
    }

    private Elevator selectBestElevator(Request request){
        //here we could use strategy pattern and have differnet strategies implemented to select the best elevator
        //but we will direclty implement one for now.
        """
        Core logic:
        1. try to find elevator moving towards us and going in the right direction
        2. if none, try idel elevators(nearest)
        3. pick nearest
        """

        Elevator best = findMovingTowardsUs(Request request);
        if(best != null)return best;

        Elevator best = findNearestIdel(request.getFloor());
        if(best != null)return best;

        Elevator best = findNearest(requset.getFloor());
        if(best != null)return best;
    }

    private Elevator findMovingTowardsUs(Request request){
        """
        Core logic:
        1. scan elevators moving in that direction
        2. keep track of closest
        3. return closest
        """

        floor = request.getFloor();
        direction = request.getType() == PICKUP_UP? UP:DOWN;

        nearest = null;
        minDistance = MAX_VALUE

        for(elevator e:elevators){
            if(e.direction != direction)continue;

            if(e.direction == UP && e.floor > floor || e.direction == DOWN && e.floor < floor)continue;

            distance = Math.abs(e.floor - floor);

            if(distance < minDistance){
                minDistance = distance;
                nearest = e;
            }
            return nearest;
        }
    }
}