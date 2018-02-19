package lt.kaunascoding.socket.client.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
        String result = null;
        try {
            result = _inputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
