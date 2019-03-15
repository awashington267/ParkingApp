import java.io.IOException;

public class Entry {
    public static void main(String[] args) throws IOException {


        Garage gar2 = new Garage("Tony's Garage");
        PosEntry pe = new PosEntry(gar2);

        pe.startUp();

    }
}
