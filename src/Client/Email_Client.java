package Client;

//Index : 200681N

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Email_Client {
    private static int numberLines;

    public static void main(String[] args) {
        boolean isExit = true;
        while (isExit){
            System.out.println("Please be patient.The Email client is Loading...");
            var file = new ReadFile();
            var automateBirthdaySend = new BirthdaySender(file);
            automateBirthdaySend.checkToSend();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter option type: \n"
                    + "1 - Adding a new recipient\n"
                    + "2 - Sending an email\n"
                    + "3 - Printing out all the recipients who have birthdays\n"
                    + "4 - Printing out details of all the emails sent\n"
                    + "5 - Printing out the number of recipient objects in the application");

            try{
                BufferedReader reader = new BufferedReader(new FileReader("src/Client/clientList.txt"));
                while (reader.readLine() != null) numberLines++;
                reader.close();
            }
            catch(IOException e){
                System.out.println(e);
            }




            int option = scanner.nextInt();
            switch(option){
                case 1:
                    System.out.println("input formats :");
                    System.out.println("Official: nimal,nimal@gmail.com,ceo");
                    System.out.println("Official_friend: nimal,nimal@gmail.com,ceo,2000/10/10");
                    System.out.println("Personal: nimal,<nick name>,nimal@gmail.com,2000/10/10");
                    var scan1 = new Scanner(System.in);
                    String contentWrite = scan1.nextLine();
//              System.out.println(contentWrite);
                    var writeFile = new WriteFile(contentWrite);
                    numberLines++;
                    writeFile.Writer();

                    break;
                case 2:
                    System.out.println("input format : email,subject,content");
                    var scan2 = new Scanner(System.in);
                    String detail = scan2.nextLine();
                    String[] detaillist = detail.split(",");
                    String email=detaillist[0];
                    String subject=detaillist[1];
                    String content=detaillist[2];
                    System.out.println("Sending...");
                    var sending = new SendEmailTLS(email,subject,content);
                    sending.sendMail();
                    System.out.println("Done.");
                    var ser3 = new Serialization("src/Client/saveOb.ser",sending);
                    ser3.saveObjects();
                    break;
                case 3:
                    System.out.println("input format : yyyy/MM/dd");
                    var scan3 = new Scanner(System.in);
                    String date = scan3.nextLine();
                    var recipient1 = new ReadFile();
                    recipient1.recipients();
                    recipient1.printRecipient(date);
                    break;
                case 4:
                    System.out.println("input format : yyyy/MM/dd");
                    var scan4 = new Scanner(System.in);
                    String Date = scan4.nextLine();
                    var deSerialization = new Deserialization("src/Client/saveOb.ser");
                    deSerialization.getSavedObjects(Date);
                    deSerialization.printDetails();
                    break;
                case 5:
                    System.out.println("Client Objects : "+numberLines);
                    break;
                case 0:
                    isExit = false;
                    break;

            }
        }
        System.out.println("Email Client has been stopped..");





    }
}

