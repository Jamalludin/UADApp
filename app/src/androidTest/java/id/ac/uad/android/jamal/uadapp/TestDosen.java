package id.ac.uad.android.jamal.uadapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.util.concurrent.SettableFuture;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import id.ac.uad.android.jamal.uadapp.simeru.callbacksimeru.DosenCallBack;

import static org.junit.Assert.assertEquals;


/**
 * Created by jamal on 10/09/17.
 */

@RunWith(AndroidJUnit4.class)
public class TestDosen {

    String datanya = "{\"hasil\":{\"Sabtu\":[{\"idajar\":\"11\",\"dosen_niynipnidn\":\"0015118001\",\"matakuliah_idmatakuliah\":\"sisdig17\",\"ruang_idruang\":\"3.1.319\",\"kelas\":\"B\",\"jam\":\"7,8\",\"hari\":\"Sabtu\",\"niynipnidn\":\"0015118001\",\"namadosen\":\"Fiftin Noviyanto, S.T., M. Cs\",\"program_studi_idprogram_studi\":\"tif00\",\"password\":\"f1163db4fa5d133f619620d0d0d0a454\",\"idmatakuliah\":\"sisdig17\",\"sks\":\"3\",\"semester\":\"4\",\"namakul\":\"Sistem Digital\"}]}}";
    Context context;

    @Before
    public void Test(){
        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void useTestDosen() throws Exception{

        DosenCallBack dosen = new DosenCallBack(context);

        final SettableFuture<String> ff = SettableFuture.create();

        dosen.DosenCallBack("0015118001", new DosenCallBack.DsnCallback() {
            @Override
            public void Result(String result) {

                ff.set(result);
            }
        });

        String isi = ff.get(5, TimeUnit.SECONDS);
        assertEquals(isi, datanya);
    }
}
