package com.jaisonbrooks.android.recyclerview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;


public class MainActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private final int num_of_items = 30;
    private RecyclerItem[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initRecyclerView();
    }

    public void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        /**
         * Set the support actionbar before any additional toolbar interactions
         */
        setSupportActionBar(mToolbar);

        /**
         * To modify the normal contents (title, subtitle, navigation icon) of the Toolbar, use getSupportActionBar().*
         */
        getSupportActionBar().setTitle(R.string.toolbar_title);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }

    /**
     * Setup the RecyclerView and populate it with data
     */
    public void initRecyclerView() {
        /**
         * Initialize the RecyclerView
         */
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        /**
         * If your content doesnt change and is consistent, set this to improve performance
         */
        mRecyclerView.setHasFixedSize(true);

        /**
         * Initialize the Layout manager and set it
         */
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        /**
         * Setup the Scrolling listeners
         */
        mRecyclerView.setOnScrollListener(new RecyclerScrollListener() {
            @Override
            public void onHide() {
                hideToolbar();
            }

            @Override
            public void onShow() {
                showToolbar();
            }
        } );

        /**
         * Setup the list adapter
         */
        mAdapter = new RecyclerViewAdapter(this,getData());
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Hide the toolbar
     */
    private void hideToolbar() {
        mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
    }

    /**
     * Show the toolbar
     */
    private void showToolbar() {
        mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    }

    /**
     * Create an array of RecyclerItem objects
     * @return RecyclerItem[]
     */
    private RecyclerItem[] getData() {
        data = new RecyclerItem[num_of_items];
        for (int i = 0; i < num_of_items; i++) {
            data[i] = new RecyclerItem(getString(R.string.list_title), getString(R.string.list_description));
        }
        return data;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
