package services;

import java.util.List;

import pojos.Serveis;

public interface ServicesService {

    public List<Serveis> readAll();

    public boolean createOne();

    public Serveis readOne();

    public boolean updateOne();

    public boolean deleteOne();
}
