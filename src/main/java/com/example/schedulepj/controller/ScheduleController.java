import model.Schedule;
import service.ScheduleService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ScheduleController {
    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    public Schedule addSchedule(String task, String author, String password) {
        return service.createSchedule(task, author, password);
    }

    public List<Schedule> viewSchedules(String dateStr, String author) {
        LocalDate date = (dateStr == null || dateStr.isBlank()) ? null : LocalDate.parse(dateStr);
        return service.getAllSchedules(date, author);
    }

    public Optional<Schedule> viewSchedule(long id) {
        return service.getSchedule(id);
    }

    public boolean editSchedule(long id, String task, String author, String password) {
        return service.updateSchedule(id, task, author, password);
    }

    public boolean removeSchedule(long id, String password) {
        return service.deleteSchedule(id, password);
    }
}