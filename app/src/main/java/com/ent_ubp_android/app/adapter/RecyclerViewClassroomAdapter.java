package com.ent_ubp_android.app.adapter;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ent_ubp_android.app.Interface.IRecyclerViewClickListener;
import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.model.classroom.Classroom;
import com.ent_ubp_android.app.model.classroom.ClassroomType;

import java.util.List;

public class RecyclerViewClassroomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static IRecyclerViewClickListener mListener;
    private List<Classroom> listClassroom;

    private class ViewHolderClassroom extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nom;
        TextView capacite;
        TextView type;

        public ViewHolderClassroom(View itemView) {
            super(itemView);

            nom = (TextView) itemView.findViewById(R.id.classroom_nom);
            capacite = (TextView) itemView.findViewById(R.id.classroom_capacity);
            type = (TextView) itemView.findViewById(R.id.classroom_type);

            itemView.setOnClickListener(this);
        }

        public void bind(Classroom classroom) {
            nom.setText(classroom.getName());
            capacite.setText("Capacité : " + String.valueOf(classroom.getRoomCapacity().getMaxCapacity()) + " places");

            Object[] tabTypes = classroom.getTypes().toArray();
            type.setText("Type : ");
            for(int i = 0; i < tabTypes.length; i++){
                type.append( ((ClassroomType)tabTypes[i]).name());

                if(i != tabTypes.length-1)
                    type.append(" / ");
            }

        }

        @Override
        public void onClick(View v) {
            mListener.onRecyclerViewItemClicked(v, getLayoutPosition());
        }
    }

    public RecyclerViewClassroomAdapter(IRecyclerViewClickListener listener, List<Classroom> listClassroom){
        this.listClassroom = listClassroom;
        this.mListener = listener;
    }

    @Override
    public ViewHolderClassroom onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.classroom_display_cell_card, parent, false);
        return new ViewHolderClassroom(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderClassroom) holder).bind(listClassroom.get(position));
    }

    @Override
    public int getItemCount() {
        return listClassroom.size();
    }
}
