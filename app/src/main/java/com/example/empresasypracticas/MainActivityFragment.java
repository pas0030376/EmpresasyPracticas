package com.example.empresasypracticas;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

public class MainActivityFragment extends Fragment {
    private Button out;
    private static final int RC_SIGN_IN = 123;
    FirebaseAuth auth;
    private GoogleSignInClient mGoogleSignInClient;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        getActivity().setTitle("");
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null){
        }else{
            DoLogin();
        }

        Button alumnado = view.findViewById(R.id.alumnado);
        Button empresas = view.findViewById(R.id.empresas);
        out = view.findViewById(R.id.lvout);

        alumnado.setOnClickListener(listener);
        empresas.setOnClickListener(listener);
        out.setOnClickListener(listener);


        return view;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
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
                case R.id.lvout:
                    signOut();
                    break;
            }
        }
    };

   private void signOut() {

       FirebaseAuth.getInstance().signOut();
       Toast.makeText(getContext(), "Logged out.", Toast.LENGTH_SHORT).show();
       Log.d(TAG,"onAuthStateChanged:signed_out!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        // Google revoke access
        mGoogleSignInClient.signOut().addOnCompleteListener((Executor) this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), "Logged out.", Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"onAuthStateChanged:signed_out!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    }
                });
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


}
