package main;

import java.util.HashSet;
import java.util.Set;

import manager.DAOManager;
import pojos.Departaments;
import pojos.Empleats;
import pojos.Usuaris;
import services.DepartamentosService;
import services.EmpleadosService;
import services.UsuariosService;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main(String[] args) {

        // Execution of usersCRUD:
        // usersCRUD();

        // Execution of departamentosCRUD:
        departamentosCRUD();

        // Execution of empleadosCRUD:
        empleadosCRUD();
    }

    private static void usersCRUD() {
        UsuariosService us = DAOManager.getUsersService(DAOManager.HIBERNATE);

        System.out.println("Select all: ");
        Usuaris.printUsersFromList(us.getAll());

        System.out.println("CRUD operations:");
        System.out.println("Create one: (Crud)");
        Usuaris.isCreated(us.createOne(new Usuaris(1234, "1234", "javier", "marin", "marinj460@gmail.com")));

        System.out.println("Select one: (cRud)");
        Usuaris.printUser(us.selectOne(1246));

        System.out.println("Update one: (crUd)");
        Usuaris.isUpdated(us.updateOne(new Usuaris(1234, "1234", "Javier", "Marin", "marinj460@gmail.com")));

        System.out.println("Delete one: (cruD)");
        Usuaris.isDeleted(us.deleteOne(new Usuaris(1234, "1234", "javier", "marin", "marinj460@gmail.com")));
    }

    private static void departamentosCRUD() {
        DepartamentosService ds = DAOManager.getDepartamentosService(DAOManager.HIBERNATE);

        // 2. Listar todos los Departamentos y sus Empleados:
        // System.out.println("Select all: ");
        // Departaments.printDepartamentosFromList(ds.getAll());

        // 1. Nuevo Departamento con y sin Empleados:
        // System.out.println("CRUD operations:");
        // System.out.println("Create one: (Crud)");
        // Departaments.isCreated(ds.createOne(new Departaments((byte) 50, "PRUEBAS",
        // "SANT BOI")));
        // Set<Empleats> empleados = new HashSet<>() {
        // {
        // add(new Empleats((short) 43, ds.selectOne((byte) 50), "Pruebas", "Pruebas"));
        // }
        // };
        // Departaments.isCreated(ds.createOne(new Departaments((byte) 60, "PRUEBAS2",
        // "SANT BOI", empleados)));

        // 3. Consultar un Departamento y sus Empleados.
        // System.out.println("Select one: (cRud)");
        // Departaments.printDepartamento(ds.selectOne((byte) 30));

        // System.out.println("Update one: (crUd)");
        // Departaments.isUpdated(ds.updateOne(new Usuaris(1234, "1234", "Javier",
        // "Marin",
        // "marinj460@gmail.com")));

        // 7: Eliminar un departamento y todos sus Empleados en cascada si tiene.
        // System.out.println("Delete one: (cruD)");
        // Departaments.isDeleted(ds.deleteOne(new Usuaris(1234, "1234", "javier",
        // "marin",
        // "marinj460@gmail.com")));
    }

    // TODO - Empleados CRUD
    private static void empleadosCRUD() {
        EmpleadosService es = DAOManager.getEmpleadosService(DAOManager.HIBERNATE);

        // System.out.println("Select all: ");
        // Empleats.printEmpleadosFromList(es.getAll());

        // System.out.println("CRUD operations:");
        // System.out.println("Create one: (Crud)");
        // Usuaris.isCreated(es.createOne(new Usuaris(1234, "1234", "javier", "marin",
        // "marinj460@gmail.com")));

        // System.out.println("Select one: (cRud)");
        // Usuaris.printUser(es.selectOne(1246));

        // System.out.println("Update one: (crUd)");
        // Usuaris.isUpdated(es.updateOne(new Usuaris(1234, "1234", "Javier", "Marin",
        // "marinj460@gmail.com")));

        // 6. Eliminar un Empleado.
        // System.out.println("Delete one: (cruD)");
        // Empleats.isDeleted(es.deleteOne(7369));

        // 4. Añadir un Empleado a un Departamento.
        // 5. Cambiar un Empleado de Departamento.
        System.out.println("Insert Empleado en un Depatamento: ");
        Empleats.isInDepartamento(es.changeDepartamento(7369, 30));
    }
}
