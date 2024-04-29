package com.example.bookshelfapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelfapplication.adapters.BookAdapter;
import com.example.bookshelfapplication.adapters.DataSource;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout navHome, navSearch, navAddBook, navProfile, navLogout;
    RecyclerView rvHomeBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        navHome = findViewById(R.id.navHome);
        navSearch = findViewById(R.id.navSearch);
        navAddBook = findViewById(R.id.navAddBook);
        navProfile = findViewById(R.id.navProfile);
        navLogout = findViewById(R.id.navLogout);
        rvHomeBooks = findViewById(R.id.rvHomeBooks);

        BookAdapter bookAdapter = new BookAdapter(MainActivity.this, DataSource.books);
        rvHomeBooks.setAdapter(bookAdapter);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        navSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainActivity.this, SearchActivity.class);
            }
        });

        navAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainActivity.this, AddBookActivity.class);
            }
        });

        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainActivity.this, ProfileActivity.class);
            }
        });

        navLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
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