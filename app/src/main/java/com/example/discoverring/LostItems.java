package com.example.discoverring;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LostItems extends Fragment
{
    // Floating Action Button
    FloatingActionButton mAddPostFab;
    Boolean isAllFabsVisible;

    ListView list;

    // card view
    RecyclerView recyclerView;
    ItemsLostDisplayAdapter adapter;
    DatabaseReference mbase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_lost_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        // card view to display items
        mbase = FirebaseDatabase.getInstance().getReference();
        recyclerView = view.findViewById(R.id.recyclerLostItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        FirebaseRecyclerOptions<ItemsLost> options = new FirebaseRecyclerOptions.Builder<ItemsLost>()
                .setQuery(mbase, ItemsLost.class)
                .build();

        adapter = new ItemsLostDisplayAdapter(options);
        recyclerView.setAdapter(adapter);

        // add post floating action button
        mAddPostFab = view.findViewById(R.id.fab_add_post);

//        addPostText.setVisibility(View.GONE);
        isAllFabsVisible = false;

        mAddPostFab.setOnClickListener(view1 ->
        {
            if(!isAllFabsVisible)
            {
                Intent intent = new Intent(getContext(), AddPostLostItems.class);
                startActivity(intent);
//                addPostText.setVisibility(View.VISIBLE);
                isAllFabsVisible = true;
            }
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}