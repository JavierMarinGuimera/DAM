# IMPORTS:
import random

# Clases and methods


class Jugador:

    def __init__(self, name):
        self.__name = name

    @property
    def name(self):
        return self.__name

    @name.setter
    def name(self, name):
        self.__name = name

    @staticmethod
    def jugar_ma():
        values = ["pedra", "paper", "tisores"]
        return values[random.randint(0, 2)]


class Arbitre:
    def __init__(self, jugador1, jugador2):
        self.__jugador1 = jugador1
        self.__jugador2 = jugador2
        self.__empatadas = 0
        self.solutions = {
            "pedra": "paper",
            "paper": "tisores",
            "tisores": "pedra"
        }

    @property
    def jugador1(self):
        return self.__jugador1

    @jugador1.setter
    def jugador1(self, jugador1):
        self.__jugador1 = jugador1

    @property
    def jugador2(self):
        return self.__jugador2

    @jugador2.setter
    def jugador2(self, jugador2):
        self.__jugador2 = jugador2

    @property
    def empatadas(self):
        return self.__empatadas

    @empatadas.setter
    def empatadas(self, empatadas):
        self.__empatadas = empatadas

    def arbitrar_ma(self, ma1, ma2):
        if ma1 == self.solutions[ma2]:
            self.jugador1 += 1
            return "Ma 1"
        elif ma2 == self.solutions[ma1]:
            self.jugador2 += 1
            return "Ma 2"
        else:
            self.empatadas += 1
            return "EMPATE"


# MAIN PROGRAM
print("\n--------------------------------------")
print("¡Bienvenido a piedra, papel o tijeras!\n")

j1 = Jugador("Eva")
j2 = Jugador("Tom")

print(f"Van a jugar {j1.name} contra {j2.name}")

arbitre = Arbitre(0, 0)

lastWinner = ""
dif_vict = 0
max_mans = 0
while (dif_vict != 3 and max_mans != 10):
    # - Manos
    ma1 = Jugador.jugar_ma()
    ma2 = Jugador.jugar_ma()

    res = arbitre.arbitrar_ma(ma1, ma2)

    if (res == lastWinner):
        dif_vict += 1
    elif (res != "EMPATE"):
        lastWinner = res
        dif_vict = 1

    print(
        f"Ronda {max_mans + 1}: {j1.name} - {ma1} <-> {ma2} - {j2.name} = {res}")

    max_mans += 1

    if (dif_vict == 3):
        if (res == "Ma 1"):
            print(f"\n¡Ha ganado {j1.name}!")
        else:
            print(f"\n¡Ha ganado {j2.name}!")
    else:
        if (max_mans == 10):
            print("Ha habido un empate!")
            print("--------------------------------------")
