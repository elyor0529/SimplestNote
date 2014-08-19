package beans;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Elyor on 8/17/2014.
 */
public class NoteBean {
    private BigInteger id;
    private String title;
    private String content;
    private Timestamp createDate;
    private String tags;
    private BigInteger userId;
    private BigInteger versionCount;
    private Timestamp modifiedDate;
    private BigInteger versionId;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public BigInteger getVersionCount() {
        return versionCount;
    }

    public void setVersionCount(BigInteger versionCount) {
        this.versionCount = versionCount;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public BigInteger getVersionId() {
        return versionId;
    }

    public void setVersionId(BigInteger versionId) {
        this.versionId = versionId;
    }

}
