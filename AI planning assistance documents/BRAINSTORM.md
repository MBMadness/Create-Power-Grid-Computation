# Brainstorm — the new mod (working title: "Create: …")

A living brainstorm, not a spec. Nothing here is built yet. We are **planning big now and starting small later**.

**Roles.** The developer writes all the code and is learning Java and NeoForge 1.21.1 along the way.
Claude only brainstorms, advises on structure, teaches and reviews. It does not write the mod.

**How to use this file**
- `PIN:` is an idea parked for later. Add your own anywhere.
- `NOTE:` is an annotation or aside.
- `DEFINE LATER` marks a topic we agreed to come back to.
- `Q:` is an open question for the next brainstorm round.

---

## 0. Ground rules
- **A fresh start.** The old `powerchipfast` code (0.99.6-B) is **study material only**: we read it critically, looking for common AI-code problems. Nothing is ported.
- **A new mod id and name.** Old worlds will not carry over.
- **Platform:** Minecraft 1.21.1, NeoForge.
- **Dependencies:**
  - Create and **Create: Power Grid** are core dependencies.
  - Sable is optional.
- **Other mods' code: wiki first.** Sable asks that AI not read its repository: **Sable's code is never read.** For other mods, Claude uses the wiki / official docs; reading their code is **not advised** and happens only when there is literally zero documentation of their classes, API or compatibility (and Claude says so first). Nothing from the old setup's unpacked jars (`reference/`, `/tmp/ref`) is used. When docs don't cover something, Claude says so plainly and recommends how the developer can find out or judge it (in-game testing, the mod's Discord / issue tracker, its in-game help).
- **Publicly available resources beyond wikis are fine** (mod pages, in-game Ponders, videos, public Discord / forum posts, other mods' public API docs). Exception: anything **generated from Sable's code** (e.g. auto-generated code wikis like DeepWiki) counts as reading its code and is not used.
- **Wiki lesson series:** Claude goes through each dependency's wiki, every feature / function, explains how it works, then asks how it could be implemented in our mod; if the developer finds it too abstract, Claude explains instead.
- **Sable integration (decided, not near-term):** Sable Companion is bundled for everything it can do (it works with or without Sable). Sable-specific features live in a **separate addon mod**, not in the main mod. **All our addons ship bundled together** (as Sable bundles Companion), but each is **developed one at a time**. We are nowhere near Sable work yet; for now we only learn the confusing parts.
- **Compatibility is handled by addons** (ownership, claims, radio systems and so on). Our own fallbacks stay simple.
- **Docs:** this brainstorm comes first. Later the plan will be split into one document per system, plus a short changelog.
- **Claude's role goes into `CLAUDE.md`** (the developer replaces the old one): Claude only brainstorms, advises, teaches and reviews; the developer writes the code.

### Publishing checklist (Claude: remind the developer EVERY time anything is published: a release, a Modrinth / CurseForge upload, a public push or a PR)
**Why:** transparency for anyone who wonders about the mod's integrity. The logs show what Claude did (brainstorming, advice, teaching, review) and that the developer wrote the code.
- [ ] **Include the full conversation log** (every brainstorm / class session with Claude) alongside the release, e.g. under `docs/conversations/`.
- [ ] **Censor personal details** wherever possible, in the logs and in every Markdown file: names, emails, accounts, locations, schedules, and anything personal about the developer's learning or teaching (skill assessments, struggles, class notes).

## 1. Identity
- **Pitch:** *Design, build, iterate.*
- **Closest feel:** Stormworks.
- **Name:** the developer's repository is named **"Create: Power Grid - Computation"** (working name; the mod_id is still to be fixed before the real project starts).
- **Audience:** in-depth engineers, but the mod still rewards simple builds.
- **Mode:** the building system is defined in creative first. Survival progression then sets the tiers.
- **Tiers:** none are explicit. Resource availability shapes what is practical: a cheap device is placed and timed, an expensive one is delivered.
- **Rewards beyond destruction:** exploration and mapping, logistics, mining and terraforming, challenges.

## 2. The modular core
### Scale
- A **voxel** is one texture pixel of a block, so a block is 16³ voxels.
- **Sub-voxels** (16 per voxel edge, 1/256 of a block) are used **only for wiring and electronics**.
- **One grid** runs across blocks: a machine can span many blocks seamlessly.

### Parts and modules
- **Basic (stock) parts** have a minimum size. The families are:
  - electrical, mechanical, fluid, sensing / IO;
  - **ports and interfaces**: shaft to mini-shaft, Power Grid connector to the internal system, and so on.
- **Player-made modules** are built from simpler modules, with in/outs defined by the maker or by defaults.
  - Example: turbine fan + rotational accelerometer → voltage out → RPM monitor.
- **Nesting is unlimited.** Size is the only limit, because every module takes up space. The smallest player-made modules will likely be electronic.

### Interfaces to the world
- A physical interface (shaft, pipe, anything physically connected) marks its area as the **block edge**. It is made flush with the "ground", with the interface face against the block edge.

### Rendering and opening machines
- Only the outside and open areas render. Anything hidden is a baked value.
- Machines can't be broken conventionally. They open only through:
  - designed **access panels**;
  - designed **break lines**;
  - an **overload** that breaks the weakest point.
- A broken machine stays exposed in place, and severed pieces fall or drop.

## 3. Making things
### Early game: the blueprint item
- It opens a UI almost identical to the CAD, less refined, with each component's **exact failure %** next to it.
- The **build sheet** is placed on the ground and built over with a "grocery list" of materials. It shows a hologram, and each placement is a graded hands-on action.
  - It is much cheaper, but the failure rate becomes risky depending on the product.
- **Failure factors:** environment, player gear, tools, care and time taken.
  - Gear starts simple: an **engineer's suit** (chestplate) removes a lot of build error.
  - A debug setting turns failure off.

### Later: the CAD
- The CAD is a specialised table first, and later software on built computers.
- You define the type and parameters first (rocket, engine, technology…). You then get the part list (skin, casings, stage separators, parachute…), and each part must be **manufactured individually** with realistic processes.
- **Templates enforce their rules** when you use one. In a free build, redundant parts are judged, but you can approve them anyway.
- **Export:** the CAD exports to a data device (the base one is a **thumb drive**). A machine **copies the job**, and starts building once it has the right size, amount and type of material.
- `PIN:` transferring jobs over network cables. Easy for now, "prompt me later".

### Manufacturing
- **Processes:** machining, joining, forming and casting, electronics fabrication.
- **Machines:** both ours and Create's.
- **Feeding:** item and fluid inputs. Stock must be **at least as big** as the part, and the leftover becomes swarf or scrap.
- **Quality** comes from machine quality, material quality, operator skill (minigames) and process conditions.
- **Bootstrapping:** better machine tools are built from parts made on worse ones. Machine recipes are fixed.
  - `PIN:` a clean, understandable way to make machine recipes modular.
- **Fabrication minigames are core to the mod.** They will be redefined later. `DEFINE LATER`

### Copies and defects
- The first article is made in a menu or in the world. **The method sets how faithfully copies come out:**
  - a hand copy gets new flaws plus the original's;
  - a mold or jig copies the original's flaws exactly;
  - a CAD copy comes from the design, not from the part.
- **Failure is hidden defects.**
- **Finding them:** telemetry first. Inspection tools and test benches come later. Otherwise you find them when something fails.

## 4. Electrical, signals, wiring
- **Externally** everything follows Power Grid. **Internally** it is numbers, kept cheap:
  - a shorted wire fizzles and disappears;
  - a blown capacitor sparks and smokes.
- **Connections in v1:** power, mechanical, fluid / fuel.
- **No separate data type:** data is short voltage signals, read as voltages ("real power outputs").
  - **Encoding depends on the device:** analog levels for sensors, a pulse protocol for computers.
- **Tracked:** current and overload, heat, wear, voltage noise. The simplest of these is evaluated first.
- **Wiring menu (Stormworks-style):**
  - It shows the connection points between parts.
  - Wires have mass and take space, so routing is strategic.
  - A wire runs point → any number of waypoints → point.
  - Many wires on the same path can **bundle into one voxel**.
- **Gauges:** several, from smaller than a sub-voxel up to one full voxel for big systems.
- **Sockets** make systems replaceable and more reliable. Their reliability depends on their quality.
  - What goes in a socket: wire sockets first, then chips / ICs, fuses / breakers, consumables (batteries, cartridges).
  - **Socket designer:** a separate, simpler modelling mode with blueprinting (downloadable NBT, inventory blueprints), a pin layout editor, a mating shape, retention features and ratings.
  - **Fit:** a part is held by being close to other voxels, or by a holding system (clamp, spring…). `DEFINE LATER`
- **Internal mechanics:** our own torque / RPM network.
- **Power system (brainstorm, session 01):**
  - Every electrical port is a Power Grid port. An internal port becomes a real Power Grid port only while its module is open; closed, the circuit is still respected as data.
  - Full circuits; a circuit can return to a ground, and the designer picks what ground is (casing / chassis, a ground bus part, the world).
  - Overdraw, in order: voltage sag -> brownout resets -> fuses / breakers trip (if built in) -> the weakest part fails.
  - Sources inside modules, first pass: only Power Grid input. Batteries, generators, capacitors come in a second pass, after module electricity is optimised.
  - Closed modules keep per-wire current and voltage, recalculated every few ticks (the interval is a server config). Wires are limited by current (gauge), voltage (insulation) and material; resistance by length counts only on long runs across blocks.
  - Reading inside: telemetry while closed, probes while open. Telemetry is Power Grid data and behaves like it; a viewer keeps data only if programmed to save it.
  - Events: short pulses read through telemetry are recorded on the client and queued per tick. On the server, compatible events are queued among a computer's actions and evaluated in order; a hard order (e.g. power off) acts before the remaining events, a soft order waits until the end of the tick.
- **First test piece: the accelerometer block** (session 01). Power Grid owns its terminals: power +, power -, signal out, with the - shared as ground. Voltage in powers the laser against our resistance; the signal out is RPM / a per-block factor (default 10 V). Too little supply gives an unstable, noisy reading. Assessed only with Power Grid's own meters. Done when it registers and places, Power Grid wires connect, and it reads a real voltage. Open: it needs both Power Grid's electric base and Create's kinetic base (Java allows one parent): study both and see if the data can coexist.
  - **Meeting Power Grid devices:** our analog outputs connect **directly** (a Power Grid servo can read an accelerometer's voltage as-is). Where we use an encoded signal standard (a recognised / common protocol), we provide a simplified path for players who don't want to deal with signal encoding.
  - **Power Grid's Circuit Design Table / PCBs:** separate from our CAD, but compatible (their PCBs can be used as parts in our modules).
  - **Heat (for now):** heat leaves a part automatically, with small effects only. Detailed heat could come later through compatibility with heat mods.
  - **Ports:** power and control signal use separate ports (a port has a purpose label, even though both carry volts). Conversion losses at the Create interface are split: real heat on our side, reduced SU delivered on Create's side.
- **Scope rule (developer):** don't overcomplicate; designs serve an implementation the developer can build now, because building is how they learn. Hidden-defect details are parked until something is built.
- **Development order:** fold the mod out in order of closeness to its parent mod, Power Grid. Explore every way to use Power Grid first; in real development, connections first, then the second-pass sources.

## 5. Computing and control
- **Programming:** a **visual node graph** first, with a **Lua node inside the graph** for experienced users.
  - Lua can touch **any data**: in, out, or generated internally.
- **The node graph includes control nodes** (PID, gyro input…). Every input must be wired by the player to reach a target output.
- **Built-in template list** with tutorial presets.
- **Hardware scales:**
  - A **microcontroller** can do decent work alone, for example find optical contrast and box an object. `PIN:` visual data.
  - A **motherboard + CPU** holds several microcontroller-sized graphs.
  - A **full computer** depends on its generation, from rotary dial to modern.
- **Generations:** new part kinds unlock, and quality within each kind also matters.
- **Computers come ready-made and custom-built**, and the two are very different systems. `DEFINE LATER` (a DEEP topic).
- **Limits:** memory, power and heat, IO pins. Ops are counted per tick only if real-time turns out too costly.
- **Timing:** a player watching a computer sees every action at its real speed. The server runs everything that happened between ticks, in order, every tick. Computers are weaker because of this, and that's expected.
- **Sensors read physical quantities.** "Magic values" (coordinates…) are computed from other devices' data. Such a computation may be optimised internally.
- **Vision:** a server-side abstract scan with many rays, possibly a cheap GPU / instanced pixel scan. `PIN`
- **Telemetry:** parts with telemetry outputs (marked in the creative tab) connect to a debug interface: a tablet or laptop (the laptop is placed on the ground).
  - Wire it to all systems, and it lays out live bars with highs, lows and averages.
  - It marks risk / warning / failure / optimal, from the part's defaults or player limits.
  - It can also be viewed as graphs on a computer, a live overlay, the node-graph debugger, or displays you build.

## 6. Vehicles, physics, flight
- **"Sable entity"** is a CAD property. With Sable missing, that option is hidden.
  - It sets mass / CG / inertia, the aero data table, joints, and the collision shape. `DEFINE LATER` (its own design stage).
- **Physics:** complex data, simple output. A too-hard turn shows up on the object, which then reads its data table to respond.
- **The aero bake** happens once in the CAD. It cuts the module into voxel (or sub-voxel) slices and treats each cut as its own body, so a broken part knows how to fall or fly.
  - It is bounded by:
    - designed break lines;
    - coarse cuts + interpolation;
    - a live estimate as a fallback;
    - a time budget per design.
  - It runs on the client; the server re-checks a sample.
- **Structural loads:** later.
- **Propulsion:** rotors / props, solid motors (cast from a CAD-generated fluid recipe), wheels / tracks, jets / liquid.
- **Control** is any data input the builder defines: buttons, levers, joysticks, yokes, pedals, keyboard / mouse devices, handheld remotes, radio, tethered, autonomous.

## 7. The player in the machine
- **Mannequin in the CAD:** imports the player's **Figura / CPM avatar** (full mesh), posed to fit. `PIN:` knee bending.
- **Seats, cockpits and handhelds** are all designed in the same CAD and must fit the player. That includes exit / interaction areas, breathing gear and seatbelts.
- **G-load and harm:** contact points set the axis the player moves on (no ragdoll). Strap the legs and not the body, and a sudden stop rotates you into the panel. Air / altitude and heat / cold also matter.
- **Hook areas:** a part sits on the player or on a hook part (for example in the lap).
- **Wearables (HMDs, suits, packs):**
  - Shaped on the mannequin. Adjustability fits different body proportions (Steve / Alex).
  - Clicking puts it on.
  - Uses armour and Curios slots, or is mounted on a seat or device.
  - **Outside your own view** it is a sub-level / entity attached to its parent part. **In your view** it shows only when parts enter your FOV, matching first-person body mods if present.
- **HMD content:** a **UI/UX node**. You draw the layout, text with borders, and data-driven marks. `DEFINE LATER`

## 8. Materials and chemistry
- **Materials** define nearly all mechanical and chemical function. A part made weak on purpose breaks under load.
- **First pass:** mechanical properties, and for chemistry only controlled explosive volatility.
- **Data:** datapacks plus **config presets loaded from datapack storage**, with selectable sections: material quality, chemical quality, propulsion and forces…
  - Example: a "Toon-y" preset (cartoon explosives, strong wings, unrealistic lift) for players who want arcade flying with complex machinery.
- **Chemistry is basic and CAD-defined:** you set a motor's burn time and thrust, and the CAD gives you a Create recipe for the conceptual ratios as a fluid, which you cast or mold.
  - The conceptual energetic is gunpowder + magma cream.
- **Triggers:** electric charge (wired) in the first pass. Later: heat / fire, impact / shock, friction / spark.

## 9. Multiplayer and performance
- **Radio:** range plus line of sight. Other mods' radio systems can be supported later, if one has a better system.
- **Ownership:** handled by addons (owner lock, claims mods). Without them, **keys** (any locking mechanism) are the only way to lock input.
- **World damage:** datapack config presets and per-dimension rules.
- **Performance:**
  - Anything not exposed is a baked value. Once exposed, the proper model takes over.
  - Idle machines sleep.
  - Bake everything possible.
  - More priorities later.

---

## Open questions (next brainstorm rounds)
- Q: The final name / mod_id.
- Q: The CAD's UI and workflow in detail. How do blueprint and CAD differ beyond looks and precision?
- Q: Fabrication minigames, redefined.
- Q: Computers: ready-made vs custom-built, generations, the pulse protocol.
- Q: The Sable entity design stage.
- Q: Node graph: the node set, the UI/UX node, the debugger.
- Q: Socket fit and retention systems.
- Q: Machine recipes and bootstrapping.
- Q: Network job transfer.

## Learning track (separate from the design)
### Axiom: the project's learning resources
These are the project's standing references. Lessons start from them, and Claude points back to them before explaining anything itself.
- **[NeoForge 1.21.1 docs](https://docs.neoforged.net/docs/1.21.1/gettingstarted/).** The source of truth. Check that the docs version says 1.21.1.
- **[Kaupenjoe's NeoForge 1.21.X series](https://github.com/Tutorials-By-Kaupenjoe/NeoForge-Tutorial-1.21.X).** YouTube videos with one branch per episode: diff the branches to see exactly what an episode changed. Only the episodes up to about #59 are 1.21.1; later ones target newer versions.
- **[Lille's 1.21.1 template wiki](https://github.com/Minecraft-Mods-By-Lille/Template_modFolder/wiki/Setup-Instructions).** A guided setup checklist.
- **Other mods:** their wikis and official docs only (see the ground rule on other mods' code). Where no docs cover something, Claude says so and suggests how the developer can find out.
- **The old `powerchipfast` repo:** read critically, as a source of AI-code problems to avoid.
- **Avoid:** aggregator or "AI summary" tutorial pages, and anything for another Minecraft version.

### Teaching method (evidence-based)
Each class follows the same steps:
1. Recall quiz (no notes, low stakes).
2. Concept, with a worked example.
3. The developer writes an exercise; Claude gives Socratic hints, not answers.
4. Review: the developer explains their choices back.

Earlier topics come back weeks later, mixed in with new ones (spacing, interleaving). Help is faded as skill grows.

### Standing review items
Concepts to remind the developer of whenever they come up, and to keep in the recall rotation:
- **Suppliers / deferred creation:** `Supplier<T>` / `() -> new X(...)` is a recipe; nothing is built until `get()` is called, and each call makes a new X. The recipe never checks anything: material, power and failure checks belong to the caller that holds it (the coffee-machine button).

### Session format
1. **Brainstorm** a piece of the mod.
2. **Second brainstorm:** zoom in on one specific, simpler part of it.
3. **Lesson** built around that part, **a Java example first, then a Minecraft example of the same concept**. It either produces an in-game result, or uses the part as the lesson's subject even when the lesson's code isn't what the part will really be made from.
4. **Keep going (developer's rule):** sessions are semi-free tutoring. Questions drive the lesson; Claude keeps going from one step to the next and only stops when a check-in or the developer's answer says otherwise.
5. **Exercises are themed on the mod.** Mistakes: the developer finds them; hints only when asked.

### Status
- **Classes:** daily, longer on request. Scheduling comes after the brainstorm.
- **Next step:** the developer shows their current code and skills; Claude surveys strengths and weaknesses. After that, learning and brainstorming are interleaved.
- **Old repo:** critical study, calling out common AI-code problems (duplication, swallowed errors, patch-on-patch, no up-front architecture).
