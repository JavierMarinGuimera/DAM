package service;

import java.util.List;

import pojos.Autors;

public interface AuthorsService {
    public List<Autors> readAll();

    public Autors readOne(int id);

    public boolean createOne(Autors author);
}
