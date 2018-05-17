package com.example.empresasypracticas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A placeholder fragment containing a simple view.
 */
public class EmpresasActivityFragment extends Fragment {
    Button newEmpresa;
    public static Empresa empresa;
    //This is our tablayout
    private TabLayout tabs;

    //This is our viewPager
    private ViewPager viewPager;


    public EmpresasActivityFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_empresas, container, false);
        getActivity().setTitle("Empresas");

        //Initializing the tablayout
        tabs = view.findViewById(R.id.tabsEmpresas);

        //Adding the tabs using addTab() method
        //Adding the tabs using addTab() method
        tabs.addTab(tabs.newTab());
        tabs.addTab(tabs.newTab());
        tabs.addTab(tabs.newTab());
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);


        //Initializing viewPager
        viewPager = view.findViewById(R.id.pagerEmpresas);

        //Creating our pager adapter
        PagerEmpresas adapter = new PagerEmpresas(getFragmentManager(), tabs.getTabCount());


        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        tabs = view.findViewById(R.id.tabsEmpresas);

        //to swipe views
        tabs.setupWithViewPager(viewPager);

        newEmpresa = view.findViewById(R.id.newEmpresa);

        newEmpresa.setOnClickListener(listener);


        return view;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId())
            {
                case R.id.newEmpresa:
                    Intent addEmpresa = new Intent(view.getContext(), addEmpresaActivity.class);
                    startActivityForResult(addEmpresa, 0);
                    break;
            }
        }
    };
}
