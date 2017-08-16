package id.ac.uad.android.jamal.uadapp.pojo;

/**
 * Created by jamal on 14/08/17.
 */

public class Kampus {

    String nama, alamat, id;

    public Kampus() {
    }

    public Kampus(String nama, String alamat, String id) {
        this.nama = nama;
        this.alamat = alamat;
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
