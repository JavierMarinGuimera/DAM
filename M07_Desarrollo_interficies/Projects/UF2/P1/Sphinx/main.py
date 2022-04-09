class Punt:
    """Summary
    :param x: First point coordenate, defaults to 0
    :type x: integer
    :param y: Second point coordenate, defaults to 0
    :type y: integer
    """

    def __init__(self, x=0, y=0):
        """ Create a point (x, y)."""
        self.x = x
        self.y = y

    def distancia_origen(self):
        """ Distance since the start point to the coordenates (0, 0). """
        return ((self.x ** 2) + (self.y ** 2)) ** 0.5

    def __str__(self):
        return f'({self.x}, {self.y})'  # format de cadena amb f-string

    def punt_intermedi(self, altre):
        """ Middle point between both points."""
        mx = (self.x + altre.x) / 2
        my = (self.y + altre.y) / 2
        return Punt(mx, my)
