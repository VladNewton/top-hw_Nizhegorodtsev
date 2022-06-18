package server.log;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

public class WriteLog {

    public static File file = new File("logfile.data");

    private int port;
    private String adress;
    private String timeConnection;
    private String timeDisconnection;
    private ArrayList<String> quotes = new ArrayList<>();

    public WriteLog(){}

    public WriteLog(Socket socket, Date connection, Date disconnection) {
        this.setPort(socket.getPort());
        this.setAdress(socket.getInetAddress().toString());
        this.setTimeConnection(connection.toString());
        this.setTimeDisconnection(disconnection.toString());
    }

    public WriteLog(Socket socket, Date connection) {
        this.setPort(socket.getPort());
        this.setAdress(socket.getInetAddress().toString());
        this.setTimeConnection(connection.toString());
    }

    public ArrayList<String> getQuotes() {
        return quotes;
    }

    public void addQuotes(String quote) {
        this.quotes.add(quote);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTimeConnection() {
        return timeConnection;
    }

    public void setTimeConnection(String timeConnection) {
        this.timeConnection = timeConnection;
    }

    public String getTimeDisconnection() {
        return timeDisconnection;
    }

    public void setTimeDisconnection(String timeDisconnection) {
        this.timeDisconnection = timeDisconnection;
    }



    //Процедура записи времени, адреса, порта
    public void WriteInFile() {
        try {
            file.createNewFile();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file, true));
            bos.write(this.toString().getBytes(StandardCharsets.UTF_8));
            bos.write("-----------------------------------------------------------\n".getBytes(StandardCharsets.UTF_8));
            for (int i = 0; i < this.quotes.size(); i++) {
                bos.write((quotes.get(i)+'\n').getBytes(StandardCharsets.UTF_8));
            }
            bos.write(("*************************************************************"+'\n').getBytes(StandardCharsets.UTF_8));
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return "Port: " + port + '\n'+
                "Adress: " + adress + '\n' +
                "Time is connection: " + timeConnection + '\n' +
                "Time is disconnection: " + timeDisconnection + '\n';
    }
}
