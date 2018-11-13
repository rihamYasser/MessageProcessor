package com.sales.messaging;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by riham.y.abdelmaksoud on 11/13/2018.
 */
public class MessageProcessorCaller {


    public String readInputMessagesFile(String fileName){
        try {
            Path path = Paths.get(getClass().getClassLoader()
                    .getResource(fileName).toURI());
            return new String(Files.readAllBytes(path));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in Reading file");
        }
        return "";
    }


    public static void main(String[] args){
        MessageProcessorCaller messageProcessorCaller = new MessageProcessorCaller();
        MessagingInterface messagingInterface = new MessagingInterface();
        for(int i=1;i<12; i++) {
            String fileContent = messageProcessorCaller.readInputMessagesFile("messages"+i+".json");
            if(fileContent == null || fileContent.equals("")){
                System.out.println( "Error");
            }
            System.out.println(messagingInterface.sendMessage(fileContent));
        }
    }
}
