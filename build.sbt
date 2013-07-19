name := "slick-transactional"

version := "0.0.1-EXPERIMENTAL"

scalaVersion := "2.10.1"

organizationName := "WiredThing"

organization := "com.wiredthing"

libraryDependencies += "com.typesafe.slick" %% "slick" % "1.0.0"

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.10.1"

shellPrompt <<= (thisProjectRef, version) { (id, v) =>
	object devnull extends ProcessLogger {
    def info(s: => String) {}
    def error(s: => String) {}
    def buffer[T](f: => T): T = f
  }
	def git = ("git status -sb" lines_! devnull headOption) getOrElse "-" stripPrefix "## "
  _ => "%s:%s:%s> ".format(id.project, git, v)
}


