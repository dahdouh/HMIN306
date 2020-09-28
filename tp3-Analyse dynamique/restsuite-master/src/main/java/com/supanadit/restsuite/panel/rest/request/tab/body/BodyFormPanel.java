package com.supanadit.restsuite.panel.rest.request.tab.body;
import com.supanadit.restsuite.entity.CollectionBodyEntity;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
public class BodyFormPanel extends JScrollPane {
    public ArrayList<BodyFormInputPanel> listInputPanel = new ArrayList<>();

    public ArrayList<BodyFormInputPanel> listRemovedInputPanel = new ArrayList<>();

    public JPanel formGroupPanel;

    public JButton addField;

    public BodyFormPanel() {
        formGroupPanel = new JPanel(new MigLayout("", "", "[]0[]"));
        addField = new JButton("Add Field");
        addField.addActionListener(( k) -> {
            addFormInput(new BodyFormInputPanel(this));
        });
        formGroupPanel.add(addField, "pushx,growx,wrap");
        setViewportView(formGroupPanel);
    }

    public void addFormInput(BodyFormInputPanel bodyFormInputPanel) {
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "updateChange");
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "add");
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "add");
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "add");
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "remove");
        formGroupPanel.remove(addField);
        formGroupPanel.add(bodyFormInputPanel, "pushx,growx,wrap");
        formGroupPanel.add(addField, "pushx,growx,wrap");
        listInputPanel.add(bodyFormInputPanel);
        updateChange();
    }

    public void addFormInput(CollectionBodyEntity collectionBodyEntity) {
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "addFormInput");
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "getId");
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "setId");
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "getValue");
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "getKey");
        com.supanadit.restsuite.logger.LogWriter.out("addFormInput", "getType");
        // Get Type
        String type = collectionBodyEntity.getType();
        // Get Key
        String key = collectionBodyEntity.getKey();
        // Get Value
        String value = collectionBodyEntity.getValue();
        // Declare body form input panel
        BodyFormInputPanel bodyFormInputPanel = new BodyFormInputPanel(this, type, key, value);
        // Set ID
        bodyFormInputPanel.setId(collectionBodyEntity.getId());
        // Add Form Input
        addFormInput(bodyFormInputPanel);
    }

    public void clearFormInput() {
        com.supanadit.restsuite.logger.LogWriter.out("clearFormInput", "updateChange");
        // Clone
        ArrayList<BodyFormInputPanel> listInputPanelClone = listInputPanel;
        // Clear original variable
        listInputPanel = new ArrayList<>();
        // Looping
        for (BodyFormInputPanel formInputPanel : listInputPanelClone) {
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