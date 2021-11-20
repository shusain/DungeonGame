package DungeonGame;
public class Enemy {
    int maxEnemyHealth = 100;
    int enemyAttackDamage = 25;
    int health = 20;
    EnemyEnum type;

    // enemy death
    public void enemyDeath() {
        System.out.println("You successfully manage to slay!");
    }
}
