package services;

import java.util.List;

import pojos.Clients;

public interface ClientsService {

    public List<Clients> readAll();

    public boolean createOne();

    public Clients readOne();

    public boolean updateOne();

    public boolean deleteOne();
}
