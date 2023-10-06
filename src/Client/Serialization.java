package Client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serialization {
    private String file_name;
    private SendEmailTLS object_name;

    public Serialization(String file_name, SendEmailTLS object_name) {
        this.file_name = file_name;
        this.object_name = object_name;
    }

    public void saveObjects() {
        try{
            FileOutputStream fileStream = new FileOutputStream(file_name, true);
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(object_name);

            os.close();
            fileStream.close();
        }
        catch(IOException e){
            System.out.println(e);
        }

    }

}