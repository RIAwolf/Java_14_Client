package lt.kaunascoding.socket.client.model;


import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Model {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 8080;

    private Socket _kanalas;
    private ObjectInputStream _inputStream;
    private ObjectOutputStream _outputStream;

    public Model() {
        try {
            _kanalas = new Socket(HOST, PORT);
            _inputStream = new ObjectInputStream(_kanalas.getInputStream());

            _outputStream = new ObjectOutputStream(_kanalas.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendData(String value) {

        try {
            _outputStream.writeUTF(value);
            _outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public String getData() {
        String result = "";

        String zinute = "";
        int bytesAvailable = 0;
        try {
            bytesAvailable = _inputStream.available();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (bytesAvailable <= 0) {
            return result;
        }

        while (bytesAvailable > 0) {
            try {
                zinute = _inputStream.readUTF();
                bytesAvailable = _inputStream.available();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (zinute != null) {
                result += zinute;
                zinute = null;

            }
        }
        System.out.println("Ciklas baigesi");


        return result;
    }
}
