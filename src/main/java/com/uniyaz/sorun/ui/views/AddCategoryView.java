package com.uniyaz.sorun.ui.views;

import com.uniyaz.sorun.dao.CategoryDao;
import com.uniyaz.sorun.domain.Category;
import com.uniyaz.sorun.ui.components.SaveButton;
import com.uniyaz.sorun.ui.components.StTextField;
import com.uniyaz.sorun.utils.HibernateUtil;
import com.vaadin.ui.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class AddCategoryView extends VerticalLayout {

    private FormLayout mainLayout;
    StTextField idField;
    StTextField nameField;


    public AddCategoryView() {
        buildMainLayaout();
    }

    private void buildMainLayaout() {
        mainLayout = new FormLayout();
        addComponent(mainLayout);

        idField = new StTextField("Id");
        idField.setEnabled(false);
        mainLayout.addComponent(idField);

        nameField = new StTextField("Name");
        mainLayout.addComponent(nameField);

        SaveButton saveButton = new SaveButton();
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                Long idFieldValue = null;
                if (idField.getValue() != "") {
                    idFieldValue = Long.parseLong(idField.getValue());
                }
                String nameFieldValue = nameField.getValue();

                Category category = new Category();
                category.setId(idFieldValue);
                category.setName(nameFieldValue);

                CategoryDao categoryDao = new CategoryDao();
                category = categoryDao.saveCategory(category);

                idField.setValue(category.getId().toString());
                Notification.show("İşlem Başarılı");
            }
        });
        mainLayout.addComponent(saveButton);
    }

    public void fillViewByCategory(Category category) {
        idField.setValue(category.getId().toString());
        nameField.setValue(category.getName());
    }
}
