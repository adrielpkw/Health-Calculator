package org.PehKimWei.HealthCalculator.view

import org.PehKimWei.HealthCalculator.MainApp
import scalafx.scene.control.Alert

trait ErrorHandler {
  var errorMessage: String = ""

  def notifyAlert(): Unit ={

  }

  def valueError(): Unit ={

  }

  def resultNotStored(): Unit ={

  }

  def checkInput() : Boolean = {
    if (errorMessage.length == 0) {
      return true;
    } else {
      // Show the error message.
      val inputError = new Alert(Alert.AlertType.Error) {
        initOwner(MainApp.stage)
        title = ""
        headerText = ""
        contentText = errorMessage
      }.showAndWait()
      return false;
    }
  }
}
