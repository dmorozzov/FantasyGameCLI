package company.game;

import company.game.characters.CharacterClass;
import company.game.characters.Player;
import company.game.characters.enemies.Goblin;
import company.game.characters.enemies.Orc;
import company.game.characters.enemies.Troll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class GameTest {

    private final Random random = Mockito.mock(Random.class, withSettings().withoutAnnotations());
    private final Player player = Mockito.spy(new Player("Player 1", CharacterClass.MAGE));
    private final Game game = Mockito.spy(new Game(random, player));

    @BeforeEach
    void clear() {
        Mockito.reset(random);
        Mockito.reset(game);
    }

    @Test
    void generateEnemyGoblin() {
        Mockito.when(random.nextInt(anyInt())).thenReturn(30);
        assertInstanceOf(Goblin.class, game.generateEnemy());
    }

    @Test
    void generateEnemyOrc() {
        Mockito.when(random.nextInt(anyInt())).thenReturn(70);
        assertInstanceOf(Orc.class, game.generateEnemy());
    }

    @Test
    void generateEnemyTroll() {
        Mockito.when(random.nextInt(anyInt())).thenReturn(90);
        assertInstanceOf(Troll.class, game.generateEnemy());
    }

    @Test
    void exploreEnemy() {
        Mockito.when(random.nextInt(anyInt())).thenReturn(10);
        doNothing().when(game).fightEnemy(any());
        game.explore();
        verify(game, times(1)).fightEnemy(any());
    }

    @Test
    void exploreNothing() {
        Mockito.when(random.nextInt(anyInt())).thenReturn(60);
        game.explore();
        verify(game, times(0)).fightEnemy(any());
    }

    @Test
    void exploreHealingPotion() {
        Mockito.when(random.nextInt(anyInt())).thenReturn(90);
        game.explore();
        verify(game, times(0)).fightEnemy(any());
        verify(player, times(1)).getHealed();
    }
}
