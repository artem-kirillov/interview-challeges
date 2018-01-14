import scala.collection.mutable

/**
  * https://leetcode.com/problems/lru-cache
  */
class LRUCache(_capacity: Int) {

  import LRUCache._

  private val cache = mutable.Map.empty[Int, Node[(Int, Int)]]
  private val lruSeq = new LinkedList[(Int, Int)]

  def get(key: Int): Int =
    cache.get(key) match {
      case Some(node) =>
        lruSeq.moveToHead(node)
        node.value._2
      case None => -1
    }

  def put(key: Int, value: Int) {
    cache.get(key) match {
      case Some(node) =>
        node.value = key -> value
        lruSeq.moveToHead(node)
        cache.put(key, node)
      case None =>
        val node = new Node(key -> value)
        lruSeq.prepend(node)
        cache.put(key, node)
    }

    if (cache.size > _capacity) {
      cache.remove(lruSeq.tail.value._1)
      lruSeq.dropTail()
    }
  }
}

object LRUCache {

  class Node[A](var value: A, var prev: Node[A] = null, var next: Node[A] = null)

  class LinkedList[A] {
    var head: Node[A] = _
    var tail: Node[A] = _

    def prepend(node: Node[A]): Unit = {
      if (head != null) {
        node.next = head
        head.prev = node
      } else {
        tail = node
      }
      head = node
    }

    def dropTail(): Unit =
      if (head eq tail) {
        head = null
        tail = null
      } else {
        val oldTail = tail
        tail = tail.prev
        tail.next = null
        oldTail.prev = null
      }

    def moveToHead(node: Node[A]): Unit =
      if (node eq head) {
        // already head
      } else {
        if (node.next != null) {
          node.prev.next = node.next
          node.next.prev = node.prev
        } else {
          tail = node.prev
          tail.next = null
        }
        node.next = head
        head.prev = node
        node.prev = null
        head = node
      }
  }

}

/**
  * Your LRUCache object will be instantiated and called as such:
  * var obj = new LRUCache(capacity)
  * var param_1 = obj.get(key)
  * obj.put(key,value)
  */


object LruCacheMain extends App {
  var obj = new LRUCache(2)
  obj.put(2, 1)
  obj.get(2)
}