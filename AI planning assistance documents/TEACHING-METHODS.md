# Teaching methods: what the research agrees on

Research by Claude during study time (2026-11-04), across programming education, general learning science, language learning, music and skill practice, and game-design teaching. It is a broad pass that looks for commonalities, not a deep review. Most results come from abstracts and summaries, not full papers. The strength of each piece of evidence is noted, and a single small study counts for less than a meta-analysis.

## The commonalities
Seven ideas come up again and again across the fields.

### 1. Show a worked example first, then fade it
- **What:** Novices learn more from studying a fully worked example than from solving the problem cold. As skill grows, remove steps (fading) until the learner writes the whole thing.
- **Evidence:**
  - Worked examples reduce load for novices. The **expertise reversal effect** (Kalyuga et al. 2003; Kalyuga 2007) shows the same detail *hurts* once the learner is proficient. Hence fading (Renkl, Atkinson & Große 2004). [Wikipedia summary](https://en.wikipedia.org/wiki/Expertise_reversal_effect), [Kalyuga review (PDF)](https://typeset.io/pdf/expertise-reversal-effect-and-its-instructional-implications-5787bezqgb.pdf)
  - **Subgoal-labelled worked examples** (each step named, e.g. "1. register, 2. connect, 3. dress") gave deeper understanding across a whole intro Java course (SIGCSE 2019), and beat unlabelled examples on while-loops (Morrison et al. 2016). [Morrison et al.](https://core.ac.uk/works/77773088)
- **For us:**
  - This is the **specific-objectives axiom**: new material always comes with the code shown.
  - Proficient material gets less (the reversal effect), which is the axiom's exception.
  - Label the steps of every worked example.

### 2. Read and predict before you write
- **What:** Tracing (working out what code does) and reading come before writing. Asking for a prediction before running makes the learner engage.
- **Evidence:**
  - Lister and colleagues' multi-national work suggests a hierarchy, **trace → explain → write**. Replications are mixed: tracing explains only part of writing skill. [Lister et al. 2004](https://researchr.org/publication/ListerAFFHLMMSSST04/authors), [Lister 2020 keynote](https://www.ou.nl/documents/49898/1406002/CSERC_2020_abstract_Lister.pdf)
  - **PRIMM** (Predict, Run, Investigate, Modify, Make): a 13-school, 493-student study with a control group found better post-test scores (Sentance, Waite & Kallia 2019). Other studies are smaller and mixed, and **"Make" is the hard step** on multi-statement problems (the PRIDAM study adds decomposition). [Sentance et al.](https://qmro.qmul.ac.uk/xmlui/handle/123456789/57800)
- **For us:** session 1's "predict the output" questions were PRIMM's P. Use the full order: predict → run → investigate → **modify a given example** → only then make from scratch. Session 1's "write Exercise 2 from a spec" jumped straight to Make, which matches the reported weak spot.

### 3. Retrieval practice, spaced over time
- **What:** Recalling from memory (quizzes, no notes), repeated at growing intervals, beats re-reading.
- **Evidence:**
  - Dunlosky et al. (2013) rated **practice testing** and **distributed practice** the only "high utility" techniques of ten. Re-reading and highlighting were rated low. [APS summary](https://www.psychologicalscience.org/news/releases/which-study-strategies-make-the-grade.html)
  - Language learning agrees: a 2022 meta-analysis (Kim & Webb, *Language Learning*) supports spaced over massed practice, especially on delayed tests.
- **For us:** the recall quiz at the start of every session, and the "Retest" lists, are already this. Keep them low-stakes and keep bringing old items back.

### 4. Mix topics (interleaving)
- **What:** Mixing problem types forces you to choose the method, not just apply the last one you saw.
- **Evidence:** a preregistered randomized trial in 54 maths classes found 61 % vs 38 % on a test a month later (d = 0.83; Rohrer et al. 2019/2020). There are caveats: it takes more time, and a Nigerian working paper found short-term gains only. [Rohrer et al. (PDF)](https://Www.Gwern.net/doc/psychology/spaced-repetition/2019-rohrer.pdf), [NBER](https://nber.org/system/files/working_papers/w31853/w31853.pdf)
- **For us:** recall quizzes mix `static`, `this`, lambdas and registration in one quiz, not one topic per quiz.

### 5. Explain it yourself
- **What:** Generating your own explanation beats reading one.
- **Evidence:** a meta-analysis of 64 studies found g = 0.55 for self-explanation prompts. Self-explanation beat instructor explanations, and **multiple-choice prompts were weaker** than free-form ones (Bisra et al. 2018). [BPS digest](https://bps.org.uk/research-digest/self-explanation-powerful-learning-technique-according-meta-analysis-64-studies)
- **For us:** keep the explain-backs, and **prefer free-text answers** (the "Other" box) for them. Multiple choice is fine for quick recall checks, not for explanations.

### 6. Make the invisible visible (the "notional machine")
- **What:** Many beginner misconceptions are about what happens *at runtime*, which code doesn't show: objects, references, which object a method runs on. Drawing it helps, especially when the learner draws or steps through it actively.
- **Evidence:**
  - Sorva's work on notional machines and program visualization. Active engagement matters (Naps et al.). [Sorva et al. 2013 review (PDF)](https://romisatriawahono.net/lecture/rm/survey/software%20engineering/Software%20Architecture/Sorva%20-%20Generic%20Program%20Visualization%20Systems%20for%20Introductory%20Programming%20-%202013.pdf)
  - **The `this` reference is a documented trouble spot.** Ragonis & Shmallo studied 86 high-school and 77 college students: both groups struggled to see `this` as "the current object". Chen et al. (2012) list class vs object and static misconceptions among novice Java programmers. [Ragonis & Shmallo](https://link.springer.com/article/10.1007/s10639-020-10265-6)
- **For us:** session 2's "`this` only works in constructors" is a known misconception, not a personal failing. Fix it by **drawing memory**: two boxes for two objects `a` and `b`, an arrow showing which one `this` points to during each call. Use object diagrams for `static` too (one shared box vs one box per object).

### 7. Specific, task- and process-level feedback
- **What:** Feedback about the task ("line 11 runs before line 12") and the process ("trace it by hand") helps. Praise about the person ("great job!") helps least, yet is the most common.
- **Evidence:** Hattie & Timperley (2007) on the four levels of feedback; a meta-analysis found a medium effect overall (d = 0.48), with big variation (Wisniewski, Zierer & Hattie 2020). [Frontiers paper](https://www.frontiersin.org/articles/10.3389/fpsyg.2019.03087/pdf)
- **For us:** keep reviews pointed at lines and steps. Match the level to proficiency: task feedback for new things, process feedback once partly learned.

## Useful, with caveats
- **Parsons problems** (put given lines of code in the right order) are a low-load step between a worked example and writing from scratch, and good for checking learning (Morrison et al. 2016; Guided Parsons 2025). A good option when "Make" is too big a jump.
- **Productive failure** (attempt a problem *before* instruction): a meta-analysis by the method's own author found positive effects (Kapur & Sinha 2021), but it needs careful design and the sources are mostly the author's. The developer prefers being taught when stuck ("lesson-ify, don't challenge-ify"), so use it only for small, low-stakes puzzles, if at all. [Phys.org](https://phys.org/news/2021-09-productively-wiser.html)
- **One-to-one tutoring and mastery learning:** Bloom's famous "2 sigma" is probably overstated in replication, but tutoring and mastery (move on when it's learned, not when the time is up) remain among the stronger approaches. [Wikipedia](https://en.wikipedia.org/wiki/Bloom%27s_2_sigma_problem), [Nintil review](https://nintil.com/bloom-sigma/)
- **Deliberate practice** (focused practice on weak spots, with feedback): it matters in games and music (Macnamara et al. 2014: about 26 % of performance variance in games, 21 % in music) but explains much less in education. It's useful, not magic. [Platz et al. 2014](https://dx.doi.org/10.3389/fpsyg.2014.00751)
- **Comprehensible input, "i+1"** (language learning, Krashen): new material should be one step beyond what's known. The theory is influential and its exact thresholds are debated. It's the same idea as worked examples plus fading.
- **Game-design teaching:** courses converge on **build → playtest → revise in short loops** (MIT CMS.608, among others). The outcome data is thin, but iteration is the shared backbone. For us: small playable increments, tested in game. [MIT CMS.608](https://ocw.tau.edu.ng/courses/comparative-media-studies-writing/cms-608-game-design-spring-2014/instructor-insights)
- **Programming-education meta-analysis:** Scherer, Siddiq & Sánchez Viveros (2020; 139 interventions) found programming interventions work overall, with visualization and physical/hands-on approaches doing well. The differences between methods were modest. [Open archive](https://openarchive.usn.no/usn-xmlui/handle/11250/2649258)

## What changes in our sessions
1. **New material:** a labelled worked example (Java first, then Minecraft, per the session-2 system), then **predict → run → modify the given example → make**. No jumping straight to "make" from a description.
2. **Proficient material:** less scaffolding (the expertise reversal effect), as the axiom says.
3. **Recall quizzes:** mixed topics, spaced, no notes, low stakes. Explanations as free text.
4. **Runtime concepts** (`this`, `static`, objects): drawn as memory diagrams.
5. **Feedback:** about lines and steps, not praise.
6. **When "make" stalls:** a Parsons-style step (order the given lines), or teach it directly, as the developer prefers.
7. **Build in small loops:** run in game after each small change.

**Searched, not yet deep:** tutoring dialogue patterns, motivation (self-determination theory), learning with AI tutors, music-teaching specifics. These are candidates for the next study time.
