/*
 * Copyright 2007-2010 WorldWide Conferencing, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sandbox.lift {
package hellodarwin {
package snippet{

import _root_.scala.xml.NodeSeq
import _root_.net.liftweb.http.S._
import _root_.net.liftweb.http.SHtml._
import _root_.net.liftweb.http.RequestVar
import _root_.net.liftweb.util.Helpers._
import _root_.net.liftweb.common.Full

class HelloForm3 {
  object who extends RequestVar(Full("world"))

  def show(xhtml: NodeSeq): NodeSeq = {
    <xml:group>
      Hello {who.openOr("")}
      <br/>
      <label for="whoField">Who :</label>
      { text(who.openOr(""), v => who(Full(v))) % ("size" -> "10") % ("id" -> "whoField") }
      { submit(?("Send"), () => println("value:" + who.openOr(""))) }
    </xml:group>
  }
}
}
}
}

