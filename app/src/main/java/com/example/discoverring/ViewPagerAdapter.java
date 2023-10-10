package com.example.discoverring;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter
{
    public ViewPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @Override
    public Fragment createFragment(int position)
    {
        switch (position)
        {
            case 0:
                return new LostItems();

            case 1:
                return new ReportItems();

            case 2:
                return new SearchItems();

            default:
                return new LostItems();
        }
    }

    @Override
    public int getItemCount()
    {
        return 3;
    }
}
