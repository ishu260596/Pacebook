package com.ishwar_arcore.pacebook.views.addfriemds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.annotations.NotNull;
import com.ishwar_arcore.pacebook.R;

public class SearchActivity extends AppCompatActivity {


        RecyclerView recview;
       myadapter adapter;
//    private ImageButton mSearchButtom;
//    private EditText searchInputText;
//    private ImageView mbackButton;
//    private RecyclerView searchResultList;
//    private DatabaseReference allUsersDatabaseRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);




        recview=(RecyclerView)findViewById(R.id.searchrecyclerView);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<SearchFriends> options =
                new FirebaseRecyclerOptions.Builder<SearchFriends>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users"), SearchFriends.class)
                        .build();

        adapter=new myadapter(options);
        recview.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<SearchFriends> options =
                new FirebaseRecyclerOptions.Builder<SearchFriends>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("username").startAt(5).endAt(s+"\uf69f"), SearchFriends.class)
                        .build();

       adapter =new myadapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }
}







