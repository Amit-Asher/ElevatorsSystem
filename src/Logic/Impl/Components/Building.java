package Logic.Impl.Components;

import Logic.Impl.Factories.ConstructionFactory;
import Logic.Impl.Exceptions.InvalidFloorException;

import java.util.HashMap;
import java.util.Map;

public class Building {

    private Map<Integer, Floor> floorsMap = new HashMap<>();
    private ElevatorsScheduler elevatorsScheduler = new ElevatorsScheduler();

    public Building(int numberOfFloors, int numberOfElevators) {
        buildFloors(numberOfFloors, numberOfElevators);
    }

    private void buildFloors(int numberOfFloors, int numberOfElevators) {
        elevatorsScheduler.CreateElevators(numberOfElevators);
        for(int height=0; height < numberOfFloors; height++) {
            floorsMap.put(height, ConstructionFactory.createNewFloor(height, elevatorsScheduler));
        }
    }

    public int getNumberOfFloors() {
        return floorsMap.size();
    }

    public Floor getFloor(int floor) {
        int maxFloor = getNumberOfFloors() - 1;

        if(floor > maxFloor || floor < 0) {
            throw new InvalidFloorException(floor, maxFloor);
        }

        return floorsMap.get(floor);
    }
}