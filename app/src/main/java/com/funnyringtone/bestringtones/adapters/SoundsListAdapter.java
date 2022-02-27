package com.funnyringtone.bestringtones.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.funnyringtone.bestringtones.R;
import com.funnyringtone.bestringtones.SoundRingScreen;
import com.funnyringtone.bestringtones.models.SoundModel;
import com.funnyringtone.bestringtones.ui.fragments.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class SoundsListAdapter extends RecyclerView.Adapter<SoundsListAdapter.ViewHolder> {

    private List<SoundModel> results;
    private HomeFragment fragment;
    private int[] rainbow;
    Context  mContext;



    public SoundsListAdapter(HomeFragment homeContext, List<SoundModel> data, Context mContext) {
        this.fragment = homeContext;
        this.results = data;
        rainbow = homeContext.getResources().getIntArray(R.array.rainbow);
        this.mContext = mContext;
    }

    public void setData(List<SoundModel> data) {
        this.results = new ArrayList<>();
        this.results = data;
        notifyDataSetChanged();
    }

    @Override
    public SoundsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new SoundsListAdapter.ViewHolder(inflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final SoundsListAdapter.ViewHolder holder, final int position) {
        final SoundModel object = results.get(position);
        holder.nameTV.setText(holder.itemView.getContext().getResources().getString(object.getNameId()));
        holder.durationTV.setText("00:0"+object.getDuration());

        holder.img.setColorFilter(rainbow[position], android.graphics.PorterDuff.Mode.MULTIPLY);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.playSound(object.getFileName(), position, object.isPlaying());


            }
        });
        holder.next_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent newIntent=new Intent(mContext, SoundRingScreen.class);
               newIntent.putExtra("name",object.getFileName());
               newIntent.putExtra("showname",holder.nameTV.getText());
               newIntent.putExtra("duration",object.getDuration());
               newIntent.putExtra("isplaying",String.valueOf(false));
               newIntent.putExtra("position",String.valueOf(position));
               mContext.startActivity(newIntent);
            }
        });




//        if (object.isPlaying()) {
//            holder.img.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), R.color.COLOR_YOUR_COLOR), android.graphics.PorterDuff.Mode.MULTIPLY);
//        } else {
//            holder.img.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), R.color.COLOR_YOUR_COLOR), android.graphics.PorterDuff.Mode.MULTIPLY);
//        }


    }


    @Override
    public int getItemCount() {
        if (results != null) {
            return results.size();
        } else return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout mainView;
        public ImageView img;
        public TextView nameTV;
        public TextView durationTV;
        public LinearLayout next_arrow;

        public ViewHolder(View itemView) {
            super(itemView);
            mainView = (RelativeLayout) itemView.findViewById(R.id.main_view);
            img = (ImageView) itemView.findViewById(R.id.img);
            next_arrow=itemView.findViewById(R.id.layout2);
            nameTV = (TextView) itemView.findViewById(R.id.name);
            durationTV = (TextView) itemView.findViewById(R.id.duration);
        }
    }
}