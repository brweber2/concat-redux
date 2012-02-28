The initial work on a concatenative language that runs on the JVM.  It does static type checking, but uses reflection instead of compiling to byte code at the moment.

There is still much work to be done.

Documentation
-------------
* Add documentation.
* Add examples.

Mostly just work at this point
------------------------------

* Make 'vocab' a compile time word.
* Generic lists/iteration.
* Words for viewing the state of the system.
* Add aliases for primitives.
* Add math operator words.
* Add comparison operator words.
* Add logical operator words.
* Add loop controlflow words.
* Add try/catch/finally controlflow words.
* Add Items set/set words.
* Add more stack words.
* Figure out lex/parse bug that sometimes causes the repl to hang (try another jline version?).
* Remove DefineCall (just have DefineTransformer do the static register).

Really need to have
-------------------
* Compilation instead of reflection!

Maybe some day
--------------

* Threads?
* Define classes.
* Word re-definitions (if signature matches or type check again)?
* Macro words?
* Add logic programming words (integrate with logic-redux?).