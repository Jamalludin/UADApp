package id.ac.uad.android.jamal.uadapp.pojo;

/**
 * Created by jamal on 13/08/17.
 */

public class SetListDosen {

    String niynipnidn,nama;

    public SetListDosen() {
    }

    public SetListDosen(String niynipnidn, String nama) {
        this.niynipnidn = niynipnidn;
        this.nama = nama;
    }

    public String getNiynipnidn() {
        return niynipnidn;
    }

    public void setNiynipnidn(String niynipnidn) {
        this.niynipnidn = niynipnidn;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
