package org.PehKimWei.HealthCalculator.view

import org.PehKimWei.HealthCalculator.MainApp
import scalafxml.core.macros.sfxml
@sfxml
class MenuPageController extends ButtonHandler {
  override def handleBMIBtn(): Unit = {
    MainApp.showBMIPage()
  }
  override def handleMaleNavyBodyFatPercentageBtn(): Unit = {
    MainApp.showMaleNavyBodyFatPercentagePage()
  }
  override def handleFemaleNavyBodyFatPercentageBtn(): Unit = {
    MainApp.showFemaleNavyBodyFatPercentagePage()
  }

  override def handleMaleLBMBtn(): Unit = {
    MainApp.showMaleLBMPage()
  }
  override def handleFemaleLBMBtn(): Unit = {
    MainApp.showFemaleLBMPage()
  }
}
