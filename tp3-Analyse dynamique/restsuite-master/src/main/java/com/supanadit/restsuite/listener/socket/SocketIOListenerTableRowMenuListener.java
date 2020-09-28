package com.supanadit.restsuite.listener.socket;
import com.supanadit.restsuite.panel.socket.SocketIoPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class SocketIOListenerTableRowMenuListener extends MouseAdapter {
    protected SocketIoPanel panel;

    public SocketIOListenerTableRowMenuListener(SocketIoPanel panel) {
        this.panel = panel;
    }

    public void mousePressed(MouseEvent e) {
        com.supanadit.restsuite.logger.LogWriter.out("mousePressed", "isPopupTrigger");
        if (e.isPopupTrigger()) {
            com.supanadit.restsuite.logger.LogWriter.out("mousePressed", "doPop");
            doPop(e);
        }
    }

    public void mouseReleased(MouseEvent e) {
        com.supanadit.restsuite.logger.LogWriter.out("mouseReleased", "isPopupTrigger");
        if (e.isPopupTrigger()) {
            com.supanadit.restsuite.logger.LogWriter.out("mouseReleased", "doPop");
            doPop(e);
        }
    }

    private void doPop(MouseEvent e) {
        com.supanadit.restsuite.logger.LogWriter.out("doPop", "getY");
        com.supanadit.restsuite.logger.LogWriter.out("doPop", "getX");
        com.supanadit.restsuite.logger.LogWriter.out("doPop", "getComponent");
        com.supanadit.restsuite.logger.LogWriter.out("doPop", "show");
        SocketIOListenerMouseTableRowMenu menu = new SocketIOListenerMouseTableRowMenu(panel);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}