package entity;

import javax.persistence.*;

@Entity
@Table(name = "DOCUMENT")
@NamedQueries({
        @NamedQuery(
                name = "Document.findById",
                query = "SELECT D from Document D WHERE D.id = :Id"
                ),
        @NamedQuery(
                name = "Document.findAll",
                query = "SELECT D FROM Document D"
        ),
})
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Lob()
    @Column(name = "data", columnDefinition = "MEDIUMBLOB")
    private byte[] data;

    @OneToOne @JoinColumn(name = "previousVersionId")
    private Document previousVersion;

    @OneToOne @JoinColumn(name = "nextVersionId")
    private Document nextVersion;

    @OneToOne @JoinColumn(name = "user")
    private User user;

    public Document() {}
    public Document(final Integer id, final String filename, final byte[] data, final Document previousVersion, final User user)  {
        this.id = id;
        this.filename = filename;
        this.data = data;
        this.previousVersion = previousVersion;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }
    public void setId(final Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }
    public void setFilename(final String filename) {
        this.filename = filename;
    }

    public byte[] getData() {
        return data;
    }
    public void setData(final byte[] data) {
        this.data = data;
    }

    public Document getPreviousVersion() {
        return previousVersion;
    }
    public void setPreviousVersion(final Document previousVersion) {
        this.previousVersion = previousVersion;
    }

    public Document getNextVersion() {
        return nextVersion;
    }

    public void setNextVersion(final Document nextVersion) {
        this.nextVersion = nextVersion;
    }

    @Override
    public String toString() {
        return filename;
        // return "Document{" +
        //         "id=" + id +
        //         ", filename='" + filename + '\'' +
        //         ", previous_version=" + (previousVersion == null ? "NULL" : previousVersion.getFilename()) +
        //         ", next_version='" + (nextVersion == null ? "NULL" : nextVersion.getFilename()) + '\'' +
        //         ", data=" + data.length + " bytes" +
        //         '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
