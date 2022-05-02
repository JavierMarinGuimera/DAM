class Punt:
    """Classe que permet representar un punt en un gràfic

    :param x: Representació de la posició x del punt, defaults to 0
    :type x: int, optional
    :param y: Representació de la posició y del punt, defaults to 0
    :type y: int, optional
    """
    def __init__(self, x=0, y=0):
        """ Crear un punt en (x, y) """
        self.x = x
        self.y = y
    def distancia_origen(self):
        """ Distància del punt a l'origen de coordenades (0, 0)

        :return: Distancia del punt a (0, 0)
        :rtype: int
        """
        return ((self.x ** 2) + (self.y ** 2)) ** 0.5
    def __str__(self):
        """ Retorna el punt per a poder mostrar-lo per pantalla

        :return: Retorna un String amb el format (x, y)
        :rtype: String
        """
        return f'({self.x}, {self.y})' # format de cadena amb f-string
    def punt_intermedi(self, altre):
        """ Punt intermedi entre el punt i el altre punt

        :param altre: Representa un altre objecte Punt
        :type altre: Punt, optional

        :return: Retorna un Ppunt que representa el punt intermedi
        :rtype: Punt
        """
        mx = (self.x + altre.x)/2
        my = (self.y + altre.y)/2
        return Punt(mx, my)
