from asyncio.windows_events import NULL
from distutils.ccompiler import show_compilers
from PyQt5 import QtWidgets
from PyQt5.QtWidgets import QApplication, QMainWindow, QMessageBox
from PyQt5.QtCore import Qt
from PyQt5 import uic
import sys
import locale


class Triangulos(QMainWindow):
    def __init__(self):
        self.sides = {
            "A": [1, 3, 5, 9],
            "B": [6, 7, 8, 9],
            "C": [1, 2, 4, 6]
        }

        # DEFAULT TASKS:
        super().__init__()
        uic.loadUi('ui/PE2UF1_Marin_Javier.ui', self)
        locale.setlocale(locale.LC_ALL, "es_ES")

        # CUSTOM TASKS:
        self.setWindowTitle('Triangulo mágico')
        self.setMinAndMaxSpinBoxes()

        # - OnClick METHODS:
        self.ac_configurar.triggered.connect(self.mostrarConfig)
        self.ac_sortir.triggered.connect(self.salirPrograma)
        self.ok_btn.clicked.connect(self.mostrarMain)
        self.validate_btn.clicked.connect(self.validate)

        # Some asignments:
        self.side_sb.setValue(20)
        self.maxSum_lb.setText(str(20))

        # SHOW UI and methods calls:
        self.show()

    def keyPressEvent(self, e):
        if e.key() == Qt.Key_Escape:
            self.close()

    def setMinAndMaxSpinBoxes(self):
        for key, values in self.sides.items():
            for sb in values:
                nombre_sb = "sb_" + str(sb)
                sb = self.findChild(QtWidgets.QSpinBox, nombre_sb)
                sb.setMinimum(1)
                sb.setMaximum(9)

    def setMaxSumConfig(self, num):
        self.maxSum_lb.setText(str(num))
        self.side_sb.setValue(num)

    def mostrarMain(self):
        self.setMaxSumConfig(self.side_sb.value())
        self.stackedWidget.setCurrentIndex(0)

    def mostrarConfig(self):
        self.stackedWidget.setCurrentIndex(1)

    def salirPrograma(self):
        self.close()

    def validate(self):
        sumas = {
            "A": [0, False],
            "B": [0, False],
            "C": [0, False]
        }
        currentValues = {}

        for key, values in self.sides.items():
            for sb in values:
                nombre_sb = "sb_" + str(sb)
                sb = self.findChild(QtWidgets.QSpinBox, nombre_sb)
                sumas[key][0] += sb.value()

                # Va a salir siempre Valor repetido.
                # He intentado esto if (sb.value() in currentValues and values.index(sb.value()) != 3):
                if (sb.value() in currentValues):
                    sumas[key][1] = True
                else:
                    currentValues[sb.value()] = 1

        if (sumas["A"][0] == self.side_sb.value() and sumas["B"][0] == self.side_sb.value() and sumas["C"][0] == self.side_sb.value()):
            self.showMessage("OK")
        elif (sumas["A"][1] == True or sumas["B"][1] == True or sumas["C"][1] == True):
            self.showMessage("REPEAT")
        else:
            self.showMessage("FAIL")

    def showMessage(self, res):
        msg = QMessageBox()
        msg.setIcon(QMessageBox.Information)

        msg.setText("Resultado:")

        if (res == "OK"):
            msg.setInformativeText("Felicitats, has resolt el triangle màgic")
        elif (res == "REPEAT"):
            msg.setInformativeText("Hi ha algun valor repetit")
        else:
            msg.setInformativeText(
                "Hi ha algun costat que no suma " + str(self.side_sb.value()))

        msg.setWindowTitle("Mensaje resultante")
        msg.setStandardButtons(QMessageBox.Ok | QMessageBox.Cancel)

        msg.exec_()


# Main program
app = QApplication(sys.argv)
sem = Triangulos()
app.exec_()
