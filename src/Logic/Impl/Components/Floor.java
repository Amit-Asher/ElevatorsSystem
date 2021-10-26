package Logic.Impl.Components;

public class Floor {

    private int floorHeight;
    private final ElevatorsScheduler elevatorsSchedulerRef;

    public Floor(int height, ElevatorsScheduler elevatorsManagerRef) {
        this.floorHeight = height;
        this.elevatorsSchedulerRef = elevatorsManagerRef;
    }

    public Elevator callElevator(int dstFloor) {
        return elevatorsSchedulerRef.callElevator(floorHeight, dstFloor);
    }
}