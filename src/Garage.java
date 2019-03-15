import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Garage {
    private Scanner keyboard = new Scanner(System.in);
    private String name;
    private String dataFileName;
    private ArrayList<Payment> ledger;
    private Integer lostTickets = 0;

    private HashMap<String, Ticket> tickets;

    public Garage(String name) throws IOException {
        this.name = name;
        this.dataFileName = genDataFileName();
        this.tickets = new HashMap<String, Ticket>();
        this.ledger = new ArrayList();
        loadTickets();
    }

    private String genDataFileName()
    {
        String rString;
        rString = this.name.toLowerCase().replaceAll("\\W", "");
        return rString;
    }

    public String genDailyReport()
    {
        String rString = new String();
        Float totalReceipts = 0f;
        Float totalTickets = 0f;
        Float totalFee = 0f;
        Integer tickets = 0;
        Integer lost = 0;

        rString += this.name + "\n";
        for (int x = 1; x<=this.name.length(); x++)
        {
            rString += "=";
        }

        rString += "\nActivity to date:\n\n";

        for (Payment pay : ledger)
        {
            totalReceipts += pay.getCharge();
            if(pay.getCustID().equals("LOST"))
            {
                lost++;
                totalFee+=pay.getCharge();
            }
            else
            {
                tickets++;
                totalTickets+=pay.getCharge();
            }
        }

        rString += "$" + String.format("%.02f", totalTickets) + " was collected for " + tickets + " checkins\n\n";
        rString += "$" + String.format("%.02f", totalFee) + " was collected for " + lost + " lost tickets\n\n\n";
        rString += "$" + String.format("%.02f", totalReceipts) + " was collected overall.\n";
        return rString;
    }

    public void pushTicket(Ticket t)
    {
        this.tickets.put(t.getTicketID(), t);
    }

    public void popTicket(Ticket t)
    {
        this.tickets.remove(t.getTicketID());
    }


    public void closeGarage() throws IOException {
        saveTickets();
        loadLedger();
        System.out.println(genDailyReport());
    }

    public void loadTickets() throws IOException {
        String line;
        String[] workingArray;
        FileInputStream fis = new FileInputStream("tickets-" + this.dataFileName + ".dat");

        String i = String.format(String.valueOf(fis.read()));

        /*if(i != null)
        {
            while ((line = String.valueOf(fis.read())) != null)
            {
                workingArray = line.split(", ");
                if(workingArray.length == 2)
                {
                    pushTicket(new Ticket(workingArray[0], workingArray[1]));

                }

            }
        }*/
    }

    public void saveTickets() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("tickets-" + this.dataFileName + ".dat"));
        //FileOutputStream fos = new FileOutputStream("tickets-" + this.dataFileName + ".dat");
        String records = new String();

        // persist all tickets to a file
        for (String key : tickets.keySet())
        {
            records += tickets.get(key).toString() + "\n";
        }

        bw.write(records.trim());
        bw.close();
    }

    public void addToLedger(Payment pay) throws IOException {
        ledger.add(pay);
        saveLedger();
    }


    public void saveLedger() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("ledger-" + this.dataFileName + ".dat"));
        String records = new String();

        for (Payment pay : ledger)
        {
            records += pay.toString() + "\n";
        }
        bw.write(records.trim());
        bw.close();
    }


    public void loadLedger() throws IOException {
        String line;
        String[] workingArray;
        FileInputStream fis = new FileInputStream("ledger-" + this.dataFileName + ".dat");
        String i = String.format(String.valueOf(fis.read()));

        //String i = String.valueOf(fis.read());
        /*if(i != null)
        {
            while ((line = String.valueOf(fis.read())) != null)
            {
                workingArray = line.split(", ");
                ledger.add(new Payment(workingArray[0], workingArray[1], Float.parseFloat(workingArray[2])));
            }
        }*/
    }

    /* Getters and setters */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(HashMap<String, Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setLedger(ArrayList<Payment> al)
    {
        ledger = al;
    }

    private class Transaction {
    }
}
