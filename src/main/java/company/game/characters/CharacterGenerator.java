package company.game.characters;

import java.util.Scanner;

import static java.lang.System.out;

public class CharacterGenerator {

    private static final Scanner scanner = new Scanner(System.in);

    public static Player createCharacter() {
        out.print("Enter your character's name: ");
        var name = scanner.nextLine();
        while (true) {
            out.println("Choose your class: ");
            var classes = CharacterClass.values();
            for (int i = 0; i < classes.length; i++) {
                out.printf("[%d] %s, health: %s, damage: %s.%n", i + 1, classes[i].name(), classes[i].getHealth(), classes[i].getDamage());
            }
            out.print("Choose an option: ");
            var option = scanner.nextLine();
            int classNumber;
            try {
                classNumber = Integer.parseInt(option);
            } catch (Exception ex) {
                out.println("Unknown class");
                continue;
            }
            if (classNumber - 1 >= classes.length) {
                out.println("Unknown class");
                continue;
            }
            return new Player(name, classes[classNumber - 1]);
        }
    }
}
