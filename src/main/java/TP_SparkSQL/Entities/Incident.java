package TP_SparkSQL.Entities;

import java.io.Serializable;
import java.util.Date;

public class Incident implements Serializable {
    private Long id;
    private String titre;
    private String description;
    private String service;
    private Date date;

    public Incident() {
    }

    public Incident(Long id, String titre, String description, String service, Date date) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.service = service;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
