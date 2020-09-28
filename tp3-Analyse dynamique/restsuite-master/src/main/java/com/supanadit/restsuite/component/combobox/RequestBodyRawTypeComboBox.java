package com.supanadit.restsuite.component.combobox;
import com.supanadit.restsuite.model.BodyRawTypeModel;
import com.supanadit.restsuite.renderer.RequestBodyRawTypeRenderer;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.*;
public class RequestBodyRawTypeComboBox extends JComboBox<BodyRawTypeModel> {
    ArrayList<BodyRawTypeModel> bodyRawTypeModels = new ArrayList<>();

    public RequestBodyRawTypeComboBox() {
        setRenderer(new RequestBodyRawTypeRenderer());
        bodyRawTypeModels.add(BodyRawTypeModel.JSON());
        bodyRawTypeModels.add(BodyRawTypeModel.TEXT());
        bodyRawTypeModels.add(BodyRawTypeModel.XML());
        bodyRawTypeModels.add(BodyRawTypeModel.HTML());
        bodyRawTypeModels.add(BodyRawTypeModel.JAVASCRIPT());
        for (BodyRawTypeModel bodyRawTypeModel : bodyRawTypeModels) {
            addItem(bodyRawTypeModel);
        }
    }

    public RequestBodyRawTypeComboBox(String type) {
        this();
        setType(type);
    }

    public void setType(String type) {
        com.supanadit.restsuite.logger.LogWriter.out("setType", "getBodyRawTypeModels");
        for (BodyRawTypeModel bodyRawTypeModel : getBodyRawTypeModels()) {
            com.supanadit.restsuite.logger.LogWriter.out("setType", "getName");
            com.supanadit.restsuite.logger.LogWriter.out("setType", "getName");
            if (bodyRawTypeModel.getName().equals(type)) {
                com.supanadit.restsuite.logger.LogWriter.out("setType", "setSelectedItem");
                setSelectedItem(bodyRawTypeModel);
            }
        }
    }

    public ArrayList<BodyRawTypeModel> getBodyRawTypeModels() {
        return bodyRawTypeModels;
    }

    @Override
    public String toString() {
        com.supanadit.restsuite.logger.LogWriter.out("toString", "getName");
        com.supanadit.restsuite.logger.LogWriter.out("toString", "getSelectedItem");
        BodyRawTypeModel model = ((BodyRawTypeModel) (getSelectedItem()));
        assert model != null;
        return model.getName();
    }
}