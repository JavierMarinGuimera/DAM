from PyQt5.QtWidgets import QApplication, QMainWindow
from PyQt5.QtCore import Qt
from PyQt5 import uic
import sys


class Exemple2(QMainWindow):
    mensajes = ["Hola", "Què tal?", "Encantat", "Fins la propera", "Adeu"]
    mensajeActual = 0

    def __init__(self):
        super().__init__()
        uic.loadUi('ui/exemple2.ui', self)
        self.setWindowTitle('Pràctica 9')
        self.btn_saludar.clicked.connect(self.saludar)
        self.show()

    def saludar(self):
        self.lbl_salutacio.setText(self.mensajes[self.mensajeActual])
        self.mensajeActual += 1
        if (self.mensajeActual == len(self.mensajes)):
            self.mensajeActual = 0

    def keyPressEvent(self, e):
        if e.key() == Qt.Key_H:
            self.lbl_salutacio.setText("Hola")
        elif e.key() == Qt.Key_A:
            self.lbl_salutacio.setText("Adeu")
        elif e.key() == Qt.Key_Escape:
            self.close()


app = QApplication(sys.argv)
win = Exemple2()
app.exec_()
