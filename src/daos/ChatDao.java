package daos;

import entity.Chat;

public class ChatDao extends BaseDao<Chat> {

    @Override
    protected Class<Chat> getClassType() { return Chat.class; }

}
