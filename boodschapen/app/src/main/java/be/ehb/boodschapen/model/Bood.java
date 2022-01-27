package be.ehb.boodschapen.model;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.threeten.bp.LocalDate;

import java.io.Serializable;

@Entity
public class Bood implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title, description, content;
    private LocalDate makeBoodschappenDate;
    private LocalDate creatingDate;

    public Bood(){
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Ignore
    public Bood(String title, String description, String content, LocalDate makeBoodschappenDate) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.makeBoodschappenDate = makeBoodschappenDate;
        this.creatingDate = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getMakeBoodschappenDate() {
        return makeBoodschappenDate;
    }

    public void setMakeBoodschappenDate(LocalDate makeBoodschappenDate) {
        this.makeBoodschappenDate = makeBoodschappenDate;
    }

    public LocalDate getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(LocalDate creatingDate) {
        this.creatingDate = creatingDate;
    }
}
