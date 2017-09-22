package id.ac.uad.android.jamal.uadapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.util.concurrent.SettableFuture;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import id.ac.uad.android.jamal.uadapp.simeru.callbacksimeru.JadwalKuliahCallBack;

import static org.junit.Assert.assertEquals;

/**
 * Created by jamal on 10/09/17.
 */


@RunWith(AndroidJUnit4.class)
public class TestJadwalKuliah {

    String Datanya = "{\"hasil\":{\"Senin\":[{\"idprogram_studi\":\"far00\",\"namaprodi\":\"Farmasi\",\"fakultas_idfakultas\":\"ffarma00\",\"kampus_idkampus\":\"kmp03\",\"idajar\":\"80\",\"dosen_niynipnidn\":\"60960143\",\"matakuliah_idmatakuliah\":\"2315420\",\"ruang_idruang\":\"3.1.320\",\"kelas\":\"B\",\"jam\":\"08.45-10.25\",\"hari\":\"Senin\",\"idmatakuliah\":\"2315420\",\"sks\":\"2\",\"semester\":\"1\",\"namakul\":\"Kimia Organik I\",\"program_studi_idprogram_studi\":\"far00\",\"niynipnidn\":\"60960143\",\"namadosen\":\"Dr., NURKHASANAH, M.Si., Apt.\",\"password\":\"da532bf806defa26fdbeee5dd2e0d68f\"}]}}";

    Context context;

    @Before
    public void TestJadwalKuliah(){

        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void useTestJadwalKuliah() throws Exception{

        JadwalKuliahCallBack kuliah = new JadwalKuliahCallBack(context);

        final SettableFuture<String> ff = SettableFuture.create();

        kuliah.JadwalKuliahCallBack("far00", new JadwalKuliahCallBack.Kuliah() {
            @Override
            public void Result(String result) {

                ff.set(result);
            }
        });

        String isi = ff.get(5, TimeUnit.SECONDS);
        assertEquals(isi, Datanya);
    }
}
