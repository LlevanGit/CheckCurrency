package com.example.checkcurrency.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.checkcurrency.R;

import org.w3c.dom.Text;

import java.util.List;

import static com.example.checkcurrency.MainActivity.mcontext;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private List<String> currNameData;
    private List<String> currSymbolData;
    private List<String> currRateData;
    private List<String> currChangeData;
    private List<Integer> currGifData;
    private LayoutInflater mInflater;
//    private ItemClickListener mClickListener;

    public MainRecyclerAdapter(Context context, List<String> currNameData, List<String> currSymbolData,List<String>currRateData,List<String>currChangeData,List<Integer>currGifData) {
        this.mInflater = LayoutInflater.from(context);
        this.currNameData=currNameData;
        this.currSymbolData=currSymbolData;
        this.currRateData=currRateData;
        this.currChangeData=currChangeData;
        this.currGifData=currGifData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.currencies_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String currName=currNameData.get(position);
        String currSymbol=currSymbolData.get(position);
        String currRate=currRateData.get(position);
        String currRateChange=currChangeData.get(position);
        int currGif=currGifData.get(position);

//        String writerr = writerData.get(position);

        holder.textViewcurrName.setText(currName);
        holder.textViewCurrSymbol.setText(currSymbol);
        holder.textViewcurrRate.setText(currRate);
        holder.textViewcurrChange.setText(currRateChange);
        Drawable up = mcontext.getResources().getDrawable(R.drawable.baseline_arrow_drop_up_black_18dp);
        Drawable down = mcontext.getResources().getDrawable(R.drawable.baseline_arrow_drop_down_black_18dp);
        if(currGif==1){
            holder.imageViewGIF.setImageDrawable(up);
        }else holder.imageViewGIF.setImageDrawable(down);
//
//        holder.writingTextView.setText(writing);
//        holder.writerTextView.setText(writerr);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return currNameData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewcurrName;
        TextView textViewCurrSymbol;
        TextView textViewcurrRate;
        TextView textViewcurrChange;
        ImageView imageViewGIF;

        ViewHolder(View itemView) {
            super(itemView);
            textViewcurrName = itemView.findViewById(R.id.textview_currency_name);
            textViewCurrSymbol = itemView.findViewById(R.id.textview_currency_symbol);
            textViewcurrRate = itemView.findViewById(R.id.textview_currency_rate);
            textViewcurrChange = itemView.findViewById(R.id.textview_currency_rate_change);
            imageViewGIF = itemView.findViewById(R.id.imageView_change);
//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            final String mwritingg=writingTextView.getText().toString();
//            final String mwriterr=writerTextView.getText().toString();
//            if (mClickListener != null) mClickListener.onFavouriteItemClick(view, getAdapterPosition(),mwritingg,mwriterr);
//        }
//    }

        // convenience method for getting data at click position
        String getItem(int id) {
            return currChangeData.get(id);
        }
//
//    // allows clicks events to be caught
//    void setClickListener(ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }

//    // parent activity will implement this method to respond to click events
//    public interface ItemClickListener {
//        void onFavouriteItemClick(View view, int position, String writingg, String writerr);
//    }
    }
}