package com.ent_ubp_android.app.fragment.professeur.home_teacher;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.ent_ubp_android.app.adapter.recyclerview.RecyclerViewTeacherHomeAdapter;
import com.ent_ubp_android.app.exchange.serveur.SingletonUbpRestTemplate;
import com.ent_ubp_android.app.fragment.FragmentSwitcher;
import com.ent_ubp_android.app.model.course.Course;
import com.ent_ubp_android.app.model.formation.FormationEnum;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;

public class TeacherHomeFragment extends Fragment implements IRecyclerViewClickListener {

    private WeakReference<HttpRequestTask> asyncTaskWeakRef;
    private RecyclerView recyclerView;

    public TeacherHomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startNewAsyncTask();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teacher_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
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
    private class HttpRequestTask extends AsyncTask<Void, Void, List<Course>> {

        private WeakReference<TeacherHomeFragment> fragmentWeakRef;

        private HttpRequestTask(TeacherHomeFragment fragment){
            this.fragmentWeakRef = new WeakReference<>(fragment);
        }

        @Override
        protected List<Course> doInBackground(Void... params) {
            try {
                final String url = SingletonUbpRestTemplate.BASE_URL + "course";

                SingletonUbpRestTemplate restTemplate = SingletonUbpRestTemplate.getRestTemplate();
                Course[] tabCourse = restTemplate.getForObject(url, Course[].class);
                return Arrays.asList(tabCourse);
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
        protected void onPostExecute(List<Course> courses) {
            super.onPostExecute(courses);

            if(courses != null){
                recyclerView = (RecyclerView) fragmentWeakRef.get().getView().findViewById(R.id.teacher_home_rv);
                recyclerView.setLayoutManager(new LinearLayoutManager(FragmentSwitcher.getActivity()));
                recyclerView.setAdapter(new RecyclerViewTeacherHomeAdapter(fragmentWeakRef.get(), courses));
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
