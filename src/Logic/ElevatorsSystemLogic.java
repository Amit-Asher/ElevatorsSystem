package Logic;

import Logic.Impl.Components.Building;
import Logic.Impl.Components.Elevator;
import Logic.Impl.Structs.ElevatorRequest;
import Logic.Impl.Components.Floor;
import Logic.Impl.Factories.ConstructionFactory;
import Logic.Impl.Exceptions.InvalidRequestException;

public class ElevatorsSystemLogic {

    private Building building;

    public void CreateNewBuilding(int numberOfFloors, int numberOfElevators) {
        building = ConstructionFactory.createNewBuilding(numberOfFloors, numberOfElevators);
    }

    private boolean checkRequestValidity(ElevatorRequest elevatorRequest) {
        return  elevatorRequest.srcFloor >= 0 &&
                elevatorRequest.dstFloor >= 0 &&
                elevatorRequest.srcFloor < building.getNumberOfFloors() &&
                elevatorRequest.dstFloor < building.getNumberOfFloors() &&
                elevatorRequest.srcFloor != elevatorRequest.dstFloor;
    }

    public int callElevator(ElevatorRequest elevatorRequest) {
        if(!checkRequestValidity(elevatorRequest)) {
            throw new InvalidRequestException(
                    elevatorRequest.srcFloor,
                    elevatorRequest.dstFloor,
                    building.getNumberOfFloors() - 1);
        }

        Floor floor = building.getFloor(elevatorRequest.srcFloor);
        Elevator elevatorAnswered = floor.callElevator(elevatorRequest.dstFloor);
        return elevatorAnswered.getId();
    }
}