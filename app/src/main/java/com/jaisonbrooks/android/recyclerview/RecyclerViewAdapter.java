package com.jaisonbrooks.android.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jaison Brooks on 10/21/14.
 * * * Project Name = RecyclerView
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * The array of objects used to populate the adapter
     */
    private RecyclerItem[] mObjects;
    private Context mContext;
    private static final int TYPE_HEADER = 2;
    private static final int TYPE_ITEM = 1;
    private TypedArray colors;

    /**
     * Constructor for the RecyclerViewAdapter
     * @param objects is an array of RecyclerItem objects
     */
    public RecyclerViewAdapter(Context context, RecyclerItem[] objects) {
        this.mContext = context;
        this.mObjects = objects;
        this.colors = context.getResources().obtainTypedArray(R.array.colors);
    }


    /**
     * Provide a reference to the views for each data item
     * Complex data items may need more than one view per item, and
     * you provide access to all the views for a data item in a view holder
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitle;
        public TextView mDescription;
        public CardView mCard;
        public ViewHolder(View x) {
            super(x);
            mTitle = (TextView) x.findViewById(R.id.title);
            mDescription = (TextView) x.findViewById(R.id.description);
            mCard = (CardView) x.findViewById(R.id.card_view);
            /**
             * Since we used OnClick in this way
             * we need to get reference to the CardView or Root layout element
             */
            mCard.setOnClickListener(this);
        }

        /**
         * Used to bind the object in the list to the specific view holder instance
         */
        public void bindObj(RecyclerItem item) {

            mTitle.setText(item.getTitle());
            mTitle.setTextColor(colors.getColor(getPosition(),0));
            //colors.recycle();
            mDescription.setText(item.getDescription());
        }

        /**
         * Used to implement on click events
         * @param v is the Root element in the RecyclerItem layout
         */
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, mContext.getString(R.string.list_position_toast) + Integer.toString(getPosition()), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * The ViewHeader holder
     */
    public class ViewHeader extends RecyclerView.ViewHolder {
        public ViewHeader(View itemView) { super(itemView); }
    }


   /*
    * Create a new ViewHolder object
    */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * Inflate the view and return a new ViewHolder
         */
        if (viewType == TYPE_ITEM) {
            return new RecyclerViewAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
        } else if (viewType == TYPE_HEADER) {

            return new RecyclerViewAdapter.ViewHeader(LayoutInflater.from(mContext).inflate(R.layout.list_header, parent, false));
        }
        throw new RuntimeException("there is no type that matches");
        //return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    /**
     * Replace the contents of the View
     * @param holder is the ViewHolder that was already created
     * @param position is the position in the recycler item array
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Subtract the header
        if (!isPositionHeader(position)) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.bindObj(mObjects[position - 1]);
            //colors.recycle();
        }
    }

    /**
     * The size/length of your objects array
     * @return is the Length of the object array
     */
    public int getBasicItemCount() {
        return mObjects == null ? 0 : mObjects.length;
    }

    @Override
    public int getItemCount() {
        return getBasicItemCount() + 1; // include the header into the array
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    /**
     * Return whether or not the position is a header
     */
    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}