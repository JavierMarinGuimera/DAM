package services;

import java.util.List;

import pojos.Departaments;

public interface DepartamentosService {
    public List<Departaments> getAll();

    public Departaments selectOne(byte deptNo);

    public Boolean createOne(Departaments departamento);

    public Boolean updateOne(Departaments departamento);

    public Boolean deleteOne(Departaments departamento);
}
