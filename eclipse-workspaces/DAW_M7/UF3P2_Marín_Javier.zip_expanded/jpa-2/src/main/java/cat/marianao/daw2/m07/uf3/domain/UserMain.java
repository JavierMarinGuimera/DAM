package cat.marianao.daw2.m07.uf3.domain;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import cat.marianao.daw2.m07.uf3.service.UserService;

public class UserMain {

	static UserService userService;

	public static void main(String[] args) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		int opcion = -1;

		System.out.println("¡Bienvenido!");
		while (opcion != 0) {
			System.out
					.println(
							System.lineSeparator()
									+ "---------------------------------------------------------------------------------------");
			System.out.println("NAME USERNAME EMAIL RANK:");
			System.out
					.println(
							".......................................................................................");

			System.out.println("Todos los usuarios: " + userService.getAllUsers());
			List<User> usuarios = userService.findActiveUsers();

			for (User user : usuarios) {
				System.out.println(
						user.getName() + " - " + user.getUsername() + " - " + user.getEmail() + " - "
								+ user.getRank());

			}

			System.out
					.println(
							"......................................................................................."
									+ System.lineSeparator());

			System.out.println("Selecciona una opción: ");
			System.out.println("0: Salir del programa.");
			System.out.println("1: Listar usuarios activos.");
			System.out.println("2: Consultar un usuario.");
			System.out.println("3: Alta usuario.");
			System.out.println("4: Dar de baja usuario." + System.lineSeparator());

			System.out.println("Opción: ");
			try {
				opcion = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR. Debes introducir un número entero.");
				scanner.nextLine();
				opcion = -1;
				continue;
			}

			switch (opcion) {
			case 0:
				System.out.println("¡Hasta luego!");
				break;

			case 1:
				System.out.println("Estos son los usuarios activos en la BBDD:");

				for (User user : userService.findActiveUsers()) {
					System.out.println(
							user.getName() + " - " + user.getUsername() + " - " + user.getEmail() + " - "
									+ user.getRank());

				}
				System.out.println("");
				break;

			case 2:
				while (opcion != 0) {
					System.out.println(System.lineSeparator()
							+ "Vamos a consultar un usuario. Dinos de qué manera quieres consultarlo:");
					System.out.println("0. Volver para atrás.");
					System.out.println("1. Con el email.");
					System.out.println("2. Con el username.");

					try {
						System.out.println("Opcion: ");
						opcion = scanner.nextInt();
					} catch (Exception e) {
						System.out.println("ERROR. Debes introducir un número entero.");
						scanner.nextLine();
						opcion = -1;
						continue;
					}

					switch (opcion) {
					case 0:
						System.out.println("Volviendo..." + System.lineSeparator());
						break;
					case 1:
						System.out.println("Vamos a consultar el usuario por el correo. Dinos cual es su correo: ");

						try {
							String correo = scanner.next();
							System.out.println(userService.findUserByEmail(correo).getName());
						} catch (Exception e) {
							System.out.println("ERROR. Datos erróneos.");
							scanner.nextLine();
							opcion = -1;
							continue;
						}

						break;
					case 2:
						System.out.println(
								"Vamos a consultar el usuario por el username. Dinos cual es su username: ");

						try {
							String username = scanner.next();
							System.out.println(userService.findUserByUsername(username).getName());
						} catch (Exception e) {
							System.out.println("ERROR. Datos erróneos.");
							scanner.nextLine();
							opcion = -1;
							continue;
						}

						break;

					default:
						System.out.println("Opción incorrecta.");
						break;
					}
				}

				if (opcion == 0) {
					opcion = -1;
				}

				break;

			case 3:
				System.out.println("Vamos a dar de alta un usuario. Dinos lo siguiente de él:");
				try {
					System.out.println("Nombre de usuario:");
					String username = scanner.next();

					System.out.println("Contraseña:");
					String password = scanner.next();

					System.out.println("Nombre:");
					String name = scanner.next();

					System.out.println("Correo del usuario:");
					String email = scanner.next();

					User usuario = new User();
					usuario.setActive(true);
					usuario.setCreatedOn(new Timestamp(new Date().getTime()));
					usuario.setEmail(email);
					usuario.setName(name);
					usuario.setPassword(password);
					usuario.setRank(1);
					usuario.setUsername(username);

					userService.create(usuario);

					System.out.println("Usuario creado correctamente. ");
				} catch (Exception e) {
					System.out.println("Algo ha fallado en el alta del usuario.");
					break;
				}
				break;

			case 4:
				while (opcion != 0) {
					System.out.println(System.lineSeparator()
							+ "Vamos a dar de baja un usuario. Dinos de qué manera quieres buscarlo:");
					System.out.println("0. Volver para atrás.");
					System.out.println("1. Con el email.");
					System.out.println("2. Con el username.");

					try {
						opcion = scanner.nextInt();
					} catch (Exception e) {
						System.out.println("ERROR. Debes introducir un número entero.");
						scanner.nextLine();
						opcion = -1;
						continue;
					}

					switch (opcion) {
					case 0:
						System.out.println("Volviendo..." + System.lineSeparator());
						break;

					case 1:
						System.out.println("Vamos a consultar el usuario por el correo. Dinos cual es su correo: ");

						try {
							String correo = scanner.next();
							System.out.println(userService.findUserByEmail(correo).getName());

							System.out.println("Usuario encontrado. ¿Estás seguro de querer borrarlo?");
							System.out.println("1: Si.");
							System.out.println("2: No.");
							opcion = scanner.nextInt();

							switch (opcion) {
							case 1:
								userService.setUserInactive(userService.findUserByEmail(correo));
								System.out.println("Usuario desactivado exitosamente.");
								break;

							case 2:
								System.out.println("Usuario NO borrado.");
								break;

							default:
								System.out.println("Opción incorrecta.");
								break;
							}

						} catch (Exception e) {
							System.out.println("ERROR. Datos erróneos.");
							scanner.nextLine();
							opcion = -1;
							continue;
						}

						break;
					case 2:
						System.out.println("Vamos a consultar el usuario por el username. Dinos cual es su username: ");

						try {
							String username = scanner.next();
							System.out.println(userService.findUserByUsername(username).getName());

							System.out.println("Usuario encontrado. ¿Estás seguro de querer borrarlo?");
							System.out.println("1: Si.");
							System.out.println("2: No.");
							opcion = scanner.nextInt();

							switch (opcion) {
							case 1:
								userService.setUserInactive(userService.findUserByUsername(username));
								System.out.println("Usuario desactivado exitosamente.");
								break;

							case 2:
								System.out.println("Usuario NO borrado.");
								break;

							default:
								System.out.println("Opción incorrecta.");
								break;
							}
						} catch (Exception e) {
							System.out.println("ERROR. Datos erróneos.");
							scanner.nextLine();
							opcion = -1;
							continue;
						}
						break;

					default:
						System.out.println("Opción incorrecta.");
						break;
					}
				}

				if (opcion == 0) {
					opcion = -1;
				}

				break;

			default:
				System.out.println("Opción incorrecta.");
				break;
			}
		}

		scanner.close();
	}

}
