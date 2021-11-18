import java.util.Scanner;

public class Game {

    Scanner myScanner = new Scanner(System.in);
    Scanner enterScanner = new Scanner(System.in);

    // player variables
    int playerHealth; // 100
    int playerArmor; // 15;
    int playerAttackDamage; // 30;
    int maxPlayerAttackDamage; // 99;
    String playerName;
    String playerWeapon; // iron sword

    // item variables
    int ironArmor = 5;
    int steelArmor = 10;
    int elvenArmor = 15;
    int ironRing = 3;
    int steelRing = 5;
    int elvenRing = 8;
    int ironSword = 15;
    int steelSword = 35;
    int elvenSword = 55;

    // potion variables
    int numHealthPotions = 3;
    int maxNumHealthPotions = 9;
    int healthPotionHealAmount = 69;

    // game variables
    int amountMonstersKilled = 0;
    int numRoomsExplored = 0;
    int choice;
    int roomOneCage = 1;
    int roomOneTable = 1;

    // enemy variables
    public void enemies() {
        String[] enemies = { "Troll", "Goblin", "Decomposing Knight", "Smelly Gnoll", "Succubus" };
        int maxEnemyHealth = 100;
        int enemyAttackDamage = 25;
    }

    // player death
    public void playerDeath() {
        System.out.println("Oh dear. You are dead.");
        System.out.println(
                "You successfully managed to defeat " + amountMonstersKilled + " and explored " + numRoomsExplored);
    }

    // enemy death
    public void enemyDeath() {
        System.out.println("You successfully manage to slay!");
    }

    public void lineBreak() {
        System.out.println("-------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {

        Game dungeon;
        dungeon = new Game();

        dungeon.playerSettings();
        dungeon.corridor();
    }

    public void playerSettings() {

        playerHealth = 100;
        playerAttackDamage = 30;
        playerWeapon = "Iron Sword";

        System.out.println("Your hitpoints: " + playerHealth);
        System.out.println("Your weapon: " + playerWeapon);

        System.out.println("Please enter your name:");

        playerName = myScanner.nextLine();

        System.out.println("Welcome to the Dungeon " + playerName + " !");
    }

    public void corridor() {

        System.out.println(
                "\nYou find yourself in a dimly lit corridor. The only light seems to be radiating from the stairs you came down.");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println(
                "Your eyes slowly adjust to the din, you spot some tree roots, leaves, and dirt. Mostly debris that has blown in from the stairs behind you.");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println(
                "You shuffle forward apprehensively and notice some of the mud is still damp and clumped together. Someone has come this way recently.");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println(
                "A cold shudder comes over you. You make a mental note of your packs contents, and remind yourself of your equipment.");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println(
                "You have your Iron Chainmail, your weak Iron Ring. You can feel some radiance from it, but you know it wont do much. And in your pack there are three healing potions.");
        System.out.println("Your trusty Iron Sword is on your hip. This fills you with a little confidence.");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("What would you like to do?");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("1. Push onward into the next room.");
        System.out.println("2. Have a nap.");
        System.out.println("-------------------------------------------------------------------------------");

        choice = myScanner.nextInt();

        switch (choice) {
        case 1:
            roomOne();
            break;
        case 2:
            backCorridor();
            break;
        default:
            System.out.println("You need to pick 1, or 2.");
        }
    }

    public void backCorridor() {

        System.out.println("You find yourself back at the corridor you entered from.");
        System.out.println("You arent going to uncover any mysteries hanging around here.");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("What would you like to do?");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("1. Push onward into the next room.");
        System.out.println("2. Have a nap.");
        System.out.println("-------------------------------------------------------------------------------");

        choice = myScanner.nextInt();

        switch (choice) {
        case 1:
            roomOne();
            break;
        case 2:
            backCorridor();
            break;
        default:
            System.out.println("You need to pick 1, or 2.");
        }
    }

    public void roomOne() {
        System.out.println("You inch into the room north of you.");
        System.out.println(
                "A weird, sweet, and musty smell seems to cling to the inside of your nostrils. Whatever it is smells bad, but you cant stop wondering what it is.");
        System.out.println("You squint to see into the room and spot a cage and a table");
        System.out.println("There's also a wooden door on the east wall.");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("What would you like to do?");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("1. Check out the cage.");
        System.out.println("2. Investigate the table.");
        System.out.println("3. Push east through the wooden door.");
        System.out.println("4. Go back to the corridor.");
        System.out.println("-------------------------------------------------------------------------------");

        choice = myScanner.nextInt();

        switch (choice) {
        case 1:
            // roomOne - Investigate the cage
            if (roomOneCage == 1) {
                System.out.println("Your curiosity gets the better of you and you investigate the cage.");
                System.out.println(
                    "Oh okay. You glance down and see something slimy and decomposing. You know where the smell is coming from");
                System.out.println("Something catches the light, and it doesnt look wet.");
                System.out.println("You've found a healing potion! You add it to your pouch");

                numHealthPotions++;
                roomOneCage--;
                lineBreak();

            } else {
                System.out.println("You've already investigated that. You're not sure if your nose can take any more.");
            }
            roomOne();
        case 2:
            //roomOneTable();
            System.out.println("Your curiosity takes you to the table, there's a small pouch hanging from the back of one of the chairs.");
            System.out.println("");
            break;
        case 3:
            //roomTwo();
            break;
        case 4:
            backCorridor();
            break;
        default:
            System.out.println("You need to pick 1, 2, 3, or 4.");
        }
    }

    public void roomOneCage() {

    }

}
