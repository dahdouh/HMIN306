package com.supanadit.restsuite.panel.rest.request.tab.header;
import com.supanadit.restsuite.component.input.api.InputBodyKey;
import com.supanadit.restsuite.component.input.api.InputBodyValue;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
public class HeadersFormInputPanel extends JPanel implements DocumentListener {
    int id;

    InputBodyKey keyField;

    InputBodyValue valueField;

    HeadersFormPanel headersFormPanel;

    public HeadersFormInputPanel(HeadersFormPanel headersFormPanel, String key, String value) {
        this.headersFormPanel = headersFormPanel;
        setLayout(new MigLayout("insets 0 0 0 0", "[135]5[100]5[]"));
        keyField = new InputBodyKey(key);
        valueField = new InputBodyValue(value);
        add(keyField, "pushx,growx");
        add(valueField, "pushx,growx");
        keyField.getDocument().addDocumentListener(this);
        valueField.getDocument().addDocumentListener(this);
        JButton removeButton = new JButton("X");
        removeButton.addActionListener(( e) -> {
            removeFromStorage();
        });
        add(removeButton);
    }

    public HeadersFormInputPanel(HeadersFormPanel headersFormPanel) {
        this(headersFormPanel, null, null);
    }

    public void remove() {
        System.out.println("remove -> updateChange");
        System.out.println("remove -> getPanel");
        System.out.println("remove -> getPanel");
        System.out.println("remove -> remove");
        System.out.println("remove -> getPanel");
        System.out.println("remove -> getPanel");
        headersFormPanel.getPanel().remove(this);
        headersFormPanel.listInputPanel.remove(this);
        headersFormPanel.getPanel().updateUI();
        headersFormPanel.updateChange();
    }

    public void removeFromStorage() {
        System.out.println("removeFromStorage -> remove");
        remove();
        if (id != 0) {
            System.out.println("removeFromStorage -> add");
            headersFormPanel.listRemovedInputPanel.add(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InputBodyKey getKeyField() {
        return keyField;
    }

    public InputBodyValue getValueField() {
        return valueField;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        System.out.println("insertUpdate -> updateChange");
        headersFormPanel.updateChange();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        System.out.println("removeUpdate -> updateChange");
        headersFormPanel.updateChange();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        System.out.println("changedUpdate -> updateChange");
        headersFormPanel.updateChange();
    }
}