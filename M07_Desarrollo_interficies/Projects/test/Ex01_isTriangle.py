# EJERCICIO 2: COPROBAR SI UN TRIÁNGULO ES EQUILÁTERO, ISÓSCELES O ESCALENO
def readNum(lado):
    try:
        return float(input("Lado " + lado + ": "))
    except:
        print('Debes introducir un número entero o decimal (delimitado por punto).')
        readNum(lado)


def checkSides(side1, side2, side3):
    if side1 < (side2 + side3) and side2 < (side1 + side3) and side3 < (side1 + side2):
        if side1 == side2 and side1 == side3:
            return("\n - El triángulo es equilátero -")
        elif side1 == side2 or side1 == side3:
            return("\n - El triángulo es isósceles -")
        elif side2 == side3:
            return("\n - El triángulo es isósceles -")
        else:
            return("\n - El triángulo es escaleno -")
    else:
        return("\n - No es un triángulo")


print("Introduce el primer lado:")
side1 = readNum("1")

print("Introduce el segundo lado:")
side2 = readNum("2")

print("Introduce el tercer lado:")
side3 = readNum("3")

print(checkSides(side1, side2, side3))

print('Debes introducir un número entero o decimal (delimitado por punto).')

print("FIN DE PROGRAMA \n")
