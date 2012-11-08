package org.specs2
package xml

import NodeFunctions._
import Nodex._
import specification.{Group, Grouped}

class NodeFunctionsSpec extends Specification with Grouped { def is =
  "Node functions".title                                                                                                ^
                                                                                                                        p^
  "The matchNode function must return true if"                                                                          ^
    "there is a match on the node label"                                                                                ! g1.e1^
    "and a match on one attribute name"                                                                                 ! g1.e2^
    "and a match on a list of attribute names"                                                                          ! g1.e3^
    "and a match on some attribute names and values"                                                                    ! g1.e4^
    "nodes are not groups"                                                                                              ! g1.e5^
    "with exactMatch = true, it must return true if"                                                                    ^
      "there is a match on the node label"                                                                              ! g2.e1^
      "and a match on all attribute names"                                                                              ! g2.e2^
      "and a match on all attribute names and values"                                                                   ! g2.e3^
                                                                                                                        endp^
  "The equalIgnoreSpace function must"                                                                                  ^bt^
    "return false if 2 nodes are not equal after evaluation"                                                            ^
    { <a>{"a"}</a> must not ==/(<a>{"b"}</a>) }                                                                         ^bt^
    "return true if 2 nodes are equal even with spaces"                                                                 ^
    { <a>{"a"}</a> must ==/(<a>{" a "}</a>) }                                                                           ^bt^
    "return true if 2 nodes are in a Group"                                                                             ^
    { <u>{scala.xml.Group(<a>{"a"}</a>)}</u> must ==/(<u><a>{" a "}</a></u>) }                                          ^
                                                                                                                        end

  "matchNode" - new g1 {
    e1 := <a/>.matchNode(<a/>)
    e2 := <a n="v" n2="v2"/>.matchNode(<a/>, List("n"))
    e3 := <a n="v" n2="v2"/>.matchNode(<a/>, List("n", "n2"))
    e4 := <a n="v" n2="v2"/>.matchNode(<a/>, attributeValues = Map("n" -> "v"))
    e5 := !scala.xml.Group(<a/>).matchNode(scala.xml.Group(<a/>), List())
  }

  "exactMatch" - new g2 {
    e1 := <a/>.matchNode(<a/>, exactMatch = true)
    e2 := <a n="v" n2="v2"/>.matchNode(<a/>, List("n", "n2"), exactMatch = true)
    e3 := <a n="v" n2="v2"/>.matchNode(<a/>, attributeValues = Map("n" -> "v", "n2" -> "v2"), exactMatch = true)
  }
}