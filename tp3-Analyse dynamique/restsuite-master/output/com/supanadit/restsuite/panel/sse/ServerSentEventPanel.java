package com.supanadit.restsuite.panel.sse;
import com.here.oksse.OkSse;
import com.here.oksse.ServerSentEvent;
import com.here.oksse.ServerSentEvent.Listener;
import com.supanadit.restsuite.component.input.InputSseURL;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import com.here.oksse.OkSse;
import com.here.oksse.ServerSentEvent;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import okhttp3.Request;
import okhttp3.Response;
public class ServerSentEventPanel extends JPanel {
    ServerSentEvent sse;

    OkSse okSse;

    String connectDisconnect = "Connect";

    boolean isConnected = false;

    JButton connectDisconnectButton;

    InputSseURL inputURL;

    int connection = 0;

    public ServerSentEventPanel() {
        setLayout(new MigLayout("insets 10 10 10 10"));
        inputURL = new InputSseURL();
        connectDisconnectButton = new JButton(connectDisconnect);
        add(inputURL, "growx,pushx");
        add(connectDisconnectButton, "wrap");
        add(new JLabel("Message"), "pushx,growx,wrap");
        JTextArea messageTextArea = new JTextArea();
        add(new JScrollPane(messageTextArea), "push,grow,span");
        ServerSentEvent.Listener listener = new ServerSentEvent.Listener() {
            @Override
            public void onOpen(ServerSentEvent sse, Response response) {
                System.out.println("onOpen -> println");
                System.out.println("onOpen -> setText");
                System.out.println("onOpen -> setEnabled");
                connectDisconnectButton.setEnabled(true);
                connectDisconnectButton.setText("Disconnect");
                System.out.println("A connection just opened");
                if (connection == 0) {
                    System.out.println("onOpen -> getText");
                    System.out.println("onOpen -> concat");
                    System.out.println("onOpen -> concat");
                    System.out.println("onOpen -> append");
                    messageTextArea.append("Connected to ".concat(inputURL.getText()).concat("\n"));
                    connection += 1;
                }
            }

            @Override
            public void onMessage(ServerSentEvent sse, String id, String event, String message) {
                System.out.println("onMessage -> concat");
                System.out.println("onMessage -> append");
                messageTextArea.append(message.concat("\n"));
            }

            @Override
            public void onComment(ServerSentEvent sse, String comment) {
                System.out.println("onComment -> printf");
                // When a comment is received
                System.out.printf("Have a comment %s \n", comment);
            }

            @Override
            public boolean onRetryTime(ServerSentEvent sse, long milliseconds) {
                System.out.println("onRetryTime -> println");
                System.out.println("Retry Time");
                return true;// True to use the new retry time received by SSE

            }

            @Override
            public boolean onRetryError(ServerSentEvent sse, Throwable throwable, Response response) {
                System.out.println("onRetryError -> getCause");
                boolean continueProcess = true;
                if (throwable.getCause() != null) {
                    System.out.println("onRetryError -> setStatus");
                    System.out.println("onRetryError -> getMessage");
                    System.out.println("onRetryError -> getMessage");
                    System.out.println("onRetryError -> append");
                    continueProcess = false;
                    messageTextArea.append(throwable.getMessage().concat("\n"));
                    setStatus(false);
                }
                return continueProcess;
            }

            @Override
            public void onClosed(ServerSentEvent sse) {
                System.out.println("onClosed -> println");
                System.out.println("onClosed -> getText");
                System.out.println("onClosed -> concat");
                System.out.println("onClosed -> concat");
                System.out.println("onClosed -> append");
                System.out.println("onClosed -> setText");
                System.out.println("onClosed -> setEnabled");
                System.out.println("onClosed -> setEnabled");
                inputURL.setEnabled(true);
                connectDisconnectButton.setEnabled(true);
                connectDisconnectButton.setText("Connect");
                connection = 0;
                messageTextArea.append("Disconnected from ".concat(inputURL.getText()).concat("\n"));
                System.out.println("Connection is Closed");
            }

            @Override
            public Request onPreRetry(ServerSentEvent serverSentEvent, Request request) {
                System.out.println("onPreRetry -> println");
                System.out.println("Pre Requesting");
                return request;
            }
        };
        connectDisconnectButton.addActionListener(( e) -> {
            if (!isConnected) {
                String url = inputURL.getText();
                if (!url.isEmpty()) {
                    Request request = new Request.Builder().url(inputURL.getText()).build();
                    okSse = new OkSse();
                    sse = okSse.newServerSentEvent(request, listener);
                    setStatus(true);
                    inputURL.setEnabled(false);
                    // Connection Button Logic
                    connectDisconnectButton.setEnabled(false);
                    connectDisconnectButton.setText("Connecting");
                }
            } else {
                connectDisconnectButton.setEnabled(false);
                connectDisconnectButton.setText("Disconnecting");
                setStatus(false);
            }
        });
    }

    public void setStatus(boolean status) {
        isConnected = status;
        if (!isConnected) {
            if (sse != null) {
                System.out.println("setStatus -> close");
                sse.close();
            }
        }
    }
}