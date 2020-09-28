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
        System.out.println("addFormInput -> updateChange");
        System.out.println("addFormInput -> add");
        System.out.println("addFormInput -> add");
        System.out.println("addFormInput -> add");
        System.out.println("addFormInput -> remove");
        formGroupPanel.remove(addField);
        formGroupPanel.add(bodyFormInputPanel, "pushx,growx,wrap");
        formGroupPanel.add(addField, "pushx,growx,wrap");
        listInputPanel.add(bodyFormInputPanel);
        updateChange();
    }

    public void addFormInput(CollectionBodyEntity collectionBodyEntity) {
        System.out.println("addFormInput -> addFormInput");
        System.out.println("addFormInput -> getId");
        System.out.println("addFormInput -> setId");
        System.out.println("addFormInput -> getValue");
        System.out.println("addFormInput -> getKey");
        System.out.println("addFormInput -> getType");
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
        System.out.println("clearFormInput -> updateChange");
        // Clone
        ArrayList<BodyFormInputPanel> listInputPanelClone = listInputPanel;
        // Clear original variable
        listInputPanel = new ArrayList<>();
        // Looping
        for (BodyFormInputPanel formInputPanel : listInputPanelClone) {
            System.out.println("clearFormInput -> remove");
            formInputPanel.remove();
        }
        updateChange();
    }

    public void updateChange() {
        System.out.println("updateChange -> updateUI");
        formGroupPanel.updateUI();
    }
}