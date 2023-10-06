package Client;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Deserialization  {
    private String file_name;
    private SendEmailTLS obj;
    private ArrayList<SendEmailTLS> AllobjectDetails = new ArrayList<>();
    private ArrayList<SendEmailTLS> objectDetails = new ArrayList<>();
    public Deserialization(String file_name) {
        this.file_name = file_name;
    }

    public void getSavedObjects(String date) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file_name);
            ObjectInputStream is = new ObjectInputStream(fileInputStream);
            obj = (SendEmailTLS) is.readObject();
            AllobjectDetails.add(obj);
            if(obj.getDate().equals(date)){
                objectDetails.add(obj);
            }

            while (obj != null) {
                try {
                    is = new ObjectInputStream(fileInputStream);
                    obj = (SendEmailTLS)is.readObject();
                    AllobjectDetails.add(obj);
                    if(obj.getDate().equals(date)){
                        objectDetails.add(obj);
                    }

                } catch (EOFException e) {
//                    System.out.println(e);
                    break;
                }
            }
            fileInputStream.close();
            is.close();

        } catch (ClassNotFoundException | IOException e) {
            System.out.println();
        }
    }

    public boolean checkBirthday(String mail){
        for(SendEmailTLS object : AllobjectDetails){
            if(object.getEmail().equals(mail) && object.getSubject().equals("-Birthday Wish-")){
                if((object.getDate().equals(new SimpleDateFormat("yyyy/MM/dd").format(new Date())))){
                    return false;
                }
            }
        }
        return true;
    }
    public void printDetails(){
        for(SendEmailTLS object : objectDetails){
            System.out.println("Email :"+object.getEmail());
            System.out.println("Subject : "+object.getSubject());
            System.out.println("Content : "+object.getContent());
        }
    }
}
