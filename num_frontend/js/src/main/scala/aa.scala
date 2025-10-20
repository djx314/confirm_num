package confirm.num.frontend

import scala.scalajs.js.annotation.JSExportTopLevel

import com.yang_bo.html.*
import com.thoughtworks.binding.Binding
import com.thoughtworks.binding.Binding.Var
import org.scalajs.dom.HTMLSpanElement

object BindingApp {
  val color = Var("brown")

  def animal = "dog"

  val span: Binding.Stable[HTMLSpanElement] = {
    html"""<span>The quick ${color.bind} fox jumps&nbsp;over the lazy ${animal}</span>"""
  }

  @JSExportTopLevel("renderAction")
  def renderAction() = {
    import org.scalajs.dom.document
    render(document.getElementById("miao-main"), span)
  }
}
