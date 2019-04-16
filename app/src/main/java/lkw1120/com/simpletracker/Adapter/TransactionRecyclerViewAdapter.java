package lkw1120.com.simpletracker.Adapter;

import androidx.annotation.NonNull;
import android.content.Intent;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import lkw1120.com.simpletracker.Model.TransactionInfo;
import lkw1120.com.simpletracker.R;
import lkw1120.com.simpletracker.ResultActivity;
import lkw1120.com.simpletracker.Task.GetTransactionTask;

public class TransactionRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<TransactionInfo> txList = new ArrayList<>();

    public void getTransactionList(ArrayList<String> txHash) {
        try {
            String[] txArray = new String[txHash.size()];
            for(int i=0;i<txArray.length;i++) txArray[i] = txHash.get(i);
            GetTransactionTask getTransactionTask = new GetTransactionTask();
            txList.addAll(getTransactionTask.execute(txArray).get());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        holder = new TransactionViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TransactionViewHolder) {
            final TransactionInfo txInfo = txList.get(holder.getAdapterPosition());
            ((TransactionViewHolder) holder).txHash.setText(txInfo.getTxHash());
            ((TransactionViewHolder) holder).from.setText(txInfo.getFrom());
            ((TransactionViewHolder) holder).to.setText(txInfo.getTo());
            ((TransactionViewHolder) holder).txHash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), ResultActivity.class);
                    intent.putExtra("txHash",txInfo.getTxHash());
                    ContextCompat.startActivity(view.getContext(),intent,null);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return txList.size();
    }

    private class TransactionViewHolder extends RecyclerView.ViewHolder {

        private TextView txHash;
        private TextView from;
        private TextView to;

        private TransactionViewHolder(View view) {
            super(view);
            txHash = view.findViewById(R.id.transactionitem_textview_txhash);
            from = view.findViewById(R.id.transactionitem_textview_from);
            to = view.findViewById(R.id.transactionitem_textview_to);
        }
    }

}
