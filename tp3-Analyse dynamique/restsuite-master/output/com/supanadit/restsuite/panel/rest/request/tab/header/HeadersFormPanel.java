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
        System.out.println("addFormInput -> updateChange");
        System.out.println("addFormInput -> add");
        System.out.println("addFormInput -> add");
        System.out.println("addFormInput -> add");
        System.out.println("addFormInput -> remove");
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
        System.out.println("addFormInput -> addFormInput");
        System.out.println("addFormInput -> getId");
        System.out.println("addFormInput -> setId");
        System.out.println("addFormInput -> getValue");
        System.out.println("addFormInput -> getKey");
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
        System.out.println("clearFormInput -> updateChange");
        // Clone
        ArrayList<HeadersFormInputPanel> listInputPanelClone = listInputPanel;
        // Clear original variable
        listInputPanel = new ArrayList<>();
        // Clear removed input list
        listRemovedInputPanel = new ArrayList<>();
        // Looping
        for (HeadersFormInputPanel formInputPanel : listInputPanelClone) {
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