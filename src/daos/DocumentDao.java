package daos;

import entity.Document;
import org.hibernate.query.Query;

import java.util.List;

public class DocumentDao extends BaseDao<Document> {

    @Override
    protected Class<Document> getClassType() {
        return Document.class;
    }

    public Document findById(final Integer rentalId) {
        Query<Document> documentQuery = this.createNamedQuery("Document.findById");
        documentQuery.setParameter("Id", rentalId);
        return documentQuery.getSingleResult();
    }

    public List<Document> findAll() {
        Query<Document> documentQuery = this.createNamedQuery("Document.findAll");
        return documentQuery.getResultList();
    }
}
