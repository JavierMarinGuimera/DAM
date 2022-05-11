import java.io.File;
import java.io.FileInputStream;

public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("C:/Users/Javier/Desktop/prueba.html");

        FileInputStream fis = new FileInputStream(file);

        byte[] buffer = new byte[512];

        while (fis.read(buffer) != -1) {
            System.out.println(new String(buffer));
        }

        fis.close();
    }
}
