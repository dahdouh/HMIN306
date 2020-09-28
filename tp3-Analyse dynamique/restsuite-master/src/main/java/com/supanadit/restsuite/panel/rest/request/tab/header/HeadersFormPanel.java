package com.supanadit.restsuite.panel.rest.request.tab.header;
import com.supanadit.restsuite.entity.CollectionHeaderEntity;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
public class HeadersFormPanel extends JScrollPane {
    public ArrayList<HeadersFormInputPanel> listInputPanel = new ArrayList<>();

    public ArrayList<HeadersFormInputPanel> listRemovedInputPanel = new ArrayList<>();

    public JPanel formGroupPanel;

    public JButton addField;

    public HeadersFormPanel() {
        formGroupPanel = new JPanel(new MigLayout("", "", "[]0[]"));
        addField = new JButton("Add Field");
        addField.addActionListener(( k) -> {
            addFormInput(new HeadersFormInputPanel(this));
        });
        formGroupPanel.add(addField, "pushx,growx,wrap");
        setViewportView(formGroupPanel);
    }

    public JPanel getPanel() {
        return formGroupPanel;
    }

    public void addFormInput(HeadersFormInputPanel headersFormInputPanel) {
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "updateChange");
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "add");
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "add");
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "add");
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "remove");
        // remove button add field
        formGroupPanel.remove(addField);
        // add form input
        formGroupPanel.add(headersFormInputPanel, "pushx,growx,wrap");
        // add back the button add field
        formGroupPanel.add(addField, "pushx,growx,wrap");
        // add to list input
        listInputPanel.add(headersFormInputPanel);
        // refresh ui
        updateChange();
    }

    public void addFormInput(CollectionHeaderEntity collectionHeaderEntity) {
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "addFormInput");
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "getId");
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "setId");
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "getValue");
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "getKey");
        // Get Key
        String key = collectionHeaderEntity.getKey();
        // Get Value
        String value = collectionHeaderEntity.getValue();
        // Declare headers form input panel
        HeadersFormInputPanel headersFormInputPanel = new HeadersFormInputPanel(this, key, value);
        // Set ID
        headersFormInputPanel.setId(collectionHeaderEntity.getId());
        // Add Form Input
        addFormInput(headersFormInputPanel);
    }

    public void clearFormInput() {
        com.supanadit.restsuite.logger.LogWriter.out("clearFormInput", "updateChange");
        // Clone
        ArrayList<HeadersFormInputPanel> listInputPanelClone = listInputPanel;
        // Clear original variable
        listInputPanel = new ArrayList<>();
        // Clear removed input list
        listRemovedInputPanel = new ArrayList<>();
        // Looping
        for (HeadersFormInputPanel formInputPanel : listInputPanelClone) {
            com.supanadit.restsuite.logger.LogWriter.out("clearFormInput", "remove");
            formInputPanel.remove();
        }
        updateChange();
    }

    public void updateChange() {
        com.supanadit.restsuite.logger.LogWriter.out("updateChange", "updateUI");
        formGroupPanel.updateUI();
    }
}