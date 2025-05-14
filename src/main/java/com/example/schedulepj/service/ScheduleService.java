package service;

import model.Schedule;
import repository.ScheduleRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ScheduleService {
    private final ScheduleRepository repository;

    public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }

    public Schedule createSchedule(String task, String author, String password) {
        Schedule schedule = new Schedule(task, author, password);
        repository.save(schedule);
        return schedule;
    }

    public List<Schedule> getAllSchedules(LocalDate date, String author) {
        return repository.filterByDateAndAuthor(date, author);
    }

    public Optional<Schedule> getSchedule(long id) {
        return repository.findById(id);
    }

    public boolean updateSchedule(long id, String task, String author, String password) {
        Optional<Schedule> opt = repository.findById(id);
        if (opt.isPresent() && opt.get().getPassword().equals(password)) {
            Schedule s = opt.get();
            s.setTask(task);
            s.setAuthor(author);
            s.setModifiedAt(LocalDateTime.now());
            return true;
        }
        return false;
    }

    public boolean deleteSchedule(long id, String password) {
        Optional<Schedule> opt = repository.findById(id);
        if (opt.isPresent() && opt.get().getPassword().equals(password)) {
            repository.delete(id);
            return true;
        }
        return false;
    }
}
