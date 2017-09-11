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

import id.ac.uad.android.jamal.uadapp.simeru.callbacksimeru.RuangCallBack;

import static org.junit.Assert.assertEquals;

/**
 * Created by jamal on 07/09/17.
 */
@RunWith(AndroidJUnit4.class)
public class TestRuang {

    String databenr = "{\"hasil\":{\"Kamis\":[{\"idruang\":\"3.1.314\",\"kapasitas\":\"45\",\"kampus_idkampus\":\"kmp03\",\"idajar\":\"79\",\"dosen_niynipnidn\":\"60120712\",\"matakuliah_idmatakuliah\":\"prosesbisnisdanpemodelansistem\",\"ruang_idruang\":\"3.1.314\",\"kelas\":\"B\",\"jam\":\"1,2,3\",\"hari\":\"Kamis\",\"idprogram_studi\":\"tif00\",\"namaprodi\":\"Teknik Informatika\",\"fakultas_idfakultas\":\"fti00\",\"idmatakuliah\":\"prosesbisnisdanpemodelansistem\",\"sks\":\"4\",\"semester\":\"5\",\"namakul\":\"proses bisnis dan pemodelan sistem\",\"program_studi_idprogram_studi\":\"pbio00\",\"niynipnidn\":\"60120712\",\"namadosen\":\"Agus Ria Kumara, S.Pd\",\"password\":\"f9767f5e9cc3bfa77e28f71c8cd3ddc2\"}]}}";

    Context context;
    @Before
    public void Test(){
        context = InstrumentationRegistry.getContext();

    }

    @Test
    public void useAppContext() throws Exception{

        RuangCallBack personUad = new RuangCallBack(context);

        final SettableFuture<String> ff = SettableFuture.create();
             personUad.RuangCallBack("3.1.314", new RuangCallBack.Callback() {
                 @Override
                 public void Result(String result) {

                     ff.set(result);

                 }
             });
        String data = ff.get(5, TimeUnit.SECONDS);
        assertEquals(data, databenr);
    }
}
