package idv.kuan.hvnote.database.models;

import java.sql.Timestamp;

public class Memo {
    private Integer id;
    private String title;
    private  String category;
    private String content;
    private Boolean isCompleted;
    private Boolean isImportant;
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

    public Boolean getImportant() {
        return isImportant;
    }

    public void setImportant(Boolean important) {
        isImportant = important;
    }

    public Timestamp getAtCreated() {
        return atCreated;
    }

    public void setAtCreated(Timestamp atCreated) {
        this.atCreated = atCreated;
    }

    public Timestamp getAtUpdated() {
        return atUpdated;
    }

    public void setAtUpdated(Timestamp atUpdated) {
        this.atUpdated = atUpdated;
    }

    @Override
    public String toString() {
        return "Memo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", category='" + category + '\'' +
                ", isCompleted=" + isCompleted +
                ", isImportant=" + isImportant +
                ", atCreated=" + atCreated +
                ", atUpdated=" + atUpdated +
                '}';
    }
}
