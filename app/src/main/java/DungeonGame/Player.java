package DungeonGame;

import java.util.ArrayList;

public class Player {
    String name;
    int health;
    int armor;
    int attackDamage;
    int maxPlayerAttackDamage;
    String weapon;
    ArrayList<Item> items = new ArrayList<>();

    // Player Stats
    int amountMonstersKilled = 0;
    int numRoomsExplored = 0;

    GameTile currentTile;

    Player() {
        this("Bob", 100, 15, 30, 99, "Iron Sword");
    }

    Player(String name) {
        this(name, 100, 15, 30, 99, "Iron Sword");
    }

    Player(String name, int health, int armor, int attackDamage, int maxPlayerAttackDamage, String weapon) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.attackDamage = attackDamage;
        this.maxPlayerAttackDamage = maxPlayerAttackDamage;
        this.weapon = weapon;
    }

    public String toString() {
        return String.join("\n",
                Dungeon.LINE_BREAK,
                "Your name: " + this.name, "Your health: " + this.health,
                "Your armor: " + this.armor, "Your attackDamage: " + this.attackDamage,
                "Your maxPlayerAttackDamage: " + this.maxPlayerAttackDamage, "Your weapon: " + this.weapon,
                "Your items: " + this.items,
                Dungeon.LINE_BREAK);
    }

    // Player movement
    public void stepOnTile(GameTile tile) {
        this.numRoomsExplored++;
        this.currentTile = tile;
    }

    // Player action to investigate an item
    public void investigateAnItem(InvestigationElement anElement) {
        anElement.investigate(this);
    }

    // player death
    public void playerDeath() {
        System.out.println("Oh dear. You are dead.");
        System.out.println("You successfully managed to defeat " + amountMonstersKilled + " and explored " + numRoomsExplored);
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void takeAction(int choice) {
        // Check if the option was valid
        boolean isOptionValid = this.currentTile.checkIsOptionValid(choice);
        System.out.println(isOptionValid ? "Valid Option" : "Invalid option");
        if (isOptionValid) {
            this.currentTile.takeAction(this, choice);
        }
    }

    public void fight(ArrayList<Enemy> enemies) {
        // During fight we should:
        // Print out the enemies in the fight
        // Prompt the user to attack an enemy or use an item
        // If attacking an enemy should ask which one
        // If using an item should ask which one
        for (int i = 0; i < enemies.size(); i++) {
            Enemy curEnemy = enemies.get(i);
            this.health -= curEnemy.enemyAttackDamage;
            curEnemy.maxEnemyHealth -= this.attackDamage;
            if (curEnemy.health < 0)
                enemies.remove(curEnemy);
        }
    }
}
