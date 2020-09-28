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
        System.out.println("mousePressed -> isPopupTrigger");
        if (e.isPopupTrigger()) {
            System.out.println("mousePressed -> doPop");
            doPop(e);
        }
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased -> isPopupTrigger");
        if (e.isPopupTrigger()) {
            System.out.println("mouseReleased -> doPop");
            doPop(e);
        }
    }

    private void doPop(MouseEvent e) {
        System.out.println("doPop -> getY");
        System.out.println("doPop -> getX");
        System.out.println("doPop -> getComponent");
        System.out.println("doPop -> show");
        SocketIOListenerMouseTableRowMenu menu = new SocketIOListenerMouseTableRowMenu(panel);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}