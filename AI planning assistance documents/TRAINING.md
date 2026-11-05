# Training list: the Java patterns NeoForge uses most

Drilled across sessions (spaced, mixed) until comfortable. Each session also rotates in **one unusual or difficult item**, which stays in rotation until it's comfortable too. Status: new → practising → comfortable.

## Common (drilled regularly)
| # | Pattern | Where it shows up in NeoForge | Status |
|---|---|---|---|
| 1 | `public static final` fields | every registry entry (`ITEMS`, `RESISTOR`) | practising |
| 2 | `extends` + `@Override` | custom blocks, items, block entities, screens | practising |
| 3 | Imports and packages | every file | practising |
| 4 | Lambdas / suppliers `() -> new X(...)` | every registration | practising |
| 5 | Generics `Type<Other>` | `DeferredItem<Item>`, `DeferredBlock<Block>`, lists | practising |
| 6 | **Method chaining** (builder) `.a().b().c()` | block / item properties, many builders | new |
| 7 | **Static factory methods** `X.of()`, `X.create…()` | `Properties.of()`, `DeferredRegister.createBlocks(…)` | new |
| 8 | **Nested classes** `Outer.Inner` | `BlockBehaviour.Properties`, `DeferredRegister.Blocks`, `Item.Properties` | new |
| 9 | Events and listeners | `addListener`, `@SubscribeEvent`, event objects | practising |
| 10 | `this` (the object left of the dot) | everywhere | practising |
| 11 | `if` + `==` on keys / constants | creative tabs, checks | practising |
| 12 | Annotations `@Something` | `@Mod`, `@Override`, `@SubscribeEvent` | new |

## Unusual / difficult (rotating in, one per session)
| Pattern | Where | Status |
|---|---|---|
| Method references `this::addCreative` | listeners | new |
| `float` literals `3.0f` vs `double` | properties, rendering | new |
| `==` vs `.equals()` | comparing objects | new |
| Enums / constant holders (`SoundType.COPPER`) | sounds, directions | new |
| `Optional`, `null` checks | lookups that can fail | new |
