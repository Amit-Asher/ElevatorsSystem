package Logic.Impl.Components;

import Logic.Impl.Factories.ConstructionFactory;

import java.util.HashMap;
import java.util.Map;

public class ElevatorsScheduler {
    private Map<Integer, Elevator> elevatorsMap = new HashMap<>();

    public void CreateElevators(int numberOfElevators) {
        for(int id=1; id <= numberOfElevators; id++) {
            elevatorsMap.put(id, ConstructionFactory.createNewElevator(id));
        }
    }

    public Elevator findClosestElevatorToFloor(int floor) {
        Elevator closestElevator = elevatorsMap.get(1);    // default
        int distance = closestElevator.getDistance(floor); // default

        for(Elevator elevator : elevatorsMap.values()) {
            if(elevator.getDistance(floor) < distance) {
                closestElevator = elevator;
                distance = elevator.getDistance(floor);
            }
        }

        return closestElevator;
    }

    public Elevator callElevator(int srcFloor, int dstFloor) {
        Elevator closestElevator = findClosestElevatorToFloor(srcFloor);
        closestElevator.addStop(srcFloor, dstFloor);
        return closestElevator;
    }
}