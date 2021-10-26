package Logic.Impl.Exceptions;

public class InvalidFloorException extends RuntimeException {
    public int floor;
    public int maxFloor;

    public InvalidFloorException(int floor, int maxFloor) {
        super("invalid floor " + floor + ". valid floors are in range of (0-" + maxFloor + ")");
        this.floor = floor;
        this.maxFloor = maxFloor;
    }
}