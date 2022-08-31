package org.PehKimWei.HealthCalculator

import javafx.{scene => jfxs}
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.image.Image
import scalafxml.core.{FXMLLoader, NoDependencyResolver}


object MainApp extends JFXApp{

  val rootResource = getClass.getResourceAsStream("view/RootLayout.fxml")

  val loader = new FXMLLoader(null, NoDependencyResolver)

  loader.load(rootResource);

  val roots = loader.getRoot[jfxs.layout.BorderPane]()

  stage = new PrimaryStage {
    resizable = false
    title = "Health Calculator"
    scene = new Scene {
      root = roots
      stylesheets += getClass.getResource("view/HealthCalculator.css").toString
    }
    icons += new Image(getClass.getResourceAsStream("images/healthcalculator.png"))
  }
  def showMenuPage(): Unit = {
    val resource = getClass.getResourceAsStream("view/MenuPage.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    MainApp.roots.setCenter(roots)
  }

  def showBMIPage(): Unit = {
    val resource = getClass.getResourceAsStream("view/BMIPage.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    MainApp.roots.setCenter(roots)
  }

  def showMaleNavyBodyFatPercentagePage(): Unit = {
    val resource = getClass.getResourceAsStream("view/MaleNavyBodyFatPercentagePage.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    MainApp.roots.setCenter(roots)
  }

  def showFemaleNavyBodyFatPercentagePage(): Unit = {
    val resource = getClass.getResourceAsStream("view/FemaleNavyBodyFatPercentagePage.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    MainApp.roots.setCenter(roots)
  }

  def showFemaleLBMPage(): Unit = {
    val resource = getClass.getResourceAsStream("view/FemaleLeanBodyMass.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    MainApp.roots.setCenter(roots)
  }

  def showMaleLBMPage(): Unit = {
    val resource = getClass.getResourceAsStream("view/MaleLeanBodyMass.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    MainApp.roots.setCenter(roots)
  }


  def showWelcomePage(): Unit = {
    val resource = getClass.getResourceAsStream("view/WelcomePage.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    MainApp.roots.setCenter(roots)
  }
  showWelcomePage()
}
