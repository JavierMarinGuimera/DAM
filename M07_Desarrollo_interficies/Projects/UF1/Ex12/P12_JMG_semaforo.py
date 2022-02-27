# self.hola.triggered.connect(self.mostrr_x) MENÚ
# self.canvas.setCurrentIndex(1) Mostramos el SEGUNDO

from PyQt5.QtWidgets import QApplication, QMainWindow
from PyQt5.QtCore import Qt
from PyQt5.QtGui import QIntValidator, QDoubleValidator
from PyQt5 import uic
import sys
import locale


class Semaforo(QMainWindow):
    operationType = 1
    operation = 1

    def __init__(self):
        # DEFAULT TASKS:
        super().__init__()
        uic.loadUi('Ex12/ui/p12_JMG_semaforo.ui', self)
        locale.setlocale(locale.LC_ALL, "es_ES")

        # CUSTOM TASKS:
        self.setWindowTitle('Semáforo')

        # Some asignments:
        self.ac_configurar.triggered.connect(self.mostrarConfig)
        self.ac_sortir.triggered.connect(self.salirPrograma)
        self.ac_quant_a.triggered.connect(self.mostrarInfo)

        # - OnClick METHODS:

        # SHOW UI
        self.show()

    def keyPressEvent(self, e):
        if e.key() == Qt.Key_Escape:
            self.close()

    def mostrarConfig(self):
        self.
        pass

    def salirPrograma(self):
        self.close()

    def mostrarInfo(self):
        pass
        


# Main program
app = QApplication(sys.argv)
sem = Semaforo()
app.exec_()
