package com.uniyaz.sorun.ui.views;

import com.uniyaz.sorun.dao.CategoryDao;
import com.uniyaz.sorun.dao.IssueDao;
import com.uniyaz.sorun.domain.Category;
import com.uniyaz.sorun.domain.EnumIssueState;
import com.uniyaz.sorun.domain.Issue;
import com.uniyaz.sorun.ui.components.SaveButton;


import com.uniyaz.sorun.ui.components.StTextField;
import com.vaadin.ui.*;


import java.util.*;

public class AddIssueView extends VerticalLayout {

    private FormLayout mainLayout;

    private StTextField idField;
    private StTextField adressField;
    private TextArea  contentField;
    private DateField dateField;
    private ComboBox selectYourIssueCombo;
    private ComboBox kategoriCombo;

    public AddIssueView() {

        mainLayout = new FormLayout();
        addComponent(mainLayout);

        idField = new StTextField("Id");
        idField.setEnabled(false);
        mainLayout.addComponent(idField);

        adressField = new StTextField("Adress");
        mainLayout.addComponent(adressField);

        contentField = new TextArea("Content");
        mainLayout.addComponent(contentField);

        dateField = new DateField("Date");
        dateField.setValue(new Date());
        mainLayout.addComponent(dateField);

        //Combo ıssue
        List<EnumIssueState> enumIssueStatusList = new ArrayList();
        enumIssueStatusList.addAll(Arrays.asList(EnumIssueState.values()));

        selectYourIssueCombo = new ComboBox("Select your issue", enumIssueStatusList);
        selectYourIssueCombo.setInputPrompt("Hicbir secim yapmadiniz");
        selectYourIssueCombo.setWidth(30, Unit.PERCENTAGE);
        mainLayout.addComponent(selectYourIssueCombo);

        //Combo kategori
        Collection<String> kategoriNameList = new ArrayList<String>();

        CategoryDao categoryDao = new CategoryDao();
        List<Category> categoryList = categoryDao.findAllCategory();


        kategoriCombo = new ComboBox("kategori sec",categoryList);
        kategoriCombo.setInputPrompt("No category select");
        kategoriCombo.setWidth(30,Unit.PERCENTAGE);
        mainLayout.addComponent(kategoriCombo);


        SaveButton saveButton = new SaveButton();
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                saveIssueValue();
            }
        });
        mainLayout.addComponent(saveButton);
    }

    public void fillViewByIssue(Issue issue) {
        idField.setValue(issue.getId().toString());
        adressField.setValue(issue.getAddress());
        contentField.setValue(issue.getContent());
        dateField.setValue(issue.getDate());
        selectYourIssueCombo.setValue(issue.getIssueState());
        kategoriCombo.setValue(issue.getCategory());

    }
    public  void saveIssueValue(){

        Long idFieldValue = null;
        if (idField.getValue() != "") {
            idFieldValue = Long.parseLong(idField.getValue());
        }

        String adressFieldValue = adressField.getValue();
        String contentFieldValue = contentField.getValue();
        Date dateFieldValue = dateField.getValue();
        EnumIssueState isseuStatu =(EnumIssueState) selectYourIssueCombo.getValue();
        Category category = (Category) kategoriCombo.getValue();

        Issue issue = new Issue();

        issue.setId(idFieldValue);
        issue.setAddress(adressFieldValue);
        issue.setContent(contentFieldValue);
        issue.setDate(dateFieldValue);
        issue.setIssueState(isseuStatu);
        issue.setCategory(category);

        IssueDao issueDao = new IssueDao();
        issue = issueDao.saveIssue(issue);

        idField.setValue(issue.getId().toString());

        Notification.show("İşlem Başarılı");
    }
}
