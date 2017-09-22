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
import id.ac.uad.android.jamal.uadapp.perwalian.callbackperwalian.TopikCallBack;

import static org.junit.Assert.assertEquals;

/**
 * Created by jamal on 23/09/17.
 */

@RunWith(AndroidJUnit4.class)
public class TestTopik {

    String data = "[{\"idtopik\":\"4\",\"iduser\":\"1300018089\",\"topik\":\"assslaamualaikum, pak kira-kira matakuliah apa saja yang di ambil untuk semester depan, terima kasih\",\"jam\":\"2017-09-22 18:47:33\"}]";
    Context context;

    @Before
    public void TestTopik(){

        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void useTestTopik() throws Exception{

        TopikCallBack tb = new TopikCallBack(context);

        final SettableFuture<String> ff = SettableFuture.create();

        tb.TopikCallBack("1300018089", new TopikCallBack.Addtopik() {
            @Override
            public void Hasil(String hasil) {

                ff.set(hasil);
            }
        });

        String isi = ff.get(5, TimeUnit.SECONDS);
        assertEquals(isi, data);
    }
}
