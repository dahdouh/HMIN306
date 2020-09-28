package com.supanadit.restsuite.renderer;
import com.supanadit.restsuite.model.RequestTypeModel;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.*;
import javax.swing.*;
public class RequestTypeRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        com.supanadit.restsuite.logger.LogWriter.out("getListCellRendererComponent", "getListCellRendererComponent");
        if (value instanceof RequestTypeModel) {
            com.supanadit.restsuite.logger.LogWriter.out("getListCellRendererComponent", "getName");
            value = ((RequestTypeModel) (value)).getName();
        }
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        return this;
    }
}