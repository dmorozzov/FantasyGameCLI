package company.game.characters;

import java.io.Serializable;

abstract public class Character implements Serializable {
    protected final String name;
    protected int hp;
    protected int maxHp;
    protected int damage;

    public Character(String name, int hp, int damage) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.damage = damage;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getDamage() {
        return damage;
    }
}
