package id.ac.uad.android.jamal.uadapp.simeru.fragmentkuliah;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.uad.android.jamal.uadapp.R;

/**
 * Created by jamal on 15/03/17.
 */

public class JumatFragment extends Fragment {

    public JumatFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jumat, container, false);
    }
}
