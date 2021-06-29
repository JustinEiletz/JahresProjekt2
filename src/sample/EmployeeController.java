package sample;

import daos.WorkingPeriodDao;
import entity.WorkingPeriod;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import manager.ApplicationManager;
import manager.ViewManager;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private GridPane calendarGP;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button prevCalendarBtn;

    @FXML
    private Button nextCalendarBtn;

    @FXML
    private TextField startDayTF;

    @FXML
    private TextField endDayTF;

    private LocalDateTime currentDate = LocalDateTime.now();
    private LocalDateTime currentCallendarStartDate = LocalDateTime.now();

    private LocalDateTime startTime = null;
    private LocalDateTime endTime = null;

    private class CalendarDay
    {
        public Pane dayPane;
        public Label dayLabel;
        public Text hoursLabel;
        public Ellipse circle;

        public void setVisible(boolean visible) {
            circle.setVisible(visible);
            hoursLabel.setVisible(visible);
        }

        public void setColor(Paint color) {
            circle.setFill(color);
            circle.setStroke(color);
        }
    };

    private CalendarDay[] calendarDays = new CalendarDay[4*5];

    private static String[] DAY_STRINGS = {
            "MONDAY",
            "TUEDSAY",
            "WEDNESDAY",
            "THURSDAY",
            "FRIDAY"
    };

    private Pane getNewCalendarDay()
    {
        try {
            URL path = getClass().getResource("/fxmlfiles/calenderDay.fxml");
            Pane pane = FXMLLoader.load(path);
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void updateCalendar()
    {
        LocalDateTime endDate = currentCallendarStartDate.plusDays(27);
        prevCalendarBtn.setText("PREV: " + currentCallendarStartDate.toLocalDate().toString());
        nextCalendarBtn.setText("NEXT: " + endDate.toLocalDate().toString());
        WorkingPeriodDao workDao = new WorkingPeriodDao();
        List<WorkingPeriod> workingPeriods = workDao.findAll().stream()
                .filter(w -> {
                    if(w.getUser().getId() != ApplicationManager.getInstance().getCurrentUser().getId())
                        return false;
                    LocalDate date = w.getStartedWorking().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    return date.getDayOfYear() >= currentCallendarStartDate.getDayOfYear()
                            && date.getDayOfYear() <= endDate.getDayOfYear();
                })
                .toList();

        {
            int dayCounter = 0;
            LocalDateTime date = currentCallendarStartDate;
            for(CalendarDay day : calendarDays) {
                day.dayLabel.setText(DAY_STRINGS[dayCounter % 5] + "\n" + date.getDayOfMonth());
                day.setVisible(false);
                dayCounter++;
                // skip weekend
                date = dayCounter % 5 == 0 ? date.plusDays(3) : date.plusDays(1);
            }
        }

        for(WorkingPeriod work : workingPeriods) {
            LocalDate date = work.getStartedWorking().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            int day = date.getDayOfYear() - currentCallendarStartDate.getDayOfYear();
            day -= (day / 7) * 2;
            if(day >= 0 && day < calendarDays.length) {
                calendarDays[day].setVisible(true);
                calendarDays[day].setColor(Color.BLACK);
                float hours = Duration.between(work.getStartedWorking().toInstant(), work.getStoppedWorking().toInstant()).getSeconds()/60.f;
                hours /= 60.f;
                calendarDays[day].hoursLabel.setText(((int)hours) + "H");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentCallendarStartDate = currentCallendarStartDate.minusDays(currentCallendarStartDate.getDayOfWeek().ordinal() % 7);
        datePicker.setValue(LocalDate.now());
        datePicker.valueProperty().addListener(v ->
        {
            startTime = null;
            endTime = null;
            currentDate = datePicker.getValue().atTime(0, 0);
            currentCallendarStartDate = datePicker.getValue().atTime(0, 0);
            currentCallendarStartDate = currentCallendarStartDate.minusDays(currentCallendarStartDate.getDayOfWeek().ordinal() % 7);
            updateCalendar();
        });

        int day = 0;
        for(int y = 0; y < calendarGP.getRowCount(); ++y) {
            for(int x = 0; x < calendarGP.getColumnCount(); ++x) {
                Pane calenderDayPane = getNewCalendarDay();
                CalendarDay calenderDay = new CalendarDay();
                calenderDay.dayPane = calenderDayPane;

                for(Node child : calenderDay.dayPane.getChildren()) {
                    if(child.getId() == null) continue;
                    if(child.getId().equals("hoursLabel")) {
                        calenderDay.hoursLabel = (Text)child;
                    }
                    else if(child.getId().equals("dayLabel")) {
                        calenderDay.dayLabel = (Label)child;
                    }
                    else if(child.getId().equals("circle")) {
                        calenderDay.circle = (Ellipse) child;
                    }
                }

                calendarDays[day] = calenderDay;
                calendarGP.add(calenderDay.dayPane, x, y);
                calenderDay.dayLabel.setText(DAY_STRINGS[day % 5]);
                calenderDay.hoursLabel.setText("9H");
                calenderDay.setVisible(false);
                day++;
            }
        }
        updateCalendar();
    }

    @FXML
    private void sevenAMButtonClick()
    {
        startTime = currentDate.toLocalDate().atTime(7, 0);
        startDayTF.setText(startTime.toString());
    }

    @FXML
    private void eightAMButtonClick()
    {
        startTime = currentDate.toLocalDate().atTime(8, 0);
        startDayTF.setText(startTime.toString());
    }

    @FXML
    private void nineAMButtonClick()
    {
        startTime = currentDate.toLocalDate().atTime(9, 0);
        startDayTF.setText(startTime.toString());
    }

    @FXML
    private void fourPMButtonClick()
    {
        endTime = currentDate.toLocalDate().atTime(16, 0);
        endDayTF.setText(endTime.toString());
    }

    @FXML
    private void fivePMButtonClick()
    {
        endTime = currentDate.toLocalDate().atTime(17, 0);
        endDayTF.setText(endTime.toString());
    }

    @FXML
    private void sixPMButtonClick()
    {
        endTime = currentDate.toLocalDate().atTime(18, 0);
        endDayTF.setText(endTime.toString());
    }

    @FXML
    private void backButtonClick() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getDashboardScene());
    }

    @FXML
    private void prevCalendarButtonClick() {
        currentCallendarStartDate = currentCallendarStartDate.minusDays(28);
        updateCalendar();
    }

    @FXML
    private void nextCalendarButtonClick() {
        currentCallendarStartDate = currentCallendarStartDate.plusDays(28);
        updateCalendar();
    }

    @FXML
    private void applyButtonClick() {
        if(startTime == null || endTime == null) return;
        WorkingPeriodDao workDao = new WorkingPeriodDao();
        WorkingPeriod work = new WorkingPeriod();
        work.setUser(ApplicationManager.getInstance().getCurrentUser());
        work.setStartedWorking(java.sql.Timestamp.valueOf(startTime));
        work.setStoppedWorking(java.sql.Timestamp.valueOf(endTime));
        workDao.create(work);

        startDayTF.clear();
        startTime = null;
        endDayTF.clear();
        endTime = null;

        updateCalendar();
    }
}
