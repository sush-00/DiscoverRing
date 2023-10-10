package com.example.discoverring;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ItemsLostDisplayAdapter extends FirebaseRecyclerAdapter<ItemsLost, ItemsLostDisplayAdapter.ItemsLostViewholder>
{
    public ItemsLostDisplayAdapter(@NonNull FirebaseRecyclerOptions<ItemsLost> items)
    {
        super(items);
    }

    @Override
    protected void onBindViewHolder(@NonNull ItemsLostDisplayAdapter.ItemsLostViewholder holder, int position, @NonNull ItemsLost model)
    {
        holder.itemName.setText(model.getItemName());
        holder.itemDesc.setText(model.getItemDesc());
        holder.itemType.setText(model.getItemType());
        holder.lastSeen.setText(model.getLastSeenLoc());
        holder.lostDate.setText(model.getLostDate());
    }

    @NonNull
    @Override
    public ItemsLostDisplayAdapter.ItemsLostViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lost_items_data, parent, false);

        return new ItemsLostDisplayAdapter.ItemsLostViewholder(view);
    }

    class ItemsLostViewholder extends RecyclerView.ViewHolder
    {
        TextView itemName, itemDesc, itemType, lastSeen, lostDate;

        public ItemsLostViewholder(@NonNull View itemView)
        {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName_lostItemsFrag2);
            itemDesc = itemView.findViewById(R.id.itemDesc_lostItemsFrag2);
            itemType = itemView.findViewById(R.id.itemType_lostItemsFrag2);
            lastSeen = itemView.findViewById(R.id.lastSeen_lostItemsFrag2);
            lostDate = itemView.findViewById(R.id.lastSeenDate_lostItemsFrag2);
        }
    }
}
