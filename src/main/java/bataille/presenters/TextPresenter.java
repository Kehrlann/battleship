package bataille.presenters;

import bataille.BattleshipGame;

import java.awt.*;
import java.util.Scanner;

public class TextPresenter {
    BattleshipGame game = new BattleshipGame();
    Scanner scanner = new Scanner(System.in);

    private void clear() {
        System.out.print("\033[H\033[2J");
    }

    private void printLine(String line) {
        System.out.println(line);
    }

    private Point requestShot() {
        return new Point(scanner.nextInt(), scanner.nextInt());
    }

    private void drawBoard() {
        clear();
        printLine(" ");
        printLine(" ");
        for (char[] line : game.boardState()) {
            StringBuilder temp = new StringBuilder();
            temp.append(" ");
            for (char tile : line) {
                temp.append(tile);
                temp.append(" ");
            }
            printLine(temp.toString());
        }
    }

    public void play() {
        drawBoard();
        // message: nothing yet !
        while (!game.gameOver()) {
            Point shot = requestShot();
            game.shoot(shot.x, shot.y);
            drawBoard();
            // message: touché/coulé
        }
        // message "fini !"
    }

    public static void main(String[] args) {
        TextPresenter game = new TextPresenter();
        game.play();
        System.out.println("");
        System.out.println("All done !");
    }
}
