package services;

import java.util.List;

import pojos.Departaments;

public interface DepartamentosService {
    public List<Departaments> getAll();

    public Departaments selectOne(int deptNo);

    public Boolean createOne(Departaments departamento);

    public Boolean updateOne(int i);

    public Boolean deleteOne(int i);
}
