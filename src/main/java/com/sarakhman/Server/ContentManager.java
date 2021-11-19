package com.sarakhman.Server;

import java.io.*;

public class ContentManager {
    String pathToFile;
    BufferedWriter bufferedWriter;
    String staticPathWebbApp;
    private static final String beginMessage = "HTTP/1.1 200 OK\n\n";


    ContentManager(String pathToFile, BufferedWriter bufferedWriter, String staticPathWebbApp) {
        this.pathToFile = pathToFile;
        this.bufferedWriter = bufferedWriter;
        this.staticPathWebbApp = staticPathWebbApp;
    }


    public void push() {
        File file = new File(staticPathWebbApp + pathToFile);
        if (!file.exists()) {
            try {
                bufferedWriter.write("You write wrong path to resources. Try again!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                bufferedWriter.write(beginMessage);
                String line;
                while ((line= bufferedReader.readLine())!=null){
                    bufferedWriter.write(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



}
