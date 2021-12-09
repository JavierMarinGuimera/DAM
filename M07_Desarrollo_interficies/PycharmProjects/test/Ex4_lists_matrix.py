# TESTING LIST AND TUPPLES
import random


def matriu(m, n):
    matrix = []
    for i in range(m):
        matrix.append([])
        for j in range(n):
            matrix[i].append(random.randint(-100, 100))
    return matrix


def sum_mat(mat_a, mat_b):
    return mat_a + mat_b


def mult_mat(mat_a, mat_b):
    if (len(mat_a[0]) == len(mat_b)):
        resMatrix = []
        for i in range(len(mat_a)):
            resMatrix.append([])
            for j in range(len(mat_b[0])):
                resMatrix[i].append(0)
                for k in range(len(mat_b)):
                    resMatrix[i][j] += mat_a[i][k] * mat_b[k][j]

        return resMatrix
    else:
        return "Las matrices no se pueden multiplicar"


# MAIN PROGRAM:
m = 2
n = 3
p = 3

mat_a = matriu(m, n)
mat_b = matriu(n, p)

print("\nLa suma de las 2 matrices generadas es: " + str(sum_mat(mat_a, mat_b)))

# Test values:
# mat_a = [[1, 2], [-1, 0], [-3, -1]]
# mat_b = [[2, 0, 1], [-5, 2, 3]]

print("\nLa multiplicación de las 2 matrices generadas es: " +
      str(mult_mat(mat_a, mat_b)) + "\n")
