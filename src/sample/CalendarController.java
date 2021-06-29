package sample;

import daos.NoteDao;
import entity.Note;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import manager.ApplicationManager;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;

public class CalendarController extends BaseController<CalendarController> implements Initializable {

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField titleTF;

    @FXML
    private TextArea noteContentTA;

    @FXML
    private Text previewTitleLabel;

    @FXML
    private TextArea previewNoteContentTA;

    @FXML
    private GridPane previewGP;

    private Note previewNote = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        previewGP.setVisible(false);
        datePicker.setValue(LocalDate.now());
        datePicker.valueProperty().addListener(v-> {
            datePickerValueChanged();
        });
        datePickerValueChanged();
    }

    private void datePickerValueChanged() {
        NoteDao noteDao = new NoteDao();

        Optional<Note> note = noteDao.findAll()
                .stream()
                .filter(n -> {
                    if(!n.getUser().getId().equals(ApplicationManager.getInstance().getCurrentUser().getId()))
                        return false;
                    LocalDate date = n.getDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    return date.equals(datePicker.getValue());
                })
                .findFirst();
        previewNote = note.orElse(null);
        if (previewNote != null) {
            previewTitleLabel.setText(previewNote.getTitle());
            previewNoteContentTA.setText(previewNote.getContent());
            previewGP.setVisible(true);
        } else {
            previewGP.setVisible(false);
        }
    }

    @FXML
    private void updateButtonClick() {
        if (previewNote == null) return;
        NoteDao noteDao = new NoteDao();
        previewNote.setContent(previewNoteContentTA.getText());
        noteDao.update(previewNote);
        datePickerValueChanged();
    }

    @FXML
    private void deleteButtonClick() {
        if (previewNote == null) return;
        NoteDao noteDao = new NoteDao();
        noteDao.delete(previewNote);
        datePickerValueChanged();
    }

    @FXML
    private void applyButtonClick() {
        if (previewNote != null) return;
        NoteDao noteDao = new NoteDao();
        Note newNote = new Note();
        newNote.setUser(ApplicationManager.getInstance().getCurrentUser());
        newNote.setDate(java.sql.Timestamp.valueOf(datePicker.getValue().atTime(0,0)));
        newNote.setTitle(titleTF.getText());
        newNote.setContent(noteContentTA.getText());
        noteDao.create(newNote);
        datePickerValueChanged();
    }

    @Override
    protected Class<CalendarController> getClassType() { return CalendarController.class; }
}
