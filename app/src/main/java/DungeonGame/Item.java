package DungeonGame;

public class Item {
    String name;
    int power;

    Item(String name, int power) {
        this.name = name;
        this.power = power;
    }

    @Override
    public String toString() {
        return name + ":" + Integer.toString(power);
    }
}
