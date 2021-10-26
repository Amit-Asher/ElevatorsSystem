package Logic.Impl.Factories;

import Logic.Impl.Components.*;

public class ConstructionFactory {

    public static Building createNewBuilding(int numberOfFloors, int numberOfElevators) {
        return new Building(numberOfFloors, numberOfElevators);
    }

    public static Elevator createNewElevator(int id) {
        return new Elevator(id);
    }

    public static Floor createNewFloor(int height, ElevatorsScheduler elevatorsManager) {
        return new Floor(height, elevatorsManager);
    }
}
