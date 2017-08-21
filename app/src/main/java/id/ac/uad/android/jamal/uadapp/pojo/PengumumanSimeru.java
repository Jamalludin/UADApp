package id.ac.uad.android.jamal.uadapp.pojo;

/**
 * Created by jamal on 21/08/17.
 */

public class PengumumanSimeru {

    String matkulps,kelasps,harips,jamps,infops;

    public PengumumanSimeru() {
    }

    public PengumumanSimeru(String matkulps, String kelasps, String harips, String jamps, String infops) {
        this.matkulps = matkulps;
        this.kelasps = kelasps;
        this.harips = harips;
        this.jamps = jamps;
        this.infops = infops;
    }

    public String getMatkulps() {
        return matkulps;
    }

    public void setMatkulps(String matkulps) {
        this.matkulps = matkulps;
    }

    public String getKelasps() {
        return kelasps;
    }

    public void setKelasps(String kelasps) {
        this.kelasps = kelasps;
    }

    public String getHarips() {
        return harips;
    }

    public void setHarips(String harips) {
        this.harips = harips;
    }

    public String getJamps() {
        return jamps;
    }

    public void setJamps(String jamps) {
        this.jamps = jamps;
    }

    public String getInfops() {
        return infops;
    }

    public void setInfops(String infops) {
        this.infops = infops;
    }
}
