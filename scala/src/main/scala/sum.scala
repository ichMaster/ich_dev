import scala.io.Source

object Sum {
	def main(args: Array[String]): Unit  = {
		val fl = scala.io.Source.
			fromFile("/home/ich/git/ich_dev/scala/data/data.txt").
			getLines
		
		val res = fl.map(e => e.split(",").toList).
			filter(e => e(0).toFloat > 40.005).
			filter(e => e(1).toFloat > 116.311).
			map(e => e(3).toFloat).
			reduce((t, e) => t + e)
		
		println(res)
	}
}
