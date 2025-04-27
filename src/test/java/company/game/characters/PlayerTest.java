package company.game.characters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void checkMageStats() {
        Player player = new Player("Player 1", CharacterClass.MAGE);
        assertEquals(CharacterClass.MAGE.getHealth(), player.hp);
        assertEquals(CharacterClass.MAGE.getDamage(), player.damage);
    }

    @Test
    void checkWarriorStats() {
        Player player = new Player("Player 1", CharacterClass.WARRIOR);
        assertEquals(CharacterClass.WARRIOR.getHealth(), player.hp);
        assertEquals(CharacterClass.WARRIOR.getDamage(), player.damage);
    }

    @Test
    void checkRogueStats() {
        Player player = new Player("Player 1", CharacterClass.ROGUE);
        assertEquals(CharacterClass.ROGUE.getHealth(), player.hp);
        assertEquals(CharacterClass.ROGUE.getDamage(), player.damage);
    }

    @Test
    void gainXP() {
        Player player = new Player("Player 1", CharacterClass.ROGUE);
        player.gainXP(150);
        assertEquals(2, player.getLevel());
        assertEquals(50, player.getXp());
        assertEquals(CharacterClass.ROGUE.getHealth() + 20, player.getHp());
        assertEquals(CharacterClass.ROGUE.getDamage() + 5, player.getDamage());
    }
}
