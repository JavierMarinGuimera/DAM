package services;

import java.util.List;

import pojos.Assistencies;

public interface AttendanceService {

    public List<Assistencies> readAll();

    public boolean createOne();

    public Assistencies readOne();

    public boolean updateOne();

    public boolean deleteOne();
}
