package com.example.bookshelfapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelfapplication.adapters.DataSource;
import com.example.bookshelfapplication.adapters.SearchBookAdapter;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private EditText inpSearchBook;
    private RecyclerView rvSearchBooks;
    private SearchBookAdapter searchBookAdapter;
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout navHome, navSearch, navAddBook, navProfile, navLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        inpSearchBook = findViewById(R.id.inpSearchBook);
        rvSearchBooks = findViewById(R.id.rvSearchBooks);
        searchBookAdapter = new SearchBookAdapter(SearchActivity.this, new ArrayList<>());
        rvSearchBooks.setAdapter(searchBookAdapter);

        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        navHome = findViewById(R.id.navHome);
        navSearch = findViewById(R.id.navSearch);
        navAddBook = findViewById(R.id.navAddBook);
        navProfile = findViewById(R.id.navProfile);
        navLogout = findViewById(R.id.navLogout);

        inpSearchBook.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchBookAdapter.searchBook(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(SearchActivity.this, MainActivity.class);
            }
        });

        navSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        navAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(SearchActivity.this, AddBookActivity.class);
            }
        });

        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(SearchActivity.this, ProfileActivity.class);
            }
        });

        navLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchActivity.this, "Logout", Toast.LENGTH_SHORT).show();
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