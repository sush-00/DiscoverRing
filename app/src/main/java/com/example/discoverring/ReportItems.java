package com.example.discoverring;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ReportItems extends Fragment
{
    // Floating Action Button
    FloatingActionButton mAddPostFab2;
    Boolean isAllFabsVisible;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_report_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        // add post floating action button
        mAddPostFab2 = view.findViewById(R.id.fab_add_post2);

//        addPostText.setVisibility(View.GONE);
        isAllFabsVisible = false;

        mAddPostFab2.setOnClickListener(view1 ->
        {
            if(!isAllFabsVisible)
            {
                Intent intent = new Intent(getContext(), AddPostReportItems.class);
                startActivity(intent);
//                addPostText.setVisibility(View.VISIBLE);
                isAllFabsVisible = true;
            }
        });
    }
}