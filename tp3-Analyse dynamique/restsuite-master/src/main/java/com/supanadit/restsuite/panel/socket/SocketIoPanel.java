package com.supanadit.restsuite.panel.socket;
import com.supanadit.restsuite.component.input.InputSocketIoListener;
import com.supanadit.restsuite.component.input.InputSocketIoMessage;
import com.supanadit.restsuite.component.input.InputSocketIoURL;
import com.supanadit.restsuite.listener.socket.SocketIOListenerTableRowMenuListener;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.socket.client.IO;
import io.socket.client.Socket;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.RTextScrollPane;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.socket.client.IO;
import io.socket.client.Socket;
import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.RTextScrollPane;
public class SocketIoPanel extends JPanel {
    Socket socket;

    boolean isConnected = false;

    String connectionText = "Connect";

    JButton connectDisconnectButton;

    JButton emitButton;

    DefaultTableModel listenerDefaultModel;

    JTable listenerTable;

    InputSocketIoURL socketIoURL;

    RSyntaxTextArea responseBody;

    Disposable connectDisposable;

    Observable<Long> tryConnect;

    public SocketIoPanel() {
        setLayout(new MigLayout("insets 10 10 10 10"));
        Color background = UIManager.getColor("Table.background");
        Color lineColor = UIManager.getColor("Table.gridColor");
        Color fontColor = UIManager.getColor("FormattedTextField.foreground");
        Color selectionColor = UIManager.getColor("FormattedTextField.selectionBackground");
        Color headerForeground = UIManager.getColor("TableHeader.foreground");
        connectDisconnectButton = new JButton(connectionText);
        InputSocketIoMessage emitChannel = new InputSocketIoMessage();
        JPanel socketIoHeadPanel = new JPanel(new MigLayout("insets 10 10 n 10"));
        socketIoURL = new InputSocketIoURL();
        socketIoHeadPanel.add(socketIoURL, "growx,pushx");
        socketIoHeadPanel.add(connectDisconnectButton);
        JPanel socketIoLeftPanel = new JPanel(new MigLayout("w 200!,insets n 10 n n"));
        socketIoLeftPanel.add(new JLabel("Send Message"), "growx,pushx,wrap");
        socketIoLeftPanel.add(emitChannel, "growx,pushx,wrap");
        RSyntaxTextArea emitBody = new RSyntaxTextArea();
        emitBody.setCodeFoldingEnabled(true);
        emitBody.setCurrentLineHighlightColor(background);
        emitBody.setBackground(background);
        emitBody.setTabLineColor(background);
        emitBody.setBorder(BorderFactory.createLineBorder(lineColor));
        emitBody.setForeground(fontColor);
        emitBody.setSelectionColor(selectionColor);
        // This Code let selected text to be white color
        emitBody.setUseSelectedTextColor(true);
        emitBody.setSelectedTextColor(Color.white);
        emitButton = new JButton("Emit");
        emitButton.setEnabled(isConnected);
        InputSocketIoListener inputListener = new InputSocketIoListener();
        RTextScrollPane emitBodyScrollPane = new RTextScrollPane(emitBody);
        Gutter gutterEmitBodyScrollPane = emitBodyScrollPane.getGutter();
        gutterEmitBodyScrollPane.setBorderColor(lineColor);
        gutterEmitBodyScrollPane.setLineNumberColor(headerForeground);
        listenerDefaultModel = new DefaultTableModel();
        listenerDefaultModel.addColumn("Listener");
        listenerTable = new JTable(listenerDefaultModel);
        listenerTable.addMouseListener(new SocketIOListenerTableRowMenuListener(this));
        JScrollPane listenerScrollPane = new JScrollPane(listenerTable);
        JButton addListenerButton = new JButton("Add Listener");
        socketIoLeftPanel.add(emitBodyScrollPane, "grow,push,wrap");
        socketIoLeftPanel.add(emitButton, "growx,pushx,wrap");
        JPanel socketIoRightPanel = new JPanel(new MigLayout("w 200!,insets n n n 10"));
        socketIoRightPanel.add(new JLabel("Listener"), "pushx,growx,wrap");
        socketIoRightPanel.add(inputListener, "pushx,growx,wrap");
        socketIoRightPanel.add(addListenerButton, "pushx,growx,wrap");
        socketIoRightPanel.add(new JSeparator(), "pushx,growx,wrap");
        socketIoRightPanel.add(listenerScrollPane, "push,grow");
        add(new JLabel("Response Message"), "growx,pushx,wrap");
        responseBody = new RSyntaxTextArea();
        responseBody.setCodeFoldingEnabled(true);
        responseBody.setCurrentLineHighlightColor(background);
        responseBody.setBackground(background);
        responseBody.setTabLineColor(background);
        responseBody.setBorder(BorderFactory.createLineBorder(lineColor));
        responseBody.setForeground(fontColor);
        responseBody.setSelectionColor(selectionColor);
        // This Code let selected text to be white color
        responseBody.setUseSelectedTextColor(true);
        responseBody.setSelectedTextColor(Color.white);
        RTextScrollPane responseBodyScrollPane = new RTextScrollPane(responseBody);
        Gutter gutterResponseBodyScrollPane = responseBodyScrollPane.getGutter();
        gutterResponseBodyScrollPane.setBorderColor(lineColor);
        gutterResponseBodyScrollPane.setLineNumberColor(headerForeground);
        add(responseBodyScrollPane, "center,grow,push");
        add(socketIoHeadPanel, "north");
        add(socketIoLeftPanel, "west,pushy");
        add(socketIoRightPanel, "east,pushy");
        connectDisconnectButton.addActionListener(( e) -> {
            String url = socketIoURL.getText();
            if (!isConnected) {
                if (!url.isEmpty()) {
                    try {
                        socket = IO.socket(url);
                        tryConnect = // Waiting for 4 second
                        Observable.interval(1, TimeUnit.SECONDS).take(4).map(( v) -> v + 1).doOnComplete(() -> {
                            if (!isConnected) {
                                responseBody.append("Disconnected from ".concat(socketIoURL.getText()).concat("\n"));
                                socketIoURL.setEnabled(true);
                                setStatus(false);
                            }
                        });
                        connectDisposable = tryConnect.subscribe();
                        socket.on(Socket.EVENT_CONNECT, ( args) -> {
                            connectDisposable.dispose();
                            reloadSocketListener();
                            setStatus(true);
                        }).on(Socket.EVENT_DISCONNECT, ( args) -> {
                            tryConnect.subscribe();
                        });
                        socket.connect();
                        connectDisconnectButton.setEnabled(false);
                        connectDisconnectButton.setText("Connecting");
                        socketIoURL.setEnabled(false);
                    } catch (URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                setStatus(false);
            }
        });
        emitButton.addActionListener(( e) -> {
            if (socket != null) {
                if (!(emitChannel.getText().isEmpty() && emitBody.getText().isEmpty())) {
                    socket.emit(emitChannel.getText(), emitBody.getText());
                }
            }
        });
        addListenerButton.addActionListener(( e) -> {
            String listener = inputListener.getText();
            if (!listener.isEmpty()) {
                listenerDefaultModel.addRow(new Object[]{ listener });
                reloadSocketListener();
            }
        });
    }

    public void reloadSocketListener() {
        if (socket != null) {
            com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "getDataVector");
            com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "getDataVector");
            listenerDefaultModel.getDataVector().forEach(( vector) -> {
                com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "hasListeners");
                com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "toString");
                com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "toString");
                com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "toString");
                com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "toString");
                com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "toString");
                String listener = vector.toString().replace(",", "").replace("[", "").replace("]", "").trim();
                if (!socket.hasListeners(listener)) {
                    com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "on");
                    socket.on(listener, ( args) -> {
                        com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "concat");
                        com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "concat");
                        com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "concat");
                        com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "append");
                        com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "toString");
                        com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "toString");
                        com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "toString");
                        com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "toString");
                        com.supanadit.restsuite.logger.LogWriter.out("reloadSocketListener", "toString");
                        String body = Arrays.toString(args).replace(",", "").replace("[", "").replace("]", "").trim();
                        responseBody.append(listener.concat(" : ").concat(body).concat("\n"));
                    });
                }
            });
        }
    }

    public void deleteSelectedRowListener() {
        com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRowListener", "getSelectedRow");
        if (!(listenerTable.getSelectedRow() < 0)) {
            com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRowListener", "getSelectedRow");
            com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRowListener", "removeRow");
            com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRowListener", "getSelectedRow");
            com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRowListener", "getValueAt");
            com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRowListener", "getValueAt");
            String deletedListener = listenerDefaultModel.getValueAt(listenerTable.getSelectedRow(), 0).toString();
            listenerDefaultModel.removeRow(listenerTable.getSelectedRow());
            if (socket != null) {
                com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRowListener", "hasListeners");
                if (socket.hasListeners(deletedListener)) {
                    com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRowListener", "off");
                    socket.off(deletedListener);
                }
            }
        }
    }

    public void setStatus(boolean status) {
        com.supanadit.restsuite.logger.LogWriter.out("setStatus", "setEnabled");
        com.supanadit.restsuite.logger.LogWriter.out("setStatus", "setEnabled");
        isConnected = status;
        emitButton.setEnabled(isConnected);
        // It always be true
        connectDisconnectButton.setEnabled(true);
        if (isConnected) {
            com.supanadit.restsuite.logger.LogWriter.out("setStatus", "getButtonText");
            com.supanadit.restsuite.logger.LogWriter.out("setStatus", "setText");
            connectDisconnectButton.setText(getButtonText());
        } else if (socket != null) {
            com.supanadit.restsuite.logger.LogWriter.out("setStatus", "close");
            com.supanadit.restsuite.logger.LogWriter.out("setStatus", "getButtonText");
            com.supanadit.restsuite.logger.LogWriter.out("setStatus", "setText");
            connectDisconnectButton.setText(getButtonText());
            socket.close();
        }
    }

    public String getButtonText() {
        return isConnected ? "Disconnect" : "Connect";
    }
}