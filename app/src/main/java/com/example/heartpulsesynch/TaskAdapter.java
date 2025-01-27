package com.example.heartpulsesynch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewholder> {
    private Context mContext;
    private ArrayList<ModelClass> mclass;
    private  ModelClass modelClass;
    private ClickListener clickListener;
    public  TaskAdapter(Context context, ArrayList<ModelClass>mclass) {
        this.mclass= mclass;
        this.mContext = context;

    }

    /**
     * Data are shown in the main page by recycler view.
     */

    class TaskViewholder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView tx1,tx2,tx3,tx4;
        Button editButton,deleteButton;
        TextView status;
        CardView cardView;

        public TaskViewholder(@NonNull View itemView) {
            super(itemView);
            tx1= itemView.findViewById(R.id.tvDate);
            tx2= itemView.findViewById(R.id.tvSystolic);
            tx3=itemView.findViewById(R.id.tvDiastolic);
            tx4=itemView.findViewById(R.id.tvHeartRate);
            editButton=itemView.findViewById(R.id.Edit_buttonId);
            deleteButton = itemView.findViewById(R.id.DeleteBUttonId);
            cardView= itemView.findViewById(R.id.CardView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);


        }

        /**
         * view details
         * @param view
         */

        @Override
        public void onClick(View view) {
            clickListener.customOnClick(getAdapterPosition(), view);

        }

        /**
         * view details
         * @param view
         * @return
         */
        @Override
        public boolean onLongClick(View view) {
            clickListener.customOnLongClick(getAdapterPosition(), view);
            return true;
        }

    }

    /**
     * click to delete
     * @param clickL
     */
    public void setClickListener(ClickListener clickL)
    {
        this.clickListener = clickL;
    }

    @NonNull
    @Override
    public TaskViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext())  ;
        View view= inflater.inflate(R.layout.singlerow,parent,false);
        return new TaskViewholder (view);
    }

    /**
     *
     */

    public interface ClickListener {
        void customOnClick(int position, View v);

        void customOnLongClick(int position, View v);

        void onDeleteClick(int position);

        void onEditClick(int position);
        void DetailClick(int position);

    }

    /**
     * get all parameter
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.tx1.setText(mclass.get(position).getDate());
        holder.tx2.setText(mclass.get(position).getSystolic());
        holder.tx3.setText(mclass.get(position).getDiastolic());
        holder.tx4.setText(mclass.get(position).getBloodPressure());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                clickListener.onDeleteClick(position);
            }
        });
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onEditClick(position);

            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.DetailClick(position);

            }
        });

        /**
         * Set condition of text colors.
         */

        modelClass = mclass.get(position);
        if (Integer.parseInt(modelClass.getDiastolic())>60 && (Integer.parseInt(modelClass.getDiastolic())<90))holder.tx3.setTextColor(Color.parseColor("#FF018786"));
            // else if(Integer.parseInt(modelClass.getDiastolic())<89)
            // holder.tx3.setTextColor(Color.parseColor("#3C96DD"));
        else holder.tx3.setTextColor(Color.parseColor("#C3473E"));


        if (Integer.parseInt(modelClass.getSystolic())>90 &&( Integer.parseInt(modelClass.getSystolic())<140)) holder.tx2.setTextColor(Color.parseColor("#FF018786"));
            //else if(Integer.parseInt(modelClass.getSystolic())<=140) holder.tx2.setTextColor(Color.parseColor("#3C96DD"));
        else holder.tx2.setTextColor(Color.parseColor("#C3473E"));


        if (Integer.parseInt(modelClass.getBloodPressure())>60 && Integer.parseInt(modelClass.getBloodPressure())<100) holder.tx4.setTextColor(Color.parseColor("#FF018786"));
        else if(Integer.parseInt(modelClass.getBloodPressure())>=40) holder.tx4.setTextColor(Color.parseColor("#3C96DD"));
        else holder.tx4.setTextColor(Color.parseColor("#C3473E"));



    }


    @Override
    public int getItemCount() {
        return mclass.size() ;
    }
}
