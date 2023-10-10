package com.example.discoverring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{
    // logout
    FirebaseAuth auth;
    Button button;
    TextView textView;
    FirebaseUser user;

    // Tab Layout objects
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;

    // floating action button
//    FloatingActionButton mAddPostFab;
//    TextView addPostText;
//    Boolean isAllFabsVisible;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.fragment_lost_items);

//        LayoutInflater inflater = getLayoutInflater();
//        View LostItemsFrag = inflater.inflate(R.layout.fragment_lost_items, null);

        // action bar

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("DiscoverRing");
//        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // Tab layout

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(@NonNull TabLayout.Tab tab)
            {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback()
        {
            @Override
            public void onPageSelected(int position)
            {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

//        if(user == null)
//        {
//            Intent intent = new Intent(getApplicationContext(), Login.class);
//            startActivity(intent);
//            finish();
//        }
//
//        else
//        {
//            textView.setText(user.getEmail());
//        }

//        button.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getApplicationContext(), Login.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        // floating action button

//        mAddPostFab = findViewById(R.id.fab_add_post);
////        addPostText = findViewById(R.id.add_post);
////        addPostText.setVisibility(View.GONE);
//        isAllFabsVisible = false;
//
//        mAddPostFab.setOnClickListener(view1 ->
//        {
//            if(!isAllFabsVisible)
//            {
//                Intent intent = new Intent(getApplicationContext(), AddPostLostItems.class);
//                startActivity(intent);
////                addPostText.setVisibility(View.VISIBLE);
//                isAllFabsVisible = true;
//            }
//
////            else
////            {
////                addPostText.setVisibility(View.GONE);
////                isAllFabsVisible = false;
////            }
//        });
    }

    // action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == R.id.myProfile)
        {
            Intent intent = new Intent(getApplicationContext(), MyProfile.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.logout)
        {
            auth = FirebaseAuth.getInstance();
            user = auth.getCurrentUser();

            if(user == null)
            {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}