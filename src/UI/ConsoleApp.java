package UI;
import Logic.ElevatorsSystemLogic;
import UI.Components.Helpers.Constants;
import UI.Components.Helpers.IO;
import UI.Components.Menus.MainMenu;

public class ConsoleApp implements Runnable {

    @Override
    public void run() {
        ElevatorsSystemLogic logic = new ElevatorsSystemLogic();
        logic.CreateNewBuilding(Constants.NUMBER_OF_FLOORS, Constants.NUMBER_OF_ELEVATORS);

        System.out.println("Welcome to elevators system!\n");
        int choice;

        do {
            System.out.println("Main Menu");
            System.out.println("1. Make Request");
            System.out.println("2. Show Live Status");
            System.out.println("3. Show History (log)");
            System.out.println("4. Exit");

            choice = IO.readChoice("select option: ");
        }
        while(MainMenu.handleChoice(choice, logic));

        System.out.println("Elevators System turning off...");
    }
}