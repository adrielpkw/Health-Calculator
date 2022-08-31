package org.PehKimWei.HealthCalculator.model

import scala.collection.mutable.ListBuffer

class BMICalc() extends HealthCalculator {
  var weight: Float = 0
  var height: Float = 0
  var bmi:Float = 0
  var bmiStore = new ListBuffer[String]()


  def storeBMI(): Unit ={
    bmiStore += bmi.toString
  }

  override def calculateBMI(): Unit = {
    bmi = BigDecimal(weight/((height/100)*(height/100))).setScale(2, BigDecimal.RoundingMode.HALF_UP).toFloat
  }

}
