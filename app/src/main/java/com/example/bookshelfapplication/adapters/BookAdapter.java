package com.example.bookshelfapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelfapplication.BookDetailActivity;
import com.example.bookshelfapplication.R;
import com.example.bookshelfapplication.models.Book;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Book> books;

    public BookAdapter(Context context, ArrayList<Book> books) {
        this.context = context;
        this.books = books;
    }

    public void addPost(Book book) {
        books.add(0, book);
        notifyItemInserted(books.size() - 1);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.setData(book);

        holder.bookReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toBookDetailActivity = new Intent(context, BookDetailActivity.class);
                toBookDetailActivity.putExtra(BookDetailActivity.PARCEL_BOOK_DETAIL, book);

                context.startActivity(toBookDetailActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView bookImage;
        private final TextView bookTitle;
        private final TextView bookAuthor;
        private final Button bookReadBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.bookImage);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookAuthor = itemView.findViewById(R.id.bookAuthor);
            bookReadBtn = itemView.findViewById(R.id.bookReadBtn);
        }

        public void setData(Book book) {
            bookTitle.setText(book.getTitle());
            bookAuthor.setText(book.getAuthor());

            // Check the types of feedCaption and feedImage to handle both types
            if (book.getAddBookImage() != null) {
                // If there are uploaded caption and image from user
                bookImage.setImageURI(book.getAddBookImage());
            } else {
                // Otherwise, use static caption and image from resources
                bookImage.setImageResource(book.getImage());
            }
        }
    }
}
