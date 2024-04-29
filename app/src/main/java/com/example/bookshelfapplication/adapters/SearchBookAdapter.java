package com.example.bookshelfapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelfapplication.BookDetailActivity;
import com.example.bookshelfapplication.R;
import com.example.bookshelfapplication.models.Book;

import java.util.ArrayList;

public class SearchBookAdapter extends RecyclerView.Adapter<SearchBookAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Book> books;

    public SearchBookAdapter(Context context, ArrayList<Book> books) {
        this.context = context;
        this.books = books;
    }

    public void searchBook(String query) {
        if (query.isEmpty()) {
            books.clear();
            notifyDataSetChanged();
            return;
        }

        ArrayList<Book> filteredList = new ArrayList<>();

        for (Book book : DataSource.books) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) || book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(book);
            }
        }


        books.clear();
        books.addAll(filteredList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.setData(book);

        holder.searchBookContainer.setOnClickListener(new View.OnClickListener() {
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

    class ViewHolder extends RecyclerView.ViewHolder{
        private final LinearLayout searchBookContainer;
        private final ImageView searchBookImage;
        private final TextView searchBookTitle;
        private final TextView searchBookAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            searchBookContainer = itemView.findViewById(R.id.searchBookContainer);
            searchBookImage = itemView.findViewById(R.id.searchBookImage);
            searchBookTitle = itemView.findViewById(R.id.searchBookTitle);
            searchBookAuthor = itemView.findViewById(R.id.searchBookAuthor);
        }

        public void setData(Book book){
            searchBookTitle.setText(book.getTitle());
            searchBookAuthor.setText(book.getAuthor());

            if (book.getAddBookImage() != null) {
                searchBookImage.setImageURI(book.getAddBookImage());
            } else {
                searchBookImage.setImageResource(book.getImage());
            }
        }
    }
}
