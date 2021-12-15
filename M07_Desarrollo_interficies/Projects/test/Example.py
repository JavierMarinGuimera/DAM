# EJERCICIO 1: EJERCICIO DE PRUEBAS. PRIMERAS PINCELADAS
codif = input()

for char in codif:
    if char == "A":
        print("*" * 5)
    elif char == "B":
        print("*" + " " * 3 + "*")
    elif char == "L":
        print("*")
    elif char == "R":
        print(" " * 4 + "*")
    else:
        print("Error")
