package com.sales.messaging;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sales.messaging.model.Message;
import com.sales.messaging.model.Response;
import com.sales.messaging.model.ResponseStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by riham.y.abdelmaksoud on 11/13/2018.
 * Main class to run the messaging processing application. It acts as the client of the Message processing application
 * The class is for testing purpose only!
 */
public class MessageSender {


    public List<Path> getFilesPaths(String directoryName){
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            Path configFilePath = Paths.get(classLoader.getResource(directoryName).toURI());
           return Files.list(configFilePath).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in Reading files");
        }
        return null;
    }

    public String getFileContent(String fileName){
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
        MessageSender messageSender = new MessageSender();
        MessageReceiver messageReceiver = new MessageReceiver();
        if(args != null && args.length == 2){
            if(args[0].equals("bulk") ){
                sendBulkMessage(messageSender, messageReceiver,args[1]);
                return;
            }
        }
        sendMessages(messageSender, messageReceiver);
    }

    private static void sendBulkMessage(MessageSender messageSender, MessageReceiver messageReceiver, String fileName) {

        String fileContent= messageSender.getFileContent(fileName);
        if(fileContent == null || fileContent.equals("")){
            System.out.println( "File:"+ fileName +" is empty!");
            return;
        }
        String responseJson = messageReceiver.receiveBulkMessage(fileContent);
        Gson jsonObj = new Gson();
        Response response = jsonObj.fromJson(responseJson, Response.class);
        if(response.getStatus().equals(ResponseStatus.ERROR)){
            System.out.println(response.getMessage());
        }
    }

    private static void sendMessages(MessageSender messageSender, MessageReceiver messageReceiver) {
        List<Path> paths = messageSender.getFilesPaths("sample_messages");
        if(paths == null){
            System.out.println("No File To process!");
            return;
        }
        for(Path path: paths) {
            String fileContent= "";
            try {
                fileContent = new String(Files.readAllBytes(path));
            } catch (IOException e) {
                System.out.println("Error in Reading file:"+path.getFileName());
            }
            if(fileContent == null || fileContent.equals("")){
                System.out.println( "File:"+ path.getFileName()+" is empty!");
                continue;
            }
            String responseJson = messageReceiver.receiveMessage(fileContent);
            Gson jsonObj = new Gson();
            Response response = jsonObj.fromJson(responseJson, Response.class);
            if(response.getStatus().equals(ResponseStatus.ERROR)){
                System.out.println(response.getMessage());
            }
        }
    }
}
