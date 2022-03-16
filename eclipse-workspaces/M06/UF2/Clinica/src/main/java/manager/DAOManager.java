package manager;

import impls.AttendanceServiceImpl;
import impls.ClientsServiceImpl;
import impls.ProfilesServiceImpl;
import impls.ServicesServiceImpl;
import impls.UsersServiceImpl;
import services.AttendanceService;
import services.ClientsService;
import services.ProfilesService;
import services.ServicesService;
import services.UsersService;

public class DAOManager {
    public static final int JDBC = 1;
    public static final int HIBERNATE = 2;

    private static UsersService usersService;
    private static ClientsService clientsService;
    private static ServicesService servicesService;
    private static AttendanceService attendanceService;
    private static ProfilesService profilesService;

    /**
     * 
     * @param option
     * @return
     */
    public static UsersService getUsersService(int option) {
        if (usersService == null) {
            switch (option) {
                case JDBC:
                    usersService = new UsersServiceImpl();
                    break;

                case HIBERNATE:
                    // This can be created on the future.
                    usersService = null;
                    break;

                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
        return usersService;
    }

    public static ClientsService getClientsService(int option) {
        if (clientsService == null) {
            switch (option) {
                case JDBC:
                    // This can be created on the future.
                    clientsService = null;
                    break;

                case HIBERNATE:
                    clientsService = new ClientsServiceImpl();
                    break;

                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
        return clientsService;
    }

    public static ServicesService getServicesService(int option) {
        if (servicesService == null) {
            switch (option) {
                case JDBC:
                    // This can be created on the future.
                    servicesService = null;
                    break;

                case HIBERNATE:
                    servicesService = new ServicesServiceImpl();
                    break;

                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
        return servicesService;
    }

    public static AttendanceService getAttendanceService(int option) {
        if (attendanceService == null) {
            switch (option) {
                case JDBC:
                    // This can be created on the future.
                    attendanceService = null;
                    break;

                case HIBERNATE:
                    attendanceService = new AttendanceServiceImpl();
                    break;

                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
        return attendanceService;
    }

    public static ProfilesService getProfilesService(int option) {
        if (profilesService == null) {
            switch (option) {
                case JDBC:
                    // This can be created on the future.
                    profilesService = null;
                    break;

                case HIBERNATE:
                    profilesService = new ProfilesServiceImpl();
                    break;

                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
        return profilesService;
    }
}
