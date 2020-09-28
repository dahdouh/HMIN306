package com.supanadit.restsuite.component.table;
import com.supanadit.restsuite.model.RequestModel;
import com.supanadit.restsuite.panel.rest.request.tab.header.HeadersPanel;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
public class ParamsTable extends JScrollPane {
    private DefaultTableModel defaultTableModel;

    private JTable requestTable;

    public ParamsTable(boolean editable, HeadersPanel headersPanel) {
        defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return editable;
            }
        };
        defaultTableModel.addColumn("Key");
        defaultTableModel.addColumn("Value");
        requestTable = new JTable(defaultTableModel);
        setViewportView(requestTable);
        requestTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public ParamsTable(HeadersPanel headersPanel) {
        this(true, headersPanel);
    }

    public DefaultTableModel getModel() {
        return defaultTableModel;
    }

    public void deleteSelectedRow() {
        System.out.println("deleteSelectedRow -> getSelectedRow");
        if (!(requestTable.getSelectedRow() < 0)) {
            System.out.println("deleteSelectedRow -> getModel");
            System.out.println("deleteSelectedRow -> getModel");
            System.out.println("deleteSelectedRow -> getSelectedRow");
            System.out.println("deleteSelectedRow -> getModel");
            System.out.println("deleteSelectedRow -> getModel");
            getModel().removeRow(requestTable.getSelectedRow());
            if (getModel().getRowCount() != 0) {
                System.out.println("deleteSelectedRow -> getModel");
                System.out.println("deleteSelectedRow -> getModel");
                System.out.println("deleteSelectedRow -> changeSelection");
                System.out.println("deleteSelectedRow -> requestFocus");
                requestTable.requestFocus();
                requestTable.changeSelection(getModel().getRowCount() - 1, 0, true, false);
            }
        }
    }

    public void addNewEmptyRow() {
        System.out.println("addNewEmptyRow -> addRow");
        addRow(new RequestModel("", ""));
    }

    public void addRow(RequestModel requestModel) {
        System.out.println("addRow -> addRow");
        addRow(requestModel, true);
    }

    public void addRow(RequestModel requestModel, boolean withFocus) {
        System.out.println("addRow -> getValue");
        System.out.println("addRow -> getKey");
        System.out.println("addRow -> getModel");
        System.out.println("addRow -> getModel");
        getModel().addRow(new Object[]{ requestModel.getKey(), requestModel.getValue() });
        if (withFocus) {
            System.out.println("addRow -> getModel");
            System.out.println("addRow -> getModel");
            if (getModel().getRowCount() != 0) {
                System.out.println("addRow -> requestFocus");
                System.out.println("addRow -> getModel");
                System.out.println("addRow -> getModel");
                System.out.println("addRow -> editCellAt");
                requestTable.editCellAt(getModel().getRowCount() - 1, 0);
                requestTable.requestFocus();
            }
        }
    }

    public void setFromRequestArrayList(ArrayList<RequestModel> requestModelArrayList) {
        System.out.println("setFromRequestArrayList -> getModel");
        System.out.println("setFromRequestArrayList -> getModel");
        int rowCount = getModel().getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            System.out.println("setFromRequestArrayList -> getModel");
            System.out.println("setFromRequestArrayList -> getModel");
            getModel().removeRow(i);
        }
        for (RequestModel requestModel : requestModelArrayList) {
            System.out.println("setFromRequestArrayList -> addRow");
            addRow(requestModel, false);
        }
    }

    public ArrayList<RequestModel> getRequest() {
        System.out.println("getRequest -> getRowCount");
        ArrayList<RequestModel> requestModels = new ArrayList<>();
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            System.out.println("getRequest -> add");
            System.out.println("getRequest -> getValueAt");
            System.out.println("getRequest -> getValueAt");
            System.out.println("getRequest -> getValueAt");
            System.out.println("getRequest -> getValueAt");
            String key = defaultTableModel.getValueAt(i, 0).toString();
            String value = defaultTableModel.getValueAt(i, 1).toString();
            requestModels.add(new RequestModel(key, value));
        }
        return requestModels;
    }
}