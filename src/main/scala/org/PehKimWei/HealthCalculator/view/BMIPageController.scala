package org.PehKimWei.HealthCalculator.view

import org.PehKimWei.HealthCalculator.MainApp
import org.PehKimWei.HealthCalculator.model.BMICalc
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Button, Label, TextField}
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.layout.GridPane
import scalafxml.core.macros.sfxml
@sfxml
class BMIPageController(private val weightInput: TextField, private val heightInput: TextField, private val bmiResultLabel:Label,private val categoryLabel:Label, private val listOfCategory: GridPane, private val viewBtn: Button) extends ErrorHandler with ButtonHandler
{
  viewBtn.disable = true
  listOfCategory.visible = false
  var calc = new BMICalc()

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

  override def resultNotStored(): Unit ={
    val noBMIFound = new Alert(AlertType.Warning) {
      initOwner(MainApp.stage)
      title = "Not found"
      headerText = "BMI not found"
      contentText = "Please recalculate again"
    }.showAndWait()
  }

  override def handleNavigationBtn(): Unit = {
    MainApp.showMenuPage()
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
      calc.calculateBMI()
      bmiResultLabel.text=(calc.bmi.toString)
      calc.storeBMI()

    }

  }


  override def handleView():Unit= {
    if (weightInput.text().length == 0||heightInput.text().length == 0){
      notifyAlert()
    }
    else if (calc.bmiStore.length == 0){
      resultNotStored()
    }
      else if(calc.bmiStore.head.toDouble < 16.0){
      viewBtn.disable = false
      calc.bmiStore.remove(0)
      categoryLabel.text=("Severe Thinness")
      listOfCategory.visible = true
      }
      else if(calc.bmiStore.head.toDouble > 16.0 && calc.bmiStore.head.toDouble <= 17.0){
      viewBtn.disable = false
      calc.bmiStore.remove(0)
        categoryLabel.text=("Moderate Thinness")
        listOfCategory.visible = true
      }
      else if(calc.bmiStore.head.toDouble > 17.0 && calc.bmiStore.head.toDouble <= 18.5){
      viewBtn.disable = false
      calc.bmiStore.remove(0)
        categoryLabel.text=("Mild Thinness")
        listOfCategory.visible = true
      }
      else if(calc.bmiStore.head.toDouble > 18.5 && calc.bmiStore.head.toDouble <= 25.0){
      viewBtn.disable = false
      calc.bmiStore.remove(0)
        categoryLabel.text=("Normal")
        listOfCategory.visible = true
      }
      else if(calc.bmiStore.head.toDouble > 25.0 && calc.bmiStore.head.toDouble <= 30.0){
      viewBtn.disable = false
      calc.bmiStore.remove(0)
        categoryLabel.text=("Overweight")
        listOfCategory.visible = true
      }
      else if(calc.bmiStore.head.toDouble > 30.0 && calc.bmiStore.head.toDouble <= 35.0){
      viewBtn.disable = false
      calc.bmiStore.remove(0)
        categoryLabel.text=("Obese Class I")
        listOfCategory.visible = true
      }
      else if(calc.bmiStore.head.toDouble > 35.0 && calc.bmiStore.head.toDouble <= 40.0){
      viewBtn.disable = false
      calc.bmiStore.remove(0)
        categoryLabel.text=("Obese Class II")
        listOfCategory.visible = true
      }
      else {
      viewBtn.disable = false
      calc.bmiStore.remove(0)
        categoryLabel.text=("Obese Class III")
        listOfCategory.visible = true
      }

  }

  override def handleCalculateBtn(): Unit ={
    checkInput()
    calculate()
    viewBtn.disable = false
    }



  def view(e: KeyEvent): Unit = {
    if(e.code == KeyCode.V){
      handleView()
    }
  }


}
