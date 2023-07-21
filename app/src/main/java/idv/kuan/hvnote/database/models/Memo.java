package idv.kuan.hvnote.database.models;

import java.sql.Timestamp;

import idv.kuan.libs.databases.models.CommonEntity;

public class Memo implements CommonEntity {
    private Integer id;
    private String title;
    private String category;
    private String content;
    private Boolean isCompleted;
    private Boolean isImportant;
    private Boolean isArchived;
    private Timestamp atCreated;
    private Timestamp atUpdated;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public void setCompleted(Integer completed) {
        isCompleted = completed == 0 ? false : true;
    }

    public Boolean getImportant() {
        return isImportant;
    }

    public void setImportant(Boolean important) {
        isImportant = important;
    }

    public void setImportant(Integer important) {
        isImportant = important == 0 ? false : true;
    }


    public Boolean getArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    public void setArchived(Integer archived) {
        isArchived = archived == 0 ? false : true;
    }

    public Timestamp getAtCreated() {
        return atCreated;
    }

    public void setAtCreated(Timestamp atCreated) {
        this.atCreated = atCreated;
    }

    public void setAtCreated(String atCreated) {
        this.atCreated = Timestamp.valueOf(atCreated);
    }

    public Timestamp getAtUpdated() {
        return atUpdated;
    }

    public void setAtUpdated(Timestamp atUpdated) {
        this.atUpdated = atUpdated;
    }

    public void setAtUpdated(String atUpdated) {
        this.atUpdated = Timestamp.valueOf(atUpdated);
    }

    @Override
    public String toString() {
        return "Memo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", isCompleted=" + isCompleted +
                ", isImportant=" + isImportant +
                ", isArchived=" + isArchived +
                ", atCreated=" + atCreated +
                ", atUpdated=" + atUpdated +
                '}';
    }
}
