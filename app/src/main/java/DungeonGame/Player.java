package DungeonGame;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Player {
    private final static int MAX_PLAYER_ATTACK_DAMAGE = 500;

    private DungeonInput dungI = DungeonInput.getInstance();
    @NonNull
    private String name;
    private int health = 100;
    private int armor;
    private ArrayList<Item> items = new ArrayList<>(){{
        Item ironSword = new Item("Iron Sword", ItemTypeEnum.Weapon);
        ironSword.setEquipped(true);
        ironSword.setAttackStrength(10);
        add(ironSword);
    }};

    // Player Stats
    int amountMonstersKilled = 0;
    int numRoomsExplored = 0;

    GameTile currentTile;


    @Override
    public String toString() {
        System.out.println("Would you like to equip an item?");
        System.out.println("0: Cancel");
        if(printEquippableItems()) {
            int selection = dungI.getInt()-1;
            List<Item> usableItems = this.items.stream().filter(item -> !item.isEquipped()).toList();
            if(selection == -1 || selection > usableItems.size()) {
                System.out.println("No item equipped");
            } else {
                Item selectedItem = this.items.stream().filter(item -> !item.isEquipped()).toList().get(selection);
                this.equipOrUseItem(selectedItem);
            }

        }
        return String.join("\n",
                Dungeon.LINE_BREAK,
                "Name 🆔: " + this.name,
                "Health ♥: " + this.health,
                "Armor 🛡: " + this.armor,
                "AttackDamage 🤺: " + this.getAttackDamage(),
                "Weapon ⚔: " + this.getEquippedWeapon().getName(),
                "Items 📦: " + this.items.size(),
                "Rooms Explored 🏠: " + this.numRoomsExplored,
                "Monsters Slain 🐜: " + this.amountMonstersKilled,
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
        // System.out.println(isOptionValid ? "Valid Option" : "Invalid option");
        if (isOptionValid) {
            this.currentTile.takeAction(this, choice);
        }
    }

    public void fight(ArrayList<Enemy> enemies) {
        // Continue the fight until all enemies vanquished or you die
        while(enemies.size() > 0 && this.health > 0) {

            // Prompt the user to attack an enemy or use an item
            System.out.println("1: Attack an Enemy ");
            System.out.println("2: Use/Equip an inventory item");
            int attackOrInv = dungI.getInt();
            if(attackOrInv == 1) {

                for (int i = 0; i < enemies.size(); i++) {
                    Enemy curEnemy = enemies.get(i);
                    // Print out the enemies in the fight
                    System.out.print((i+1)+": ");
                    System.out.println(curEnemy);
                }

                System.out.println("Choose an enemy to attack: ");
                Enemy curEnemy = enemies.get(dungI.getInt() - 1);

                this.health -= curEnemy.getEnemyAttackDamage();

                curEnemy.setHealth(curEnemy.getHealth() - this.getAttackDamage());
                
                if (curEnemy.getHealth() < 0) {
                    enemies.remove(curEnemy);
                    this.amountMonstersKilled++;
                }
                System.out.println(this);
            } else {
                System.out.println("Choose an item to equip: ");
                // Print player items
                if(printEquippableItems()) {
                    Item selectedItem = this.items.stream().filter(item -> !item.isEquipped()).toList().get(dungI.getInt()-1);
                    this.equipOrUseItem(selectedItem);
                }
            }
        }        
    }

    public boolean printEquippableItems() {
        AtomicInteger count=new AtomicInteger(0);
        this.items.stream().filter(item -> !item.isEquipped()).forEach(item->System.out.printf("%d: %s\n", count.incrementAndGet(), item));
        if(count.get() == 0) {
            System.out.println("No items can be equipped");
            return false;
        }
        return true;
    }

    public Item getEquippedWeapon() {
        for(var playerItem: items) {
            if(playerItem.getType() == ItemTypeEnum.Weapon && playerItem.isEquipped()) return playerItem;
        }
        return null;
    }

    public int getAttackDamage() {
        Item equippedWeapon  = this.getEquippedWeapon();
        if(equippedWeapon != null) {
            return equippedWeapon.getAttackStrength();
        }
        return 0;
    }

    public void equipOrUseItem(Item item) {
        switch(item.getType()) {
            case Potion:
                if(item.getShieldingStrength() > 0) {
                    this.setHealth(this.getHealth() + item.getShieldingStrength());
                } else if(item.getAttackStrength() > 0) {
                    Item equippedWeapon = this.getEquippedWeapon();
                    if(equippedWeapon != null) {
                        equippedWeapon.setAttackStrength(equippedWeapon.getAttackStrength()+item.getAttackStrength());
                    }
                }
                this.setHealth(this.getHealth() + item.getShieldingStrength());
            break;
            case Boots:
            case Shield:
                item.setEquipped(true);
            break;
            case Weapon:
                for(var playerItem:items) {
                    if(playerItem.getType() == ItemTypeEnum.Weapon && playerItem.isEquipped()) {
                        playerItem.setEquipped(false);
                    }
                }
                item.setEquipped(true);
            break;
        }
        if(item.isSingleUse()) {
            this.items.remove(item);
        }
        System.out.printf("%s ✔ Equipped a new item: %s %s\n", ConsoleColors.GREEN, item, ConsoleColors.RESET);
    }
}
