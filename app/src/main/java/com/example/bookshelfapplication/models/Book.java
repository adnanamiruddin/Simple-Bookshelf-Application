package com.example.bookshelfapplication.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Book implements Parcelable {
    private String title, isbn, author, publisher, publicationYear;
    private int image,description;
    private Uri addBookImage;
    private String addBookDescription;

    public Book(String title, String isbn, String author, String publisher, String publicationYear, int image, int description) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.image = image;
        this.description = description;
    }

    public Book(String title, String isbn, String author, String publisher, String publicationYear, Uri addBookImage, String addBookDescription) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.addBookImage = addBookImage;
        this.addBookDescription = addBookDescription;
    }

    protected Book(Parcel in) {
        title = in.readString();
        isbn = in.readString();
        author = in.readString();
        publisher = in.readString();
        publicationYear = in.readString();
        image = in.readInt();
        description = in.readInt();
        addBookImage = in.readParcelable(Uri.class.getClassLoader());
        addBookDescription = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(isbn);
        dest.writeString(author);
        dest.writeString(publisher);
        dest.writeString(publicationYear);
        dest.writeInt(image);
        dest.writeInt(description);
        dest.writeParcelable(addBookImage, flags);
        dest.writeString(addBookDescription);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public Uri getAddBookImage() {
        return addBookImage;
    }

    public void setAddBookImage(Uri addBookImage) {
        this.addBookImage = addBookImage;
    }

    public String getAddBookDescription() {
        return addBookDescription;
    }

    public void setAddBookDescription(String addBookDescription) {
        this.addBookDescription = addBookDescription;
    }
}
