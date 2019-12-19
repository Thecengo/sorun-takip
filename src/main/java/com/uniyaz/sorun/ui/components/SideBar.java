package com.uniyaz.sorun.ui.components;

import com.uniyaz.sorun.ui.views.AddCategoryView;
import com.uniyaz.sorun.ui.views.AddIssueView;
import com.uniyaz.sorun.ui.views.ListCategoryView;
import com.uniyaz.sorun.ui.views.ListIssueView;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public class SideBar extends VerticalLayout {

    private Header header;
    private Content content;

    private MenuButton btnCategoryMenuButton;
    private MenuButton btnCategoryListMenuButton;
    private MenuButton btnIssueMenuButton;
    private MenuButton btnIssueListeleMenuButton;
    public SideBar(Header header, Content content) {

        this.header = header;
        this.content = content;

        setSpacing(true);
        setMargin(true);

        btnCategoryMenuButton = new MenuButton(FontAwesome.PLUS_SQUARE);
        btnCategoryMenuButton.setCaption("Kategori Ekle");
        btnCategoryMenuButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AddCategoryView addCategoryView = new AddCategoryView();
                content.setContent(addCategoryView);
            }
        });
        addComponent(btnCategoryMenuButton);

        btnCategoryListMenuButton = new MenuButton(FontAwesome.LIST);
        btnCategoryListMenuButton.setCaption("Kategori Listele");
        btnCategoryListMenuButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ListCategoryView listCategoryView = new ListCategoryView();
                content.setContent(listCategoryView);
            }
        });
        addComponent(btnCategoryListMenuButton);

        btnIssueMenuButton = new MenuButton(FontAwesome.PLUS);
        btnIssueMenuButton.setCaption("Issue Ekle");
        btnIssueMenuButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AddIssueView addIssueView = new AddIssueView();
                content.setContent(addIssueView);
            }
        });
        addComponent(btnIssueMenuButton);

        btnIssueListeleMenuButton = new MenuButton(FontAwesome.PLUS);
        btnIssueListeleMenuButton.setCaption("Issue Listele");
        btnIssueListeleMenuButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ListIssueView addIssueView = new ListIssueView();
                content.setContent(addIssueView);
            }
        });
        addComponent(btnIssueListeleMenuButton);
    }
}