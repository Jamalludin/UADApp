

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

    String datanya = "{\"hasil\":{\"Kamis\":[{\"idajar\":\"75\",\"dosen_niynipnidn\":\"0015118001\",\"matakuliah_idmatakuliah\":\"1865631\",\"ruang_idruang\":\"3.1.313\",\"kelas\":\"A\",\"jam\":\"08.45-10.45\",\"hari\":\"Kamis\",\"niynipnidn\":\"0015118001\",\"namadosen\":\"Fiftin Noviyanto, S.T., M. Cs\",\"program_studi_idprogram_studi\":\"tif00\",\"password\":\"da532bf806defa26fdbeee5dd2e0d68f\",\"idmatakuliah\":\"1865631\",\"namakul\":\"Pemrograman Web Dinamis\",\"semester\":\"6\",\"sks\":\"3\"},{\"idajar\":\"80\",\"dosen_niynipnidn\":\"0015118001\",\"matakuliah_idmatakuliah\":\"1815230\",\"ruang_idruang\":\"Ruang.8\",\"kelas\":\"D\",\"jam\":\"12.30-15.05\",\"hari\":\"Kamis\",\"niynipnidn\":\"0015118001\",\"namadosen\":\"Fiftin Noviyanto, S.T., M. Cs\",\"program_studi_idprogram_studi\":\"tif00\",\"password\":\"da532bf806defa26fdbeee5dd2e0d68f\",\"idmatakuliah\":\"1815230\",\"namakul\":\"Dasar Pemrograman\",\"semester\":\"1\",\"sks\":\"3\"}]}}";
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
