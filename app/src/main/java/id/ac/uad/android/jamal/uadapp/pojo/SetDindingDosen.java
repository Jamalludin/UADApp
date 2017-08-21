package id.ac.uad.android.jamal.uadapp.pojo;

/**
 * Created by jamal on 17/08/17.
 */

public class SetDindingDosen {

    String jamdinding, pengumuman;

    public SetDindingDosen() {
    }

    public SetDindingDosen(String jamdinding, String pengumuman) {
        this.jamdinding = jamdinding;
        this.pengumuman = pengumuman;
    }

    public String getJamdinding() {
        return jamdinding;
    }

    public void setJamdinding(String jamdinding) {
        this.jamdinding = jamdinding;
    }

    public String getPengumuman() {
        return pengumuman;
    }

    public void setPengumuman(String pengumuman) {
        this.pengumuman = pengumuman;
    }
}
