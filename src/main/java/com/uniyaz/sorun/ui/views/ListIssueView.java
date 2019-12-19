package com.uniyaz.sorun.ui.views;

import com.uniyaz.sorun.dao.IssueDao;
import com.uniyaz.sorun.domain.Category;
import com.uniyaz.sorun.domain.EnumIssueState;
import com.uniyaz.sorun.domain.Issue;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.*;

import java.util.Date;
import java.util.List;

public class ListIssueView extends VerticalLayout{

    private Table table;

    private IndexedContainer indexedContainer;
    private AddIssueView addIssueView;


    Issue issue;

    public ListIssueView() {

        buildTableContainer();

        buildTable();
        addComponent(table);

        addIssueView = new AddIssueView();
        addComponent(addIssueView);

        fillTable();
    }

    private void fillTable() {

        IssueDao issueDao = new IssueDao();

        List<Issue> issueList = issueDao.findAllIssue();

        for (Issue issue : issueList) {
            Item item = indexedContainer.addItem(issue);
            item.getItemProperty("id").setValue(issue.getId());
            item.getItemProperty("address").setValue(issue.getAddress());
            item.getItemProperty("content").setValue(issue.getContent());
            item.getItemProperty("date").setValue(issue.getDate());
            item.getItemProperty("issueState").setValue(issue.getIssueState());
            item.getItemProperty("id_kategori").setValue(issue.getCategory());

        }

    }

    private void buildTableContainer() {

        indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("id", Long.class, null);
        indexedContainer.addContainerProperty("address", String.class, null);
        indexedContainer.addContainerProperty("content", String.class, null);
        indexedContainer.addContainerProperty("date", Date.class, null);
        indexedContainer.addContainerProperty("issueState", EnumIssueState.class, null);
        indexedContainer.addContainerProperty("id_kategori", Category.class, null);
    }

    private void buildTable() {
        table = new Table();

        table.setContainerDataSource(indexedContainer);
        table.setColumnHeaders("ID","ADDRESS","CONTENT","DATE","ISSUE STATU","KATEGORİ");
        table.setSelectable(true);
        table.setMultiSelectMode(MultiSelectMode.SIMPLE);
        table.setMultiSelect(false);

        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                issue = (Issue) itemClickEvent.getItemId();

                addIssueView.fillViewByIssue(issue);
            }
        });
    }

}
