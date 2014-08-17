package db;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Elyor on 8/16/2014.
 */
@Entity
@Table(name = "note_permissions", schema = "", catalog = "simplestnote")
public class NotePermissionsEntity {
    private long id;
    private long noteVersionId;
    private long ownerId;
    private Timestamp sharedDate;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "note_version_id", nullable = false, insertable = true, updatable = true)
    public long getNoteVersionId() {
        return noteVersionId;
    }

    public void setNoteVersionId(long noteVersionId) {
        this.noteVersionId = noteVersionId;
    }

    @Basic
    @Column(name = "owner_id", nullable = false, insertable = true, updatable = true)
    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    @Basic
    @Column(name = "shared_date", nullable = false, insertable = true, updatable = true)
    public Timestamp getSharedDate() {
        return sharedDate;
    }

    public void setSharedDate(Timestamp sharedDate) {
        this.sharedDate = sharedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotePermissionsEntity that = (NotePermissionsEntity) o;

        if (id != that.id) return false;
        if (noteVersionId != that.noteVersionId) return false;
        if (ownerId != that.ownerId) return false;
        if (sharedDate != null ? !sharedDate.equals(that.sharedDate) : that.sharedDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (noteVersionId ^ (noteVersionId >>> 32));
        result = 31 * result + (int) (ownerId ^ (ownerId >>> 32));
        result = 31 * result + (sharedDate != null ? sharedDate.hashCode() : 0);
        return result;
    }
}
