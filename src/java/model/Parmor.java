package model;

import java.io.Serializable;

/**
 *
 * @author Jon VanZile - modified from John Phillips' code
 * @version 0.1 on 2015-12-01
 */
public class Parmor implements Serializable {

    private int id;
    private String modelName;
    private String slot;
    private String location;
    private String paint;
    private int dResist;
    private int eResist;
    private int rResist;

    public Parmor(int id, String modelName, String slot, String paint, int dResist, int eResist, int rResist, String location) {
        this.id = id;
        this.modelName = modelName;
        this.slot = slot;
        this.paint = paint;
        this.dResist = dResist;
        this.eResist = eResist;
        this.rResist = rResist;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int geteResist() {
        return eResist;
    }

    public void seteResist(int eResist) {
        this.eResist = eResist;
    }

    public int getrResist() {
        return rResist;
    }

    public void setrResist(int rResist) {
        this.rResist = rResist;
    }
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getdResist() {
        return dResist;
    }

    public void setdResist(int dResist) {
        this.dResist = dResist;
    }

    public String getSlot() {
        return slot;
    }
    public void setSlot(String slot) {
        this.slot = slot;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getLocation() {
        return location;
    }

    public void setPaint(String paint) {
        this.paint = paint;
    }
    
    public String getPaint() {
        return paint;
    }


    public String inHTMLRowFormat() {
        return "<tr><td>" + id + "</td>"
                + "<td>" + modelName + "</td>"
                + "<td>" + slot + "</td>"
                + "<td>" + paint + "</td>"
                + "<td>" + dResist + "</td>"
                + "<td>" + eResist + "</td>"
                + "<td>" + rResist + "</td>"
                + "<td>" + location + "</td></tr>\n";
    }

    @Override
    public String toString() {
        return "Parmor{" + "id=" + id + ", modelName=" + modelName + ", slot=" + slot + ", paint=" + paint + ", dResist=" + dResist + ", eResist=" + eResist + ", rResist=" + rResist + ", location=" + location +'}';
    }
}
