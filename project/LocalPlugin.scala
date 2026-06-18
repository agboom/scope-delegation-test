import sbt.*

object LocalPlugin extends AutoPlugin {

  object autoImport {
    lazy val testSetting = settingKey[Int]("")
    lazy val testTask = taskKey[String]("")
    lazy val testInputTask = inputKey[String]("")
  }

  import autoImport.*

  override def trigger: PluginTrigger = allRequirements

  override lazy val globalSettings = Def.settings(
    testTask / testSetting := 1,

    // works in 1.12.12, not in 2.0.0
    testTask / testInputTask := {
      (testTask / testSetting).value.toString
    },

    // works in 1.12.12 and 2.0.0
    testTask := (testTask / testSetting).value.toString,
  )
}
