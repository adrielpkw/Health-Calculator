package org.PehKimWei.HealthCalculator.view

import org.PehKimWei.HealthCalculator.MainApp
import org.PehKimWei.HealthCalculator.model.LBMCalc
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Button, Label, TextField}
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.layout.GridPane
import scalafxml.core.macros.sfxml
@sfxml
class FemaleLeanBodyMassPageController(private val weightInput: TextField, private val heightInput: TextField, private val lbmResultLabel:Label, private val fpResultLabel:Label, private val listOfCategory: GridPane, private val viewBtn: Button) extends ErrorHandler with ButtonHandler
{

  var calc = new LBMCalc()

  override def notifyAlert(): Unit = {
    val alert = new Alert(AlertType.Warning){
      initOwner(MainApp.stage)
      title = "Empty input"
      headerText = "All input must be filled!"
      contentText = "Please input your height and weight!"
    }.showAndWait()
  }

  override def valueError(): Unit = {
    val error = new Alert(AlertType.Warning) {
      initOwner(MainApp.stage)
      title = "Error value"
      headerText = "Negative numbers detected!"
      contentText = "Please input your values more than 0!"
    }.showAndWait()

  }



  override def handleNavigationBtn(): Unit = {
    MainApp.showMenuPage()
  }

  override def handleToGenderVer(): Unit = {

    MainApp.showMaleLBMPage()

  }


  override def checkInput() : Boolean = {

    if (weightInput.text().length == 0||heightInput.text().length == 0)
      notifyAlert()

    else {
      try {

        weightInput.text().toFloat
        heightInput.text().toFloat
      } catch {
        case e: NumberFormatException =>
          errorMessage = "Not valid (must be an number)!\n"
      }
    }
    if (errorMessage.length == 0) {
      return true;
    } else {
      // Show the error message.
      val inputError = new Alert(Alert.AlertType.Error) {
        initOwner(MainApp.stage)
        title = "Error"
        headerText = "Could not calculate"
        contentText = errorMessage
      }.showAndWait()
      errorMessage = ""
      return false
    }
  }


  def calculate(): Unit ={
    if(weightInput.text().toFloat < 0||heightInput.text().toFloat < 0) {
      valueError()

    }else{
      calc.weight = weightInput.text().toFloat
      calc.height = heightInput.text().toFloat
      calc.calculateFemaleLBM()
      lbmResultLabel.text=(calc.lbm.toString + "kg")
      calc.calculateFemaleFatPercent()
      fpResultLabel.text=(calc.fatPercent + "%")

    }

  }


  override def handleCalculateBtn(): Unit ={
    checkInput()
    calculate()
  }



  def goGender(e: KeyEvent): Unit = {
    if (e.code == KeyCode.G) {
      handleToGenderVer()
    }
  }
}
