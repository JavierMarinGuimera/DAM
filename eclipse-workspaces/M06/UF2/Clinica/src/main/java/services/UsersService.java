package services;

import java.util.List;

import pojos.Usuaris;

public interface UsersService {

    public List<Usuaris> readAll();

    public boolean createOne(Usuaris user);

    public Usuaris readOne(String user_id);

    public boolean updateOne(Usuaris user);

    public boolean deleteOne(String user_id);
}
