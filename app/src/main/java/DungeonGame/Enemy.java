package DungeonGame;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Enemy {
    private int maxEnemyHealth = 100;
    private int enemyAttackDamage = 25;
    private int health = 20;
    private EnemyEnum type = EnemyEnum.Goblin;

    public void setHealth(int health) {
        this.health = health;
        if(health<0) {
            this.die();
        }
    }

    // enemy death
    public void die() {
        String formatted = String.format("%s✅ You successfully manage to slay a %s! %s", ConsoleColors.GREEN, type.name(), ConsoleColors.RESET);
        System.out.println(formatted);
    }
}
