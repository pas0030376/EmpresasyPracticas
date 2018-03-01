package com.example.empresasypracticas;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class MainActivityFragment extends Fragment {
    private Button out;
    private static final int RC_SIGN_IN = 123;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        getActivity().setTitle("");
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null){
        }else{
            DoLogin();
        }

        //out = view.findViewById(R.id.lvout);
        Button alumnado = view.findViewById(R.id.alumnado);
        Button empresas = view.findViewById(R.id.empresas);


        //out.setOnClickListener(listener);
        alumnado.setOnClickListener(listener);
        empresas.setOnClickListener(listener);


        return view;
    }

    private void DoLogin() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.EmailBuilder().build(),
                                new AuthUI.IdpConfig.GoogleBuilder().build()))
                        .build(),
                RC_SIGN_IN);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId())
            {
                case R.id.alumnado:
                    Intent alumnado = new Intent(view.getContext(), AlumnadoActivity.class);
                    startActivityForResult(alumnado, 0);
                    break;
                case R.id.empresas:
                    Intent empresas = new Intent(view.getContext(), EmpresasActivity.class);
                    startActivityForResult(empresas, 0);
                    break;
               /* case R.id.lvout:
                    FirebaseAuth.getInstance().signOut();
                    Intent restart = new Intent(view.getContext(),MainActivity.class);
                    startActivityForResult(restart,0);
                    break; */
            }
        }
    };
}
