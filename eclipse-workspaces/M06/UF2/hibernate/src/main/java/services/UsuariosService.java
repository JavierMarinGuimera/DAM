package services;

import java.util.List;

import pojos.Usuaris;

public interface UsuariosService {

    public List<Usuaris> getAll();

    public Usuaris selectOne(int userId);

    public Boolean createOne(Usuaris usuaris);

    public Boolean updateOne(Usuaris usuaris);

    public Boolean deleteOne(Usuaris usuaris);
}
