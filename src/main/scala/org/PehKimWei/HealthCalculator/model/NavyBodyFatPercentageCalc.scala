package org.PehKimWei.HealthCalculator.model

import scala.collection.mutable.ListBuffer
import scala.math.log10

class NavyBodyFatPercentageCalc extends HealthCalculator {
  var neck: Float = 0
  var waist:Float = 0
  var height: Float = 0
  var formula: String = ""
  var hip: Float = 0
  var fatPercent: String = ""
  var fatStore = new ListBuffer[String]()

  def storeFat(): Unit ={
    fatStore += fatPercent
  }

  override def calculateMaleFatPercent(): Unit = {
    formula = BigDecimal((495 / ((1.0324 - (0.19077 * log10(waist - neck))) + (0.15456 * log10(height)))) - 450).setScale(2, BigDecimal.RoundingMode.HALF_UP).toString
    fatPercent  = formula
  }

  override def calculateFemaleFatPercent(): Unit = {
    formula = BigDecimal(((495 / ((1.29579 - (0.35004 * log10((waist + hip) - neck))) + (0.22100 * log10(height)))) - 450)).setScale(2, BigDecimal.RoundingMode.HALF_UP).toString
    fatPercent  = formula
  }


}
