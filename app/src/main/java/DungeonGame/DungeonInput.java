package DungeonGame;

import java.util.Scanner;

public class DungeonInput {
    private static DungeonInput instance = null;

    Scanner consoleInput;

    private DungeonInput() {
        consoleInput = new Scanner(System.in);
    }

    public static DungeonInput getInstance() {
        if(DungeonInput.instance == null) {
            DungeonInput.instance = new DungeonInput();
        }
        return DungeonInput.instance;
    }

    public int getInt() {
        return consoleInput.nextInt();
    }

    public String getName() {
        return promptForString("Please enter your name:");
    }

    public String promptForString(String promptText) {
        System.out.println(promptText);
        return consoleInput.nextLine();
    }
}
