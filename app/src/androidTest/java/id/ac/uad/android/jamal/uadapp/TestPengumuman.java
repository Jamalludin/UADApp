package id.ac.uad.android.jamal.uadapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.util.concurrent.SettableFuture;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import id.ac.uad.android.jamal.uadapp.simeru.callbacksimeru.PengumumanCallBack;

import static org.junit.Assert.assertEquals;

/**
 * Created by jamal on 11/09/17.
 */

@RunWith(AndroidJUnit4.class)

public class TestPengumuman {

    String datanya = "{\"hasil\":[{\"id_dinding\":\"2\",\"id_ajar\":\"80\",\"jam\":\"08.45-10.25\",\"keterangan\":\"Diganti Elearning\",\"lainlain\":\"\",\"idajar\":\"80\",\"dosen_niynipnidn\":\"0512078304\",\"matakuliah_idmatakuliah\":\"2315420\",\"ruang_idruang\":\"3.1.320\",\"kelas\":\"B\",\"hari\":\"Senin\",\"idambil\":\"23\",\"mahasiswa_nim\":\"1300018056\",\"nilai\":null,\"nim\":\"1300018056\",\"nama\":\"Adi Saputra\",\"password\":\"0357a7592c4734a8b1e12bc48e29e9e9\",\"program_studi_idprogram_studi\":\"far00\"}]}";
    Context context;

    @Before
    public void TestPengumuman(){

        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void useTestPengumuman() throws Exception{

        PengumumanCallBack pc = new PengumumanCallBack(context);
        final SettableFuture<String> ff = SettableFuture.create();

        pc.PengumumanCallBack("1300018056", new PengumumanCallBack.PngumumanCallback() {
            @Override
            public void Result(String result) {

                ff.set(result);
            }
        });

        String isi = ff.get(5, TimeUnit.SECONDS);
        assertEquals(isi, datanya);
    }
}
