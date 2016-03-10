package com.ent_ubp_android.app.Interface;

import android.view.View;
import com.ent_ubp_android.app.model.formation.FormationEnum;

public interface IRecyclerViewClickListener {
    void onRecyclerViewFormationItemClicked(View view, int position, FormationEnum type);
    void onRecyclerViewItemClicked(View view, int position);
}
