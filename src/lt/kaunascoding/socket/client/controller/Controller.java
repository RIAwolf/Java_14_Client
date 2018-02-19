package lt.kaunascoding.socket.client.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lt.kaunascoding.socket.client.model.Model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Controller {

    public TextArea logArea;
    public TextField nickInput;
    public TextField messageInput;
    public Button sendButton;

    private Model _model;

    public Controller() {

        _model = new Model();

        run();
    }

    private void run(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                doTimer();
            }
        }, 500, 500);
    }


    public void doTimer(){
        logArea.appendText(_model.getData());

    }
    public void onSendButton() {
        String msg = "";
        msg += nickInput.getText();
        msg += ":" + messageInput.getText() + "\n";
        _model.sendData(msg);
        logArea.appendText(msg);
        sendButton.setDisable(true);
        messageInput.clear();
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        if (nickInput.getText().equals("") || messageInput.getText().equals("")) {
            sendButton.setDisable(true);
        } else {
            sendButton.setDisable(false);
        }

        if (sendButton.isDisabled() == false) {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                onSendButton();
            }
        }
    }
}
