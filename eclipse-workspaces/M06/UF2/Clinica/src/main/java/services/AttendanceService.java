package services;

import java.util.List;

import pojos.Assistencies;

public interface AttendanceService {

    public List<Assistencies> readAll();

    public boolean createOne(Assistencies attendance);

    public Assistencies readOne(int id);

    public boolean updateOne(Assistencies attendance);

    public boolean deleteOne(int id);
}
