package DungeonGame;

import java.util.ArrayList;

public class InvestigationElement {
    ArrayList<Enemy> enemies;
    ArrayList<Item> items;
    String elementName;
    boolean hasBeenChecked = false;
    String alreadyBeenSearchedMsg;
    String searchText;

    InvestigationElement(String elementName, String searchText, ArrayList<Item> items) {
        this.elementName = elementName;
        this.searchText = searchText;
        this.items = items;
        alreadyBeenSearchedMsg = String.format("You've already checked the %s.", elementName);
    }

    InvestigationElement(String elementName, String searchText, String alreadyBeenSearchedMsg) {
        this(elementName, searchText, alreadyBeenSearchedMsg, null);
    }

    InvestigationElement(String elementName, String searchText, String alreadyBeenSearchedMsg, ArrayList<Item> items) {
        this.elementName = elementName;
        this.alreadyBeenSearchedMsg = alreadyBeenSearchedMsg;
        this.searchText = searchText;
        this.items = items;
    }

    public ArrayList<Item> investigate(Player player) {
        System.out.println(this.hasBeenChecked ? this.alreadyBeenSearchedMsg : searchText);
        if (!this.hasBeenChecked) {
            if (this.items != null && this.items.size() > 0) {
                player.items.addAll(this.items);
            }
            if (this.enemies != null && this.enemies.size() > 0) {
                player.fight(this.enemies);
            }
        }
        this.hasBeenChecked = true;
        return items;
    }

    @Override
    public String toString() {
        return this.elementName;
    }
}
