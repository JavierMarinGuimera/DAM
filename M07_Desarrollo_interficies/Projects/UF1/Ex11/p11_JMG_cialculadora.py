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

        # Some asignments:
        self.le_num1.setMaxLength(12)
        self.le_num1.setAlignment(Qt.AlignRight)
        self.le_num2.setMaxLength(12)
        self.le_num2.setAlignment(Qt.AlignRight)
        self.le_resultat.setAlignment(Qt.AlignRight)

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
            doubleValidator = QDoubleValidator()
            self.le_num1.setValidator(doubleValidator)
            self.le_num2.setValidator(doubleValidator)

    def calc(self):
        text = 0
        if (self.le_num1.text() != '' and self.le_num2.text() != ''):
            if (self.rb_suma.isChecked()):
                if (self.rb_decimal.isChecked()):
                    text = locale.atof(self.le_num1.text()) + \
                        locale.atof(self.le_num2.text())
                else:
                    text = locale.atoi(self.le_num1.text()) + \
                        locale.atoi(self.le_num2.text())
            elif (self.rb_resta.isChecked()):
                if (self.rb_decimal.isChecked()):
                    text = locale.atof(self.le_num1.text()) - \
                        locale.atof(self.le_num2.text())
                else:
                    text = locale.atoi(self.le_num1.text()) - \
                        locale.atoi(self.le_num2.text())

            elif (self.rb_producte.isChecked()):
                if (self.rb_decimal.isChecked()):
                    text = locale.atof(self.le_num1.text()) * \
                        locale.atof(self.le_num2.text())
                else:
                    text = locale.atoi(self.le_num1.text()) * \
                        locale.atoi(self.le_num2.text())
            else:
                if (self.rb_decimal.isChecked()):
                    text = locale.atof(self.le_num1.text()) / \
                        locale.atof(self.le_num2.text())
                else:
                    text = locale.atoi(self.le_num1.text()) / \
                        locale.atoi(self.le_num2.text())

            self.le_resultat.setText(locale.format_string("%.12g", text))


app = QApplication(sys.argv)
calc = Calculator()
app.exec_()
