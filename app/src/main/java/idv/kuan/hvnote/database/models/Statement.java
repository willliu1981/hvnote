package idv.kuan.hvnote.database.models;

import java.io.Serializable;
import java.sql.Timestamp;

public class Statement implements Serializable {
    private Integer id;
    private String statement;
    private String category;
    private Boolean isFavorite;
    private Boolean isArchived;
    private Timestamp atCreated;
    private Timestamp atUpdated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public void setFavorite(Integer favorite) {
        if (favorite == null) {
            isFavorite = false;
        }

        isFavorite = favorite >= 1 ? true : false;
    }


    public Boolean getArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    public void setArchived(Integer archived) {
        if (archived == null) {
            isArchived = false;
        }

        isArchived = archived >= 1 ? true : false;
    }

    public Timestamp getAtCreated() {
        return atCreated;
    }

    public void setAtCreated(Timestamp atCreated) {
        this.atCreated = atCreated;
    }

    public void setAtCreated(String atCreated) {
        if (atCreated != null) {
            this.atCreated = Timestamp.valueOf(atCreated);

        }

    }

    public Timestamp getAtUpdated() {
        return atUpdated;
    }

    public void setAtUpdated(Timestamp atUpdated) {
        this.atUpdated = atUpdated;
    }

    public void setAtUpdated(String atUpdated) {

        if (atUpdated != null) {
            this.atUpdated = Timestamp.valueOf(atUpdated);

        }

    }
}
