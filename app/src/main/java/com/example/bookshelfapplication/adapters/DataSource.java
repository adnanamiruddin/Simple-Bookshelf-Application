package com.example.bookshelfapplication.adapters;

import com.example.bookshelfapplication.R;
import com.example.bookshelfapplication.models.Book;

import java.util.ArrayList;

public class DataSource {
    //    public static ArrayList<Account> accounts = generateDummyAccounts();
    public static ArrayList<Book> books = generateDummyBooks();

    public static ArrayList<Book> generateDummyBooks() {
        ArrayList<Book> newBooks = new ArrayList<>();

        newBooks.add(new Book("Aplikasi Klinis Induk Ovulasi & Stimulasi Ovariu", "978-979-328-876-5", "Samsulhadi", "Sagung Seto", "2013", R.drawable.aplikasi_klinis_induk, R.string.desc_aplikasi_klinik_induk));

        newBooks.add(new Book("Aplikasi Praktis Asuhan Keperawatan Keluarga", "978-602-867-404-1", "Komang Ayu Heni", "Sagung Seto", "2012", R.drawable.aplikasi_praktis_asuhan, R.string.desc_aplikasi_praktis_asuhan));

        newBooks.add(new Book("A-Z Psikologi : Berbagai kumpulan topik Psikologi", "978-979-293-215-7", "Zainul Anwar", "Andi Offset", "2012", R.drawable.az_psikologi, R.string.desc_az_psikologi));

        newBooks.add(new Book("Bangsa gagal ; Mencari identitas kebangsaan", "978-979-128-365-6", "Nasruddin Anshoriy", "LKiS", "2008", R.drawable.bangsa_gagal, R.string.desc_bangsa_gagal));

        newBooks.add(new Book("Biografi Gus Dur ; The Authorized Biography of KH. Abdurrahman Wahid (Soft Cover)", "978-979-338-125-1", "Greg Barton", "LKiS", "2011", R.drawable.biografi_gus_dur, R.string.desc_biografi_gus_dur));

        return newBooks;
    }
}
