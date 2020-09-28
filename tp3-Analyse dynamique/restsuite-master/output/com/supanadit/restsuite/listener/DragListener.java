package com.supanadit.restsuite.listener;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
public class DragListener extends MouseInputAdapter {
    Point location;

    MouseEvent pressed;

    public void mousePressed(MouseEvent me) {
        pressed = me;
    }

    public void mouseDragged(MouseEvent me) {
        System.out.println("mouseDragged -> setLocation");
        System.out.println("mouseDragged -> getY");
        System.out.println("mouseDragged -> getY");
        System.out.println("mouseDragged -> getX");
        System.out.println("mouseDragged -> getX");
        System.out.println("mouseDragged -> getLocation");
        System.out.println("mouseDragged -> getComponent");
        Component component = me.getComponent();
        location = component.getLocation(location);
        int x = (location.x - pressed.getX()) + me.getX();
        int y = (location.y - pressed.getY()) + me.getY();
        component.setLocation(x, y);
    }
}