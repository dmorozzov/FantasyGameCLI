package company.game;

import company.game.characters.Player;
import company.game.characters.enemies.Enemy;
import company.game.characters.enemies.Goblin;
import company.game.characters.enemies.Orc;
import company.game.characters.enemies.Troll;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

import static company.game.characters.CharacterGenerator.createCharacter;
import static java.lang.System.out;

public class Game {

    private static final String SAVE_FILE_NAME = "game_save.dat";

    private final Scanner scanner = new Scanner(System.in);
    private final Random random;
    private Player player;

    public Game() {
        this.random = new Random();
    }

    public Game(Random random, Player player) {
        this.random = random;
        this.player = player;
    }

    public void start() {
        mainMenu();
    }

    private void mainMenu() {
        while (true) {
            out.println("""
                        [1] New Game
                        [2] Load Game
                        [3] Exit"""
            );
            out.print("Choose an option: ");
            var option = scanner.nextLine();

            switch (option) {
                case "1":
                    player = createCharacter();
                    out.println("Welcome, " + player.getName() + " the " + player.getCharacterClass() + "!\n");
                    gameLoop();
                    break;
                case "2":
                    loadGame();
                    if (player != null) {
                        gameLoop();
                    } else {
                        out.println("Couldn't load the game");
                    }
                    break;
                case "3":
                    out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    out.println("Invalid option!");
            }
        }
    }

    private void gameLoop() {
        while (true) {
            out.println("""
                        [1] Move North
                        [2] Move South
                        [3] Move West
                        [4] Move East
                        [5] Check status
                        [6] Save game
                        [7] Return to main menu"""
            );
            out.print("Choose an action: ");
            var action = scanner.nextLine();

            switch (action) {
                case "1": player.moveNorth(); explore(); break;
                case "2": player.moveSouth(); explore(); break;
                case "3": player.moveWest(); explore(); break;
                case "4": player.moveEast(); explore(); break;
                case "5": showStatus(); break;
                case "6": saveGame(); break;
                case "7": out.println("Returning to main menu..."); return;
                default: out.println("Invalid action!"); break;
            }
        }
    }

    private void showStatus() {
        out.printf("""
                    ===============
                    Your status is:
                    %s
                    ===============
                    """, player.getStatus());
    }

    void explore() {
        int roll = random.nextInt(100);
        if (roll < 50) {
            Enemy enemy = generateEnemy();
            out.println("A wild " + enemy.getName() + " appears!");
            fightEnemy(enemy);
        } else if (roll < 70) {
            out.println("The area is quiet.");
        } else {
            out.println("You found healing potion. Your health is restored.");
            player.getHealed();
        }
    }

    Enemy generateEnemy() {
        int roll = random.nextInt(100);
        if (roll < 50) {
            return new Goblin();
        } else if (roll < 85) {
            return new Orc();
        } else {
            return new Troll();
        }
    }

    void fightEnemy(Enemy enemy) {
        while (enemy.isAlive() && player.isAlive()) {
            out.println(enemy.getName() + " HP: " + enemy.getHp() + " | Your HP: " + player.getHp());
            out.println("""
                        [1] Attack
                        [2] Run away"""
            );
            out.print("Choose option: ");
            var option = scanner.nextLine();

            if (option.equals("1")) {
                int playerDamage = random.nextInt(player.getDamage()) + 1;
                int enemyDamage = random.nextInt(enemy.getDamage()) + 1;

                enemy.takeDamage(playerDamage);
                out.println("You hit the " + enemy.getName() + " for " + playerDamage + " damage!");

                if (enemy.isAlive()) {
                    player.takeDamage(enemyDamage);
                    out.println("The " + enemy.getName() + " hits you for " + enemyDamage + " damage!");
                }
            } else if (option.equals("2")) {
                out.println("You managed to escape!");
                return;
            } else {
                out.println("Invalid option!");
            }
        }

        if (!player.isAlive()) {
            out.println("You have been defeated... Game Over!");
            System.exit(0);
        } else if (!enemy.isAlive()) {
            out.println("You defeated the " + enemy.getName() + ": +" + enemy.getXpReward() + " XP!");
            player.gainXP(enemy.getXpReward());
        }
    }

    private void saveGame() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(SAVE_FILE_NAME))) {
            outputStream.writeObject(player);
            out.println("Game saved!\n");
        } catch (IOException e) {
            out.println("Failed to save the game.");
        }
    }

    private void loadGame() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_FILE_NAME))) {
            player = (Player) in.readObject();
            out.println("Game loaded! Welcome back, " + player.getName() + "!\n");
        } catch (IOException | ClassNotFoundException e) {
            out.println("Failed to load the game.");
        }
    }
}
