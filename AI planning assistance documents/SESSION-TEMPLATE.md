# Session template

Every session follows this shape. It collects the rules in SCHEDULE.md, TEACHING-METHODS.md and DIAGRAMS.md.

## 0. Check-in
- Weekdays: "any school or work first?"
- After the weekday limit: a check-in about a schoolwork break.

## 1. Recall quiz (no notes, low stakes)
- **Mixed topics:** the last session's **Retest** list plus the standing review items (suppliers…).
- Quick multiple-choice checks, plus **at least one free-text** "explain in your own words" item.
- Anything missed goes back onto the Retest list.

## 2. Brainstorm, then zoom in
- A piece of the mod, then one smaller part of it, which becomes the lesson's subject.

## 3. Lesson: Java example first, then Minecraft, same concept
For new material:
1. **A labelled worked example:** steps named ("STEP 1: build two objects…").
2. **A diagram:** UML per DIAGRAMS.md (object or sequence diagram); a key to the symbols for the first few of each kind.
3. **Predict:** "what will this print?"
4. **Run:** with the **expected output** given.
5. **Investigate:** where a failure point matters, it is **shown** as an example (the error or wrong output), never caused by breaking working code.
6. **Modify:** a **specific** change: file, method, code to type, expected result.
7. **Make:** only once comfortable; otherwise a Parsons step (order the given lines).

Then the **Minecraft example of the same concept**:
- UML for the call flow.
- Only the basic functions a developer calls; Minecraft's large internal systems are named, not taught.

For review of proficient material: less scaffolding.

**Quiz rule:** questions use only variables, classes and objects the developer has seen declared (preferably in their own code); never undeclared names.

**Indicative wording (the developer, session 03):** questions say what fills what, instead of asking for a bare label. E.g. not "what is `this`?" but "when `b.setMaterial(\"titanium\")` runs, which object fills in `this`, and which value fills in `material`?"

**Syntax rules (the developer, session 03):**
- **One new syntax idea per step.**
- **New syntax always gets a plain-Java example first**, then its Minecraft use.
- **Reinforce before stacking:** a concept is practised at least twice before the next one builds on it.
- **Flag new syntax** by name when it first appears.
- Minecraft code is explained **line by line, with diagrams**.

**Desmos analogs (the developer, 2026-11-08):** Claude introduces (or refers to) a concept in Java and gives its **Desmos analog**; the developer **builds it in Desmos** (LaTeX graphing) to see it, using only Desmos features close to code (functions, sliders, piecewise, lists, a ticker; no systems or advanced parametrics). Desmos values then serve as checkable expected outputs. Translation table in REFERENCE.md.

## 4. If stuck: the hint ladder
1. A general nudge.
2. An intermediate step.
3. Teach it outright ("lesson-ify, don't challenge-ify").
4. If it still doesn't land: stop drilling, ground it in the developer's own working code, and move it to the Retest list.

## 5. Explain-back (free text)
- One or two sentences in your own words.

## 6. Feedback
- At most two points, about lines and steps, not praise.

## 7. Reference sheet
- New concepts are added to REFERENCE.md (definition, smallest example, where learned).

## 8. End-of-session log
- **Covered**, **Retest next time**, **Next session starts with**.
- Pushed to `main`.
