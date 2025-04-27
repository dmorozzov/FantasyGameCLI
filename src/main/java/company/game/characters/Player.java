package company.game.characters;

import static java.lang.System.out;

public class Player extends Character {
    private final CharacterClass characterClass;
    private int xp = 0;
    private int level = 1;
    private int x = 0, y = 0;

    public Player(String name, CharacterClass characterClass) {
        super(name, characterClass.getHealth(), characterClass.getDamage());
        this.characterClass = characterClass;
    }

    public void gainXP(int amount) {
        xp += amount;
        int xpNeeded = level * 100;
        if (xp >= xpNeeded) {
            level++;
            xp -= xpNeeded;
            maxHp += 20;
            hp = maxHp;
            damage += 5;
            out.println("\n=== Level Up! ===");
            out.println("You got Level " + level + "!");
            out.println("Your health and attack power have increased!");
        }
    }

    public void getHealed() {
        hp = maxHp;
    }

    public String getStatus() {
        return String.format(
                " Name: %s\n Class: %s\n Health: %d\n Damage: %d\n XP: %d\n Level: %d\n X, Y: (%d, %d)",
                name, characterClass, hp, damage, xp, level, x, y
        );
    }

    public void moveNorth() {
        y++;
    }

    public void moveSouth() {
        y--;
    }

    public void moveWest() {
        x--;
    }

    public void moveEast() {
        x++;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }
}
