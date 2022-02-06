# Examen Javier Marín guimerà

# IMPORTS:
import random


def comprobarCoord(casillaList):
    if casillaList[0].upper() not in PUNTOS_CARDINALES or casillaList[1].isnumeric() == False or int(casillaList[1:]) < 1 or int(casillaList[1]) > 49:
        return False

    return True


def moviment():
    txt_mov = input("Introduce un movimiento: ")
    if comprobarCoord(txt_mov):
        return txt_mov
    else:
        return None


def moviment2(pos_ini):
    move = moviment()

    if (move != None):
        pos_fi = None

        if (move[0].upper() in "NS"):
            if (move[0].upper() == "N"):
                final_y = pos_ini[0] - int(move[1:])

            else:
                final_y = pos_ini[0] + int(move[1:])

            if (final_y >= 0 and final_y <= 99):
                print("Hola")
                pos_fi = (final_y, pos_ini[1])

        else:
            if (move[0].upper() == "O"):
                final_x = pos_ini[1] - int(move[1:])

            else:
                final_x = pos_ini[1] + int(move[1:])

            if (final_x >= 0 and final_x <= 99):
                pos_fi = (pos_ini[0], final_x)

        return pos_fi

    return None


def lotto_6_49():
    total_nums = 6
    list_nums = []

    while (total_nums > 0):
        current_num = random.randint(0, 49)

        if (current_num not in list_nums):
            list_nums.append(current_num)
            total_nums -= 1

    return list_nums


def calcular_ronda(parelles_ronda):
    parejas_ganadoras = []

    pareja = 0
    total_parejas = len(parelles_ronda)

    while (pareja < total_parejas):
        pareja_ganadora = []

        for i in range(2):
            print(str(pareja))
            ganador = random.randint(0, 1)
            pareja_ganadora.append(parelles_ronda[pareja][ganador])
            pareja += 1

        parejas_ganadoras.append(pareja_ganadora)

    return parejas_ganadoras


def mes_repetits(llista):
    dic_to_count = {}
    list_to_return = []
    max_repeticiones = 0

    for num_actual in llista:
        if (num_actual not in dic_to_count):
            dic_to_count[num_actual] = 1

        else:
            dic_to_count[num_actual] += 1

        if (max_repeticiones < dic_to_count[num_actual]):
            max_repeticiones = dic_to_count[num_actual]

    for (key, val) in dic_to_count.items():
        if (val == max_repeticiones):
            list_to_return.append(key)

    return list_to_return


# MAIN PROGRAM
PUNTOS_CARDINALES = "NSEO"

# Ej1 parecido pescamonedas input
print("Ejercicio moviment()!")
print(moviment())

# Ej2
print("Ejercicio moviment2(pos_ini)!")
pos_ini = (0, 0)
print(moviment2(pos_ini))

# Ej3
print("Ejercicio lotto_6_49()!")
print(lotto_6_49())

# Ej4
print("Ejercicio calcular_ronda(parelles_ronda)!")
parelles_ronda = [[1, 2], [3, 4], [5, 6], [
    7, 8], [9, 10], [11, 12], [13, 14], [15, 16]]
print(calcular_ronda(parelles_ronda))

# Ej5
print("Ejercicio mes_repetits(llista)!")
llista = [37, 37, 34, 34, 1, 3]
print(mes_repetits(llista))
