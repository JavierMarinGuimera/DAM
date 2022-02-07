from PyQt5.QtWidgets import QApplication, QMainWindow
from PyQt5.QtCore import Qt
from PyQt5 import uic
import sys


class Encrypter(QMainWindow):
    alfabet = "abcdefghijklmnopqrstuvwxyzñç"

    def __init__(self):
        super().__init__()
        uic.loadUi('Ex10/ui/p10_JMG_cifrador.ui', self)
        self.setWindowTitle('Encriptar - desencriptar')
        self.sb_clau.setValue(11)
        self.sb_clau.setMinimum(1)
        self.sb_clau.setMaximum(len(self.alfabet) - 1)
        self.bt_xifrar.clicked.connect(self.codificar)
        self.bt_desxifrar.clicked.connect(self.codificar)
        self.show()

    def keyPressEvent(self, e):
        if e.key() == Qt.Key_Escape:
            self.close()

    def codificar(self):
        buttonPressed = self.sender().objectName()
        clau = self.sb_clau.value()

        # TERNARY IF TO SELECT THE "xifrar" OR "desxifrar" TEXT:
        txt = self.te_desxifrat.toPlainText() if (
            buttonPressed == "bt_xifrar") else self.te_xifrat.toPlainText()

        if clau > 0 and clau <= len(self.alfabet) - 1:
            # CHECK IF THE PRESSED BUTTON IS TO "desxifrar", THEN, WE INVERT THE KEY:
            if (buttonPressed != "bt_xifrar"):
                clau = len(self.alfabet) - clau

            codifiedAlphabet = ""
            finalText = ""

            # THIS FOR MOUNTS THE CODIFIED ALPHAFET TO CODIFY THE TEXTS:
            for letraAlfabet in self.alfabet:
                if clau == len(self.alfabet):
                    clau = 0
                codifiedAlphabet += self.alfabet[clau]
                clau += 1

            # THIS FOR MOUNTS THE TEXT CODIFIED:
            for letraTXT in txt:
                isMayus = False

                # IF THE CURRENT CHAR IS UPPERCASE, WE NEED TO MAKE THE OUTPUT CHAR UPPERCASE TOO
                if letraTXT.isupper():
                    isMayus = True

                if letraTXT.lower() in codifiedAlphabet:
                    finalText += codifiedAlphabet[self.alfabet.index(letraTXT.lower())].upper(
                    ) if (isMayus == True) else codifiedAlphabet[self.alfabet.index(letraTXT)]
                else:
                    finalText += letraTXT

            if (self.cb_majuscules.isChecked()):
                finalText = finalText.upper()

            if (buttonPressed == "bt_xifrar"):
                self.te_xifrat.setPlainText(finalText)
            else:
                self.te_desxifrat.setPlainText(finalText)
        else:
            if (buttonPressed == "bt_xifrar"):
                self.te_xifrat.setPlainText("Clave errónea!")
            else:
                self.te_desxifrat.setPlainText("Clave errónea!")


app = QApplication(sys.argv)
win = Encrypter()
app.exec_()
