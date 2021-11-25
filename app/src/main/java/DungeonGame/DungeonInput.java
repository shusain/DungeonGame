package DungeonGame;

import java.util.Scanner;

public class DungeonInput {
    private static DungeonInput instance = null;

    Scanner consoleInput = new Scanner(System.in);

    private DungeonInput() {
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
