import scala.reflect.ClassTag

/**
  * MinMaxQueue implementation challenge
  * * enqueue - O(1)
  * * dequeue - O(1)
  * * min - O(1)
  * * max - O(1)
  * * size - O(n)
  */
object MinMaxQueueChallenge {
  private val SegmentSize = 100

  class MinMaxQueue[A: ClassTag : Ordering] {

    private var segments = Vector(new ArrayQueue[A])
    private var aggStats: StatsLike[A] = EmptyStats()
    private var staleStats = true

    def enqueue(elem: A): Unit = {
      if (segments.last.isFull) {
        segments = segments :+ new ArrayQueue[A]
        staleStats = true
      }
      segments.last.enqueue(elem)
      aggStats = aggStats.combine(Stats(elem, elem))
    }

    def dequeue(): A = {
      require(nonEmpty, "Queue is empty!")
      val value = segments.head.dequeue
      if (segments.head.isEmpty) {
        segments = segments.drop(1)
        staleStats = true
      }
      value
    }

    private def rebuildStats(): Unit =
      if (segments.size < 2)
        aggStats = EmptyStats()
      else
        aggStats = segments.drop(1).dropRight(1).foldLeft(EmptyStats().asInstanceOf[StatsLike[A]]) { (stats, q) =>
          stats.combine(q.stats)
        }

    def min: A = {
      require(nonEmpty)
      if (staleStats) rebuildStats()
      aggStats.combine(segments.head.stats).combine(segments.last.stats).min
    }

    def max: A = {
      require(nonEmpty)
      if (staleStats) rebuildStats()
      aggStats.combine(segments.head.stats).combine(segments.last.stats).max
    }

    def size: Int =
      segments.foldLeft(0) { (acc, queue) => acc + queue.size }

    def isEmpty: Boolean = size == 0

    def nonEmpty: Boolean = !isEmpty
  }

  /*
    Stats
   */

  sealed abstract class StatsLike[A:Ordering] {
    def min: A

    def max: A

    def combine(stats: StatsLike[A]): Stats[A]

    def needToRefresh(elem: A): Boolean
  }

  case class Stats[A:Ordering](min: A, max: A) extends StatsLike[A] {
    def combine(stats: StatsLike[A]): Stats[A] = {
      val ord = implicitly[Ordering[A]]
      copy(min = ord.min(min, stats.min), max = ord.max(max, stats.max))
    }

    override def needToRefresh(elem: A): Boolean = min == elem || max == elem
  }

  case class EmptyStats[A:Ordering]() extends StatsLike[A] {
    override def min: A = throw new IllegalStateException

    override def max: A = throw new IllegalStateException

    override def combine(stats: StatsLike[A]): Stats[A] =
      Stats(stats.min, stats.max)

    override def needToRefresh(elem: A): Boolean = false
  }

  /*
    Queue implementation
   */

  private class ArrayQueue[A: ClassTag:Ordering] {
    private val elems = new Array[A](SegmentSize)
    private var offset = 0
    private var end = 0
    private var _stats: StatsLike[A] = EmptyStats()

    def enqueue(elem: A)(): Unit = {
      require(!isFull, "Queue is full!")
      elems(end) = elem
      end += 1
      _stats = stats.combine(Stats(elem, elem))
    }

    def dequeue(): A = {
      val value = elems(offset)
      offset += 1

      if (stats.needToRefresh(value)) {
        rebuildStats()
      }

      value
    }

    private def rebuildStats(): Unit = {
      var i = offset
      var newStats: StatsLike[A] = EmptyStats[A]()
      while (i < end) {
        val elem = elems(i)
        newStats = newStats.combine(Stats(elem, elem))
        i += 1
      }
      _stats = newStats
    }

    def size: Int = end - offset

    def isEmpty: Boolean = size == 0

    def nonEmpty: Boolean = !isEmpty

    def isFull: Boolean = size == SegmentSize

    def stats: StatsLike[A] = _stats
  }

}

object MainQ extends App {
  val q = new MinMaxQueueChallenge.MinMaxQueue[Int]
  q.enqueue(1)
  q.enqueue(2)
  q.enqueue(3)
  q.enqueue(4)
  q.enqueue(4)

  assert(q.min == 1 && q.max == 4)

  q.dequeue()
  q.dequeue()

  assert(q.min == 3 && q.max == 4)

  println(q)
}