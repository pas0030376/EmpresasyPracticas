package com.example.empresasypracticas;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AlumnadoActivityFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    //This is our tablayout
    private TabLayout tabs;

    //This is our viewPager
    private ViewPager viewPager;

    Button newStudent;
    net.bohush.geometricprogressview.GeometricProgressView progressBar;

    public AlumnadoActivityFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alumnado, container, false);
        getActivity().setTitle("Alumnado");

        //Initializing the tablayout
        tabs = view.findViewById(R.id.tabs);

        //Adding the tabs using addTab() method
        tabs.addTab(tabs.newTab());
        tabs.addTab(tabs.newTab());
        tabs.addTab(tabs.newTab());
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);


        //Initializing viewPager
        viewPager = view.findViewById(R.id.pager);

        //Creating our pager adapter
        PagerAdapter adapter = new PagerAdapter(getFragmentManager(), tabs.getTabCount());


        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        tabs = view.findViewById(R.id.tabs);

        //to swipe views
        tabs.setupWithViewPager(viewPager);

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