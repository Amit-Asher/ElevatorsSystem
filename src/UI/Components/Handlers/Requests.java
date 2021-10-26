package UI.Components.Handlers;

import Logic.ElevatorsSystemLogic;
import Logic.Impl.Structs.ElevatorRequest;

public class Requests {

    public static void callElevator(ElevatorsSystemLogic logic,
                               ElevatorRequest elevatorRequest) {
        try {
            System.out.println("request: " +
                    "src floor (" + elevatorRequest.srcFloor + ") -> " +
                    "dst floor (" + elevatorRequest.dstFloor + ") received.");
            int elevatorAnswered = logic.callElevator(elevatorRequest);
            System.out.println("elevator: " + elevatorAnswered + " is on the way!");
            System.out.println();
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }
}