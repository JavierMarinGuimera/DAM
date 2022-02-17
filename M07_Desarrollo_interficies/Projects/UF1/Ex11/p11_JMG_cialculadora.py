from PyQt5.QtWidgets import QApplication, QMainWindow
from PyQt5.QtCore import Qt
from PyQt5.QtGui import QIntValidator, QDoubleValidator
from PyQt5 import uic
import sys
import locale


class Calculator(QMainWindow):
    operationType = 1
    operation = 1

    def __init__(self):
        # DEFAULT TASKS:
        super().__init__()
        uic.loadUi('ui/p11_JMG_calculadora.ui', self)
        locale.setlocale(locale.LC_ALL, "es_ES")

        # CUSTOM TASKS:
        self.setWindowTitle('Calculadora')
        self.rb_decimal.setChecked(True)
        self.rb_suma.setChecked(True)
        self.getType()
        # self.getOperation()

        # - OnClick METHODS:
        self.rb_decimal.clicked.connect(self.getType)
        self.rb_enter.clicked.connect(self.getType)
        self.bt_calcular.clicked.connect(self.calc)

        # SHOW UI
        self.show()

    def keyPressEvent(self, e):
        if e.key() == Qt.Key_Escape:
            self.close()

    def getType(self):
        operationType = 1 if (self.rb_decimal.isChecked()) else 2

        if (operationType == 2):
            print("Entero")
            intValidator = QIntValidator()

            self.le_num1.setValidator(intValidator)

            num1 = self.le_num1.text()
            if (num1 != ''):
                self.le_num1.setText(str(round(locale.atof(num1))))

            self.le_num2.setValidator(intValidator)
            num2 = self.le_num2.text()
            if (num2 != ''):
                self.le_num2.setText(str(round(locale.atof(num2))))
        else:
            print("Decimal")
            doubleValidator = QDoubleValidator()
            self.le_num1.setValidator(doubleValidator)
            self.le_num2.setValidator(doubleValidator)

    def calc(self):
        if (self.le_num1.text() != '' and self.le_num2.text() != ''):
            if (self.rb_suma.isChecked()):
                self.le_resultat.setText(
                    str(locale.atof(self.le_num1.text()) + locale.atof(self.le_num2.text())))
            elif (self.rb_resta.isChecked()):
                self.le_resultat.setText(
                    str(locale.atof(self.le_num1.text()) - locale.atof(self.le_num2.text())))
            elif (self.rb_producte.isChecked()):
                self.le_resultat.setText(
                    str(locale.atof(self.le_num1.text()) * locale.atof(self.le_num2.text())))
            else:
                self.le_resultat.setText(
                    str(locale.atof(self.le_num1.text()) / locale.atof(self.le_num2.text())))


app = QApplication(sys.argv)
calc = Calculator()
app.exec_()
