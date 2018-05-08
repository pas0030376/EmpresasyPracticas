package com.example.empresasypracticas;

import android.content.Context;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.concurrent.Executor;



public class MainActivityFragment extends Fragment {
    private Button out;
    private static final int RC_SIGN_IN = 123;
    FirebaseAuth auth;

    GoogleSignInClient mGoogleSignInClient;
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
        Button centre = view.findViewById(R.id.btncentre);
        out = view.findViewById(R.id.lvout);

        alumnado.setOnClickListener(listener);
        empresas.setOnClickListener(listener);
        centre.setOnClickListener(listener);
        out.setOnClickListener(listener);


        return view;
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
                case R.id.btncentre:
                    Intent centre = new Intent(view.getContext(), CentreActivity.class);
                    startActivityForResult(centre, 0);
                    break;
                case R.id.lvout:
                    signOut();
                    break;
            }
        }
    };

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

    //sign out user
    private void signOut() {
       auth.signOut();
        CharSequence text = "Signed out.";
        int duration = Toast.LENGTH_SHORT;
        Context context = getContext();
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        DoLogin();
    }

    //disconnect Google account
    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener((Executor) this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        CharSequence text = "Google account disconnected.";
                        int duration = Toast.LENGTH_SHORT;
                        Context context = getContext();
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }});
    }
}
