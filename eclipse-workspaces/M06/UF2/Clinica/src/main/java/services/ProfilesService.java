package services;

import java.util.List;

import pojos.Perfils;

public interface ProfilesService {

    public List<Perfils> readAll();

    public boolean createOne(Perfils perfil);

    public Perfils readOne(int id);

    public boolean updateOne(Perfils perfil);

    public boolean deleteOne(int id);
}
