package services;

import java.util.List;

import pojos.Clients;

public interface ClientsService {

    public List<Clients> readAll();

    public boolean createOne(Clients client);

    public Clients readOne(int id);

    public boolean updateOne(Clients client);

    public boolean deleteOne(int id);
}
