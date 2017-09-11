package id.ac.uad.android.jamal.uadapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.util.concurrent.SettableFuture;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import id.ac.uad.android.jamal.uadapp.perwalian.callbackperwalian.TranskipCallBack;

import static org.junit.Assert.assertEquals;

/**
 * Created by jamal on 11/09/17.
 */
@RunWith(AndroidJUnit4.class)
public class TestTranskip {

    String datanya = "[{\"idambil\":\"22\",\"matakuliah_idmatakuliah\":\"pmob18\",\"mahasiswa_nim\":\"1300018089\",\"nilai\":\"B-\",\"idajar\":\"78\",\"nim\":\"1300018089\",\"nama\":\"jamalludin\",\"password\":\"0357a7592c4734a8b1e12bc48e29e9e9\",\"dosen_niynipnidn\":\"0512078304\",\"program_studi_idprogram_studi\":\"tif00\",\"idmatakuliah\":\"pmob18\",\"sks\":\"3\",\"semester\":\"7\",\"namakul\":\"Pemrograman Mobile\",\"ruang_idruang\":\"3.1.319\",\"kelas\":\"A\",\"jam\":\"8\",\"hari\":\"Selasa\"}]";
    Context context;

    @Before
    public void Test(){

        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void useTestTranskip() throws Exception{

        TranskipCallBack tc = new TranskipCallBack(context);

        final SettableFuture<String> ff = SettableFuture.create();
        tc.TranskipCallBack("1300018089", new TranskipCallBack.TransCallBack() {
            @Override
            public void Result(String result) {

                ff.set(result);
            }
        });

        String data = ff.get(5, TimeUnit.SECONDS);
        assertEquals(data, datanya);
    }
}
