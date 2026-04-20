import java.util.Random;
import java.util.Scanner;

public class PacmanGame {

    static class Pacman {
        int x = 1, y = 1;

        void move(char dir, int size) {
            if (dir == 'U' || dir == 'u') x--;      // up
            else if (dir == 'D' || dir == 'd') x++; // down
            else if (dir == 'L' || dir == 'l') y--; // left
            else if (dir == 'R' || dir == 'r') y++; // right

            // keep inside board
            if (x < 0) x = 0;
            if (y < 0) y = 0;
            if (x > size - 1) x = size - 1;
            if (y > size - 1) y = size - 1;
        }
    }

    static class Ghost {
        int x, y;
        Random r = new Random();

        Ghost(int size) {
            x = size - 2;
            y = size - 2;
        }

        void move(int size) {
            int dir = r.nextInt(4);

            if (dir == 0 && x > 0) x--;
            else if (dir == 1 && x < size - 1) x++;
            else if (dir == 2 && y > 0) y--;
            else if (dir == 3 && y < size - 1) y++;
        }
    }

    public static void main(String[] args) {

        int size = 10;

        Pacman p = new Pacman();
        Ghost g = new Ghost(size);

        int foodX = 5, foodY = 5;
        boolean foodEaten = false;

        Scanner sc = new Scanner(System.in);

        while (true) {

            // draw board
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {

                    if (i == p.x && j == p.y) System.out.print("P ");
                    else if (i == g.x && j == g.y) System.out.print("G ");
                    else if (!foodEaten && i == foodX && j == foodY) System.out.print(". ");
                    else System.out.print("# ");
                }
                System.out.println();
            }

            // win
            if (foodEaten) {
                System.out.println("🎉 You Win!");
                break;
            }

            // lose
            if (p.x == g.x && p.y == g.y) {
                System.out.println("💀 Game Over!");
                break;
            }

            // input
            System.out.println("Move (U=up, D=down, L=left, R=right): ");
            char move = sc.next().charAt(0);

            p.move(move, size);
            g.move(size);

            if (p.x == foodX && p.y == foodY) {
                foodEaten = true;
            }

            System.out.println("\n-------------------\n");
        }

        sc.close();
    }
}
