package StartUp;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class main {
    public static void main(String[] args) {
        InputStream inputstream = null;
        System.out.println("General-Infomation-Management System");
        File config = new File("d\\GIM\\config.yml");
        if(!config.exists()) {
            try {
                config.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
