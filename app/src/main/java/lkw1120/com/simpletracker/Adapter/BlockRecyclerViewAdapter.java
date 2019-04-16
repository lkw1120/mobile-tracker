package lkw1120.com.simpletracker.Adapter;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import lkw1120.com.simpletracker.Model.BlockInfo;
import lkw1120.com.simpletracker.R;
import lkw1120.com.simpletracker.Task.GetBlockTask;
import lkw1120.com.simpletracker.TransactionActivity;

public class BlockRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<BlockInfo> blockList = new ArrayList<>();

    private final int TYPE_ITEM = 0;
    private final int TYPE_FOOTER = 1;

    private int height = -1;

    public void getBlockList() {
        try {
            GetBlockTask getBlockTask = new GetBlockTask();
            blockList.addAll(getBlockTask.execute(height).get());
            height = Integer.parseInt(blockList.get(blockList.size() - 1).getHeight());
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /*
    public void saveCheckedBlock() {
        for(BlockInfo blockInfo : blockList) {
            if(blockInfo.isSelected()) {
            }
        }
    }
    */

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View view;
        if (viewType == TYPE_FOOTER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer, parent, false);
            holder = new FooterViewHolder(view);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_block, parent, false);
            holder = new BlockViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof BlockViewHolder) {
            final BlockInfo blockInfo = blockList.get(holder.getAdapterPosition());
            BlockViewHolder blockViewHolder = (BlockViewHolder) holder;
            blockViewHolder.blockHash.setText(blockInfo.getBlockHash());
            blockViewHolder.blockHash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), TransactionActivity.class);
                    intent.putStringArrayListExtra("txHash",blockInfo.getTxHash());
                    ContextCompat.startActivity(view.getContext(),intent,null);
                }
            });

            blockViewHolder.checkBox.setOnCheckedChangeListener(null);
            blockViewHolder.checkBox.setChecked(blockInfo.isSelected());
            blockViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (buttonView.isChecked()) {
                            Log.d("Database","insert success?");
                    }
                    else {
                            Log.d("Database","delete success?");
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(blockList.size()%10 == 0)
            return blockList.size()+1;
        else
            return blockList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == blockList.size() && blockList.size()%10 == 0)
            return TYPE_FOOTER;
        else
            return TYPE_ITEM;
    }

    private class BlockViewHolder extends RecyclerView.ViewHolder {

        private TextView blockHash;
        private CheckBox checkBox;

        private BlockViewHolder(View view) {
            super(view);
            blockHash = view.findViewById(R.id.blockitem_textview_blockhash);
            checkBox = view.findViewById(R.id.blockitem_checkbox);
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {
        FooterViewHolder(View footerView) {
            super(footerView);
        }
    }
}