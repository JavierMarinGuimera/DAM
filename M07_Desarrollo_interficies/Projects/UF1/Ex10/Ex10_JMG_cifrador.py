from PyQt5.QtWidgets import QApplication, QMainWindow
from PyQt5.QtCore import Qt
from PyQt5 import uic
import sys


class Encrypter(QMainWindow):
    alfabet = "abcdefghijklmnopqrstuvwxyz"

    def __init__(self):
        super().__init__()
        uic.loadUi('ui/Ex10_JMG_cifrador.ui', self)
        self.setWindowTitle('Encriptar - desencriptar')
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

    def codificar(self, txt, clau):
        if clau > 0 and clau <= len(self.alfabet) - 1:
            codifiedAlphabet = ""
            finalText = ""

            # THIS FOR MOUNTS THE CODIFIED ALPHAFET TO CODIFY THE TEXTS
            for letraAlfabet in self.alfabet:
                if clau == len(self.alfabet):
                    clau = 0
                codifiedAlphabet += self.alfabet[clau]
                clau += 1

            # THIS FOR MOUNTS THE TEXT CODIFIED
            for letraTXT in txt:
                if letraTXT in codifiedAlphabet:
                    finalText += codifiedAlphabet[self.alfabet.index(letraTXT)]
                else:
                    finalText += letraTXT
            return(finalText)
        else:
            return("Texto no codificado: \n" + txt)


app = QApplication(sys.argv)
win = Encrypter()
app.exec_()
