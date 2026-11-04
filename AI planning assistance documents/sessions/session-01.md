# Session 01 (2026-11-03)

**The plan** (kept in the log; not announced to the developer up front):
1. **Brainstorm:** what a "part" is. What every part shares, and what makes a turbine fan different from a sensor.
2. **Zoom in:** the rotational accelerometer, which reads rotation and outputs a voltage.
3. **Lesson:** inheritance and interfaces, in plain Java (no NeoForge yet).
   - Concept: `extends`, overriding, `super`; an interface for "outputs a voltage"; polymorphism (the Animal / Cat question).
   - Worked example, then an exercise the developer writes: a `Part` base class, a fan and an accelerometer that extend it, and an interface they implement.
   - The developer explains it back.
4. **Retest later:** `static`, lambdas, `null` / NullPointerException (Days 3–5 of the first-week plan).

**Rules:** it's a weekday, so ask first whether other work comes first; hints only when asked.

## Notes during the session
- No school today. After the weekday time limit, Claude checks in about a schoolwork break.
- **Brainstorm result (developer):**
  - ALL parts have: an interface (no part is self-reliant: a disposable battery outputs, a wind-up toy has an input), a voxel shape / size, a material, connection points, a failure chance, **mass** (the developer caught that mass is universal).
  - SOME parts have: rotation (RPM / torque), a voltage output, fluid inside (defined by the part's mission).
- **Where abilities live (developer):** each ability (rotation, voltage, fluid…) follows its Create / addon analog: the simplest, most efficient interface is called, and addons that change behaviour (sub-modifiers) are recognised. -> In design terms, abilities are opt-in, like Create's own kinetic interfaces. This leads into the interfaces lesson.
- **Zoom: the rotational accelerometer (developer):** a small part with mass that meshes with existing rotational parts. It takes voltage in to power a laser, reads the laser's data, and outputs it as serial / digital data depending on quality and choice. NOTE: physically a laser reading a turning shaft is an optical encoder / tachometer, and acceleration is derived from it; the naming is for a later brainstorm.
- Lesson started: inheritance (concept + worked example).
- Worked example: `Part` base class + `Battery extends Part` (`super(...)`, `@Override`, `super.describe()`).
- Retrieval: polymorphism prediction correct. Constructor chaining (implicit `super()`) unknown -> explained (the wording "empty constructor" needed defining first).
- Exercise set: `Accelerometer extends Part` (supply voltage, serial / digital output, own `describe()`), held in a `Part` variable; explain back why it works. Next: interfaces.
- The exercise needed more support (recall of Java syntax is lower than expected). Re-issued as a faded worked example: IntelliJ project setup, a table explaining field / constructor / `this.` / method, an `Accelerometer` skeleton with blanks (`extends`, `super`, assignments), a `main` with blanks, the expected output, and the explain-back. Teaching note: start lessons with more scaffolding and fade it.
- Exercise 1 done (developer modified it: extra fields / values, own describe() text; code not shared).
- Explain-back: "Accelerometer's describe overwrites Part's, with @Override". Refined: overrides (not overwrites; `super` still reaches Part's), and the object's real type decides, not the annotation (`@Override` is only a compiler check).
- Lesson part 2: interfaces (`VoltageOutput`; extends = is-a, one parent; implements = can-do, many; a `readMeter(VoltageOutput)` method as the payoff; tied to Create's kinetic interface). Exercise 2: Accelerometer implements `VoltageOutput` + `RotationInput` (own interface), output depends on RPM; hint given about the variable's declared type. Explain-back pending: why not two `extends`.
- Question from the developer (lesson paused): how to turn the voltmeter into an output. Answered at three levels: (1) return a value instead of printing (calculate in one place, display in another); (2) a `Voltmeter extends Part implements VoltageOutput` holding a `VoltageOutput source`, so parts chain (turbine -> accelerometer -> meter); (3) in the game, the last link hands the value to Power Grid as a real terminal voltage (a later NeoForge lesson). Exercise 2 resumes afterwards.
- Clarified: the developer's `readMeter` "did nothing, no run prompt". Cause: a method only runs when called, and a program starts only at `public static void main(String[] args)` (IntelliJ's run button appears only there). Taught: methods must sit inside a class; `readMeter` goes in `Main` beside `main`; it needs an object that `implements VoltageOutput`; call it from `main`. Teaching note: the entry point / calling a method was not yet secure; include it in the next recall quiz.

## Transcript: the voltmeter output questions

**Developer:** Question, how would I take the voltmeter system we have at the moment, and generate an output? Don't continue the lesson yet.

**Claude:** It depends what you mean by "output". There are three levels, and each builds on the one before.

1. **Give a value back instead of printing it.** At the moment `readMeter` prints. That's a dead end, because the value goes to the console and no other code can use it. Change it to return the value, and the calling code decides what happens to it:
   ```java
   static double readMeter(VoltageOutput source) {   // void -> double
       return source.outputVolts();
   }
   ```
   ```java
   double reading = readMeter(battery);
   System.out.println("Meter reads " + reading + " V");   // printing is now just one use
   ```
   The value can now be printed, compared (`if (reading > 12)`), or passed on. Calculate in one place, display in another: the "complex data, simple output" idea from the physics brainstorm, in miniature.
2. **Make the meter a part that outputs too.** In the mod a meter is a part wired to a source, so it can be a `Part` that holds a reference to its source and is itself a `VoltageOutput`:
   ```java
   public class Voltmeter extends Part implements VoltageOutput {
       private VoltageOutput source;          // "what it's wired to"

       public Voltmeter(String material, double massKg, double failureChance,
                        VoltageOutput source) {
           super(material, massKg, failureChance);
           this.source = source;
       }

       @Override
       public double outputVolts() {
           return source.outputVolts();      // reads its input, passes it on
       }
   }
   ```
   Parts can now chain: turbine -> accelerometer -> meter -> whatever reads the meter. Each link only knows the interface of the one before it, not its class.
3. **A real output in the game (later).** A block entity hands the number to Create: Power Grid, and it becomes a real voltage on a terminal. The plain-Java shape stays the same; only the last link changes.

**Developer:** What I meant is that when I simply write the code for the original voltmeter, it just does nothing, no run prompt, nothing, so I have no method of printing from a source at the moment, unless I'm forgetting.

**Claude:** You're not forgetting anything. That's how Java works, and the lesson left it out.
- **A method never runs by itself.** Writing `readMeter` only defines it, like writing a recipe down without cooking it. Something has to call it.
- **A Java program starts in exactly one place: `main`.** IntelliJ only shows the green ▶ next to a `public static void main(String[] args)` method.

Three things to check:
1. **Where `readMeter` lives.** Every method must be inside a class: put it in `Main.java`, inside `public class Main { ... }`, next to `main`.
2. **Something to measure.** It takes a `VoltageOutput`, so the object's class must `implements VoltageOutput` and have `outputVolts()`.
3. **Call it from `main`.**

```java
public class Main {

    // The program starts here: this is what gets the green ▶
    public static void main(String[] args) {
        Battery battery = new Battery("zinc", 0.1, 0.02, 1.5);  // something to measure
        readMeter(battery);                                     // the CALL: now it runs
    }

    // The definition: does nothing until main calls it
    static void readMeter(VoltageOutput source) {
        System.out.println("Meter reads " + source.outputVolts() + " V");
    }
}
```
Expected: `Meter reads 1.5 V`. No ▶? Check that `main` is exactly `public static void main(String[] args)`.

**Developer:** perfect.

## To confirm (the developer's checklist for this lesson)
Confirm each item in your own words, or by doing it, before the lesson moves on.

**Inheritance**
- [ ] `extends` means "is a", and a class has exactly one parent.
- [ ] `super(...)` builds the parent's portion first. Without it, Java inserts `super()`, which fails to compile when the parent has no no-argument constructor.
- [ ] A subclass *overrides* (not overwrites) a method; the parent's version is still reachable with `super.method()`.
- [ ] The **object's real type** decides which method runs, not the variable's type. `@Override` is only a compiler check.

**Interfaces**
- [ ] `implements` means "can do"; a class can implement any number of interfaces.
- [ ] An interface method has no body; the class must supply it.
- [ ] A method that takes an interface type (`readMeter(VoltageOutput source)`) accepts any class that implements it.

**Running code**
- [ ] A method only runs when something calls it.
- [ ] A program starts at `public static void main(String[] args)`, the only place IntelliJ puts the ▶.
- [ ] Every method lives inside a class.
- [ ] `readMeter` printed `Meter reads … V` from a real source in your project.

**Output design**
- [ ] Why returning a value (`double`) is more useful than printing it (`void`).
- [ ] How a part holding a `VoltageOutput source` lets parts chain.

**Still open**
- [ ] Exercise 2: Accelerometer implements `VoltageOutput` and your own `RotationInput`, output depends on RPM.
- [ ] Explain-back: why the accelerometer can't `extends RotatingPart, VoltagePart`.

## Code review: exercise 2, revised (screenshots shared by the developer)
- Fixed by the developer: constructor order (`detectioncost` before `voltageout`, 9 V in -> 4.5 V out); RPM is now passed in (sensor-like, matches the brainstorm); `@Override` on `outputVoltage()`; lowerCamelCase method name.
- Questions given (hints only):
  1. `describeFactors()` no longer overrides `describe()`; what does `Part p = a; p.describe()` print?
  2. `int` vs `double` for `failurechance` (widening; the `%` shows 20.0).
  3. `rpmout` is an input now (naming), and it's fixed at construction; how could a new RPM be given later (the open `RotationInput` piece).
  4. Interface name `voltageOutput` vs the type naming convention.
  5. Stray `;` after a method's `}` in `Part`.
- (An earlier review reply was cut off before delivery; this review covers the revised code.)

## 2026-11-04
- The developer answered the revised-review questions (override error on `describeFactors`, int vs double, `rpmIn`, `VoltageOutput`). Claude's reply was cut off before delivery and is not recorded.
- Weekday check: no school / work first; continue.
- `describeFactors()` renamed back to `describe()` (the override is valid now).
- Narrowing predicted correctly (`int n = 2.7;` won't compile); cast `(int) 2.7` -> 2 (truncates).
- New RPM after construction: not sure -> taught the setter (`setRpmIn`), why a new constructor call loses the part's state, and encapsulation (`private` blocks `a.rpmIn = 500;`). The setter is where a part can react (validate, recalc, overload).
- Exercise 3: `setRpmIn`, called with 1 / 500 / 1200, printing `describeRPM()` each time. Developer's design: just store the value for now.
- Next (developer's choice): recall quiz, then brainstorm.
- Setter example reviewed (a generic `Fan.setBladeAngle`). Exercise 3 runs: the same object at 500 and 1500 RPM; the output stays 4.5 V (by design: "just store it").
- **Recall quiz: 3 / 4.** Correct: polymorphism (the object's type decides), `extends Part implements A, B`, `main` as the entry point. Wrong: why a setter ("fields can only be set in constructors") -> corrected: `private` + encapsulation. **Retest encapsulation next quiz.**
- Next: brainstorm round.

## Brainstorm: the power system (built on Create: Power Grid)
- **Border:** every electrical port is a Power Grid port. Open a portion, disconnect internal wires, and you can read the output or send inputs there. (Consistent with "anything not exposed is baked; exposed shows the proper model".)
- **Circuits:** full circuits, but a circuit can return to a ground.
- **Overdraw:** voltage sags, brownout resets, the weakest part fails, and fuses / breakers trip (when built in).
- **Sources inside modules (first pass):** only Power Grid input.
- **Exposing:** an internal port is a real Power Grid port only while open (Power Grid-style circuits aren't cheap); closed, circuits are still respected as data.
- **Ground:** the designer picks (casing / chassis, a ground bus part, the world).
- **Overdraw order:** sag -> brownout -> fuse -> failure.
- **Development order (developer's principle):** fold the mod out in order of closeness to its parent mod, Power Grid. Explore every way to use Power Grid before making real modules. In real development: connections first; batteries, generators, capacitors in a second pass once module electricity is optimised.
- **Closed model:** per wire (current, voltage); recalculated every few ticks.
- **Wire limits:** current (by gauge), voltage (insulation), material. (Resistance by length not picked; follow-up asked.)
- **Reading inside:** telemetry while closed, probes while open.
- **Short pulses:** recorded on the client when read through telemetry, queued as events per tick. (Follow-up: the server side, see below.)
- **Resistance by length:** only for long runs (across blocks), not inside a module.
- **Recalc interval:** server config.
- **Server-side events:** compatible events are queued among a computer's actions. The server evaluates each against the actions in order. Example: the power-off button on a terminal queues an event mid-tick; a **hard** order turns the machine off before the remaining events, a **soft** order waits until the end of that tick.
- **Telemetry after viewing:** telemetry is Power Grid data and behaves like it, and so does the laptop / viewer: it keeps data only if it is programmed to save it.

## Lesson: static
- Concept: one copy owned by the class vs one per object; `Part.partsBuilt` counter + per-part `serialNumber`; static methods called on the class, no `this`; `main` and `readMeter` were already static; NeoForge registries are static fields.
- Retrieval: `static count` / `id` with 3 objects -> **correct** (1 count, 3 id; unknown at the start of the session). `this.massKg` in a static method -> answered "the last part's mass"; corrected: compile error (no object, so no `this`; also int vs double).
- Exercise 4: static `partsBuilt`, `serialNumber`, static `getPartsBuilt()`, serial in `describe()`, three accelerometers, predict the count. Explain-back: why `getPartsBuilt()` is static and `describe()` is not.
- **Retest next quiz:** static methods can't use `this` / instance fields; encapsulation.
- Exercise 4 runs: the shared counter goes 1 -> 2 across two accelerometers. Not yet shown: the serial number in `describe()`, a third part. The developer duplicated the test block -> pointed out as the duplication problem; a `runTests(Accelerometer a)` method fixes it (parameter type answered correctly, with the reason).
- Explain-back on static: "like b in y = mx + b" (a shared value) -> close; tightened: `getPartsBuilt()` only reads the count, the constructor changes it; `describe()` needs one part's fields, so it needs `this`.

## Lesson: lambdas
- Concept: passing behaviour; a `WarningRule` interface, the long way (a class) vs `value -> value > 12`; a `check(name, reading, rule)` telemetry method; lambda shapes, including `() -> new ...` as used in NeoForge registration.
- Retrieval: `v -> v >= 5` at 5.0 -> correct. Why a lambda fits -> "it returns a boolean" (half right); corrected: a functional interface has exactly one method.
- Exercise 5: `WarningRule` + `check` on real accelerometer readings; design question: a getter for the RPM. Explain-back: why `() -> new Accelerometer(...)` helps for building later.
- **Retest next quiz:** functional interfaces; static vs `this`; encapsulation.
- Question: how to use `getPartsBuilt()` as the value instead of `v` -> pass it in as the reading (`check("parts built", Part.getPartsBuilt(), v -> v > 2)`); `v` is just the lambda's name for the reading; int widens to double (prints 3.0); calling it inside the lambda works but ignores the reading. Developer: understood.
- Exercise 5 runs: RPM rules warn at 1500 and pass at 500; the parts count goes through `check`; the developer added an "adequate" message (an else branch, own design).
- Predict: A's describe() after B is built -> "1 parts built". The answer depends on whether describe() prints the static `partsBuilt` or the per-part `serialNumber`; the developer is to test it with one extra println.
- Explain-back on `() -> new ...`: hint requested -> hint given via the developer's own thumb-drive design (the lambda is the job; calling it builds; when and how many).
- Q1 (A's describe after B): Claude initially didn't say right or wrong; then answered: wrong if describe() prints the static `partsBuilt` (it would say 2; the wording suggests this), right if it prints `serialNumber`.
- The hint for the `() -> new ...` explain-back was too thin (developer: "nowhere near descriptive enough"). Re-given as a worked contrast: build now (`new Accelerometer(...)`) vs a `PartJob` interface + lambda (`job.make()`), with three guiding questions (the counter after each line, how many parts each gives, waiting for material) and why NeoForge registration uses it. Teaching note: hints need a worked contrast, not just an analogy.
- Developer asked to switch from challenge to lesson ("just teach me how it works"). Taught deferred creation directly: `new` builds one part now vs a lambda recipe (`PartJob`, `job.make()`) that builds on each call; a step-by-step counter trace (0, 0, 3); a table (when / how many / who decides); the thumb-drive machine as code; NeoForge `BLOCKS.register("my_block", () -> new Block(...))` and why it is deferred; Java's built-in `Supplier<T>` / `get()`. Teaching note: when an explain-back stalls after a hint, switch to teaching it outright.
- Developer's analogy: a coffee machine's start button (pressing gives coffee even without a pod) -> confirmed and mapped: the lambda = the brewing program, `get()` = the button, each press a new cup; the recipe never checks, so checks (material / power / failure) belong to the caller holding it.
- **Standing review item (developer's request):** whenever more suppliers / deferred creation come up, remind the developer of this pattern (`Supplier<T>` / `() -> new ...`, caller does the checks), and keep it in the recall rotation.
- **Recall quiz 2: 3 / 4.** Correct: encapsulation (`private` = only inside the class; wrong this morning), functional interfaces (two methods -> no lambda), suppliers (two `get()` calls -> 2 fans). Wrong: what a static method can't use (picked "other static fields") -> corrected with the developer's own `getPartsBuilt()`; check: "with zero parts in the world, would this value still exist?". **Retest static-method access again.**

## Brainstorm: the first test piece
- **Test piece:** the accelerometer block.
- **Integration:** study Power Grid first, but Power Grid owns the terminals now. The block computes: voltage in, against our resistance, powers the laser; the voltage out is the exact RPM divided by a factor (so wires aren't overloaded).
- **Feedback:** the output is a voltage, so it is assessed only through Power Grid's own meters.
- **Done when:** it registers and places; Power Grid wires connect to its terminals; it reads a real voltage.
- **Two parents (Power Grid electric base + Create kinetic base; Java allows one):** study both together, and see if the data can coexist.
- **Terminals:** power +, power -, signal out; the - is shared as ground.
- **Output factor:** set per block, default 10 V. (Developer: "I'm technically making this and you're teaching me how": Claude teaches, the developer decides values.)
- **Low supply:** an unstable / noisy reading.
- Next: a study session reading how Power Grid and Create build their blocks. It needs a NeoForge workspace with both as dependencies (to Ctrl+click into their classes).
- **New ground rule (developer):** Sable asks AI not to read its repository; the project respects that for any mod checked. Claude uses wikis / official docs, never other mods' repos or sources; if no docs apply, Claude says so and recommends how the developer can judge or find out. The learning axiom "read the Create and Power Grid sources" was replaced accordingly.
- Clarified (developer): the request is a **wiki lesson series**: read each dependency's wiki, every feature / function, explain how it works, then ask how it could be implemented in our mod (or explain if too abstract). Code reading: Sable never; other mods only with literally zero docs, not advised.
- Developer: publicly available resources beyond wikis may be used. Claude's caveat: auto-generated code wikis of Sable (DeepWiki) are its code in another form, so they are not used.
- Wiki search results: Power Grid has no wiki (mod pages + in-game Ponders only); Sable's developer wiki is linked from its GitHub README (the developer opens the README and shares the wiki link, so Claude doesn't read the repo); Create has a community Fandom wiki + createmod.com guides; NeoForge has its official docs.

## Wiki lesson series: Sable
- The developer shared the Sable wiki links (Home, Working with Entities) and the Sable Companion README (no agent restriction on Companion). The wiki has 4 pages: Home, Working with Entities, Block Physics Properties, Dimension Physics Data; plus the Companion README. Read by Claude: those pages only (no repo code).
- **Sable lesson 1:** the `build.gradle` snippet: Maven repositories, `exclusiveContent` + `filter` (why: speed and no fake artifacts), group:artifact:version coordinates, `${project.x}` from `gradle.properties`, `api` vs `implementation` vs `compileOnly` (optional Sable = compileOnly, not the wiki's `api`); Sable Companion: plots far away break distance math, a bundled (jarJar) library with safe defaults swapped for real ones when Sable is present (polymorphism at scale).
- **Developer's decisions:** Companion for everything possible; Sable-specific code in a separate addon mod. Not near-term: "we are simply respecting the confusing parts at the moment".
- Next Sable lessons: Working with Entities (kicking tags, tracking), Block Physics Properties, Dimension Physics Data.
- Developer: all our addons (e.g. the Sable one) are bundled together in the release, but developed one at a time.

## Wiki lesson series: Power Grid
- Power Grid has no wiki; source for the series: its Modrinth page (mechanics, block / item list, links: issues, Discord) and the in-game Ponders (the developer reports what they show).
- **Power Grid lesson 1** (from the Modrinth page): circuit loops; Ohm's law; the junction rule; higher voltage = less loss (P = VI, loss = I^2 R); balancing production and demand; the Circuit Design Table + PCBs; its block list. Each tied to the developer's earlier decisions (return to ground, overdraw order, resistance on long runs).
- Developer's decisions: analog outputs meet Power Grid devices directly; encoded standards get a simplified path for players who don't want encoding. Our CAD is separate from Power Grid's design table / PCBs, but compatible.
- Developer's rule: keep going unless prompted after a question; questions define the lesson (semi-free tutoring), changing course only after a check-in says so.
- **Power Grid lesson 2:** converting between rotation and electricity (motor, generators), Create's stress units vs watts, the servo as a control signal, FE converters.
- Power Grid lesson 2 answers: motor efficiency prediction correct (90 W, 30 W heat); losses split (our side heat, Create's side reduced SU); servo power and signal on separate ports (the developer asked why: because data is just voltages, the design must label port purpose).
- **Power Grid lesson 3:** loads, heat and damage (P = VI, I^2 R, overheat config). Prediction correct (3 A, 36 W).
- **Developer's steer:** heat just leaves automatically for small effects (heat mods may hold it later); hidden defects are not the priority: "we are specifically looking at making an implementation; if we overcomplicate, my building will be heavily hampered, and that is attached to my learning". -> Claude moves to implementation: setting up the NeoForge project.
- **Implementation step 1:** NeoForge project setup (from the official docs): the ModDevGradle 1.21.1 MDK, opening it in IntelliJ (first import is slow), `gradle.properties` keys (mod_id permanent, mod_name, mod_group_id, mod_version), runClient, the Mods list as the first in-game result, `gradlew build`.
- Developer: the real repository is "Create: Power Grid - Computation" (Claude can be moved there); for now a throwaway practice project, entirely the developer's own ("it's all me").

## Moved to Create: Power Grid - Computation (2026-11-04)
- The planning documents now live in this repository's `AI planning assistance documents/` folder (no CLAUDE.md, by the developer's choice). This repository holds documentation and, later, the first real mod; practice happens in a separate practice project.

## Practice curriculum (modding, in a practice project made with the IntelliJ Minecraft Development plugin)
0 project anatomy; 1 registration (item, block + item); 2 assets; 3 data (recipe, loot table, tags); 4 events; 5 block states; 6 block entities (saving); 7 ticking; 8 client / server sync; 9 Power Grid integration (the zero-docs decision); 10 menus / screens; debugging throughout.

- **Step 0, project anatomy:** the plugin generated `java/` with the `@Mod` class, `templates/META-INF/neoforge.mods.toml`, `assets/<modid>/`, `mixins/` (no `data/` yet); not run yet. Explained: the @Mod entry point, the mods.toml template filled from gradle.properties, assets = client / data = server, mixins (powerful, fragile; a late lesson). Task: runClient, find the mod in the Mods list, change mod_name and see it change.
- Step 0 done: the practice project runs (repeated while following Kaupenjoe's tutorials).
- **Step 1, registration:** registries and permanent ids; `DeferredRegister` as deferred creation; worked example `ModItems.ITEMS` + `RESISTOR` with `() -> new Item(new Item.Properties())`, registered on the mod event bus; a table tying static / final, suppliers, generics (`DeferredItem<Item>`, `.get()`), properties and events to the code; lang file, item model / texture, creative tab (copy the generated example's pattern). Exercise: the resistor item, then a scaffolded `part_casing` block (`DeferredRegister.Blocks`, `strength(___)`, `registerSimpleBlockItem`, blockstate + block model + texture), placed in the world. Explain-back: why the block's item is registered separately.
- Developer: step 1 jumped too far ahead; asked where block and item classes go. Stepped back to **project structure**: packages = folders with dot names, the `package` line, the mod's home package (the @Mod class's folder, from mod_group_id + mod id), sub-packages `item/` (ModItems) and `block/` (ModBlocks) only as needed (Kaupenjoe's layout), one job per class, IntelliJ New -> Package / Java Class, `import` + Alt+Enter, resources under `assets/<modid>/` (not packages). Task: create the two packages and empty classes, report ModItems' package line. Teaching note: confirm the structure before code; step 1's registration lesson waits until then.
- Structure confirmed: `package com.MBMadness.createpracticetestingenv.item;` (correct placement). Style note: Java packages are conventionally all lowercase; fine for practice, use lowercase in the real mod.
- **Step 1, slowly:** only the resistor item: fill `ModItems` (Alt+Enter imports; pick `net.minecraft.world.item.Item`; MODID is the @Mod class's static constant), one line in the @Mod constructor (`ModItems.ITEMS.register(modEventBus)`), `/give` to get it. Expected: purple-black checkered texture and a raw `item.<modid>.resistor` name = registered but not dressed (step 2).
- First runtime error (screenshot): NeoForge found the entry class for mod id "Createpracticetestingenv" (capital C), which doesn't exist. Taught: mod ids must be all lowercase, and `@Mod(MODID)` must equal `mod_id` in gradle.properties (the toml is filled from it); class names may keep capitals; check the toml for a hard-coded id. (The screenshot's local file path is not recorded.)
