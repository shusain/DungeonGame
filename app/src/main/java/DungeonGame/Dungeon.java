package DungeonGame;

import java.util.ArrayList;
import java.util.Scanner;

public class Dungeon {
    public static String WHAT_LIKE_TO_DO = "# What would you like to do? #";
    public static String LINE_BREAK = "-------------------------------------------------------------------------------";

    Scanner myScanner = new Scanner(System.in);

    Player player;

    GameTile corridor;
    GameTile roomOne;

    Choice napChoice = new Choice(
        "Have a nap.",
        String.join(
            "\n",
            "You find yourself back at the corridor you entered from.",
            "You arent going to uncover any mysteries hanging around here."
        )
    );

    // Constructing the dungeon
    Dungeon() {
        this.makePlayer();
        corridor = this.makeCorridor();

        corridor.nearbyTiles = new ArrayList<>(){{
            add(corridor);
            add(makeRoomOne());
        }};

        // Sets up the current tile for the player which
        // player actions will move them from here to other tiles nearby
        corridor.playerEntersTile(player);
        System.out.println(player.currentTile);

        // Continue the game while the player is still alive
        while( player.isAlive() ) {
            // Prints prompt for current tile
            System.out.println(player.currentTile.getOptionsList());

            // Wait for user 
            int choice = myScanner.nextInt();

            // Use the choice to take an action with the user
            player.takeAction(choice);

            // Print player stats after the action
            System.out.println(player);
        }
    }

    public void makePlayer() {
        System.out.println("Please enter your name:");

        String playerName = myScanner.nextLine();
        player = new Player(playerName);

        System.out.println("Welcome to the Dungeon " + playerName + " !");
        System.out.println(player);
    }

    public GameTile makeCorridor() {
        String entryText = String.join("\n", "You find yourself in a dimly lit corridor. The only light seems to be radiating from the stairs you came down.",
        "Your eyes slowly adjust to the din, you spot some tree roots, leaves, and dirt. Mostly debris that has blown in from the stairs behind you.",
        "You shuffle forward apprehensively and notice some of the mud is still damp and clumped together. Someone has come this way recently.",
        "A cold shudder comes over you. You make a mental note of your packs contents, and remind yourself of your equipment.",
        "You have your Iron Chainmail, your weak Iron Ring. You can feel some radiance from it, but you know it wont do much. And in your pack there are three healing potions.",
        "Your trusty Iron Sword is on your hip. This fills you with a little confidence.");

        GameTile tile = new GameTile(entryText, "corridor");

        tile.choices.add(napChoice);

        return tile;
    }

    public GameTile makeRoomOne() {
        String entryText = String.join("\n",
        "You inch into the room north of you.",
        "A weird, sweet, and musty smell seems to cling to the inside of your nostrils. Whatever it is smells bad, but you cant stop wondering what it is.",
        "You squint to see into the room and spot a cage and a table",
        "There's also a wooden door on the east wall.");

        GameTile tile = new GameTile(entryText, "room one");
        tile.choices.add(new Choice("Have a nap."));
        tile.roomElements = new ArrayList<>(){{
            add(makeRoomOneCage());
            add(makeRoomOneTable());
        }};
        return tile;
    }

    public InvestigationElement makeRoomOneCage() {
        String searchText = String.join("\n",
        "Your curiosity gets the better of you and you investigate the cage.",
        "Oh okay. You glance down and see something slimy and decomposing. You know where the smell is coming from",
        "Something catches the light, and it doesnt look wet.",
        "You've found a healing potion! You add it to your pouch");

        InvestigationElement anElement = new InvestigationElement(
            "cage",
            searchText,
            "You've already investigated that. You're not sure if your nose can take any more.",
            new ArrayList<>(){{add(new Item("potion", 40));}}
        );

        return anElement;
    }

    public InvestigationElement makeRoomOneTable() {
        String searchText = String.join("\n",
        "Your curiosity takes you to the table, there's a small pouch hanging from the back of one of the chairs.",
        "You give the pouch a tentative squeeze. You're sure there's nothing nasty lurking in there.",
        "Inside the pouch there are just two loose pieces of parchment. There's nothing remarkable about them.");
        
        InvestigationElement theTable = new InvestigationElement("table", searchText, (ArrayList<Item>) null);

        return theTable;
    }
}
