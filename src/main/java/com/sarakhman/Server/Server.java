package com.sarakhman.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private int port;
    private String staticPath;


    public void setPort(int port) {
        this.port = port;
    }

    public void setWebAppPath(String path) {
        staticPath = path;
    }


    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                try {
                    (Socket socket = serverSocket.accept();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())))

                while (true) {
                    String messageFromClient = bufferedReader.readLine();

                    List<String> content = prepareStringFromClient(messageFromClient);
                    if (content.get(0) != null) {
                        ContentManager contentManager = new ContentManager(messageFromClient, bufferedWriter, staticPath);
                        contentManager.push();
                    }
                }
            }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static List<String> prepareStringFromClient(String messageFromClient) {
        List<String> forWork = divideMessageToSentence(messageFromClient);
        List<String> result = getFirstSentenceFromMessage(forWork);
        List<String> forReturn = new ArrayList<>() {
        };
        for (int i = 0; i < result.size() - 1; i++) {
            forReturn.add(result.get(i + 1));
        }
        return forReturn;
    }

    protected static List<String> divideMessageToSentence(String message) {
        String[] result = message.split("\n");
        List<String> forReturn = new ArrayList<>();
        for (int i = 0; i < result.length; i++) {
            forReturn.add(result[i]);

        }
        return forReturn;
    }

    protected static List<String> getFirstSentenceFromMessage(List<String> message) {
        String forJob = message.get(0);
        String[] splitString = forJob.split(" ");
        List<String> result = new ArrayList<>();

        for (int i = 0; i < splitString.length; i++) {
            result.add(splitString[i]);
        }
        return result;
    }


}
