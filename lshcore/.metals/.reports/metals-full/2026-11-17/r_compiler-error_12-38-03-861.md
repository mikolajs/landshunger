error id: 9BCE99B0612CE4EAF852D9F06F77A287
file://<WORKSPACE>/src/main/scala/eu/brosbit/lshcore/immovable/Clover.scala
### java.lang.NullPointerException: Cannot invoke "dotty.tools.dotc.parsing.Scanners$Region.commasExpectedInEnclosing()" because the return value of "dotty.tools.dotc.parsing.Scanners$Indented.outer()" is null

occurred in the presentation compiler.



action parameters:
uri: file://<WORKSPACE>/src/main/scala/eu/brosbit/lshcore/immovable/Clover.scala
text:
```scala
package eu.brosbit.lshcore.immovable

import eu.brosbit.lshcore.items.ItemList
import eu.brosbit.lshcore.movable.Peasants

class Clover extends Plant:
  override val obj = Clover

  override def harvest(items: ItemList, work: Int): (Int, Int) =
    var workTime = work
    val mx = bio * 2
    if workTime > mx then
      items.forage += bio
      bio = 0
      workTime -= mx
      bio, 
    else
      val bioMax = workTime / 2
      bio -= bioMax
      workTime -= bioMax*2
      items.forage += bioMax
      bioMax
   


object Clover extends PlantConst:
  def apply(): Clover = new Clover()
  override val name: String = "clover"
  override val shortName: String = "C"
  override val symbol: String = "-"
  override val maxBio: Int = 850
  override val growBio: Int = 30 
  override val maxFood: Int = 0;
  override val growFood: Int = 0;
  override val growHP: Int = 0
  override val maxHP: Int = 1


```


presentation compiler configuration:
Scala version: 3.3.8-bin-nonbootstrapped
Classpath:
<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.8/scala3-library_3-3.3.8.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.18/scala-library-2.13.18.jar [exists ]
Options:





#### Error stacktrace:

```
dotty.tools.dotc.parsing.Scanners$Region.commasExpectedInEnclosing(Scanners.scala:1655)
	dotty.tools.dotc.parsing.Scanners$Region.commasExpectedInEnclosing(Scanners.scala:1655)
	dotty.tools.dotc.parsing.Scanners$Region.commasExpectedInEnclosing(Scanners.scala:1655)
	dotty.tools.dotc.parsing.Scanners$Scanner.observeOutdented(Scanners.scala:720)
	dotty.tools.dotc.parsing.Parsers$Parser.statSepOrEnd(Parsers.scala:384)
	dotty.tools.dotc.parsing.Parsers$Parser.blockStatSeq$$anonfun$1(Parsers.scala:4471)
	dotty.tools.dotc.parsing.Parsers$Parser.checkNoEscapingPlaceholders(Parsers.scala:535)
	dotty.tools.dotc.parsing.Parsers$Parser.blockStatSeq(Parsers.scala:4474)
	dotty.tools.dotc.parsing.Parsers$Parser.block(Parsers.scala:2777)
	dotty.tools.dotc.parsing.Parsers$Parser.blockExpr$$anonfun$1(Parsers.scala:2769)
	dotty.tools.dotc.parsing.Parsers$Parser.enclosed(Parsers.scala:591)
	dotty.tools.dotc.parsing.Parsers$Parser.inBracesOrIndented(Parsers.scala:621)
	dotty.tools.dotc.parsing.Parsers$Parser.inDefScopeBraces(Parsers.scala:627)
	dotty.tools.dotc.parsing.Parsers$Parser.blockExpr(Parsers.scala:2767)
	dotty.tools.dotc.parsing.Parsers$Parser.simpleExpr(Parsers.scala:2583)
	dotty.tools.dotc.parsing.Parsers$Parser.$init$$$anonfun$10(Parsers.scala:2536)
	dotty.tools.dotc.parsing.Parsers$Parser.postfixExpr(Parsers.scala:2512)
	dotty.tools.dotc.parsing.Parsers$Parser.expr1(Parsers.scala:2353)
	dotty.tools.dotc.parsing.Parsers$Parser.expr(Parsers.scala:2243)
	dotty.tools.dotc.parsing.Parsers$Parser.$init$$$anonfun$9(Parsers.scala:2211)
	dotty.tools.dotc.parsing.Parsers$Parser.subPart(Parsers.scala:683)
	dotty.tools.dotc.parsing.Parsers$Parser.subExpr(Parsers.scala:2213)
	dotty.tools.dotc.parsing.Parsers$Parser.ifExpr(Parsers.scala:2413)
	dotty.tools.dotc.parsing.Parsers$Parser.expr1(Parsers.scala:2257)
	dotty.tools.dotc.parsing.Parsers$Parser.expr(Parsers.scala:2243)
	dotty.tools.dotc.parsing.Parsers$Parser.blockStatSeq$$anonfun$1(Parsers.scala:4458)
	dotty.tools.dotc.parsing.Parsers$Parser.checkNoEscapingPlaceholders(Parsers.scala:535)
	dotty.tools.dotc.parsing.Parsers$Parser.blockStatSeq(Parsers.scala:4474)
	dotty.tools.dotc.parsing.Parsers$Parser.block(Parsers.scala:2777)
	dotty.tools.dotc.parsing.Parsers$Parser.blockExpr$$anonfun$1(Parsers.scala:2769)
	dotty.tools.dotc.parsing.Parsers$Parser.enclosed(Parsers.scala:591)
	dotty.tools.dotc.parsing.Parsers$Parser.inBracesOrIndented(Parsers.scala:621)
	dotty.tools.dotc.parsing.Parsers$Parser.inDefScopeBraces(Parsers.scala:627)
	dotty.tools.dotc.parsing.Parsers$Parser.blockExpr(Parsers.scala:2767)
	dotty.tools.dotc.parsing.Parsers$Parser.simpleExpr(Parsers.scala:2583)
	dotty.tools.dotc.parsing.Parsers$Parser.$init$$$anonfun$10(Parsers.scala:2536)
	dotty.tools.dotc.parsing.Parsers$Parser.postfixExpr(Parsers.scala:2512)
	dotty.tools.dotc.parsing.Parsers$Parser.expr1(Parsers.scala:2353)
	dotty.tools.dotc.parsing.Parsers$Parser.expr(Parsers.scala:2243)
	dotty.tools.dotc.parsing.Parsers$Parser.$init$$$anonfun$9(Parsers.scala:2211)
	dotty.tools.dotc.parsing.Parsers$Parser.subPart(Parsers.scala:683)
	dotty.tools.dotc.parsing.Parsers$Parser.subExpr(Parsers.scala:2213)
	dotty.tools.dotc.parsing.Parsers$Parser.defDefOrDcl(Parsers.scala:3803)
	dotty.tools.dotc.parsing.Parsers$Parser.defOrDcl(Parsers.scala:3682)
	dotty.tools.dotc.parsing.Parsers$Parser.templateStatSeq$$anonfun$1(Parsers.scala:4376)
	dotty.tools.dotc.parsing.Parsers$Parser.checkNoEscapingPlaceholders(Parsers.scala:535)
	dotty.tools.dotc.parsing.Parsers$Parser.templateStatSeq(Parsers.scala:4384)
	dotty.tools.dotc.parsing.Parsers$Parser.$anonfun$42(Parsers.scala:4259)
	dotty.tools.dotc.parsing.Parsers$Parser.enclosed(Parsers.scala:591)
	dotty.tools.dotc.parsing.Parsers$Parser.inBracesOrIndented(Parsers.scala:621)
	dotty.tools.dotc.parsing.Parsers$Parser.inDefScopeBraces(Parsers.scala:627)
	dotty.tools.dotc.parsing.Parsers$Parser.templateBody(Parsers.scala:4259)
	dotty.tools.dotc.parsing.Parsers$Parser.templateBodyOpt(Parsers.scala:4252)
	dotty.tools.dotc.parsing.Parsers$Parser.template(Parsers.scala:4229)
	dotty.tools.dotc.parsing.Parsers$Parser.templateOpt(Parsers.scala:4237)
	dotty.tools.dotc.parsing.Parsers$Parser.classDef(Parsers.scala:3931)
	dotty.tools.dotc.parsing.Parsers$Parser.tmplDef(Parsers.scala:3904)
	dotty.tools.dotc.parsing.Parsers$Parser.defOrDcl(Parsers.scala:3688)
	dotty.tools.dotc.parsing.Parsers$Parser.topStatSeq(Parsers.scala:4316)
	dotty.tools.dotc.parsing.Parsers$Parser.topstats$1(Parsers.scala:4510)
	dotty.tools.dotc.parsing.Parsers$Parser.topstats$1(Parsers.scala:4504)
	dotty.tools.dotc.parsing.Parsers$Parser.compilationUnit$$anonfun$1(Parsers.scala:4515)
	dotty.tools.dotc.parsing.Parsers$Parser.checkNoEscapingPlaceholders(Parsers.scala:535)
	dotty.tools.dotc.parsing.Parsers$Parser.compilationUnit(Parsers.scala:4520)
	dotty.tools.dotc.parsing.Parsers$Parser.parse(Parsers.scala:200)
	dotty.tools.dotc.parsing.Parser.parse$$anonfun$1(ParserPhase.scala:32)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	dotty.tools.dotc.core.Phases$Phase.monitor(Phases.scala:474)
	dotty.tools.dotc.parsing.Parser.parse(ParserPhase.scala:40)
	dotty.tools.dotc.parsing.Parser.$anonfun$2(ParserPhase.scala:52)
	scala.collection.Iterator$$anon$6.hasNext(Iterator.scala:489)
	scala.collection.Iterator$$anon$9.hasNext(Iterator.scala:593)
	scala.collection.immutable.List.prependedAll(List.scala:152)
	scala.collection.immutable.List$.from(List.scala:674)
	scala.collection.immutable.List$.from(List.scala:671)
	scala.collection.IterableOps$WithFilter.map(Iterable.scala:901)
	dotty.tools.dotc.parsing.Parser.runOn(ParserPhase.scala:51)
	dotty.tools.dotc.Run.runPhases$1$$anonfun$1(Run.scala:351)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.ArrayOps$.foreach$extension(ArrayOps.scala:1324)
	dotty.tools.dotc.Run.runPhases$1(Run.scala:344)
	dotty.tools.dotc.Run.compileUnits$$anonfun$1(Run.scala:384)
	dotty.tools.dotc.Run.compileUnits$$anonfun$adapted$1(Run.scala:393)
	dotty.tools.dotc.util.Stats$.maybeMonitored(Stats.scala:69)
	dotty.tools.dotc.Run.compileUnits(Run.scala:393)
	dotty.tools.dotc.Run.compileSources(Run.scala:297)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:180)
	dotty.tools.pc.CachingDriver.run(CachingDriver.scala:56)
	dotty.tools.pc.WithCompilationUnit.<init>(WithCompilationUnit.scala:29)
	dotty.tools.pc.SimpleCollector.<init>(PcCollector.scala:362)
	dotty.tools.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:60)
	dotty.tools.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:60)
	dotty.tools.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:60)
	dotty.tools.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:83)
	dotty.tools.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:175)
	scala.meta.internal.pc.CompilerAccess.withSharedCompiler(CompilerAccess.scala:149)
	scala.meta.internal.pc.CompilerAccess.$anonfun$1(CompilerAccess.scala:93)
	scala.meta.internal.pc.CompilerAccess.onCompilerJobQueue$$anonfun$1(CompilerAccess.scala:210)
	scala.meta.internal.pc.CompilerJobQueue$Job.run(CompilerJobQueue.scala:153)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1090)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:614)
	java.base/java.lang.Thread.run(Thread.java:1474)
```
#### Short summary: 

java.lang.NullPointerException: Cannot invoke "dotty.tools.dotc.parsing.Scanners$Region.commasExpectedInEnclosing()" because the return value of "dotty.tools.dotc.parsing.Scanners$Indented.outer()" is null