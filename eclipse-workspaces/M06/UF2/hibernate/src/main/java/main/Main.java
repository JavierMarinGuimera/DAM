package main;

import java.util.ArrayList;
import java.util.List;

import manager.DAOManager;
import pojos.Departaments;
import pojos.Usuaris;
import services.DepartamentosService;
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
        // usersCRUD();
    }

    public static void usersCRUD() {
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

        System.out.println("Select all: ");
        Departaments.printDepartamentosFromList(ds.getAll());

        // System.out.println("CRUD operations:");
        // System.out.println("Create one: (Crud)");
        // Usuaris.isCreated(ds.createOne(new Usuaris(1234, "1234", "javier", "marin",
        // "marinj460@gmail.com")));

        // System.out.println("Select one: (cRud)");
        // Usuaris.printUser(ds.selectOne(1246));

        // System.out.println("Update one: (crUd)");
        // Usuaris.isUpdated(ds.updateOne(new Usuaris(1234, "1234", "Javier", "Marin",
        // "marinj460@gmail.com")));

        // System.out.println("Delete one: (cruD)");
        // Usuaris.isDeleted(ds.deleteOne(new Usuaris(1234, "1234", "javier", "marin",
        // "marinj460@gmail.com")));
    }
}
