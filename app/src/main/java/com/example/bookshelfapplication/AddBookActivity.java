package com.example.bookshelfapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.bookshelfapplication.adapters.BookAdapter;
import com.example.bookshelfapplication.adapters.DataSource;
import com.example.bookshelfapplication.models.Book;

public class AddBookActivity extends AppCompatActivity {

    private EditText inpBookTitle, inpBookIsbn, inpBookAuthor, inpBookPublisher, inpBookPublicationYear, inpBookDescription;
    private ImageButton inpBookImage;
    public static Uri inpBookImageUri;
    private Button submitPostBookBtn;
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout navHome, navSearch, navAddBook, navProfile, navLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        inpBookTitle = findViewById(R.id.inpBookTitle);
        inpBookIsbn = findViewById(R.id.inpBookIsbn);
        inpBookAuthor = findViewById(R.id.inpBookAuthor);
        inpBookPublisher = findViewById(R.id.inpBookPublisher);
        inpBookPublicationYear = findViewById(R.id.inpBookPublicationYear);
        inpBookDescription = findViewById(R.id.inpBookDescription);
        inpBookImage = findViewById(R.id.inpBookImage);
        submitPostBookBtn = findViewById(R.id.submitPostBookBtn);

        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        navHome = findViewById(R.id.navHome);
        navSearch = findViewById(R.id.navSearch);
        navAddBook = findViewById(R.id.navAddBook);
        navProfile = findViewById(R.id.navProfile);
        navLogout = findViewById(R.id.navLogout);

        inpBookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                openGalleryIntent.setType("image/*");
                openGallery.launch(Intent.createChooser(openGalleryIntent, "Choose Your Picture"));
            }
        });

        submitPostBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inpBookTitleValue = inpBookTitle.getText().toString().trim();
                if (inpBookTitleValue.isEmpty()) {
                    inpBookTitle.setError("Please fill in the book title");
                    return;
                }

                String inpBookIsbnValue = inpBookIsbn.getText().toString().trim();
                if (inpBookIsbnValue.isEmpty()) {
                    inpBookIsbn.setError("Please fill in the ISBN");
                    return;
                }

                String inpBookAuthorValue = inpBookAuthor.getText().toString().trim();
                if (inpBookAuthorValue.isEmpty()) {
                    inpBookAuthor.setError("Please fill in the author");
                    return;
                }

                String inpBookPublisherValue = inpBookPublisher.getText().toString().trim();
                if (inpBookPublisherValue.isEmpty()) {
                    inpBookPublisher.setError("Please fill in the publisher");
                    return;
                }

                String inpBookPublicationYearValue = inpBookPublicationYear.getText().toString().trim();
                if (inpBookPublicationYearValue.isEmpty()) {
                    inpBookPublicationYear.setError("Please fill in the publication year");
                    return;
                }

                String inpBookDescriptionValue = inpBookDescription.getText().toString().trim();
                if (inpBookDescriptionValue.isEmpty()) {
                    inpBookDescription.setError("Please fill in the book description");
                    return;
                }

                if (inpBookImageUri == null) {
                    Toast.makeText(AddBookActivity.this, "Please upload your image", Toast.LENGTH_SHORT).show();
                    return;
                }

                Book newBook = new Book(inpBookTitleValue, inpBookIsbnValue, inpBookAuthorValue, inpBookPublisherValue, inpBookPublicationYearValue, inpBookImageUri, inpBookDescriptionValue);
                BookAdapter bookAdapter = new BookAdapter(AddBookActivity.this, DataSource.books);
                bookAdapter.addPost(newBook);

                Intent toHomeActivity = new Intent(AddBookActivity.this, MainActivity.class);
                startActivity(toHomeActivity);
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
                redirectActivity(AddBookActivity.this, MainActivity.class);
            }
        });

        navSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(AddBookActivity.this, SearchActivity.class);
            }
        });

        navAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(AddBookActivity.this, ProfileActivity.class);
            }
        });

        navLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddBookActivity.this, "Logout", Toast.LENGTH_SHORT).show();
            }
        });
    }

    ActivityResultLauncher<Intent> openGallery = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri imageUri = data.getData();
                            if (imageUri != null) {
                                inpBookImage.setImageURI(imageUri);
                                inpBookImageUri = imageUri;
                            }
                        }
                    }
                }
            }
    );

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