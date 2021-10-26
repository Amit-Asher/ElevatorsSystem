package Logic.Impl.Exceptions;

public class InvalidRequestException extends RuntimeException {
    public int srcFloor;
    public int dstFloor;
    public int maxFloor;

    public InvalidRequestException(int srcFloor, int dstFloor, int maxFloor) {
        super("invalid request. src and dst floors must be different in range of (0-" + maxFloor + ")");
        this.srcFloor = srcFloor;
        this.dstFloor = dstFloor;
        this.maxFloor = maxFloor;
    }
}
