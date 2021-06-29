package daos;

import entity.Chat;
import entity.User;
import org.hibernate.query.Query;

import java.util.List;

public class ChatDao extends BaseDao<Chat> {

    @Override
    protected Class<Chat> getClassType() { return Chat.class; }

    public List<Chat> findAll() {
        Query<Chat> chatQuery = this.createNamedQuery("Chat.findAll");
        return chatQuery.getResultList();
    }

}
