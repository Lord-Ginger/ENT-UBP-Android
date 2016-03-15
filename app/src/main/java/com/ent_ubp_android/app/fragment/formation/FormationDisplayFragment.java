package com.ent_ubp_android.app.fragment.formation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ent_ubp_android.app.Interface.IRecyclerViewClickListener;
import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.adapter.recyclerview.RecyclerViewFormationAdapter;
import com.ent_ubp_android.app.exchange.serveur.SingletonUbpRestTemplate;
import com.ent_ubp_android.app.fragment.FragmentSwitcher;
import com.ent_ubp_android.app.model.formation.FormationComponent;
import com.ent_ubp_android.app.model.formation.FormationComposite;
import com.ent_ubp_android.app.model.formation.FormationEnum;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

//TODO: Put a return button on the display list
public class FormationDisplayFragment extends Fragment implements IRecyclerViewClickListener{

    private WeakReference<HttpRequestTask> asyncTaskWeakRef;
    private RecyclerView recyclerView;

    public static FormationDisplayFragment newInstance(int position) {
        FormationDisplayFragment myFragment = new FormationDisplayFragment();

        Bundle args = new Bundle();
        args.putInt(String.valueOf(position), position);
        myFragment.setArguments(args);

        return myFragment;
    }

    public FormationDisplayFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setRetainInstance(true);
        startNewAsyncTask();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_formation_display, container, false);
    }

    //Do Process
    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume (){
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause (){
        super.onPause();
    }

    @Override
    public void onStop (){
        super.onStop();
    }

    @Override
    public void onViewStateRestored (Bundle savedInstanceState){
        super.onViewStateRestored(savedInstanceState);
    }

    //TODO: PRINT IN GREEN WHEN THE NODE IS A LEAF
    //TODO: ENABLE BUTTON + MAKE A TRANSITION TO UE FRAGMENT
    @Override
    public void onRecyclerViewFormationItemClicked(View view, int position, FormationEnum type) {
        if(type.equals(FormationEnum.COMPOSITE)) {

            RecyclerViewFormationAdapter adapter = (RecyclerViewFormationAdapter) recyclerView.getAdapter();
            List<FormationComponent> childList = new ArrayList<>();

            if (adapter.getItem(position).isLeaf()) {
                childList.add(adapter.getItem(position));
            } else {
                childList.addAll(adapter.getItem(position).getFormations());
            }

            adapter.changeDataSet(childList);
        }

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
    private class HttpRequestTask extends AsyncTask<Void, Void, FormationComposite> {

        private WeakReference<FormationDisplayFragment> fragmentWeakRef;
        private List<FormationComposite> listFormation;

        private HttpRequestTask(FormationDisplayFragment fragment){
            this.fragmentWeakRef = new WeakReference<>(fragment);
            listFormation = new ArrayList<>();
        }

        @Override
        protected FormationComposite doInBackground(Void... params) {
            try {
                final String url = SingletonUbpRestTemplate.BASE_URL + "formation/root";

                SingletonUbpRestTemplate restTemplate = SingletonUbpRestTemplate.getRestTemplate();
                return restTemplate.getForObject(url, FormationComposite.class);

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
        protected void onPostExecute(FormationComposite formationComposite) {
            super.onPostExecute(formationComposite);

            if(formationComposite != null){
                listFormation.add(formationComposite);
                recyclerView = (RecyclerView) fragmentWeakRef.get().getView().findViewById(R.id.formation_rv);
                recyclerView.setLayoutManager(new LinearLayoutManager(FragmentSwitcher.getActivity()));
                recyclerView.setAdapter(new RecyclerViewFormationAdapter(fragmentWeakRef.get(), listFormation));
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
    public void onRecyclerViewItemClicked(View view, int position) {
    }
}
