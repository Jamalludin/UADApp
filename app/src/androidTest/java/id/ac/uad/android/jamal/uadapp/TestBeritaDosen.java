package id.ac.uad.android.jamal.uadapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.util.concurrent.SettableFuture;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import id.ac.uad.android.jamal.uadapp.perwalian.callbackperwalian.BeritaCallBack;

import static org.junit.Assert.assertEquals;

/**
 * Created by jamal on 22/09/17.
 */

@RunWith(AndroidJUnit4.class)
public class TestBeritaDosen {

    String data = "{\"hasil\":[{\"idinformasi_perwalian\":\"8\",\"jam\":\"2017-09-22 02:50:16\",\"info\":\"pergi\",\"dosen_niynipnidn\":\"0014107301\"}]}";
    Context context;

    @Before
    public void TestBeritaDosen(){

        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void useTestBeritaDosen() throws Exception{

        BeritaCallBack bc = new BeritaCallBack(context);

        final SettableFuture<String> ff = SettableFuture.create();
        bc.BeritaCallBack("0014107301", new BeritaCallBack.Beritadsn() {
            @Override
            public void Result(String result) {

                ff.set(result);
            }
        });

        String isi = ff.get(5, TimeUnit.SECONDS);
        assertEquals(isi, data);
    }
}
