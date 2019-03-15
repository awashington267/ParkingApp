import java.io.IOException;
import java.util.Scanner;

public class PosEntry {
   private Scanner keyboard = new Scanner(System.in);
    private Garage location;
    private Boolean done = false;

    private Ticket t;
    private String userChoice;

    public PosEntry(Garage someGarage)
    {
        this.location = someGarage;
    }

    /**
     * Display a common 'banner" for POS system
     */
    public void displayBanner()
    {
        System.out.println("Welcome to " + this.location.getName());
        System.out.println("=========================");
        System.out.println("\n");

        System.out.println("1 - Enter / print ticket");
        System.out.println("\n");
        System.out.println("3 - Close garage");
        System.out.println("\n");
        System.out.printf("=> ");
    }

    /**
     * Main method to run the simple loop of the parking entry POS
     */
    public void startUp() throws IOException {
        displayBanner();

        userChoice = keyboard.nextLine();

        while(userChoice.equals("1"))
        {
            t = new Ticket();
            location.pushTicket(t);

            displayBanner();
            userChoice = keyboard.nextLine();

        }
        if (Integer.parseInt(userChoice) == 3)
        {
            location.closeGarage();

        }
    }
}
