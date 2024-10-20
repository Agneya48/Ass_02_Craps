import java.util.Scanner;
import java.util.Random;

public class OnePlayerCrapsWagerless {
    public static void main(String[] args) {

        String continueYN = "";
        int die1Roll = 0;
        int die2Roll = 0;
        int diceSum = 0;
        int pointTrys = 0;
        int point = 0;
        int playerWins = 0;
        int playerLosses = 0;
        int gameCounter = 0;
        Random rnd = new Random();
        boolean done = false;

        System.out.println("\nWelcome to single-player Craps!");
        System.out.println("This program will auto-play a game of craps without wagers, \nthen prompt if you want to continue.");

        try (Scanner in = new Scanner(System.in)) { // auto-close scanner to avoid memory leaks

            do {//loop for running a game, will execute at least once and repeat after only if player indicates they want to continue
                done = false; //reset at start of loop
                gameCounter++;
                pointTrys = 0; //reset for each game
                die1Roll = rnd.nextInt(6) + 1;
                die2Roll = rnd.nextInt(6) + 1;
                diceSum = die1Roll + die2Roll;

                System.out.println("\nGame " + gameCounter);
                System.out.println("\nInitial Roll -- " + die1Roll + " and " + die2Roll+ ", Sum: " + diceSum);

                if(diceSum == 2 || diceSum == 3 || diceSum == 12) {
                    //Player has crapped out, add loss to tally and prompt to play again
                    playerLosses++;
                    System.out.println("\nYou rolled a sum of " + diceSum + " and crapped out.");
                }

                else if (diceSum == 7 || diceSum == 11) {
                    //Player rolled a natural, add win to tally and prompt to play again
                    playerWins++;
                    System.out.println("\nYou rolled a natural sum of " + diceSum + ", and won!");
                }

                else {//rolled sum becomes point and automated rolls continue until win or loss
                    point = diceSum;
                    System.out.println("\nTrying for point:");
                    System.out.println();
                    System.out.printf("%5s%5s%5s%5s%6s", "Roll", "Die1", "Die2", "Sum", "Point"); //header row
                    System.out.print("\n--------------------------");
                    do { //generate random dice rolls until sum matches point, or a sum of 7 is rolled
                        die1Roll = rnd.nextInt(6) + 1;
                        die2Roll = rnd.nextInt(6) + 1;
                        diceSum = die1Roll + die2Roll;
                        pointTrys++;

                        System.out.printf("%n%5d%5d%5d%5d%6d", pointTrys, die1Roll, die2Roll, diceSum, point);

                        if (diceSum == point) {//point matched, player wins
                            playerWins++;
                            System.out.println("\n\nYou matched the point with a sum of " + diceSum + " and won!");
                            done = true;
                        }
                        else if (diceSum == 7) {//player rolled a sum of 7, losing the game
                            playerLosses++;
                            System.out.println("\n\nWhile trying for the point you rolled a sum of 7 and lost.");
                            done = true;
                        }
                    } while(!done);
                }

                System.out.println("\nWins: " + playerWins + "  Losses: " + playerLosses);

                System.out.print("\nPlay again? Y/N: ");
                continueYN = in.nextLine();

            } while(continueYN.equalsIgnoreCase("Y") || continueYN.equalsIgnoreCase("yes"));
            // will stop unless y or yes entered, ignoring case
        }
    }
}