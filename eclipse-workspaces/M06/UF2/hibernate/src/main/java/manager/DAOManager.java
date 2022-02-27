package manager;

import impls.DepartamentosServiceImplHibernate;
import impls.EmpeladosServiceImplHibernate;
import impls.UsuariosServiceImplHibernate;
import services.DepartamentosService;
import services.EmpleadosService;
import services.UsuariosService;

public class DAOManager {
    public static final int JDBC = 1;
    public static final int HIBERNATE = 2;

    private static UsuariosService userService;
    private static DepartamentosService departamentosService;
    private static EmpleadosService empleadosService;

    public static UsuariosService getUsersService(int option) {
        if (userService == null) {
            switch (option) {
                case JDBC:
                    // This can be created on the future.
                    userService = null;
                    break;

                case HIBERNATE:
                    userService = new UsuariosServiceImplHibernate();
                    break;

                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
        return userService;
    }

    public static DepartamentosService getDepartamentosService(int option) {
        if (departamentosService == null) {
            switch (option) {
                case JDBC:
                    // This can be created on the future.
                    departamentosService = null;
                    break;

                case HIBERNATE:
                    departamentosService = new DepartamentosServiceImplHibernate();
                    break;

                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
        return departamentosService;
    }

    public static EmpleadosService getEmpleadosService(int option) {
        if (empleadosService == null) {
            switch (option) {
                case JDBC:
                    // This can be created on the future.
                    empleadosService = null;
                    break;

                case HIBERNATE:
                    empleadosService = new EmpeladosServiceImplHibernate();
                    break;

                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
        return empleadosService;
    }
}
