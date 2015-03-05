package com.jaisonbrooks.android.recyclerview;

/**
 * Created by Jaison Brooks on 10/21/14.
 * * Project Name = RecyclerView
 **/

public class RecyclerItem {
    private String title;
    private String description;

    public RecyclerItem(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
}
