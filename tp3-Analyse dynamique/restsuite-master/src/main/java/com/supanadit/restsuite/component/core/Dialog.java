package com.supanadit.restsuite.component.core;
import com.supanadit.restsuite.helper.DefaultIcon;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
public class Dialog extends JDialog {
    public Dimension dimension;

    public Dialog(String title, int width, int height) {
        setTitle(title);
        setIconImage(new DefaultIcon().getImage());
        dimension = new Dimension(width, height);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dimension);
        setLayout(new MigLayout());
        setAlwaysOnTop(true);
        setLocation((dim.width / 2) - (getSize().width / 2), (dim.height / 2) - (getSize().height / 2));
    }

    public Dialog(String title) {
        this(title, Frame.defaultWidth, Frame.defaultHeight);
    }

    public void close() {
        com.supanadit.restsuite.logger.LogWriter.out("close", "setVisible");
        setVisible(false);
    }

    public void open() {
        com.supanadit.restsuite.logger.LogWriter.out("open", "setVisible");
        setVisible(true);
    }
}