package Client;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriteFile{
    private String content;


    public WriteFile(String content) {

        this.content = content;
    }

    public void Writer() {
        try {
            FileWriter Writer = new FileWriter("src/Client/clientList.txt",true);
            BufferedWriter Writer1 = new BufferedWriter(Writer);


            Writer1.newLine();
            Writer1.append(content);
            System.out.println("Done.");

            Writer1.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
