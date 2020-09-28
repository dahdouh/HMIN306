package com.supanadit.restsuite.panel.rest.dialog.renderer;
import com.supanadit.restsuite.entity.CollectionEntity;
import com.supanadit.restsuite.entity.CollectionStructureFolderEntity;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import javax.swing.*;
public class CollectionTreeRenderer extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        com.supanadit.restsuite.logger.LogWriter.out("getTreeCellRendererComponent", "getTreeCellRendererComponent");
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        if (value instanceof DefaultMutableTreeNode) {
            com.supanadit.restsuite.logger.LogWriter.out("getTreeCellRendererComponent", "getUserObject");
            DefaultMutableTreeNode node = ((DefaultMutableTreeNode) (value));
            Object userValue = node.getUserObject();
            if (userValue instanceof CollectionEntity) {
                com.supanadit.restsuite.logger.LogWriter.out("getTreeCellRendererComponent", "getTitle");
                com.supanadit.restsuite.logger.LogWriter.out("getTreeCellRendererComponent", "setText");
                setText(((CollectionEntity) (userValue)).getTitle());
            }
            if (userValue instanceof CollectionStructureFolderEntity) {
                com.supanadit.restsuite.logger.LogWriter.out("getTreeCellRendererComponent", "getName");
                com.supanadit.restsuite.logger.LogWriter.out("getTreeCellRendererComponent", "setText");
                setText(((CollectionStructureFolderEntity) (userValue)).getName());
            }
        }
        return this;
    }
}