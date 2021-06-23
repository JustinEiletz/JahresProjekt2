package service;

import daos.DocumentDao;
import entity.Document;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class DocumentService {
    private User user;
    private DocumentDao dao;

    public DocumentService(User user)
    {
        this.user = user;
        this.dao = new DocumentDao();
    }

    public List<Document> GetUserDocuments()
    {
        // this could be replaced with a query
        List<Document> docs = dao.findAll()
                .stream()
                .filter(d -> d.getUser().getId().equals(user.getId()))
               .toList();
        return docs;
    }


}
