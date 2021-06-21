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
    private Document previous_version;

    @OneToOne @JoinColumn(name = "next_version_id")
    private Document next_version;

    public Document() {}
    public Document(final Integer id,
                    final String filename,
                    final byte[] data,
                    final Document previous_version)  {
        this.id = id;
        this.filename = filename;
        this.data = data;
        this.previous_version = previous_version;
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
        return previous_version;
    }

    public void setPrevious_version(Document previous_version) {
        this.previous_version = previous_version;
    }

    public Document getNext_version() {
        return next_version;
    }

    public void setNext_version(Document next_version) {
        this.next_version = next_version;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", previous_version=" + (previous_version == null ? "NULL" : previous_version.getFilename()) +
                ", next_version='" + (next_version == null ? "NULL" : next_version.getFilename()) + '\'' +
                ", data=" + Integer.toString(data.length) + " bytes" +
                '}';
    }
}
