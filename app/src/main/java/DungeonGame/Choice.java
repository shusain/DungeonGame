package DungeonGame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Choice {
    @NonNull
    String displayString;
    String actionResponse;
    GameTile placeToGoIfChosen;
    Enemy enemyOnTile;

    Choice(String displayString, String actionResponse) {
        this(displayString);
        this.actionResponse = actionResponse;
    }
}
