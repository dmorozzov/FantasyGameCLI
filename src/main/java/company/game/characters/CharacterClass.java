package company.game.characters;

public enum CharacterClass {
    WARRIOR(120, 10),
    MAGE(80, 20),
    ROGUE(100, 15);

    private final int health;
    private final int damage;

    CharacterClass(int health, int damage) {
        this.health = health;
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }
}
