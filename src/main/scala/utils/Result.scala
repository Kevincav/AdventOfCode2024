package utils

case class Result[A](result: A, time: Double) {
  override def toString: String = s"Result: $result\nTime Elapsed: $time seconds"
}

def Timer[A](blockOfCode: => A) = {
  val startTime = System.nanoTime
  val result = blockOfCode
  val stopTime = System.nanoTime
  val elapsedTime = (stopTime - startTime) / 1e9d
  Result(result, elapsedTime)
}
