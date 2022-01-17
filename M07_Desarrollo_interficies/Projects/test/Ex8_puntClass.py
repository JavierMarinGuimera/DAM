# Prueba clase Punto
class Punt():
    def __init__(self, x=0, y=0):
        #  Crear un punt en (x, y)
        self.x = x
        self.y = y

    def __str__(self):
        return f'Punt: ({self.x}, {self.y})'  # format de cadena amb f-string

    def __add__(self, other):
        return (Punt(self.x + other.x, self.y + other.y))

    def distancia_origen(self):
        # Distància del punt a l'origen de coordenades (0, 0)
        return ((self.x ** 2) + (self.y ** 2)) ** 0.5

    def punt_intermedi(self, other):
        # Punt intermedi entre el punt i el other punt
        mx = (self.x + other.x)/2
        my = (self.y + other.y)/2
        return Punt(mx, my)

    def pendent_al_origen(self, other):
        return ((self.y - other.y) / (self.x - other.x))

    def coeficients_recta(self, other):
        m = self.pendent_al_origen(other)
        n = self.y - (m * self.x)
        return (m, n)


class Rectangle():
    def __init__(self, punt, width, height):
        self.punt = punt
        self.width = width
        self.height = height

    def area(self):
        return (self.width * self.height)


class Quadrat(Rectangle):
    def __init__(self, punt, width):
        super().__init__(punt, width, width)


# MAIN PROGRAM
p1 = Punt(5, 4)
print(p1)

p2 = p1.punt_intermedi(Punt(5, 8))
print(p2)

print(p1.pendent_al_origen(Punt(0, 0)))

print(type(p1.coeficients_recta(Punt(-5, -2))))

print(p1 + p1)

print(Quadrat(Punt(3, 2), 9).area())
