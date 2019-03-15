import java.io.IOException;

public class Exit {
    public static void main(String[] args) throws IOException {

        Garage gar2 = new Garage("Tony's Garage");

        PosExit pe = new PosExit(gar2);

        pe.startUp();
    }
}
