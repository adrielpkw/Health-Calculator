package org.PehKimWei.HealthCalculator.view

import org.PehKimWei.HealthCalculator.MainApp
import scalafxml.core.macros.sfxml
@sfxml
class WelcomePageController()extends ButtonHandler {
  override def handleNavigationBtn(): Unit = {
    MainApp.showMenuPage()
  }

}