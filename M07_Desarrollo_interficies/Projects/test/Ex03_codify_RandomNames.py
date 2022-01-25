# EXERCISE 4: CODIFY AND DECODIFY TEXTS + RANDOM NAMES

# Imports
import random

# Part 1:
input("\nParte 1 de la práctica. Enter para continuar:")


def codificar(txt, clau):
    if clau > 0 and clau <= len(alfabet) - 1:
        codifiedAlphabet = ""
        finalText = ""

        # THIS FOR MOUNTS THE CODIFIED ALPHAFET TO CODIFY THE TEXTS
        for letraAlfabet in alfabet:
            if clau == len(alfabet):
                clau = 0
            codifiedAlphabet += alfabet[clau]
            clau += 1

        # THIS FOR MOUNTS THE TEXT CODIFIED
        for letraTXT in txt:
            if letraTXT in codifiedAlphabet:
                finalText += codifiedAlphabet[alfabet.index(letraTXT)]
            else:
                finalText += letraTXT
        return(finalText)
    else:
        return("Texto no codificado: \n" + txt)


def possibles_codif(txt):
    for num in range(len(alfabet)):
        print(str(num) + ": " + codificar(txt, num) + "\n")


# PROGRAMA PRINCIPAL 1
text = """co rqt xqryji buqt je bqju dywxji, udtydw qbedu
sedluhiqjyedi myjx q ijhqdwuh y rqhubo adem
imuqhydw jxyi mybb ru jxu bqij, rkj yj fherqrbo med'j
y wej dejxydw buvj je beiu, eh kiu, eh te"""
clau = 10
alfabet = "abcdefghijklmnopqrstuvwxyz"

print("El texto codificado de ejemplo es: " +
      codificar(text.lower(), clau) + "\n")

possibles_codif(text)
# --------------------------------------------------------------------------


# Part 2:
input("\nParte 2 de la práctica. Enter para continuar:")


def nom_aleat(min_car, max_car):
    long = random.randint(min_car, max_car)

    name = ""
    for x in range(long):
        if len(name) == 0:
            name += alfabet[random.randint(0, len(alfabet) - 1)].upper()
        elif name[-1].lower() in "aeiou":
            while True:
                randomChar = alfabet[random.randint(0, len(alfabet) - 1)]
                if randomChar not in "aeiou":
                    name += randomChar
                    break
        else:
            while True:
                randomChar = alfabet[random.randint(0, len(alfabet) - 1)]
                if randomChar in "aeiou":
                    name += randomChar
                    break
    return(name)


def escriure_noms(num_noms,  min_car, max_car):
    nums = ""
    for x in range(num_noms):
        nums += nom_aleat(min_car, max_car) + " "
    print(str(num_noms) + " nombres aleatorios: \n" + nums + "\n")


# PROGRAMA PRINCIPAL 2
print("Nombre aleatorio: " + nom_aleat(4, 7) + "\n")

escriure_noms(10, 3, 8)
