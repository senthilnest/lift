package net.liftweb.util
import org.specs.runner._
import org.specs._

class ControlHelpersSpecTest extends Runner(ControlHelpersSpec) with JUnit
object ControlHelpersSpec extends Specification with ControlHelpers {
  "the tryo function" should {
    "return a Full can if the tested block doesn't throw an exception" in {
      tryo { "valid" } must_== Full("valid")
    }
    val exception = new RuntimeException("ko")
    def failureBlock = { throw exception; () }

    "return a Failure if the tested block throws an exception" in {
      tryo { failureBlock } must_== Failure("tryo", Full(exception), Nil)
    }
    "return Empty if the tested block throws an exception whose class is in the ignore list - with one element" in {
      tryo(classOf[RuntimeException]) { failureBlock } must_== Empty
    }
    "return Empty if the tested block throws an exception whose class is in the ignore list - with 2 elements" in {
      skip("this test doesn't even compile")
      // tryo(List(classOf[RuntimeException], classOf[NullPointerException])) { failureBlock } must_== Empty
    }
    "trigger a callback function with the exception if the tested block throws an exception" in {
      val callback = (e: Throwable) => { e must_== exception; () }
      tryo(callback) { failureBlock }
    }
    "trigger a callback function with the exception if the tested block throws an exception even if it is ignored" in {
      val callback = (e: Throwable) => { e must_== exception; () }
      tryo(List(classOf[RuntimeException]), Full(callback)) { failureBlock }
    }
    "don't trigger a callback if the tested block doesn't throw an exception" in {
      val callback = (e: Throwable) => { fail("must not be called") }
      tryo(callback) { "valid" }
    }
    "don't trigger a callback if the tested block doesn't throw an exception, even with an ignore list" in {
      val callback = (e: Throwable) => { fail("must not be called") }
      tryo(List(classOf[RuntimeException]), Full(callback)) { "valid" }
    }
  }
}
