package id.ac.uad.android.jamal.uadapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import id.ac.uad.android.jamal.uadapp.simeru.Pengumuman;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("id.ac.uad.android.jamal.uadapp", appContext.getPackageName());
    }
    /*@Test
    public void testPengumuman() throws Exception{

        Pengumuman pengumuman = (Pengumuman) InstrumentationRegistry.getTargetContext();

        String data = pengumuman.testKon();
        assertEquals(data, "2016-10-28 19:00:07");
    }*/
}
