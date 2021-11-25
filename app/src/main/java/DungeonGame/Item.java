package DungeonGame;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Item {
    @NonNull
    private String name;
    private int attackStrength;
    private int shieldingStrength;
    private int itemHealth;

    @NonNull
    private ItemTypeEnum type;

    private boolean equipped;
    private boolean singleUse;
}
