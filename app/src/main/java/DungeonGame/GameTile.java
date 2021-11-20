package DungeonGame;
import java.util.ArrayList;

public class GameTile {

    String entryText;
    String roomName;
    
    ArrayList<Choice> choices = new ArrayList<>();
    ArrayList<GameTile> nearbyTiles = new ArrayList<>();
    ArrayList<InvestigationElement> roomElements = new ArrayList<>();

    GameTile(String entryText) {
        this.entryText = entryText;
    }
    GameTile(String entryText, String roomName) {
        this.entryText = entryText;
        this.roomName = roomName;
    }

    public void playerEntersTile(Player player) {
        player.stepOnTile(this);
    }

    public String toString() {
        return String.join("\n",
            entryText,
            Dungeon.LINE_BREAK,
            Dungeon.WHAT_LIKE_TO_DO,
            Dungeon.LINE_BREAK);
    }

    public String getOptionsList() {
        String toReturn = "";
        int listCount = 1;
        for (int i=0; i < this.choices.size(); i++, listCount++) {
            toReturn += Integer.toString(listCount) + ": ";
            toReturn += this.choices.get(i).displayString + "\n";
        }
        for (int i=0; i < this.nearbyTiles.size(); i++, listCount++) {
            toReturn += Integer.toString(listCount) + ": ";
            toReturn += this.nearbyTiles.get(i).roomName + "\n";
        }
        for (int i=0; i < this.roomElements.size(); i++, listCount++) {
            toReturn += Integer.toString(listCount) + ": ";
            toReturn += this.roomElements.get(i).elementName + "\n";
        }
        return toReturn;
    }

    public boolean checkIsOptionValid(int choice) {
        return choice <= this.choices.size() + this.nearbyTiles.size() + this.roomElements.size();
    }

    public void takeAction(Player player, int choice) {
        if(choice <= this.choices.size()) {
            System.out.println(this.choices.get(choice-1).actionResponse);
        } else if(choice <= this.choices.size()+this.nearbyTiles.size()) {
            player.stepOnTile(this.nearbyTiles.get(choice-this.choices.size()-1));
            System.out.println(player.currentTile);
        } else if(choice <= this.choices.size()+this.nearbyTiles.size()+this.roomElements.size()) {
            InvestigationElement itemToInvestigate = this.roomElements.get(choice-this.choices.size()-this.nearbyTiles.size()-1);
            player.investigateAnItem(itemToInvestigate);
        }
    }
}
