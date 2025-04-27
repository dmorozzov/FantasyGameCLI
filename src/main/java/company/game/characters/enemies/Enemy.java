package company.game.characters.enemies;

import company.game.characters.Character;

public abstract class Enemy extends Character {
    private final int xpReward;

    public Enemy(String name, int hp, int attackPower, int xpReward) {
        super(name, hp, attackPower);
        this.xpReward = xpReward;
    }

    public int getXpReward() {
        return xpReward;
    }
}
