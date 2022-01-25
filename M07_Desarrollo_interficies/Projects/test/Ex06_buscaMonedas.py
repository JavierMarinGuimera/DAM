# ¡EL BUSCAMONEDAS!

# Imports
import random

# Métodos:


def crearTablero():
    # ** FUNCIÓN PARA CREAR EL TABLERO DE 0 **
    mat_res = []

    # Siguiente FOR crea la matriz del tamaño m · n con todo espacios vacios.
    for i in range(DIMENSION):
        mat_res.append([])
        for j in range(DIMENSION):
            mat_res[i].append("  ")

    return(mat_res)


def esconderMonedas():
    # ** FUNCIÓN PARA UBICAR LAS MONEDAS ALEATORIAMENTE **
    totalMonedas = round((DIMENSION ** 2) * (PORCENTAGE_OCUPADAS / 100))

    listMonedas = []

    while totalMonedas != 0:
        x = random.randint(0, DIMENSION - 1)
        y = random.randint(0, DIMENSION - 1)

        if (x, y) not in listMonedas:
            listMonedas.append((x, y))
            totalMonedas -= 1

    return listMonedas


def modificarTablero(tablero, monedas, casillaList):
    # ** FUNCIÓN PARA MODIFICAR EL TABLERO **
    x = ALFABETO.index(casillaList[0].upper())
    y = int(casillaList[1]) - 1

    if (x, y) in monedas:
        # monedas.pop(monedas.index((x, y)))
        tablero[x][y] = "XX"
    else:
        tablero[x][y] = "0" + str(comprobarCercanias(monedas, x, y))


def comprobarCercanias(monedas, x, y):
    monedasEncontradas = 0
    for i in range(x - 1, x + 2):
        if (x < 0 or x > DIMENSION):
            continue

        for j in range(y - 1, y + 2):
            if (j < 0 or j > DIMENSION):
                continue

            if (i, j) in monedas:
                monedasEncontradas += 1

    return monedasEncontradas


def mostrarTablero(tablero, totalMonedas):
    # ** FUNCIÓN PARA MOSTRAR EL ESTADO DEL TABLERO **
    print("\nResultado: Faltan todavía " +
          str(totalMonedas) + " monedas por encontrar. \n")

    for i in range(DIMENSION + 1):
        linea = ""
        for j in range(DIMENSION + 1):
            if (i == 0 and j == 0):
                linea += "  | "
            else:
                if (i == 0 or j == 0):
                    if (i == 0):
                        linea += ("0" if j < 10 else "") + str(j) + " | "

                    if (j == 0):
                        linea += ALFABETO[i - 1] + " | "
                else:
                    linea += tablero[i - 1][j - 1] + " | "
        print(linea + "\n")


def getCasillaList(casilla):
    # ** FUNCIÓN QUE HACE UN SPLIT A LA CASILLA QUE HA INTRODUCIDO EL USUARIO Y LA CONVERTIMOS EN UN ARRAY **
    return [casilla[0:1], casilla[1:]]


def comprobarCasilla(casillaList):
    if casillaList[0].upper() not in ALFABETO or ALFABETO.index(casillaList[0].upper()) > DIMENSION - 1 or casillaList[1].isnumeric() == False or int(casillaList[1]) < 0 or int(casillaList[1]) > DIMENSION:
        return False

    return True


def comenzarJuego():
    # ** FUNCIÓN DE TODO EL JUEGO **

    # SALIR DEL JUEGO SI EL TABLERO NO CUMPLE CON LOS TAMAÑOS REQUERIDOS
    if (DIMENSION < 4 or DIMENSION > 20):
        print("El tamaño del tablero es demasiado " +
              "pequeño" if (DIMENSION < 4) else "grande" + ".")
        return

    tablero = crearTablero()
    monedas = esconderMonedas()
    print("\nHay un total de " + str(round((DIMENSION ** 2) * (PORCENTAGE_OCUPADAS / 100))) +
          " monedas, pero debes encontrar solo " + str(round((DIMENSION ** 2) * (PORCENTAGE_OCUPADAS / 100) / 2) + 1) + " para ganar.")
    totalMonedas = (
        round((DIMENSION ** 2) * (PORCENTAGE_OCUPADAS / 100) / 2)) + 1

    while True:
        mostrarTablero(tablero, totalMonedas)

        if (totalMonedas == 0):
            print("¡Has ganado! \n")
            break

        casilla = input(
            "Introduce una casilla (Ej: A1) o 'Salir' para salir del juego: ")

        if (casilla.lower() != "salir"):
            casillaList = getCasillaList(casilla)
            if comprobarCasilla(casillaList):
                modificarTablero(tablero, monedas, casillaList)
                if (tablero[ALFABETO.index(casillaList[0].upper())][int(casillaList[1]) - 1] == "XX"):
                    totalMonedas -= 1
            else:
                print("\nCasilla errónea!")

        else:
            print("\n¡Hasta otra!\n")
            break


# Programa principal:
DIMENSION = 20
PORCENTAGE_OCUPADAS = 20
ALFABETO = "ABCDEFGHIJKLMNOPQRST"

comenzarJuego()
