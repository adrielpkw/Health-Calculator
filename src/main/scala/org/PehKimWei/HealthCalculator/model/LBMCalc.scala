package org.PehKimWei.HealthCalculator.model

class LBMCalc() extends HealthCalculator {
  var weight: Float = 0
  var height: Float = 0
  var formula: String = ""
  var lbm:Float = 0
  var fatPercent: String = ""


  override def calculateMaleLBM(): Unit = {
    formula = BigDecimal((0.407*weight)+(0.267*height)- 19.2).setScale(1, BigDecimal.RoundingMode.UP).toString
    lbm = formula.toFloat
  }

  override def calculateMaleFatPercent(): Unit = {
    fatPercent = BigDecimal(100-((lbm/weight)*100)).setScale(1, BigDecimal.RoundingMode.UP).toString
  }

  override def calculateFemaleLBM(): Unit = {
    formula = BigDecimal((0.252*weight)+(0.473*height)- 48.3).setScale(1, BigDecimal.RoundingMode.UP).toString
    lbm = formula.toFloat
  }

  override def calculateFemaleFatPercent(): Unit = {
    fatPercent = BigDecimal(100-((lbm/weight)*100)).setScale(1, BigDecimal.RoundingMode.UP).toString
  }
}
