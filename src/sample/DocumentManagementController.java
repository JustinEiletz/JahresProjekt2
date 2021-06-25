package sample;

import daos.DocumentDao;
import entity.Document;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import manager.ApplicationManager;
import manager.ViewManager;
import service.DocumentService;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class DocumentManagementController implements Initializable {
    @FXML
    private TextField filterTF;
    @FXML
    private ListView<Document> documentListView;
    @FXML
    private Label currentFileLabel;
    @FXML
    private ImageView previewImageView;
    @FXML
    private TextArea previewTextArea;
    @FXML
    private CheckBox showArchiveCheckBox;

    private ObservableList<Document> documents;

    private String filterText = null;
    private int selectedItem = -1;
    private Document selectedDocument = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filterTF.textProperty().addListener((observableValue, s, t1) -> {
            if (filterTF.getText().isBlank()) {
                filterText = null;
            } else {
                filterText = filterTF.getText();
            }
            updateDocumentList();
        });
        Stage s = ViewManager.getInstanceVM().getStage();
        s.setTitle("Document Management");
        documents = FXCollections.observableArrayList();
        documentListView.setItems(documents);
        updateDocumentList();
    }

    private void updateDocumentList()
    {
        documents.clear();
        ApplicationManager app = ApplicationManager.getInstance();
        DocumentService docService = new DocumentService(app.getCurrentUser());
        List<Document> docs = docService.GetUserDocuments();

        for (Document doc : docs) {
            if (showArchiveCheckBox.isSelected()) {
                if (doc.getNextVersion() == null) continue;
            } else {
                if (doc.getNextVersion() != null) continue;
            }
            if (filterText != null) {
                if (!doc.getFilename().toLowerCase(Locale.ROOT).contains(filterText.toLowerCase(Locale.ROOT)))
                    continue;
            }
            documents.add(doc);
        }
        selectedItem = -1;
        selectedDocument = null;
        previewImageView.setImage(null);
        previewTextArea.clear();
        newDocumentSelected();
    }

    @FXML
    private void showArchiveCheckBoxClick() {
        System.out.println(showArchiveCheckBox.isSelected() ? "archive selected" : "archive not selected");
        updateDocumentList();
    }

    @FXML
    private void newDocumentSelected() {
        if (selectedItem != documentListView.getSelectionModel().getSelectedIndex()) {
            selectedItem = documentListView.getSelectionModel().getSelectedIndex();
            selectedDocument = documentListView.getItems().get(selectedItem);
            currentFileLabel.setText(selectedDocument.getFilename());
            if (selectedDocument.getFilename().endsWith(".jpg") || selectedDocument.getFilename().endsWith(".png")) {
                previewImageView.toFront();
                ByteArrayInputStream bis = new ByteArrayInputStream(selectedDocument.getData());
                previewImageView.setImage(new Image(bis));
                previewImageView.setPreserveRatio(false);
            } else {
                previewTextArea.toFront();
                previewTextArea.setText(new String(selectedDocument.getData()));
            }
        }
    }

    private Document getDocumentFromFileSystem() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Datei hochladen");
        File newFile = fileChooser.showOpenDialog(null);
        if (newFile != null) {
            Document newDoc = new Document();
            newDoc.setUser(ApplicationManager.getInstance().getCurrentUser());
            newDoc.setFilename(newFile.getName());
            try {
                byte[] data = java.nio.file.Files.readAllBytes(newFile.toPath());
                newDoc.setData(data);
                return newDoc;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @FXML
    private void backButtonClick() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getDashboardScene());
    }

    @FXML
    private void addNewButtonClick() {
        Document newDoc = getDocumentFromFileSystem();
        if (newDoc != null) {
            DocumentDao docDao = new DocumentDao();
            docDao.create(newDoc);
            updateDocumentList();
        }
    }

    @FXML
    private void downloadButtonClick() {
        if (selectedDocument == null) return;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Speichern");
        fileChooser.setInitialFileName(selectedDocument.getFilename());
        File saveFile = fileChooser.showSaveDialog(null);
        if (saveFile != null) {
            try {
                java.nio.file.Files.write(saveFile.toPath(), selectedDocument.getData());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void replaceButtonClick() {
        if (selectedDocument == null) return;
        Document newDoc = getDocumentFromFileSystem();
        if (newDoc != null) {
            DocumentDao docDao = new DocumentDao();
            selectedDocument.setData(newDoc.getData());
            selectedDocument.setFilename(newDoc.getFilename());
            docDao.update(selectedDocument);
            updateDocumentList();
        }
    }

    @FXML
    private void archiveButtonClick() {
        if (selectedDocument == null) return;
        Document newDoc = getDocumentFromFileSystem();
        if (newDoc != null) {
            DocumentDao docDao = new DocumentDao();
            selectedDocument.setNextVersion(newDoc);
            newDoc.setPreviousVersion(selectedDocument);
            docDao.create(newDoc);
            docDao.update(selectedDocument);
            updateDocumentList();
        }
    }

    @FXML
    private void deleteButtonClick() {
        if (selectedDocument == null) return;
        DocumentDao documentDao = new DocumentDao();
        documentDao.delete(selectedDocument);
        updateDocumentList();
    }
}
