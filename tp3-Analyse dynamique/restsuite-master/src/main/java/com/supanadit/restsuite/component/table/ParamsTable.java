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
        com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRow", "getSelectedRow");
        if (!(requestTable.getSelectedRow() < 0)) {
            com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRow", "getModel");
            com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRow", "getModel");
            com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRow", "getSelectedRow");
            com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRow", "getModel");
            com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRow", "getModel");
            getModel().removeRow(requestTable.getSelectedRow());
            if (getModel().getRowCount() != 0) {
                com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRow", "getModel");
                com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRow", "getModel");
                com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRow", "changeSelection");
                com.supanadit.restsuite.logger.LogWriter.out("deleteSelectedRow", "requestFocus");
                requestTable.requestFocus();
                requestTable.changeSelection(getModel().getRowCount() - 1, 0, true, false);
            }
        }
    }

    public void addNewEmptyRow() {
        com.supanadit.restsuite.logger.LogWriter.out("addNewEmptyRow", "addRow");
        addRow(new RequestModel("", ""));
    }

    public void addRow(RequestModel requestModel) {
        com.supanadit.restsuite.logger.LogWriter.out("addRow", "addRow");
        addRow(requestModel, true);
    }

    public void addRow(RequestModel requestModel, boolean withFocus) {
        com.supanadit.restsuite.logger.LogWriter.out("addRow", "getValue");
        com.supanadit.restsuite.logger.LogWriter.out("addRow", "getKey");
        com.supanadit.restsuite.logger.LogWriter.out("addRow", "getModel");
        com.supanadit.restsuite.logger.LogWriter.out("addRow", "getModel");
        getModel().addRow(new Object[]{ requestModel.getKey(), requestModel.getValue() });
        if (withFocus) {
            com.supanadit.restsuite.logger.LogWriter.out("addRow", "getModel");
            com.supanadit.restsuite.logger.LogWriter.out("addRow", "getModel");
            if (getModel().getRowCount() != 0) {
                com.supanadit.restsuite.logger.LogWriter.out("addRow", "requestFocus");
                com.supanadit.restsuite.logger.LogWriter.out("addRow", "getModel");
                com.supanadit.restsuite.logger.LogWriter.out("addRow", "getModel");
                com.supanadit.restsuite.logger.LogWriter.out("addRow", "editCellAt");
                requestTable.editCellAt(getModel().getRowCount() - 1, 0);
                requestTable.requestFocus();
            }
        }
    }

    public void setFromRequestArrayList(ArrayList<RequestModel> requestModelArrayList) {
        com.supanadit.restsuite.logger.LogWriter.out("setFromRequestArrayList", "getModel");
        com.supanadit.restsuite.logger.LogWriter.out("setFromRequestArrayList", "getModel");
        int rowCount = getModel().getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            com.supanadit.restsuite.logger.LogWriter.out("setFromRequestArrayList", "getModel");
            com.supanadit.restsuite.logger.LogWriter.out("setFromRequestArrayList", "getModel");
            getModel().removeRow(i);
        }
        for (RequestModel requestModel : requestModelArrayList) {
            com.supanadit.restsuite.logger.LogWriter.out("setFromRequestArrayList", "addRow");
            addRow(requestModel, false);
        }
    }

    public ArrayList<RequestModel> getRequest() {
        com.supanadit.restsuite.logger.LogWriter.out("getRequest", "getRowCount");
        ArrayList<RequestModel> requestModels = new ArrayList<>();
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            com.supanadit.restsuite.logger.LogWriter.out("getRequest", "add");
            com.supanadit.restsuite.logger.LogWriter.out("getRequest", "getValueAt");
            com.supanadit.restsuite.logger.LogWriter.out("getRequest", "getValueAt");
            com.supanadit.restsuite.logger.LogWriter.out("getRequest", "getValueAt");
            com.supanadit.restsuite.logger.LogWriter.out("getRequest", "getValueAt");
            String key = defaultTableModel.getValueAt(i, 0).toString();
            String value = defaultTableModel.getValueAt(i, 1).toString();
            requestModels.add(new RequestModel(key, value));
        }
        return requestModels;
    }
}