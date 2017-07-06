package id.ac.uad.android.jamal.uadapp.simeru.fragmentruang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.uad.android.jamal.uadapp.R;

/**
 * Created by jamal on 06/07/17.
 */

public class RuangRabu extends Fragment {

    public RuangRabu() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ruang_rabu, container, false);
    }
}
