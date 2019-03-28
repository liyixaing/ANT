package com.example.administrator.mode.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.mode.Fragment.homeFragment.ConsultActivity;
import com.example.administrator.mode.Fragment.homeFragment.GameActivity;
import com.example.administrator.mode.Fragment.homeFragment.dialActivity;
import com.example.administrator.mode.Fragment.red_packet.RedPacketGetActivity;
import com.example.administrator.mode.R;
import com.example.administrator.mode.Utlis.ClickUtlis;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {

    private List<Integer> list;
    private Context context;

    public BannerAdapter(Context context, List<Integer> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.imageView.setOnClickListener(new ClickUtlis() {
            @Override
            public void onMultiClick(View v) {
                Integer aa = list.get(position % list.size());
                Log.i("dasdas", aa.toString());

                if (aa == 2131230821) {
                    Intent intent = new Intent(context, GameActivity.class);
                    context.startActivity(intent);
                }
                if (aa == 2131230823) {
                    Intent intent = new Intent(context, ConsultActivity.class);
                    intent.putExtra("webUrl","mountain");
                    context.startActivity(intent);
                }
                if (aa == 2131230824) {
                    Intent intent = new Intent(context, ConsultActivity.class);
                    intent.putExtra("webUrl","funding");
                    context.startActivity(intent);
                }


                if (aa == 2131230822) {
                    Intent intent = new Intent(context, RedPacketGetActivity.class);
                    context.startActivity(intent);
                }
                if (aa == 2131230825) {

                    Intent intent = new Intent(context, dialActivity.class);
                    context.startActivity(intent);
                }
            }
        });
        Glide.with(context).load(list.get(position % list.size())).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
        }
    }

}