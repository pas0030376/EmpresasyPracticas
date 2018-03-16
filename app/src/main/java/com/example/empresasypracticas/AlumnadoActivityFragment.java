package com.example.empresasypracticas;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AlumnadoActivityFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    FirebaseListAdapter<Estudiante> adapter;
    FirebaseListOptions<Estudiante> options;
    Button newStudent;
    DatabaseReference query;
    DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Date currentTime = Calendar.getInstance().getTime();
    ListView lvalumnes;
    net.bohush.geometricprogressview.GeometricProgressView progressBar;
    TabLayout tabs;

    public AlumnadoActivityFragment() {
    }

  /*  @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alumnado, container, false);
        getActivity().setTitle("Alumnado");

        //Initializing the tablayout
        tabLayout = view.findViewById(R.id.tabs);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Todos"));
        tabLayout.addTab(tabLayout.newTab().setText("En Curso"));
        tabLayout.addTab(tabLayout.newTab().setText("Terminadas"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = view.findViewById(R.id.pager);

        //Creating our pager adapter
        PagerAdapter adapter = new PagerAdapter(getFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);

       // lvalumnes = (ListView) view.findViewById(R.id.lvalumnos);
        progressBar = view.findViewById(R.id.progressView);
        tabs = view.findViewById(R.id.tabs);

      /* query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Estudiantes");

        options = new FirebaseListOptions.Builder<Estudiante>()
                .setQuery(query,Estudiante.class)
                .setLayout(R.layout.lv_alumnos)
                .build();

        AdapterALL();

        lvalumnes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              Estudiante estudiante = (Estudiante) adapterView.getItemAtPosition(i);
              try {
                  Date fechafin = sdf.parse(estudiante.getFin_practicas());

                  if (currentTime.after(fechafin)){
                      Intent intent = new Intent(getContext(), DetailEstudent2Activity.class);
                      intent.putExtra("estudiante", estudiante);
                      startActivity(intent);}
                  else if (fechafin.after(currentTime)){
                      Intent intent = new Intent(getContext(), DetailEstudentActivity.class);
                      intent.putExtra("estudiante", estudiante);
                      startActivity(intent);
                    }
                  } catch (ParseException e1) {
                  e1.printStackTrace();
              }

           }
    }); */

        newStudent = view.findViewById(R.id.newStudent);

        newStudent.setOnClickListener(listener);

        return view;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId())
            {
                case R.id.newStudent:
                    Intent addStudiante = new Intent(view.getContext(), addEstudiantesActivity.class);
                    startActivityForResult(addStudiante, 0);
                break;
            }
        }
    };

   /* public void AdapterALL(){
        adapter = new FirebaseListAdapter<Estudiante>(options){
            @Override
            protected void populateView(View view, Estudiante model, int position) {
                TextView tvName = view.findViewById(R.id.tvname);
                tvName.setText(model.getNom()+" "+model.getCognom());
                TextView empresa = view.findViewById(R.id.tvEmpresa);
                empresa.setText(model.getEmpresa());
                TextView practica = view.findViewById(R.id.tvpracticas);
                try {
                    Date fechafin = sdf.parse(model.getFin_practicas());
                    if (currentTime.after(fechafin)){
                        practica.setText("Practicas terminadas");
                    }
                    else if (fechafin.after(currentTime)){
                        practica.setText("Practicas en curso");
                        practica.setTextColor(Color.parseColor("#0eae20"));
                    }
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                progressBar.setVisibility(View.GONE);
            }
        };
        lvalumnes.setAdapter(adapter);
    } */

    /**
     * Called when a tab enters the selected state.
     *
     * @param tab The tab that was selected
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    /**
     * Called when a tab exits the selected state.
     *
     * @param tab The tab that was unselected
     */
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    /**
     * Called when a tab that is already selected is chosen again by the user. Some applications
     * may use this action to return to the top level of a category.
     *
     * @param tab The tab that was reselected.
     */
    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}