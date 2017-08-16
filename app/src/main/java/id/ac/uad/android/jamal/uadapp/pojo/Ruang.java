package id.ac.uad.android.jamal.uadapp.pojo;

/**
 * Created by jamal on 14/08/17.
 */

public class Ruang {

    String idruang, kapasitas;

    public Ruang() {
    }

    public Ruang(String idruang, String kapasitas) {
        this.idruang = idruang;
        this.kapasitas = kapasitas;
    }

    public String getIdruang() {
        return idruang;
    }

    public void setIdruang(String idruang) {
        this.idruang = idruang;
    }

    public String getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(String kapasitas) {
        this.kapasitas = kapasitas;
    }
}
