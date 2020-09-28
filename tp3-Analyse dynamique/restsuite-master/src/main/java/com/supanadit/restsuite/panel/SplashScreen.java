package com.supanadit.restsuite.panel;
import com.supanadit.restsuite.helper.DefaultIcon;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JWindow;
import java.awt.*;
import javax.swing.*;
public class SplashScreen extends JWindow {
    public static int defaultWidth = 500;

    public static int defaultHeight = 300;

    public Dimension dimension;

    private long startTime;

    private int minimumMilliseconds;

    public SplashScreen() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dimension = new Dimension(defaultWidth, defaultHeight);
        setAlwaysOnTop(true);
        setIconImage(new DefaultIcon().getImage());
        setSize(dimension);
        setLocation((dim.width / 2) - (getSize().width / 2), (dim.height / 2) - (getSize().height / 2));
    }

    public void show(int minimumMilliseconds) {
        com.supanadit.restsuite.logger.LogWriter.out("show", "currentTimeMillis");
        com.supanadit.restsuite.logger.LogWriter.out("show", "setVisible");
        this.minimumMilliseconds = minimumMilliseconds;
        setVisible(true);
        startTime = System.currentTimeMillis();
    }

    public void close() {
        com.supanadit.restsuite.logger.LogWriter.out("close", "setVisible");
        com.supanadit.restsuite.logger.LogWriter.out("close", "currentTimeMillis");
        long elapsedTime = System.currentTimeMillis() - startTime;
        try {
            com.supanadit.restsuite.logger.LogWriter.out("close", "max");
            com.supanadit.restsuite.logger.LogWriter.out("close", "sleep");
            Thread.sleep(Math.max(minimumMilliseconds - elapsedTime, 0));
        } catch (InterruptedException e) {
            com.supanadit.restsuite.logger.LogWriter.out("close", "printStackTrace");
            e.printStackTrace();
        }
        setVisible(false);
    }
}