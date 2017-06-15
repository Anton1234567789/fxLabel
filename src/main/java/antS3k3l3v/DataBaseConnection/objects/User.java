package antS3k3l3v.DataBaseConnection.objects;

import javafx.beans.property.SimpleStringProperty;

public class User {

    private final SimpleStringProperty DATE_OP;
    private final SimpleStringProperty OLDNAME;
    private final SimpleStringProperty NEWNAME;
    private final SimpleStringProperty XTO;
    private final SimpleStringProperty TACHKA;

    public User(String date_op, String oldname, String newname, String xto, String tachka) {
        this.DATE_OP = new SimpleStringProperty(date_op);
        this.OLDNAME = new SimpleStringProperty(oldname);
        this.NEWNAME = new SimpleStringProperty(newname);
        this.XTO = new SimpleStringProperty(xto);
        this.TACHKA = new SimpleStringProperty(tachka);
    }

    public String getDATE_OP() {
        return DATE_OP.get();
    }

    public SimpleStringProperty DATE_OPProperty() {
        return DATE_OP;
    }

    public void setDATE_OP(String DATE_OP) {
        this.DATE_OP.set(DATE_OP);
    }

    public String getOLDNAME() {
        return OLDNAME.get();
    }

    public SimpleStringProperty OLDNAMEProperty() {
        return OLDNAME;
    }

    public void setOLDNAME(String OLDNAME) {
        this.OLDNAME.set(OLDNAME);
    }

    public String getNEWNAME() {
        return NEWNAME.get();
    }

    public SimpleStringProperty NEWNAMEProperty() {
        return NEWNAME;
    }

    public void setNEWNAME(String NEWNAME) {
        this.NEWNAME.set(NEWNAME);
    }

    public String getXTO() {
        return XTO.get();
    }

    public SimpleStringProperty XTOProperty() {
        return XTO;
    }

    public void setXTO(String XTO) {
        this.XTO.set(XTO);
    }

    public String getTACHKA() {
        return TACHKA.get();
    }

    public SimpleStringProperty TACHKAProperty() {
        return TACHKA;
    }

    public void setTACHKA(String TACHKA) {
        this.TACHKA.set(TACHKA);
    }
}
