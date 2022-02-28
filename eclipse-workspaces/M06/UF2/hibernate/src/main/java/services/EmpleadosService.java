package services;

import java.util.List;

import pojos.Empleats;

public interface EmpleadosService {
    public List<Empleats> getAll();

    public Empleats selectOne(int empNo);

    public Boolean createOne(Empleats empleado);

    public Boolean updateOne(Empleats empleado);

    public Boolean deleteOne(Empleats empleado);
}
