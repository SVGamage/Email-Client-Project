package Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile implements ReadFile_interface{
    private final ArrayList<String[]> detailList = new ArrayList<>();
    private Scanner Read;
    private File file;
    public ReadFile() {
        try{
            file = new File("src/Client/clientList.txt");
            Read = new Scanner(file);

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    @Override
    public void recipients(){
        while (Read.hasNextLine()) {
            String[] data = Read.nextLine().split(": ");
            if(!(data[0].equals("Official"))){
                String[] details = data[1].split(",");
                detailList.add(details);
            }
        }
        Read.close();
    }

    public void printRecipient(String date) {
        for(String[] i: detailList){
            if(date.equals(i[3])){
                System.out.println("Name --> "+i[0]);
            }
        }
        System.out.println();
        System.out.println("Done.");

    }
    @Override
    public ArrayList<String[]> getDetailList() {
        return detailList;
    }
}
