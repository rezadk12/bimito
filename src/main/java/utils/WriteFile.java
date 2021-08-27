package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    public static void write(String path,String data){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.append(data);
            writer.newLine();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
