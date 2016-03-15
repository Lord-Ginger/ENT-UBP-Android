package com.ent_ubp_android.app.fragment.classroom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import com.ent_ubp_android.app.R;

//TODO: Add link with the server
public class ClassroomAddFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    AppCompatCheckBox checkBoxTypeCM;
    AppCompatCheckBox checkBoxTypeTD;
    AppCompatCheckBox checkBoxTypeTP;

    public ClassroomAddFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classroom_add, container, false);

        checkBoxTypeCM = (AppCompatCheckBox) view.findViewById(R.id.checkbox_cm);
        checkBoxTypeTD = (AppCompatCheckBox) view.findViewById(R.id.checkbox_td);
        checkBoxTypeTP = (AppCompatCheckBox) view.findViewById(R.id.checkbox_tp);

        checkBoxTypeCM.setOnCheckedChangeListener(this);
        checkBoxTypeTD.setOnCheckedChangeListener(this);
        checkBoxTypeTP.setOnCheckedChangeListener(this);

        AppCompatButton submitButton = new AppCompatButton(getActivity());
        submitButton.setText("Valider");
        submitButton.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.button_validation));
        submitButton.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.white));

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10, 10, 10, 10);

        LinearLayout ll = (LinearLayout) view.findViewById(R.id.classroom_add_linearLayout);
        ll.addView(submitButton, lp);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            buttonView.setChecked(true);
        }else
            buttonView.setChecked(false);
    }

}
