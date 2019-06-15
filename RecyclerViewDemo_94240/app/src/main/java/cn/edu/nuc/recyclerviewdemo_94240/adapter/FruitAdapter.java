package cn.edu.nuc.recyclerviewdemo_94240.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import cn.edu.nuc.recyclerviewdemo_94240.Fruit;
import cn.edu.nuc.recyclerviewdemo_94240.R;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> fruits = null;

    static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView fruit_image = null;

        private TextView fruit_name = null;

        private View friutview = null;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fruit_image = (ImageView) itemView.findViewById(R.id.fruit_image);

            fruit_name = (TextView) itemView.findViewById(R.id.fruit_name);

            friutview = itemView;

        }
    }

    public FruitAdapter(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fruit_layout, viewGroup, false);

        final ViewHolder holder = new ViewHolder(view);

        holder.friutview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();

                Fruit fruit = fruits.get(position);

                Toast.makeText(v.getContext(), "You click view." + fruit.getName(), Toast.LENGTH_SHORT).show();

            }
        });

        holder.fruit_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();

                Fruit fruit = fruits.get(position);

                Toast.makeText(v.getContext(), "You click view." + fruit.getName(), Toast.LENGTH_SHORT).show();

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Fruit fruit = fruits.get(i);

        viewHolder.fruit_image.setImageResource(fruit.getImageId());

        viewHolder.fruit_name.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }
}