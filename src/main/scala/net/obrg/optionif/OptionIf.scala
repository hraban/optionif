package net.obrg.optionif

import scala.tools.nsc.{Global, Phase}
import scala.tools.nsc.plugins.{Plugin, PluginComponent}

class OptionIf(val global: Global) extends Plugin {
  import global._

  override val name = "optionif"
  override val description = "flattens map operations on options to if statements"
  override val components = List[PluginComponent](Component)
  
  private object Component extends PluginComponent {
    override val global: OptionIf.this.global.type = OptionIf.this.global
    // I'm not 100% on these constraints, but afaik these are appropriate outer bounds.
    // The typer phase is, obviously, necessary to determine which objects are of type Option, to begin with.
    override val runsAfter = List("typer")
    // The refchecks phase seems to do some if-related optimizations ("Eliminate branches in a conditional if the
    // condition is a constant"). So put it before this, at least.
    override val runsBefore = List("refchecks")
    override val phaseName = OptionIf.this.name
    override def newPhase(_prev: Phase) = new OptionIfPhase(_prev)

    class OptionIfPhase(prev: Phase) extends StdPhase(prev) {
      override def name = OptionIf.this.name
      override def apply(unit: CompilationUnit) {
        for ( tree @ Apply(Select(rcvr, nme.DIV), List(Literal(Constant(0)))) <- unit.body;
             if rcvr.tpe <:< definitions.IntClass.tpe)
          {
            reporter.error(tree.pos, "definitely division by zero")
          }
      }
    }
  }
}
