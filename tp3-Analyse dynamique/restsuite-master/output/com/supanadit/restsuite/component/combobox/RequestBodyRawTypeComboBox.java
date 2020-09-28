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
        System.out.println("setType -> getBodyRawTypeModels");
        for (BodyRawTypeModel bodyRawTypeModel : getBodyRawTypeModels()) {
            System.out.println("setType -> getName");
            System.out.println("setType -> getName");
            if (bodyRawTypeModel.getName().equals(type)) {
                System.out.println("setType -> setSelectedItem");
                setSelectedItem(bodyRawTypeModel);
            }
        }
    }

    public ArrayList<BodyRawTypeModel> getBodyRawTypeModels() {
        return bodyRawTypeModels;
    }

    @Override
    public String toString() {
        System.out.println("toString -> getName");
        System.out.println("toString -> getSelectedItem");
        BodyRawTypeModel model = ((BodyRawTypeModel) (getSelectedItem()));
        assert model != null;
        return model.getName();
    }
}