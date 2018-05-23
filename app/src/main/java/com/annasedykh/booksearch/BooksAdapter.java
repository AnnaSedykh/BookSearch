package com.annasedykh.booksearch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link BooksAdapter} displays a scrolling list of {@link Book} objects using RecyclerView.
 */
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    private static final String THUMBNAIL_URI_KEY = "smallThumbnail";
    private List<Book> data = new ArrayList<>();


    /**
     * Create new views (invoked by the layout manager)
     */
    @Override
    public BooksAdapter.BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book, parent, false);
        return new BookViewHolder(view);
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    @Override
    public void onBindViewHolder(BooksAdapter.BookViewHolder holder, int position) {
        Book book = data.get(position);
        holder.bind(book, position);
    }

    /**
     * Return the size of book's dataset (invoked by the layout manager)
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Book> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public List<Book> getData() {
        return data;
    }

    /**
     * {@link BookViewHolder} provide a reference to the views for each book
     */
    static class BookViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.thumbnail) ImageView thumbnail;
        @BindView(R.id.title) TextView title;
        @BindView(R.id.authors) TextView authors;
        @BindView(R.id.description) TextView description;
        private String infoLink;
        private Context context;

        /**
         * {@link BookViewHolder} constructor
         */
        public BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        /**
         * Binds view with book info
         */
        public void bind(final Book book, final int position) {
            Book.BookInfo info = book.getInfo();
            if (info != null) {
                title.setText(info.getTitle());
                if (info.getAuthors() != null) {
                    authors.setText(TextUtils.join(", ", info.getAuthors()));
                }
                description.setText(info.getDescription());
                if (info.getImageLinks() != null) {
                    String path = info.getImageLinks().get(THUMBNAIL_URI_KEY);

                    Picasso.get()
                            .load(path)
                            .centerCrop()
                            .fit()
                            .into(thumbnail);
                }
                infoLink = info.getInfoLink();
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!infoLink.isEmpty()) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(infoLink));
                        context.startActivity(browserIntent);
                    }
                }
            });
        }
    }
}
