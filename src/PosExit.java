import java.io.IOException;
import java.util.*;

public class PosExit extends Exit {
    private Scanner keyboard = new Scanner(System.in);
    private Garage location;
    private Boolean done = false;
    private Ticket t;
    private HashMap<String, String> billDetails;
    private Date outTime;
    Date date1 = new GregorianCalendar(2019, 03, 14, 10, 00, 00).getTime();
    Date date2 = new GregorianCalendar(2019, 03, 14, 20,00, 00).getTime();


    String userChoice;

    public PosExit(Garage location)
    {
        this.location = location;
    }

    public String generateBill(HashMap<String, String> details)
    {
        String rString = new String();

        rString += "Receipt for vehicle: ";

        if(details.containsKey("in"))
        {
            rString += details.get("id") + "\n";
            rString += date1.toString() + details.get("in");
            rString += date2.toString() + details.get("out");
            rString += " hours parked: \n";
            rString += details.get("in") + " - " + details.get("out") + "\n";
        }
        else
        {
            rString += "\n\nLost Ticket:\n";
        }

        rString += "$" + details.get("charge");

        rString += "\n\n\n";

        return rString;
    }

    /**
     * Display a common 'banner" for POS system
     */
    public void displayBanner()
    {
        for (int i = 1; i < 100; i++)
        {
            System.out.print("\n");
        }

        System.out.println("Thank you for visiting " + this.location.getName());
        System.out.println("=====================================");

    }

    private HashMap doTicketExit() throws IOException {
        HashMap bd = new HashMap();
        System.out.println("Enter Ticket ID");
        System.out.println("\n => ");
        userChoice = keyboard.nextLine();

        if(location.getTickets().containsKey(userChoice))
        {
            t = location.getTickets().get(userChoice);
            location.loadTickets();
            location.popTicket(t);
            location.saveTickets();
            bd.put("id", t.getTicketID());
            bd.put("charge", String.format("%.02f", t.getCharge(outTime)));
            bd.put("in", date1.before(t.getTimeIn()));
            bd.put("out", date2.after(outTime));
        }
        else
        {
            displayBanner();
            System.out.println("Unable to locate ticket.");
            System.out.println("Please check the number and try again.");
            bd.put("charge", "nil");
        }

        return bd;
    }

    private HashMap doLostTicket()
    {
        HashMap bd = new HashMap();

        bd.put("charge", "25.00");
        bd.put("id", "LOST");

        return bd;
    }

    /**
     * Start up simple POS exit program
     */
    public void startUp() throws IOException {
        while(!done)
        {
            displayBanner();
            System.out.println("1 - Leave / receive bill");
            System.out.println("\n");
            System.out.println("2 - Lost ticket");
            System.out.println("\n");
            System.out.printf("=> ");

            userChoice = keyboard.nextLine();

            displayBanner();

            switch (Integer.parseInt(userChoice))
            {
                case 1:
                    billDetails = doTicketExit();
                    break;
                case 2:
                    billDetails = doLostTicket();
                    break;
                case 3:
                    location.closeGarage();
                    System.exit(0);
                    break;
            }

            if(billDetails.get("charge") != "nil")
            {
                location.addToLedger(new Payment(billDetails.get("id"), "pay", Float.parseFloat(billDetails.get("charge"))));
                displayBanner();
                System.out.println(generateBill(billDetails));
            }

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
