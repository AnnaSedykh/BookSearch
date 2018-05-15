package com.annasedykh.booksearch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    private static final String THUMBNAIL_URI_KEY = "smallThumbnail";
    private List<Book> data = new ArrayList<>();

    @Override
    public BooksAdapter.BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BooksAdapter.BookViewHolder holder, int position) {
        Book book = data.get(position);
        holder.bind(book, position);
    }

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

    static class BookViewHolder extends RecyclerView.ViewHolder {
        private final ImageView thumbnail;
        private final TextView title;
        private final TextView authors;
        private final TextView description;
        private String infoLink;
        private Context context;

        public BookViewHolder(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            authors = itemView.findViewById(R.id.authors);
            description = itemView.findViewById(R.id.description);
            context = itemView.getContext();
        }

        public void bind(final Book book, final int position) {
            Book.BookInfo info = book.getInfo();
            if (info != null) {
                title.setText(info.getTitle());
                if(info.getAuthors() != null){
                authors.setText(android.text.TextUtils.join(", ",info.getAuthors()));
                }
                description.setText(info.getDescription());
                if(info.getImageLinks() != null){
                Uri thumbnailUri = Uri.parse(info.getImageLinks().get(THUMBNAIL_URI_KEY));
                thumbnail.setImageURI(thumbnailUri);
                }
                infoLink = info.getInfoLink();
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   if(!infoLink.isEmpty()){
                       Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(infoLink));
                       context.startActivity(browserIntent);
                   }
                }
            });
        }
    }
}
