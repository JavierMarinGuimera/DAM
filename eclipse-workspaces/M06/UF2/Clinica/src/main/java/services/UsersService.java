package services;

import java.util.List;

import pojos.Usuaris;

public interface UsersService {

    public List<Usuaris> readAll();

    public boolean createOne();

    public Usuaris readOne();

    public boolean updateOne();

    public boolean deleteOne();
}
