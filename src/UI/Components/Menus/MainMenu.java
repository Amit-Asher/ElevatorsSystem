package UI.Components.Menus;

import Logic.ElevatorsSystemLogic;
import Logic.Impl.Logger.Log;
import Logic.Impl.Structs.ElevatorRequest;
import UI.Components.Helpers.IO;
import UI.Components.Handlers.Requests;

public class MainMenu {

    public static boolean handleChoice(int choice, ElevatorsSystemLogic logic) {
        boolean keepRunning = true;

        switch(choice) {
            case 1:
                MakeRequest(logic);
                break;
            case 2:
                ShowLiveStatus();
                break;
            case 3:
                ShowHistory();
                break;
            case 4:
                // EXIT
                keepRunning = false;
                break;
            default:
                System.out.println("invalid choice. please try again.\n");
                break;
        }

        return keepRunning;
    }

    private static void MakeRequest(ElevatorsSystemLogic logic) {
        System.out.println("\nMake Request");
        ElevatorRequest newRequest = new ElevatorRequest();
        newRequest.srcFloor = IO.readInt("choose src floor: ");
        newRequest.dstFloor = IO.readInt("choose dst floor: ");
        Requests.callElevator(logic, newRequest);
    }

    private static void ShowLiveStatus() {
        System.out.println("\nShow Live Status");
        System.out.println("(Press any key to stop the presentation)");
        Log.getInstance().setPrintToConsole(true);
        IO.readLine();
        Log.getInstance().setPrintToConsole(false);
    }

    private static void ShowHistory() {
        System.out.println("\nShow History");
        System.out.print(Log.getInstance().readFromLog());
        System.out.println("(Press any key to return main menu)");
        IO.readLine();
    }
}
