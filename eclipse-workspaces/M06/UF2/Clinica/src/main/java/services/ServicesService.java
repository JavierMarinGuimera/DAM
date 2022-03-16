package services;

import java.util.List;

import pojos.Serveis;

public interface ServicesService {

    public List<Serveis> readAll();

    public boolean createOne(Serveis service);

    public Serveis readOne(int id);

    public boolean updateOne(Serveis service);

    public boolean deleteOne(int id);
}
