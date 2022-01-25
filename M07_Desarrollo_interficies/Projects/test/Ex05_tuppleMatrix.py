# TUPLES AND MATRIX
import random


def sum_mat(mat_a, mat_b):
    mat_res = {}

    for (key, val) in mat_a.items():
        if key in mat_b:
            mat_res[key] = mat_a[key] + mat_b[key]
        else:
            mat_res[key] = mat_a[key]

    for (key, val) in mat_b.items():
        if key not in mat_a:
            mat_res[key] = mat_b[key]
    return(mat_res)


def matriu(m, n, k):
    # Siguiente condición comprueba que los valores que se van a cambiar son menores que el 10% del total de la matriz.
    if (m * n) * 0.10 >= k:
        mat_res = []
        diccionary_res = {}

        # Siguiente for crea la matriz del tamaño m · n con todo 0.
        for i in range(m):
            mat_res.append([])
            for j in range(n):
                mat_res[i].append(0)

        # Siguiente bucle se encarga de generar un valor aleatorio y posicionarlo en una posición aleatoria.
        for i in range(k):
            x = random.randint(0, m-1)
            y = random.randint(0, n-1)
            mat_res[x][y] = 1

        return(mat_res)

    else:
        raise ValueError


def distancies(dic_pos):
    dic_res = {}

    for (key, val) in dic_pos.items():
        dicToSave = {}
        for (key2, val2) in dic_pos.items():
            if dic_pos[key] != dic_pos[key2]:
                dicToSave[key2] = round(
                    ((val[0] - val2[0]) ** 2 + (val[1] - val2[1]) ** 2) ** 0.5)

        dic_res[key] = dicToSave

    return(dic_res)


# MAIN
# Exercise 1:
a = {(51, 3): -4, (105, 80): 79, (105, 120): 20}
b = {(51, 3): 8, (105, 120): -35, (105, 180): 40, (70, 150): -3}

sum_mat(a, b)


# Exercise 2:
m = 3
n = 4
k = 1
print(matriu(m, n, k))

# Exercise 3:
pos = {
    'Brana': (172, 167),
    'Griva': (225, 104),
    'Levira': (44, 141),
    'Tarsos': (95, 198)
}

dist = distancies(pos)

print(dist)
