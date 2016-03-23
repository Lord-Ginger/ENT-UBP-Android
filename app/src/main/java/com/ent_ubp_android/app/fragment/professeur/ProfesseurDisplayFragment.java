package com.ent_ubp_android.app.fragment.professeur;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ent_ubp_android.app.Interface.IRecyclerViewClickListener;
import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.adapter.recyclerview.RecyclerViewProfesseurAdapter;
import com.ent_ubp_android.app.exchange.serveur.SingletonUbpRestTemplate;
import com.ent_ubp_android.app.fragment.FragmentSwitcher;
import com.ent_ubp_android.app.model.formation.FormationEnum;
import com.ent_ubp_android.app.model.teacher.Teacher;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ProfesseurDisplayFragment extends Fragment implements IRecyclerViewClickListener {
    private WeakReference<HttpRequestTask> asyncTaskWeakRef;
    private RecyclerView recyclerView;

    public static ProfesseurDisplayFragment newInstance(int position) {

        ProfesseurDisplayFragment myFragment = new ProfesseurDisplayFragment();

        Bundle args = new Bundle();
        args.putInt(String.valueOf(position), position);
        myFragment.setArguments(args);



        return myFragment;
    }

    public ProfesseurDisplayFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startNewAsyncTask();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_professeur_display, container, false);
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
    public void onRecyclerViewItemClicked(View view, int position) {

    }

    //Make the AsyncTask
    private void startNewAsyncTask(){
        HttpRequestTask asyncTask = new HttpRequestTask(this);
        this.asyncTaskWeakRef = new WeakReference<>(asyncTask);
        asyncTask.execute();
    }

    private boolean isAsyncTaskPending(){
        return this.asyncTaskWeakRef != null &&
                this.asyncTaskWeakRef.get() != null &&
                !this.asyncTaskWeakRef.get().getStatus().equals(AsyncTask.Status.FINISHED);
    }

    /* ************************************************* */
    /*  Inner Class make the transaction with the server */
    /* ************************************************* */
    private class HttpRequestTask extends AsyncTask<Void, Void, List<Teacher>> {

        private WeakReference<ProfesseurDisplayFragment> fragmentWeakRef;
        private List<Teacher> listTeacher;

        private HttpRequestTask(ProfesseurDisplayFragment fragment){
            this.fragmentWeakRef = new WeakReference<>(fragment);
            listTeacher = new ArrayList<>();
        }

        @Override
        protected List<Teacher> doInBackground(Void... params) {

            try {
                final String url = SingletonUbpRestTemplate.BASE_URL + "teacher";

                SingletonUbpRestTemplate restTemplate = SingletonUbpRestTemplate.getRestTemplate();
                Teacher[] tabProf = restTemplate.getForObject(url, Teacher[].class);
                return Arrays.asList(tabProf);
            }
            catch (HttpClientErrorException e){
                return null;
            }
            catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Teacher> teachers) {
            super.onPostExecute(teachers);

            if(teachers != null){
                recyclerView = (RecyclerView) fragmentWeakRef.get().getView().findViewById(R.id.professeur_rv);
                recyclerView.setLayoutManager(new LinearLayoutManager(FragmentSwitcher.getActivity()));
                recyclerView.setAdapter(new RecyclerViewProfesseurAdapter(fragmentWeakRef.get(), teachers));
            }
            else{
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
                alert.setTitle("Erreur de connection");
                alert.setMessage("Nous ne pouvons pas contacter le serveur. \n\n " +
                        "Vous devez être connecté à internet pour utiliser cette application");
                alert.setPositiveButton("Ok", null);
                alert.show();
            }
        }
    }

    @Override
    public void onRecyclerViewFormationItemClicked(View view, int position, FormationEnum type) {
    }
}


