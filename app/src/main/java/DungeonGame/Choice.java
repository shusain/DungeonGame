package DungeonGame;
public class Choice {
    String displayString;
    String actionResponse;
    GameTile placeToGoIfChosen;
    Enemy enemyOnTile;

    Choice(String displayString) {
        this.displayString = displayString;
    }

    Choice(String displayString, String actionResponse) {
        this(displayString);
        this.actionResponse = actionResponse;
    }
}
