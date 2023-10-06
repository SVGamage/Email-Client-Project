package Client;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BirthdaySender{
    private String currentDate;
    private ReadFile_interface sendDetails;
    public BirthdaySender(ReadFile_interface sendDetails) {
        currentDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        this.sendDetails = sendDetails;
    }
    public void checkToSend(){
        String currentMonth = currentDate.split("/")[1];
        String currentDays = currentDate.split("/")[2];
        sendDetails.recipients();
        var sendDetailList =sendDetails.getDetailList();

        for (String[] j: sendDetailList){
            String month = j[3].split("/")[1];
            String day = j[3].split("/")[2];
            if(currentMonth.equals(month) && currentDays.equals(day)){
                if(j[1].contains("@gmail.com")){

                    var chkSend1 = new Deserialization("src/Client/saveOb.ser");
                    chkSend1.getSavedObjects(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
                    if(chkSend1.checkBirthday(j[1])){
                        var sendTo_1 = new SendEmailTLS(j[1],"-Birthday Wish-","Wish you a Happy Birthday!! "+j[0]+"\n"+"\n"+"Sithum");
                        sendTo_1.sendMail();
                        var ser1 = new Serialization("src/Client/saveOb.ser",sendTo_1);
                        ser1.saveObjects();
                    }

                }
                else{
                    var chkSend2 = new Deserialization("src/Client/saveOb.ser");
                    chkSend2.getSavedObjects(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
                    if(chkSend2.checkBirthday(j[2])){
                        var sendTo_2 = new SendEmailTLS(j[2],"-Birthday Wish-","Happy Birthday Brother!! "+"\n"+"\n"+"Sithum");
                        sendTo_2.sendMail();
                        var ser2 = new Serialization("src/Client/saveOb.ser",sendTo_2);
                        ser2.saveObjects();
                    }

                }

                System.out.println();
            }
        }
    }
}
