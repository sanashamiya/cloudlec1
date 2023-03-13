package com.example.cloudlec1.NoteApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloudlec1.R;
import com.example.cloudlec1.UserAdapter;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    private List<Note> mData;
    private LayoutInflater mInflater;
    private NoteAdapter.ItemClickListener mClickListener;
    private NoteAdapter.ItemClickListener2 itemClickListener2;


    NoteAdapter(Context context, List<Note> data, NoteAdapter.ItemClickListener onClick, NoteAdapter.ItemClickListener2 onClick2) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mClickListener = onClick;
        this.itemClickListener2 = onClick2;
    }

    public  ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
          View view = mInflater.inflate(R.layout.activity_note_card, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder,  int position) {

        holder.Titel.setText(mData.get(position).getTitele());
        holder.Desc.setText(mData.get(position).getDescription());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(holder.getAdapterPosition(), mData.get(position).id);

            }
        });
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener2.onItemClick2(holder.getAdapterPosition(), mData.get(position).id);

            }
        });
    }

    @Override
    public int getItemCount() {
        return  mData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView Titel;
        public TextView Desc;
        public ImageView delete;
        public CardView card;

        ViewHolder(View itemView) {
            super(itemView);
            this.Titel = itemView.findViewById(R.id.Title);
            this.Desc = itemView.findViewById(R.id.Description);
            this.delete = itemView.findViewById(R.id.delete);
            this.card = itemView.findViewById(R.id.card2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

    }
    Note getItem(int id) {
        return mData.get(id);
    }
    void setClickListener(UserAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = (ItemClickListener) itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(int position, String id);
    }

    public interface ItemClickListener2{
        void onItemClick2(int position, String id);
    }

}
