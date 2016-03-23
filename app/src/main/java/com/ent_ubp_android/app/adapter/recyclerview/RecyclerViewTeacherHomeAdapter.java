package com.ent_ubp_android.app.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ent_ubp_android.app.Interface.IRecyclerViewClickListener;
import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.model.course.Course;

import java.util.List;

public class RecyclerViewTeacherHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Course> listCourse;
    public static IRecyclerViewClickListener mListener;

    private class ViewHolderTeacherHome extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nom_type;
        TextView formation;

        public ViewHolderTeacherHome(View itemView) {
            super(itemView);

            nom_type = (TextView) itemView.findViewById(R.id.teacher_name_type_course);
            formation = (TextView) itemView.findViewById(R.id.teacher_formation);
        }

        public void bind(Course cours){
            nom_type.setText(cours.getName() + " " + cours.getType());
            formation.setText("Master 1 - Informatique");
        }

        @Override
        public void onClick(View v) {
            mListener.onRecyclerViewItemClicked(v, this.getLayoutPosition());
        }
    }

    public RecyclerViewTeacherHomeAdapter(IRecyclerViewClickListener listener, List<Course> listCourse){
        this.listCourse = listCourse;
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_home_cell_card, parent, false);
        return new ViewHolderTeacherHome(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderTeacherHome) holder).bind(listCourse.get(position));
    }

    @Override
    public int getItemCount() {
        return listCourse.size();
    }
}
