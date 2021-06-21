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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Lob()
    @Column(name = "data", columnDefinition = "blob")
    private byte[] data;

    @OneToOne @JoinColumn(name = "previous_version_id")
    private Document previousVersion;

    @OneToOne @JoinColumn(name = "next_version_id")
    private Document nextVersion;

    public Document() {}
    public Document(final Integer id,
                    final String filename,
                    final byte[] data,
                    final Document previousVersion)  {
        this.id = id;
        this.filename = filename;
        this.data = data;
        this.previousVersion = previousVersion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Document getPrevious_version() {
        return previousVersion;
    }

    public void setPrevious_version(Document previousVersion) {
        this.previousVersion = previousVersion;
    }

    public Document getNext_version() {
        return nextVersion;
    }

    public void setNext_version(Document nextVersion) {
        this.nextVersion = nextVersion;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", previous_version=" + (previousVersion == null ? "NULL" : previousVersion.getFilename()) +
                ", next_version='" + (nextVersion == null ? "NULL" : nextVersion.getFilename()) + '\'' +
                ", data=" + Integer.toString(data.length) + " bytes" +
                '}';
    }
}
