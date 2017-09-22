package id.ac.uad.android.jamal.uadapp.pojo;

/**
 * Created by jamal on 22/09/17.
 */

public class SetTopik {

    String idtopik,jamtopik, infotopik;

    public SetTopik() {
    }

    public SetTopik(String jamtopik, String infotopik, String idtopik) {
        this.idtopik = idtopik;
        this.jamtopik = jamtopik;
        this.infotopik = infotopik;
    }

    public String getIdtopik() {
        return idtopik;
    }

    public void setIdtopik(String idtopik) {
        this.idtopik = idtopik;
    }

    public String getJamtopik() {
        return jamtopik;
    }

    public void setJamtopik(String jamtopik) {
        this.jamtopik = jamtopik;
    }

    public String getInfotopik() {
        return infotopik;
    }

    public void setInfotopik(String infotopik) {
        this.infotopik = infotopik;
    }
}
