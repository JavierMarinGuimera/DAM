# Examen de prueba.

import random


def escriure_poema(text):
    # EJERCICIO 1

    x = 0
    y = 1

    reached9 = False

    while (x < len(text)):
        print(text[x:x+y])
        x += y

        if (y == 9):
            reached9 = True
        elif (reached9 and y == 1):
            reached9 = False

        if (reached9):
            y -= 2
        else:
            y += 2


def crear_parcials_aleatoris():
    # EJERCICIO 2
    # random.randint(0, m-1)

    temps_parcials = []

    for i in range(5):
        temps_parcials.append([])
        for j in range(3):
            temps_parcials[i].append(random.randint(80, 90))

    return temps_parcials


def calcular_millors_temps(temps_parcials):
    # EJERCICIO 3
    print(temps_parcials)

    meta1 = 0
    ciclista1 = 0

    meta2 = 0
    ciclista2 = 0

    meta3 = 0
    ciclista3 = 0

    for i in range(5):
        for j in range(3):
            if (j == 0):
                if (meta1 > temps_parcials[i][j] or meta1 == 0):
                    meta1 = temps_parcials[i][j]
                    ciclista1 = i + 1
            elif (j == 1):
                sumaMeta2 = temps_parcials[i][j - 1] + temps_parcials[i][j]
                if (meta2 > sumaMeta2 or meta2 == 0):
                    meta2 = sumaMeta2
                    ciclista2 = i + 1
            elif (j == 2):
                sumaMeta3 = temps_parcials[i][j - 2] + \
                    temps_parcials[i][j - 1] + temps_parcials[i][j]
                if (meta3 > sumaMeta3 or meta3 == 0):
                    meta3 = sumaMeta3
                    ciclista3 = i + 1

            temps_parcials[i][j]

    print("1ª meta: " + str(meta1) + "(" + str(ciclista1) + "º ciclista)")
    print("2ª meta: " + str(meta2) + "(" + str(ciclista2) + "º ciclista)")
    print("3ª meta: " + str(meta3) + "(" + str(ciclista3) + "º ciclista)")


def validar_acces(contrasenyes, usuari, contrasenya):
    if (usuari in contrasenyes):
        if (contrasenyes[usuari][1] == 3):
            print("Usuario bloqueado")
            return

        if (contrasenyes[usuari][0] == contrasenya):
            print("Acceso concedido")
        else:
            print("Acceso denegado")
            contrasenyes[usuari][1] += 1

    else:
        print("¡Usuario incorrecto!")
    pass


def login():

    contrasenyes = {"admin": ["55682919", 3], "brent": ["53803208", 0], "eunie":
                    ["12210457", 2]}

    while (True):
        if (input("Escribe 'Salir' si quieres salir del programa:").lower() == "salir"):
            break

        usuari = input("Introduce un usuario:").lower()
        contrasenya = input("Introduce una contraseña: ")

        validar_acces(contrasenyes, usuari, contrasenya)

        print("\n")


# MAIN PROGRAM
poema = "Si haguessis nascut en una altra terra, podries ser blanc, podries ser negre..."

escriure_poema(poema)

temps_parcials = crear_parcials_aleatoris()

calcular_millors_temps(temps_parcials)

login()
