package daos;

import entity.Document;
import entity.Note;
import org.hibernate.query.Query;

import java.util.List;

public class NoteDao extends BaseDao<Note> {

    public List<Note> findAll()
    {
        Query<Note> noteQuery = this.createNamedQuery("Note.findAll");
        return noteQuery.getResultList();
    }

    @Override
    protected Class<Note> getClassType() {
        return Note.class;
    }
}
