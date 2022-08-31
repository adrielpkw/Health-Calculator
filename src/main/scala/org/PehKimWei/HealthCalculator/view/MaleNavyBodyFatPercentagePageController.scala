package org.PehKimWei.HealthCalculator.view

import org.PehKimWei.HealthCalculator.MainApp
import org.PehKimWei.HealthCalculator.model.NavyBodyFatPercentageCalc
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Button, Label, TextField}
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.layout.GridPane
import scalafxml.core.macros.sfxml
@sfxml
class MaleNavyBodyFatPercentagePageController(private val neckInput: TextField, private val waistInput: TextField, private  val viewBtn: Button, private val fpResultLabel:Label, private  val heightInput: TextField, private val categoryLabel: Label, private val listOfCategory: GridPane) extends  ErrorHandler with ButtonHandler {
  viewBtn.disable = true
  listOfCategory.visible = false
  var calc = new NavyBodyFatPercentageCalc()



  // creating alert message when there are empty inputs
  override def notifyAlert(): Unit = {
    val alert = new Alert(AlertType.Warning){
      initOwner(MainApp.stage)
      title = "Empty input"
      headerText = "All input are still empty!"
      contentText = "Please input your neck, waist and height!"
    }.showAndWait()

  }
  // creating alert message when the neck input is bigger than waist input.
  override def valueError(): Unit = {
    val error = new Alert(AlertType.Warning) {
      initOwner(MainApp.stage)
      title = "Error value"
      headerText = "Cannot calculate"
      contentText = "Please input your neck, height and waist value again (not in negative) and ensure neck is NOT MORE THAN YOUR WAIST!"
    }.showAndWait()

  }
  // creating alert message when the fat percentage is not stored into fatStore
  override def resultNotStored(): Unit ={
    val notFound = new Alert(AlertType.Warning) {
      initOwner(MainApp.stage)
      title = "Not found"
      headerText = "Fat Percentage not found"
      contentText = "Please recalculate again"
    }.showAndWait()
  }
  //to handle the button for female version is being pressed
  override def handleNavigationBtn(): Unit = {

      MainApp.showMenuPage()


  }
  //to handle the button for female version is being pressed
  override def handleToGenderVer(): Unit = {

      MainApp.showFemaleNavyBodyFatPercentagePage()

  }
  // to check if input is null or not integer
  override def checkInput() : Boolean = {

    if (neckInput.text().length == 0||waistInput.text().length == 0|| heightInput.text().length == 0)
      notifyAlert()

    else {
      try {
        neckInput.text().toFloat
        waistInput.text().toFloat
        heightInput.text().toFloat
      } catch {
        case e: NumberFormatException =>
          errorMessage = "Not valid (must be an number)!\n"
      }
    }
    if (errorMessage.length == 0) {
      return true
    } else {
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

  //to calculate fat percentage
  def calculate(): Unit ={
    if (neckInput.text().toFloat >= waistInput.text().toFloat||heightInput.text().toFloat < 0||neckInput.text().toFloat < 0 || waistInput.text().toFloat < 0){
      valueError()
    }

    else  {
      viewBtn.disable = false
      calc.neck = neckInput.text().toFloat
       calc.waist= waistInput.text().toFloat
       calc.height = heightInput.text().toFloat
       calc.calculateMaleFatPercent()
       fpResultLabel.text=(calc.fatPercent + "%")
       calc.storeFat()

    }

  }
  // handle event where the view button is clicked by user
  override def handleView():Unit= {
    if (neckInput.text().length == 0 || heightInput.text().length == 0|| waistInput.text().length == 0) {
      notifyAlert()
    }
    else if (calc.fatStore.length == 0){
      resultNotStored()
    }
    else if (calc.fatStore.head.toDouble < 2.0) {
      viewBtn.disable = false
      calc.fatStore.remove(0)
      categoryLabel.text=("Less than essential fat")
      listOfCategory.visible = true
    }
    else if (calc.fatStore.head.toDouble >= 2.0 && calc.fatStore.head.toDouble < 6.0) {
      viewBtn.disable = false
      calc.fatStore.remove(0)
      categoryLabel.text=("Essential fat")
      listOfCategory.visible = true
    }
    else if (calc.fatStore.head.toDouble >= 6.0 && calc.fatStore.head.toDouble < 14.0) {
      viewBtn.disable = false
      calc.fatStore.remove(0)
      categoryLabel.text=("Athletes")
      listOfCategory.visible = true
    }
    else if (calc.fatStore.head.toDouble >= 14.0 && calc.fatStore.head.toDouble < 18.0) {
      viewBtn.disable = false
      calc.fatStore.remove(0)
      categoryLabel.text=("Fitness")
      listOfCategory.visible = true
    }
    else if (calc.fatStore.head.toDouble >= 18.0 && calc.fatStore.head.toDouble < 26.0) {
      viewBtn.disable = false
      calc.fatStore.remove(0)
      categoryLabel.text=("Average")
      listOfCategory.visible = true
    }
    else {
      viewBtn.disable = false
      calc.fatStore.remove(0)
      categoryLabel.text=("Obese")
      listOfCategory.visible = true
    }
  }

  //handle event when user click calculate
  override def handleCalculateBtn(): Unit ={
    checkInput()
    calculate()
  }


  def view(e: KeyEvent): Unit = {
    if(e.code == KeyCode.V){
      handleView()
    }
  }


  def goGender(e: KeyEvent): Unit = {
    if(e.code == KeyCode.G){
      handleToGenderVer()
    }
  }

}
