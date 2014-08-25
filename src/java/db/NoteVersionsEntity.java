package db;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Elyor on 8/16/2014.
 */
@Entity
@Table(name = "note_versions", schema = "", catalog = "simplestnote")
public class NoteVersionsEntity {
    private long id;
    private long noteId;
    private Timestamp modifiedDate;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "note_id", nullable = false, insertable = true, updatable = true)
    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    @Basic
    @Column(name = "modified_date", nullable = false, insertable = true, updatable = true)
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteVersionsEntity that = (NoteVersionsEntity) o;

        if (id != that.id) return false;
        if (noteId != that.noteId) return false;
        if (modifiedDate != null ? !modifiedDate.equals(that.modifiedDate) : that.modifiedDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (noteId ^ (noteId >>> 32));
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        return result;
    }
}
