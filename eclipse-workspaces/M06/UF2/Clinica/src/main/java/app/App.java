package app;

import manager.DAOManager;
import pojos.Perfils;
import pojos.Usuaris;
import services.AttendanceService;
import services.ClientsService;
import services.ProfilesService;
import services.ServicesService;
import services.UsersService;

public class App {
    public static void main(String[] args) {
        // usersDAO();
        clientsDAO();
        // attendancesDAO();
        // servicesDAO();
        // profilesDAO();
    }

    private static void usersDAO() {
        UsersService us = DAOManager.getUsersService(DAOManager.JDBC);
        ProfilesService pf = DAOManager.getProfilesService(DAOManager.HIBERNATE);

        Usuaris testUser = new Usuaris("1234", pf.readOne(1), "1234", "Javier", "Marín", "marinj460@gmail.com");
        System.out.println(us.createOne(testUser));

        System.out.println(us.readOne("1234"));
        System.out.println(us.readAll());

        testUser.setCognoms("Marín Guimerà");
        System.out.println(us.updateOne(testUser));

        System.out.println(us.deleteOne(testUser.getIdUsuari()));
    }

    private static void clientsDAO() {
        ClientsService cs = DAOManager.getClientsService(DAOManager.HIBERNATE);
    }

    private static void attendancesDAO() {
        AttendanceService as = DAOManager.getAttendanceService(DAOManager.HIBERNATE);
    }

    private static void servicesDAO() {
        ServicesService ss = DAOManager.getServicesService(DAOManager.HIBERNATE);
    }

    private static void profilesDAO() {
        ProfilesService pf = DAOManager.getProfilesService(DAOManager.HIBERNATE);

        Perfils testProfile = new Perfils(4, "Test");
        System.out.println(pf.createOne(testProfile));

        System.out.println(pf.readOne(4));
        System.out.println(pf.readAll());

        testProfile.setDescripcio("Test Update");
        System.out.println(pf.updateOne(testProfile));

        System.out.println(pf.deleteOne(testProfile.getCodi()));
    }

}
