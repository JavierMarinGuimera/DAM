# self.hola.triggered.connect(self.mostrr_x) MENÚ
# self.canvas.setCurrentIndex(1) Mostramos el SEGUNDO

from PyQt5.QtWidgets import QApplication, QMainWindow
from PyQt5.QtCore import Qt, QTimer
from PyQt5 import uic
import sys
import locale


class Semaforo(QMainWindow):
    cicles = {"Estàndard":
              ((33, True, False, False),
               (24, False, False, True),
                  (3, False, True, False)),
              "Majoria d'Europa":
              ((30, True, False, False),
                  (3, True, True, False),
                  (24, False, False, True),
                  (3, False, True, False))}
    llums = {"nom etiquetes": ("lb_vermell", "lb_groc", "lb_verd"),
             "colors on": ("red", "yellow", "green"),
             "colors off": ("grey", "grey", "grey")}

    def __init__(self):
        # DEFAULT TASKS:
        super().__init__()
        uic.loadUi('ui/p12_JMG_semaforo.ui', self)
        locale.setlocale(locale.LC_ALL, "es_ES")

        # CUSTOM TASKS:
        self.setWindowTitle('Semáforo')
        self.timer = QTimer()

        # - OnClick METHODS:
        self.ac_configurar.triggered.connect(self.mostrarConfig)
        self.ac_sortir.triggered.connect(self.salirPrograma)
        self.ac_quant_a.triggered.connect(self.mostrarInfo)
        self.bt_ok1.clicked.connect(self.mostrarMain)
        self.bt_ok2.clicked.connect(self.mostrarMain)

        # Some asignments:
        self.timer.timeout.connect(self.changeState)

        # SHOW UI and methods calls:
        self.show()
        self.setComboBoxValues()
        self.mainProgram()

    def keyPressEvent(self, e):
        if e.key() == Qt.Key_Escape:
            self.close()

    def setComboBoxValues(self):
        for cicle in self.cicles:
            self.cb_cicle.addItem(cicle)

    def mainProgram(self):

        pass

    def changeState(self):
        pass
        self.timer.start(1000)

    def mostrarMain(self):
        self.stackedWidget.setCurrentIndex(0)
        self.changeState()

    def mostrarConfig(self):
        self.stackedWidget.setCurrentIndex(1)

    def mostrarInfo(self):
        self.stackedWidget.setCurrentIndex(2)

    def salirPrograma(self):
        self.close()


# Main program
app = QApplication(sys.argv)
sem = Semaforo()
app.exec_()
