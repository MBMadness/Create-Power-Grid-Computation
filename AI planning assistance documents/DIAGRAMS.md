# Diagram standards

Teaching diagrams follow UML notation and readability guidelines. They're drawn with Mermaid (text in, rectangles and text out; GitHub draws it in these docs).

## UML notation used
| Diagram | Use it for | Notation |
|---|---|---|
| **Object diagram** | What exists in memory at one moment | A box per object: **underlined `name : Type`**, a line, then slots `attribute = value`. No methods compartment. (UML 2.5 InstanceSpecification) |
| **Sequence diagram** | Who calls whom, in what order | Lifelines across the top, labelled `name : Type`. **Solid line, filled arrowhead** = a call (synchronous message). **Dashed line** = a return, labelled with what comes back. **Activation bar** = while that object is working. Time runs downwards. |
| **Class diagram** | How classes relate (later) | Class boxes; a hollow triangle for `extends`; a dashed line with a hollow triangle for `implements`. |

## Readability rules
After Ambler, *The Elements of UML 2.0 Style*:
- **One idea per diagram.** Split the object view and the call view rather than mixing them.
- **Top-left start, left-to-right / top-to-bottom flow.**
- **Straight horizontal and vertical lines only**; avoid crossings (hop one line over the other if a crossing can't be avoided).
- **At most about 4 lifelines or 5 boxes.** Leave out anything not needed for the point.
- **Short labels:** method names and values, not full code lines.
- **Notes** only for the one key fact (e.g. `this = a`).
- **Phases** (shaded bands) when something happens at two different times, as in registration.
- **Large text:** about 18–20 px Arial, black on white.
- **Lines are solid, and hidden only where they cross text:** each label sits on its own background, so a line disappears behind the words and nowhere else (the developer, session 02). No blanket dotting.
- **Objects look like objects everywhere:** a lifeline for an object starts from a UML object box (an underlined `name : Type`, a divider, then its slots), the same notation as the object diagram (the developer, session 02).
- **Lifelines end just below the last message.**

## Explaining the notation
The **first few times** a kind of diagram appears in a lesson, it comes with a short key to its symbols (e.g. "solid arrow = a call; dashed arrow = what comes back; grey bar = the object is busy; underlined `a : Accelerometer` = an object named a, of class Accelerometer"). Once the developer reads that kind of diagram comfortably, the key is dropped.

## How they're made
Mermaid renders the diagram; a small post-processing step then turns object lifelines into object boxes, makes lines solid, puts a background behind each label, and trims the lifelines. This is a docs tool kept in Claude's scratchpad, not part of the mod. Diagrams embedded in these docs are plain Mermaid (GitHub's own rendering); the polished images are sent in chat.

## Sources
- [uml-diagrams.org: UML object](https://uml-diagrams.org/object.html) · [Wikipedia: Object diagram](https://en.wikipedia.org/wiki/Object_diagram) · [UML spec excerpt: InstanceSpecification](https://site.uottawa.ca/~tcl/gradtheses/mnojoumian/ThesisFiles/FinalSpec/UML/7.3.22.html)
- [RIT: sequence diagrams (PDF)](https://www.se.rit.edu/~swen-261/slides/Sequence%20Diagrams.pdf) · [Visual Paradigm: lifelines and messages](https://skills.visual-paradigm.com/?p=1691)
- [Ambler, The Elements of UML 2.0 Style: general diagramming guidelines](https://www.cambridge.org/core/books/elements-of-uml-20-style/general-diagramming-guidelines/9C2553636C85C84B03ABCB9311158553)
