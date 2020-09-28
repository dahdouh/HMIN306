package com.supanadit.restsuite.component.combobox;
import com.supanadit.restsuite.model.BodyTypeModel;
import com.supanadit.restsuite.renderer.RequestBodyTypeRenderer;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.*;
public class RequestBodyTypeComboBox extends JComboBox<BodyTypeModel> {
    ArrayList<BodyTypeModel> bodyTypeModels = new ArrayList<>();

    public RequestBodyTypeComboBox() {
        setRenderer(new RequestBodyTypeRenderer());
        bodyTypeModels.add(BodyTypeModel.RAW());
        bodyTypeModels.add(BodyTypeModel.FORM());
        for (BodyTypeModel bodyTypeModel : bodyTypeModels) {
            addItem(bodyTypeModel);
        }
    }

    public RequestBodyTypeComboBox(String type) {
        this();
        setType(type);
    }

    public void setType(String type) {
        System.out.println("setType -> getBodyTypeModels");
        for (BodyTypeModel bodyTypeModel : getBodyTypeModels()) {
            System.out.println("setType -> getName");
            System.out.println("setType -> getName");
            if (bodyTypeModel.getName().equals(type)) {
                System.out.println("setType -> setSelectedItem");
                setSelectedItem(bodyTypeModel);
            }
        }
    }

    public ArrayList<BodyTypeModel> getBodyTypeModels() {
        return bodyTypeModels;
    }
}