package app;

import java.util.Date;

import manager.DAOManager;
import pojos.Assistencies;
import pojos.Clients;
import pojos.Perfils;
import pojos.Serveis;
import pojos.Usuaris;
import services.AttendanceService;
import services.ClientsService;
import services.ProfilesService;
import services.ServicesService;
import services.UsersService;

public class App {
    public static void main(String[] args) {
        usersDAO();
        // clientsDAO();
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

        Clients testClient = new Clients(1, "Javier", "Marín", "683152743", "marinj460@gmail.com");
        System.out.println("Create one client: " + cs.createOne(testClient));

        System.out.println("Read one client: " + cs.readOne(testClient.getIdClient()));
        System.out.println("Read all client: " + cs.readAll());

        testClient.setCognoms("Marín Guimerà");
        System.out.println("Update one client: " + cs.updateOne(testClient));

        System.out.println("Delete one client: " + cs.deleteOne(testClient.getIdClient()));
    }

    private static void attendancesDAO() {
        AttendanceService as = DAOManager.getAttendanceService(DAOManager.HIBERNATE);
        ProfilesService pf = DAOManager.getProfilesService(DAOManager.HIBERNATE);

        Clients testClient = new Clients(1, "Javier", "Marín Guimerà", "683152743", "marinj460@gmail.com");
        Serveis testService = new Serveis(1234, "Test service");
        Usuaris testUser = new Usuaris("1234", pf.readOne(1), "1234", "Javier", "Marín", "marinj460@gmail.com");
        Assistencies testAttendance = new Assistencies(testClient, testService, testUser, new Date());
        System.out.println(as.createOne(testAttendance));

        System.out.println(as.readOne(1));
        System.out.println(as.readAll());

        testClient.setCognoms("Marín Guimerà");
        System.out.println(as.updateOne(testAttendance));

        System.out.println(as.deleteOne(testAttendance.getCodiAssistencia()));
    }

    private static void servicesDAO() {
        ServicesService ss = DAOManager.getServicesService(DAOManager.HIBERNATE);

        Serveis testService = new Serveis(1234, "Test service");
        System.out.println(ss.createOne(testService));

        System.out.println(ss.readOne(1));
        System.out.println(ss.readAll());

        testService.setDescripcio("Updated test service");
        System.out.println(ss.updateOne(testService));

        System.out.println(ss.deleteOne(testService.getCodi()));
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
