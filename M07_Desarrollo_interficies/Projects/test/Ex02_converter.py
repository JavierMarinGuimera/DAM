# EJERCICIO 3: CONVERTIDOR DE NÚMERO ENTERO A BINARIO, HEXADECIMAL
def main():
    option = selectConversion()

    if option == "B" or option == "X":
        num = readNum()

        res = []

        if option == "B":
            decToBin(num, res)
        else:
            decToHex(num, res)

        printList(option, num, res)

    else:
        print("See you!")


def selectConversion():
    while True:
        try:
            print("\n--------------------------------------------------------")
            print("Select one of the following options:")
            print("B: Calculate binari number using a decimal number.")
            print("X: Calculate hexadecimal number using a decimal number.")
            print("Any other character: Exit program.")
            print("--------------------------------------------------------\n")

            return input("Opción: ").upper()
        except:
            print('You have to enter a letter!')
            input("Press a key to execute the program again: ")


def readNum():
    while True:
        try:
            num = int(input("Number: "))
            if num < 0:
                raise Exception
            else:
                return num
        except:
            print('You have to enter a positive decimal number!')
            input("Press a key to execute the program again: ")


def decToBin(num, res):
    while num >= 2:
        res.append(num % 2)
        num = int(num / 2)

    res.append(num)

    return


def decToHex(num, res):
    while num >= 16:
        hexNum = str(num % 16)

        if int(hexNum) > 10:
            hexNum = calculateHexNum(hexNum)

        res.append(hexNum)

        num = int(num / 16)

    if num > 10:
        res.append(calculateHexNum(num))
    else:
        res.append(num)
    return


def calculateHexNum(hexNum):
    number = int(hexNum)
    if number == 10:
        return "A"
    elif number == 11:
        return "B"
    elif number == 12:
        return "C"
    elif number == 13:
        return "D"
    elif number == 14:
        return "E"
    else:
        return "F"


def printList(option, num, res):
    res.reverse()

    finalString = ""

    # for val in range(len(res)):
    #     finalString = finalString + str(res[-val])

    for val in res:
        finalString = finalString + str(val)

    if option == "B":
        print("The number " + str(num) + " to binari is: " + finalString)
    else:
        print("The number " + str(num) + " to hexadecimal is: " + finalString)

    input("Press a key to execute the program again: ")

    main()
    return


main()
