package manager;

import impls.AttendanceServiceImplHibernate;
import impls.ClientsServiceImplHibernate;
import impls.ProfilesServiceImplHibernate;
import impls.ServicesServiceImplHibernate;
import impls.users.UsersServiceImplHibernate;
import impls.users.UsersServiceImplJDBC;
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
                    usersService = new UsersServiceImplJDBC();
                    break;

                case HIBERNATE:
                    // This can be created on the future.
                    usersService = new UsersServiceImplHibernate();
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
                    clientsService = new ClientsServiceImplHibernate();
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
                    servicesService = new ServicesServiceImplHibernate();
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
                    attendanceService = new AttendanceServiceImplHibernate();
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
                    profilesService = new ProfilesServiceImplHibernate();
                    break;

                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
        return profilesService;
    }
}
