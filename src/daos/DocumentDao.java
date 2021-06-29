package daos;

import entity.Document;
import org.hibernate.query.Query;

import java.util.List;

public class DocumentDao extends BaseDao<Document> {

    @Override
    protected Class<Document> getClassType() {
        return Document.class;
    }

    public List<Document> findById(final Integer documentId) {
        Query<Document> documentQuery = this.createNamedQuery("Document.findById");
        documentQuery.setParameter("Id", documentId);
        return documentQuery.getResultList();
    }

    public List<Document> findByUserId(final Integer userId) {
        Query<Document> documentQuery = this.createNamedQuery("Document.findByUserId");
        documentQuery.setParameter("Id", userId);
        return documentQuery.getResultList();
    }

    public List<Document> findAll() {
        Query<Document> documentQuery = this.createNamedQuery("Document.findAll");
        return documentQuery.getResultList();
    }
}
