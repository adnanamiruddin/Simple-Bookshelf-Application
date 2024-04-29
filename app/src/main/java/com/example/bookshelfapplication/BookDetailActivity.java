package com.example.bookshelfapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.bookshelfapplication.models.Book;

public class BookDetailActivity extends AppCompatActivity {
    public static final String PARCEL_BOOK_DETAIL = "";
    private ImageView detailBookImage;
    private TextView detailBookTitle, detailBookAuthor, detailBookIsbn, detailBookPublisher, detailBookPublicationYear, detailBookDescription;

    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout navHome, navSearch, navAddBook, navProfile, navLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        detailBookImage = findViewById(R.id.detailBookImage);
        detailBookTitle = findViewById(R.id.detailBookTitle);
        detailBookAuthor = findViewById(R.id.detailBookAuthor);
        detailBookIsbn = findViewById(R.id.detailBookIsbn);
        detailBookPublisher = findViewById(R.id.detailBookPublisher);
        detailBookPublicationYear = findViewById(R.id.detailBookPublicationYear);
        detailBookDescription = findViewById(R.id.detailBookDescription);

        Book book = getIntent().getParcelableExtra(PARCEL_BOOK_DETAIL);
        if (book != null) {
            detailBookTitle.setText(book.getTitle());
            detailBookAuthor.setText(book.getAuthor());
            detailBookIsbn.setText(book.getIsbn());
            detailBookPublisher.setText(book.getPublisher());
            detailBookPublicationYear.setText(book.getPublicationYear());

            if (book.getAddBookImage() != null & book.getAddBookDescription() != null) {
                detailBookImage.setImageURI(book.getAddBookImage());
                detailBookDescription.setText(book.getAddBookDescription());
            } else {
                detailBookImage.setImageResource(book.getImage());
                detailBookDescription.setText(book.getDescription());
            }
        }

        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        navHome = findViewById(R.id.navHome);
        navSearch = findViewById(R.id.navSearch);
        navAddBook = findViewById(R.id.navAddBook);
        navProfile = findViewById(R.id.navProfile);
        navLogout = findViewById(R.id.navLogout);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(BookDetailActivity.this, MainActivity.class);
            }
        });

        navSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(BookDetailActivity.this, SearchActivity.class);
            }
        });

        navAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(BookDetailActivity.this, AddBookActivity.class);
            }
        });

        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(BookDetailActivity.this, ProfileActivity.class);
            }
        });

        navLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookDetailActivity.this, "Logout", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity, Class secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}