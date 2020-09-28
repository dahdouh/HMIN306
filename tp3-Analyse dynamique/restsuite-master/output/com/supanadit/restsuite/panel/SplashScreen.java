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
        System.out.println("show -> currentTimeMillis");
        System.out.println("show -> setVisible");
        this.minimumMilliseconds = minimumMilliseconds;
        setVisible(true);
        startTime = System.currentTimeMillis();
    }

    public void close() {
        System.out.println("close -> setVisible");
        System.out.println("close -> currentTimeMillis");
        long elapsedTime = System.currentTimeMillis() - startTime;
        try {
            System.out.println("close -> max");
            System.out.println("close -> sleep");
            Thread.sleep(Math.max(minimumMilliseconds - elapsedTime, 0));
        } catch (InterruptedException e) {
            System.out.println("close -> printStackTrace");
            e.printStackTrace();
        }
        setVisible(false);
    }
}