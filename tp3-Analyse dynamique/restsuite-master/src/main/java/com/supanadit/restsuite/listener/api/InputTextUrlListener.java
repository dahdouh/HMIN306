package com.supanadit.restsuite.listener.api;
import io.reactivex.subjects.PublishSubject;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import io.reactivex.subjects.PublishSubject;
import javax.swing.*;
public class InputTextUrlListener implements DocumentListener {
    public JTextField textField;

    public PublishSubject<String> subject;

    public InputTextUrlListener(JTextField textField, PublishSubject<String> subject) {
        this.textField = textField;
        this.subject = subject;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        com.supanadit.restsuite.logger.LogWriter.out("insertUpdate", "valueChanged");
        this.valueChanged();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        com.supanadit.restsuite.logger.LogWriter.out("removeUpdate", "valueChanged");
        this.valueChanged();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        com.supanadit.restsuite.logger.LogWriter.out("changedUpdate", "valueChanged");
        this.valueChanged();
    }

    protected void valueChanged() {
        com.supanadit.restsuite.logger.LogWriter.out("valueChanged", "getText");
        com.supanadit.restsuite.logger.LogWriter.out("valueChanged", "onNext");
        subject.onNext(this.textField.getText());
    }
}