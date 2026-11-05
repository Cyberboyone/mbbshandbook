package com.mbbshandbook.app.data

import com.mbbshandbook.app.data.ContentBlock.Bullets
import com.mbbshandbook.app.data.ContentBlock.Heading
import com.mbbshandbook.app.data.ContentBlock.Paragraph
import com.mbbshandbook.app.data.ContentBlock.Tip

private fun h(text: String) = Heading(text)
private fun p(text: String) = Paragraph(text)
private fun b(vararg items: String) = Bullets(items.toList())
private fun tip(text: String) = Tip(text)

/**
 * All handbook content lives here so it can be reviewed, expanded and
 * fact-checked in one place. Content is educational guidance for medical
 * students, not a substitute for supervised clinical training.
 */
object Handbook {

    fun sectionById(id: String): Section? = sections.firstOrNull { it.id == id }

    fun topicById(id: String): Topic? =
        sections.asSequence().flatMap { it.topics.asSequence() }.firstOrNull { it.id == id }

    fun sectionForTopic(topicId: String): Section? =
        sections.firstOrNull { section -> section.topics.any { it.id == topicId } }

    fun search(query: String): List<Topic> {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return emptyList()
        return allTopics.filter { topic ->
            topic.title.lowercase().contains(q) ||
                topic.summary.lowercase().contains(q) ||
                topic.blocks.any { block ->
                    when (block) {
                        is Heading -> block.text.lowercase().contains(q)
                        is Paragraph -> block.text.lowercase().contains(q)
                        is Bullets -> block.items.any { it.lowercase().contains(q) }
                        is Tip -> block.text.lowercase().contains(q)
                    }
                }
        }
    }

    // ------------------------------------------------------------------
    // 1. Getting started
    // ------------------------------------------------------------------
    private val startSection = Section(
        id = "start",
        title = "Getting Started",
        description = "Orientation, the MBBS journey and first-year survival",
        topics = listOf(
            Topic(
                id = "mbbs-journey",
                title = "The MBBS Journey",
                summary = "How the medical degree is structured, phase by phase.",
                blocks = listOf(
                    p("The MBBS (Bachelor of Medicine, Bachelor of Surgery) is typically a 5-6 year programme followed by a compulsory internship or housemanship year. Exact structures vary by country and university, but most follow the same broad arc."),
                    h("Phase 1 - Preclinical"),
                    p("Years 1-2 build the foundations: Anatomy, Physiology and Biochemistry. Learning is classroom, laboratory and dissection based. Many schools now integrate these subjects around body systems."),
                    h("Phase 2 - Paraclinical"),
                    p("Years 2-3 introduce disease and its treatment: Pathology, Pharmacology, Microbiology, Forensic Medicine and Community Medicine. This phase bridges the lab and the ward."),
                    h("Phase 3 - Clinical"),
                    p("The final years are spent on rotations through Internal Medicine, Surgery, Obstetrics and Gynaecology, Paediatrics, Psychiatry and other specialties. You learn at the bedside, in clinics and in theatre."),
                    h("Internship / Housemanship"),
                    p("After graduating you practise under supervision for a year (or more, depending on the country) before full registration as a doctor."),
                    tip("Early on, learn how YOUR school assesses: the weight of continuous assessment, attendance rules and exam formats. It shapes how you should study from day one.")
                )
            ),
            Topic(
                id = "first-year-survival",
                title = "First-Year Survival Guide",
                summary = "Practical habits that make the transition to medical school easier.",
                blocks = listOf(
                    b(
                        "Attend everything in the first weeks - attendance habits and friendships form fast.",
                        "Buy one core textbook per subject, not five. Add others only when you actually need them.",
                        "Start a simple note system on day one: one place per subject, dated, with room for later additions.",
                        "Learn the campus geography: lecture halls, labs, library, skills lab and the nearest coffee.",
                        "Form a small study group (3-5 people) early. Explaining concepts to each other is powerful revision.",
                        "Ask questions in class. Everyone is confused; the confident-looking students are confused too.",
                        "Protect your sleep. Consistent 7-8 hours beats heroic all-nighters across a whole term."
                    ),
                    tip("The volume of material is the shock, not the difficulty. You cannot read everything - learn to identify what your school actually examines.")
                )
            ),
            Topic(
                id = "how-to-use",
                title = "How to Use This Handbook",
                summary = "What is inside and how to get the most out of it.",
                blocks = listOf(
                    p("This handbook is a companion for the whole MBBS journey: study strategies for each phase, clinical skills checklists, exam technique, professionalism and wellbeing."),
                    b(
                        "Browse by section from the home screen, or use Search to jump straight to a topic.",
                        "Bookmark topics you return to often - they appear under Bookmarks.",
                        "Share any topic with coursemates using the share button.",
                        "Everything works offline; no account or internet needed."
                    ),
                    tip("Content is general educational guidance. Always follow your own school's curriculum, your supervisors' instructions and current local clinical guidelines.")
                )
            )
        )
    )

    // ------------------------------------------------------------------
    // 2. Preclinical
    // ------------------------------------------------------------------
    private val preclinicalSection = Section(
        id = "preclinical",
        title = "Preclinical Sciences",
        description = "Anatomy, Physiology and Biochemistry",
        topics = listOf(
            Topic(
                id = "anatomy",
                title = "Anatomy",
                summary = "How to learn structure: dissection, imaging and high-yield regions.",
                blocks = listOf(
                    p("Anatomy is the geography of the body. It rewards repetition and visual learning far more than passive reading."),
                    h("How to study it"),
                    b(
                        "Read the region BEFORE dissection or prosection so you know what you are looking for.",
                        "Draw simple diagrams from memory - branches of the aorta, the brachial plexus, the femoral triangle.",
                        "Learn relations, not just names: what is anterior, posterior, medial to the structure?",
                        "Attach clinical hooks: radial nerve at the surgical neck of the humerus, common peroneal nerve at the fibular neck.",
                        "Use an atlas alongside your text (Netter or an online atlas) and cross-sections for imaging."
                    ),
                    h("High-yield areas"),
                    b(
                        "Upper and lower limb nerves and their injuries",
                        "Brachial plexus and lumbosacral plexus",
                        "Coronary circulation and heart chambers",
                        "Inguinal and femoral regions, hernias",
                        "Cranial nerves: nuclei, course, functions, lesions",
                        "Peritoneal reflections, spaces and referred pain",
                        "Cross-sectional anatomy for CT and MRI basics"
                    ),
                    tip("Flashcards with images (labelled diagrams with the labels hidden) are the single most efficient anatomy revision tool.")
                )
            ),
            Topic(
                id = "physiology",
                title = "Physiology",
                summary = "Understand mechanisms and you will not need to memorise.",
                blocks = listOf(
                    p("Physiology explains how the body works. It is the most conceptual preclinical subject - if you can explain a mechanism out loud, you know it."),
                    h("How to study it"),
                    b(
                        "Master the core concept first (e.g. Starling forces) before the exceptions.",
                        "Draw flowcharts and feedback loops from memory.",
                        "Do numerical questions: renal clearance, lung volumes, cardiac output.",
                        "Predict before you read: what SHOULD happen if a channel is blocked? Then check.",
                        "Teach a topic to a friend weekly - the Feynman technique in practice."
                    ),
                    h("High-yield areas"),
                    b(
                        "Cardiac cycle, pressure-volume loops, control of blood pressure",
                        "Oxygen dissociation curve and its shifts",
                        "Renal handling of sodium, water, potassium; acid-base balance",
                        "Neuromuscular transmission and reflex arcs",
                        "Endocrine axes and their feedback",
                        "GI secretions, absorption and their regulation"
                    ),
                    tip("Guyton and Hall for depth, Costanzo or BRS Physiology for crisp exam-focused revision.")
                )
            ),
            Topic(
                id = "biochemistry",
                title = "Biochemistry",
                summary = "Pathways with purpose: energy, enzymes and clinical correlations.",
                blocks = listOf(
                    p("Biochemistry feels abstract until you anchor every pathway to a purpose and a disease. Learn the WHY of each pathway, its rate-limiting step, and what happens when it fails."),
                    h("How to study it"),
                    b(
                        "For each pathway learn: location, purpose, rate-limiting enzyme, regulation, and one classic deficiency disease.",
                        "Draw the big map: how glycolysis, TCA, gluconeogenesis, glycogen, lipid and amino acid metabolism connect.",
                        "Use tables for vitamins: source, function, deficiency, toxicity.",
                        "Do clinical vignettes early - they show you what examiners care about."
                    ),
                    h("High-yield areas"),
                    b(
                        "Glycolysis, TCA cycle, oxidative phosphorylation",
                        "Glycogen storage diseases",
                        "Urea cycle and inborn errors of metabolism",
                        "Vitamins and coenzymes",
                        "Enzyme kinetics and inhibition (competitive vs non-competitive)",
                        "Molecular biology: replication, transcription, translation, PCR",
                        "Lipoprotein metabolism and dyslipidaemias"
                    ),
                    tip("Lippincott's Illustrated Reviews is the classic for making biochemistry visual and examinable.")
                )
            )
        )
    )

    // ------------------------------------------------------------------
    // 3. Paraclinical
    // ------------------------------------------------------------------
    private val paraclinicalSection = Section(
        id = "paraclinical",
        title = "Paraclinical Sciences",
        description = "Pathology, Pharmacology, Microbiology and friends",
        topics = listOf(
            Topic(
                id = "pathology",
                title = "Pathology",
                summary = "The bridge from normal to disease - mechanisms and morphology.",
                blocks = listOf(
                    p("Pathology explains what goes wrong in disease. General pathology (cell injury, inflammation, neoplasia, haemodynamics) is the foundation for every clinical subject that follows."),
                    h("How to study it"),
                    b(
                        "Learn general pathology deeply - it pays dividends in every system.",
                        "For each disease: cause, mechanism, morphology, clinical features, complications.",
                        "Look at real histology images and gross specimens, not just descriptions.",
                        "Build comparison tables for easily confused entities (e.g. transudate vs exudate, benign vs malignant)."
                    ),
                    h("High-yield areas"),
                    b(
                        "Cell injury, adaptation and necrosis vs apoptosis",
                        "Acute and chronic inflammation, wound healing",
                        "Neoplasia: hallmarks, grading vs staging, tumour markers",
                        "Haemodynamic disorders: oedema, thrombosis, embolism, shock",
                        "Anaemias and leukaemias",
                        "Systemic pathology of heart, lung, kidney, liver, GI"
                    ),
                    tip("Robbins is the reference; pair it with a condensed review text in exam season.")
                )
            ),
            Topic(
                id = "pharmacology",
                title = "Pharmacology",
                summary = "Drug classes first, individual drugs second.",
                blocks = listOf(
                    p("Learn pharmacology as classes with prototypes. Once you know the prototype drug, the rest of the class is variations on a theme."),
                    h("For every drug class, know"),
                    b(
                        "Mechanism of action (one sentence you can say out loud)",
                        "Main clinical uses",
                        "Key adverse effects and contraindications",
                        "One or two important interactions",
                        "The prototype drug and how siblings differ"
                    ),
                    h("High-yield areas"),
                    b(
                        "Autonomic pharmacology - the map for everything else",
                        "Antihypertensives, antianginals, heart failure drugs",
                        "Antibiotics by mechanism, spectrum and resistance",
                        "Anticoagulants and antiplatelets",
                        "Diuretics - site of action and electrolyte effects",
                        "Diabetes drugs, thyroid drugs, corticosteroids",
                        "Analgesics, NSAIDs and opioids",
                        "Basic pharmacokinetics: half-life, loading dose, first-pass effect"
                    ),
                    tip("Make one-page class summaries yourself. The act of compressing a chapter into one page IS the learning.")
                )
            ),
            Topic(
                id = "microbiology",
                title = "Microbiology",
                summary = "Bugs, drugs and the lab - organised tables win.",
                blocks = listOf(
                    p("Microbiology is detail-heavy. Organise organisms into tables and revisit them on a schedule; sketchy-style visual mnemonics help many students."),
                    h("For each organism, know"),
                    b(
                        "Classification and key lab features (Gram stain, culture, special tests)",
                        "Virulence factors and toxins",
                        "Diseases caused and classic presentations",
                        "Diagnosis: which specimen, which test",
                        "Treatment principle and prevention (vaccine? prophylaxis?)"
                    ),
                    h("High-yield areas"),
                    b(
                        "Staphylococci, streptococci, enterobacteria, TB",
                        "HIV, hepatitis viruses, herpesviruses",
                        "Malaria and other parasites relevant to your region",
                        "Sterilisation, disinfection and infection control",
                        "Immunology basics: hypersensitivity types, immunodeficiencies",
                        "Healthcare-associated infections and antimicrobial stewardship"
                    ),
                    tip("Tie every bug to a clinical story - 'the rusty sputum of pneumococcus' sticks better than a list.")
                )
            ),
            Topic(
                id = "community-forensic",
                title = "Community Medicine & Forensic Medicine",
                summary = "Population health, evidence and the law.",
                blocks = listOf(
                    h("Community Medicine"),
                    b(
                        "Epidemiology: study designs, bias, confounding",
                        "Biostatistics: sensitivity, specificity, predictive values, NNT, p-values and confidence intervals",
                        "Screening criteria and programme evaluation",
                        "Vaccination schedules and national health programmes",
                        "Social determinants of health, primary care and health systems"
                    ),
                    h("Forensic Medicine"),
                    b(
                        "Consent, confidentiality and medico-legal documentation",
                        "Injury types and wound interpretation",
                        "Post-mortem changes and estimation of time since death",
                        "Toxicology: common poisonings and their management principles",
                        "The doctor's duties in courts and to the law"
                    ),
                    tip("Biostatistics questions are guaranteed marks if you practise calculations - make a formula sheet and drill it.")
                )
            )
        )
    )

    // ------------------------------------------------------------------
    // 4. Clinical rotations
    // ------------------------------------------------------------------
    private val clinicalSection = Section(
        id = "clinical",
        title = "Clinical Rotations",
        description = "Medicine, Surgery, OBGYN, Paediatrics and ward craft",
        topics = listOf(
            Topic(
                id = "internal-medicine",
                title = "Internal Medicine",
                summary = "Thinking in differentials and managing the whole patient.",
                blocks = listOf(
                    p("Medicine rotations teach diagnostic reasoning. The core skill is building a differential and defending it with findings."),
                    h("How to shine on the rotation"),
                    b(
                        "Pre-round on your patients: know their vitals, results and plan before the team arrives.",
                        "Present in a structured way: one-liner, positives and negatives, assessment, plan.",
                        "For every symptom, practise a differential list (surgical sieve: vascular, infective, traumatic, autoimmune, metabolic, iatrogenic, neoplastic, congenital).",
                        "Follow up on investigations YOU requested - own your patients' results.",
                        "Read around one case per day, in depth, rather than a whole textbook shallowly."
                    ),
                    h("Bread-and-butter topics"),
                    b(
                        "Heart failure, ACS, arrhythmias, hypertension",
                        "Asthma, COPD, pneumonia, pleural effusion",
                        "Diabetes and its emergencies, thyroid disease",
                        "Anaemia, CKD and AKI, liver disease and cirrhosis",
                        "Stroke, seizures, meningitis",
                        "Common infections and sepsis recognition"
                    )
                )
            ),
            Topic(
                id = "surgery",
                title = "Surgery",
                summary = "Anatomy applied: acute abdomen, lumps, trauma and theatre etiquette.",
                blocks = listOf(
                    h("How to learn surgery"),
                    b(
                        "Revise the relevant anatomy before every theatre list.",
                        "Learn to describe a lump systematically: site, size, shape, surface, consistency, mobility, fluctuance, transillumination.",
                        "Master the acute abdomen framework: inflammation, obstruction, perforation, ischaemia.",
                        "Practise surgical skills (knots, suturing) in the skills lab until boring.",
                        "In theatre: introduce yourself, ask where to stand, never touch anything sterile unless invited."
                    ),
                    h("Bread-and-butter topics"),
                    b(
                        "Appendicitis, cholecystitis, pancreatitis, bowel obstruction",
                        "Hernias and their complications",
                        "Fluid and electrolyte management, perioperative care",
                        "Wound healing, surgical site infection, sutures",
                        "Trauma primary survey (ABCDE)",
                        "Breast and thyroid lumps, skin lesions"
                    ),
                    tip("Bailey and Love cover the whole rotation; learn your hospital's actual protocols for common admissions too.")
                )
            ),
            Topic(
                id = "obgyn",
                title = "Obstetrics & Gynaecology",
                summary = "Two patients at once: pregnancy care and women's health.",
                blocks = listOf(
                    h("Obstetrics core"),
                    b(
                        "Antenatal care schedule, dating and screening",
                        "Normal labour: stages, partograph, fetal monitoring",
                        "Pre-eclampsia and eclampsia",
                        "Antepartum and postpartum haemorrhage",
                        "Common medical disorders in pregnancy (diabetes, anaemia)"
                    ),
                    h("Gynaecology core"),
                    b(
                        "Menstrual cycle and abnormal uterine bleeding",
                        "Contraception counselling",
                        "Fibroids, endometriosis, PCOS",
                        "Cervical screening and gynaecological cancers",
                        "Early pregnancy complications: miscarriage, ectopic"
                    ),
                    tip("Always offer a chaperone for intimate examinations and learn the local consent practice - examiners watch this closely.")
                )
            ),
            Topic(
                id = "paediatrics",
                title = "Paediatrics",
                summary = "Children are not small adults: growth, development and ages-and-stages.",
                blocks = listOf(
                    b(
                        "Learn milestones by age - gross motor, fine motor, speech, social. They are examined constantly.",
                        "Know age-specific normal ranges for heart rate, respiratory rate and blood pressure.",
                        "Weight-based drug dosing: always calculate twice.",
                        "Assess dehydration and know oral vs IV rehydration principles.",
                        "Learn the immunisation schedule of your country.",
                        "Recognise the sick child: work of breathing, perfusion, responsiveness.",
                        "Practise talking to parents AND to the child at their level."
                    ),
                    tip("Neonatal examination, developmental assessment and growth chart plotting are classic OSCE stations - rehearse them.")
                )
            ),
            Topic(
                id = "other-rotations",
                title = "Psychiatry & Other Rotations",
                summary = "Short rotations, big learning: psychiatry, ENT, eyes, and more.",
                blocks = listOf(
                    h("Psychiatry"),
                    b(
                        "Master the mental state examination - it is your physical exam for the mind.",
                        "Learn risk assessment (self-harm, harm to others) as a structured, documented process.",
                        "Depression, bipolar disorder, psychosis, anxiety disorders: diagnostic criteria and first-line management.",
                        "Practise empathetic, non-judgemental communication - it is examinable."
                    ),
                    h("Short rotations (ENT, Ophthalmology, Dermatology, Orthopaedics)"),
                    b(
                        "Buy or borrow a pocket atlas - these specialties are visual.",
                        "Learn the examination sequence for the organ: otoscope, ophthalmoscope, joint exam.",
                        "Know the emergencies: sudden vision loss, airway obstruction, septic joint, compartment syndrome.",
                        "Short rotations are examined soon after they start - revise from week one."
                    )
                )
            ),
            Topic(
                id = "ward-craft",
                title = "Ward Craft & Professional Etiquette",
                summary = "The unwritten curriculum of being useful and trusted on the team.",
                blocks = listOf(
                    b(
                        "Arrive early, know your patients, and never disappear without telling someone.",
                        "Introduce yourself to every patient as a student doctor, every time.",
                        "Ask before you act. Students examine and observe; procedures need explicit supervision.",
                        "Write clearly in notes with date, time, name and grade - if your school allows student entries.",
                        "Nurses run the ward. Be humble, learn their routines, and ask for their help respectfully.",
                        "Hand over properly using SBAR: Situation, Background, Assessment, Recommendation.",
                        "If you make a mistake, say so immediately. Honesty is non-negotiable in medicine.",
                        "Dress per hospital policy: clean, professional, bare below the elbows where required."
                    ),
                    tip("The students who get taught most are the ones who are prepared, honest about limits, and easy to work with - not the ones who pretend to know.")
                )
            )
        )
    )

    // ------------------------------------------------------------------
    // 5. Clinical skills
    // ------------------------------------------------------------------
    private val skillsSection = Section(
        id = "skills",
        title = "Clinical Skills",
        description = "History, examination, vitals, procedures and BLS",
        topics = listOf(
            Topic(
                id = "history-taking",
                title = "History Taking",
                summary = "The single highest-yield clinical skill - a repeatable structure.",
                blocks = listOf(
                    h("The structure"),
                    b(
                        "Introduction: name, role, confirm patient identity, gain consent.",
                        "Presenting complaint: the patient's own words.",
                        "History of presenting complaint: onset, course, associated symptoms. Use SOCRATES for pain.",
                        "Past medical and surgical history.",
                        "Drug history and ALLERGIES (name + reaction).",
                        "Family history.",
                        "Social history: smoking, alcohol, occupation, living situation, functional baseline.",
                        "Systems review.",
                        "ICE: Ideas, Concerns, Expectations - what does the patient think and fear?",
                        "Summarise back to the patient and signpost next steps."
                    ),
                    h("SOCRATES for pain"),
                    b(
                        "Site, Onset, Character, Radiation, Associations, Time course, Exacerbating/relieving factors, Severity"
                    ),
                    tip("Practise with friends until the structure is automatic - then spend your attention on listening, not remembering what to ask next.")
                )
            ),
            Topic(
                id = "physical-exam",
                title = "Physical Examination",
                summary = "Systematic sequences for cardio, respiratory, abdominal and neuro.",
                blocks = listOf(
                    p("Every examination follows the same skeleton: prepare, position, expose appropriately, then inspect, palpate, percuss, auscultate (IPPA) - except abdomen where auscultation comes before palpation, and joints where it is look, feel, move."),
                    h("Universal opening"),
                    b(
                        "Wash hands, introduce, confirm identity, explain, consent, position (usually 45 degrees), adequate exposure with dignity.",
                        "General inspection from the end of the bed: comfort, colour, devices, surroundings.",
                        "Always offer a chaperone for intimate examinations."
                    ),
                    h("Core systems to rehearse"),
                    b(
                        "Cardiovascular: pulses, BP, JVP, praecordium, auscultation with manoeuvres.",
                        "Respiratory: expansion, percussion, breath sounds, vocal resonance.",
                        "Abdominal: inspection, light and deep palpation, organs, percussion, bowel sounds.",
                        "Neurological: cranial nerves, upper and lower limb power/tone/reflexes/sensation/coordination, gait.",
                        "Finish every exam: thank the patient, cover them, wash hands, summarise findings and suggest next steps."
                    ),
                    tip("Rehearse the FULL sequence out loud with a timer. In OSCEs, structure and fluency earn marks even when findings are normal.")
                )
            ),
            Topic(
                id = "vital-signs",
                title = "Vital Signs & Normal Ranges",
                summary = "Adult reference values every student should know cold.",
                blocks = listOf(
                    p("Typical resting adult reference ranges. Ranges vary slightly between labs and individuals - always interpret in context and use your local laboratory's reference values."),
                    b(
                        "Heart rate: 60-100 beats/min",
                        "Respiratory rate: 12-20 breaths/min",
                        "Blood pressure: around 90/60 to 120/80 mmHg (optimal near 120/80)",
                        "Temperature: 36.1-37.2 C (oral)",
                        "SpO2: 95-100 percent on air (lower targets in some chronic lung disease)",
                        "Capillary refill: under 2 seconds",
                        "Blood glucose (fasting): 3.9-5.5 mmol/L (70-99 mg/dL)",
                        "Haemoglobin: roughly 13.5-17.5 g/dL (men), 12.0-15.5 g/dL (women)",
                        "White cell count: 4-11 x10^9/L",
                        "Platelets: 150-400 x10^9/L",
                        "Sodium: 135-145 mmol/L; Potassium: 3.5-5.0 mmol/L",
                        "Urine output: at least 0.5 mL/kg/hour"
                    ),
                    h("Do not miss the sick patient"),
                    p("Trends matter more than single numbers. Rising respiratory rate, falling urine output and new confusion are early warning signs. Learn your hospital's early warning score (e.g. NEWS2) and escalate promptly."),
                    tip("Respiratory rate is the most sensitive and most neglected vital sign. Count it properly for a full 30-60 seconds.")
                )
            ),
            Topic(
                id = "basic-procedures",
                title = "Basic Procedures & Infection Control",
                summary = "Hand hygiene, PPE, injections, cannulation and suturing - the student level.",
                blocks = listOf(
                    p("Students perform procedures ONLY after teaching and with supervision. This is a study checklist, not a licence to practise."),
                    h("Hand hygiene - WHO 5 moments"),
                    b(
                        "Before touching a patient",
                        "Before a clean/aseptic procedure",
                        "After body fluid exposure risk",
                        "After touching a patient",
                        "After touching patient surroundings"
                    ),
                    h("Core procedure checklist skills"),
                    b(
                        "Venepuncture and IV cannulation: site selection, asepsis, sharps safety, labelling at the bedside.",
                        "Injections: intradermal, subcutaneous, intramuscular - sites, angles, aspiration rules per local policy.",
                        "Suturing: instrument handling, simple interrupted suture, knots, aftercare and removal timing.",
                        "Urinary catheterisation and NG tube insertion: indications, asepsis, confirmation of placement.",
                        "ECG recording: correct lead placement every time.",
                        "Peak flow, inhaler and spacer technique - you will teach these to patients."
                    ),
                    tip("Get signed off in your logbook early in each rotation. Do not leave procedural sign-offs to the last week.")
                )
            ),
            Topic(
                id = "bls",
                title = "Basic Life Support (Adult)",
                summary = "The recognition-and-CPR algorithm every student must know.",
                blocks = listOf(
                    p("This outline follows standard adult BLS teaching (AHA/Resuscitation Council style). Get formally certified and follow current local guidelines and in-hospital protocols."),
                    b(
                        "Safety first: check the scene is safe for you.",
                        "Check response: shake shoulders, ask loudly.",
                        "Shout for help; activate the emergency response / crash call and send for an AED.",
                        "Open the airway (head-tilt chin-lift) and check breathing for no more than 10 seconds.",
                        "Not breathing normally: start CPR. 30 compressions to 2 breaths if trained and willing; continuous compressions if not.",
                        "Compressions: centre of the chest, rate 100-120/min, depth 5-6 cm, full recoil, minimal interruptions.",
                        "Attach the AED as soon as it arrives and follow its prompts.",
                        "Continue until signs of life, trained help takes over, or you are exhausted."
                    ),
                    tip("Book a certified BLS course in your first clinical year and refresh it annually - skills fade fast.")
                )
            )
        )
    )

    // ------------------------------------------------------------------
    // 6. Exams
    // ------------------------------------------------------------------
    private val examsSection = Section(
        id = "exams",
        title = "Exam Success",
        description = "Study science, MCQs, OSCEs, vivas and exam-day craft",
        topics = listOf(
            Topic(
                id = "study-techniques",
                title = "Evidence-Based Study Techniques",
                summary = "Active recall and spaced repetition beat re-reading, every time.",
                blocks = listOf(
                    h("What actually works"),
                    b(
                        "Active recall: close the book and retrieve. Self-test constantly.",
                        "Spaced repetition: revisit material at increasing intervals (flashcard apps automate this).",
                        "Practice questions: the single best predictor of exam performance.",
                        "Interleaving: mix topics in a session rather than blocking one subject for hours.",
                        "Teaching others: if you cannot explain it simply, you do not know it yet.",
                        "Dual coding: pair words with diagrams, especially in anatomy and pathology."
                    ),
                    h("What feels productive but is not"),
                    b(
                        "Re-reading and highlighting - familiarity is not memory.",
                        "Copying notes verbatim.",
                        "Marathon passive video-watching without recall."
                    ),
                    tip("Plan the term backwards from the exam date. A realistic weekly plan you follow beats a perfect plan you abandon.")
                )
            ),
            Topic(
                id = "mcq-strategy",
                title = "MCQ Technique",
                summary = "How to squeeze marks out of single-best-answer papers.",
                blocks = listOf(
                    b(
                        "Read the last line (the actual question) first, then the vignette.",
                        "Underline the discriminators: age, timeline, key positive and negative findings.",
                        "Answer before looking at options when you can - it prevents anchoring.",
                        "Eliminate actively: most SBA questions have 2 plausible options; find the detail that separates them.",
                        "Flag and move on. Never burn 5 minutes on one question early in the paper.",
                        "Learn the exam's style: past papers from YOUR school are gold.",
                        "Review every question you got wrong AND every lucky guess - keep an error log.",
                        "Check whether negative marking applies before guessing strategy."
                    ),
                    tip("Do questions from week one of each block, not just in revision season. Retrieval practice builds durable memory.")
                )
            ),
            Topic(
                id = "osce-practicals",
                title = "OSCEs & Practical Exams",
                summary = "Stations reward rehearsal, structure and slick time management.",
                blocks = listOf(
                    b(
                        "Learn the marking scheme mindset: stations award marks for steps, not brilliance.",
                        "Open every station the same way: wash hands, introduce, consent, position, expose.",
                        "Narrate what you are doing - examiners cannot mark silence.",
                        "Practise with a timer and a friend reading the candidate instructions.",
                        "Rehearse the classic stations: cardio/respiratory/abdo/neuro exam, history stations, BP, ECG, cannulation, BLS, counselling.",
                        "If you blank: return to your structure. Say your framework out loud and the content follows.",
                        "Finish stations cleanly: thank, cover, wash hands, summarise.",
                        "Between stations, RESET. The last station is gone; the next one is full marks."
                    ),
                    tip("Form a weekly OSCE practice group in clinical years. Two hours a week from term start is worth more than a panicked weekend before exams.")
                )
            ),
            Topic(
                id = "viva",
                title = "Viva Voce",
                summary = "Thinking out loud, safely, under gentle interrogation.",
                blocks = listOf(
                    b(
                        "Structure answers: definition, then classification, then detail. Buy thinking time with structure.",
                        "Start with the common and the important, not the exotic.",
                        "When you do not know: say so, then reason aloud from principles. Safe reasoning scores; bluffing sinks you.",
                        "Know your final-year project and any case you present inside out.",
                        "Practise being asked 'why?' three times in a row on the same fact.",
                        "Dress professionally, greet the examiners, sit only when invited.",
                        "Never argue with an examiner. Acknowledge, clarify, move on."
                    ),
                    tip("Examiners probe until they find your edge - reaching 'I don't know' is normal. What matters is that everything before it was organised and safe.")
                )
            ),
            Topic(
                id = "exam-day",
                title = "Exam-Day Checklist",
                summary = "Remove every avoidable way to lose marks.",
                blocks = listOf(
                    b(
                        "Confirm date, time, venue and format the night before - twice.",
                        "Pack: student ID, exam number, pens (spares), calculator if allowed, water, watch.",
                        "For OSCEs: white coat per policy, penlight, stethoscope, tape measure, tendon hammer if required.",
                        "Sleep before the exam beats one more hour of cramming.",
                        "Eat something. Low blood glucose is a silent mark-killer.",
                        "Arrive 30 minutes early; find the room, use the bathroom.",
                        "Read instructions on the paper: rubric changes cost real marks.",
                        "After the exam: no post-mortems in the corridor. The next paper deserves a fresh head."
                    )
                )
            )
        )
    )

    // ------------------------------------------------------------------
    // 7. Ethics
    // ------------------------------------------------------------------
    private val ethicsSection = Section(
        id = "ethics",
        title = "Ethics & Professionalism",
        description = "Principles, consent, confidentiality and digital conduct",
        topics = listOf(
            Topic(
                id = "four-principles",
                title = "The Four Principles",
                summary = "Autonomy, beneficence, non-maleficence, justice - the ethicist's toolkit.",
                blocks = listOf(
                    b(
                        "Autonomy: respect the patient's right to decide, given capacity and information.",
                        "Beneficence: act in the patient's best interests.",
                        "Non-maleficence: first, do no harm - weigh risks against benefits.",
                        "Justice: treat fairly and distribute resources fairly."
                    ),
                    h("Using them in exams and on the ward"),
                    p("When principles conflict (e.g. a refusing patient), name the conflict explicitly, assess capacity, explore reasons, involve seniors and document. Ethics questions reward a structured approach, not a 'right answer'."),
                    tip("Capacity is decision-specific and time-specific. A patient may lack capacity for one decision now, and have it for another, or for the same one later.")
                )
            ),
            Topic(
                id = "consent-confidentiality",
                title = "Consent & Confidentiality",
                summary = "The two rules you will use every single day.",
                blocks = listOf(
                    h("Valid consent needs"),
                    b(
                        "Capacity to decide",
                        "Sufficient information: nature, benefits, material risks, alternatives (including doing nothing)",
                        "Voluntariness - no coercion",
                        "For students: explicit permission to be present, examine or participate. Patients may decline, and care must not suffer."
                    ),
                    h("Confidentiality"),
                    b(
                        "Patient information belongs to the patient. Share only within the care team, or with consent.",
                        "Exceptions exist (serious risk of harm, legal requirements) - involve seniors before breaching.",
                        "No case discussions in lifts, cafeterias or social media. Ever.",
                        "Access only the records you need for your role - audit trails exist."
                    ),
                    tip("As a student, when in doubt about consent or disclosure: pause and ask your supervisor. Nobody has ever been failed for asking.")
                )
            ),
            Topic(
                id = "digital-professionalism",
                title = "Social Media & Digital Professionalism",
                summary = "Your online conduct is part of your fitness to practise.",
                blocks = listOf(
                    b(
                        "Never post identifiable patient information, images or stories - even 'anonymised' anecdotes can identify.",
                        "No photos in clinical areas without explicit institutional permission.",
                        "Assume everything you post will be read by a patient, a dean and a regulator.",
                        "Keep personal and professional accounts separate; set privacy settings, but know they are not a shield.",
                        "Do not give individual medical advice online.",
                        "Report unprofessional content by colleagues through proper channels rather than piling on publicly.",
                        "Messaging apps: use approved platforms for clinical information, never full patient identifiers on personal chats."
                    ),
                    tip("Regulators increasingly treat online misconduct exactly like ward misconduct. A deleted post is never really deleted.")
                )
            )
        )
    )

    // ------------------------------------------------------------------
    // 8. Wellbeing
    // ------------------------------------------------------------------
    private val wellbeingSection = Section(
        id = "wellbeing",
        title = "Wellbeing",
        description = "Stress, time management and looking after yourself",
        topics = listOf(
            Topic(
                id = "stress-burnout",
                title = "Stress & Burnout",
                summary = "Normal pressure vs warning signs - and what to do.",
                blocks = listOf(
                    p("Medical school is demanding; feeling stretched at times is normal. Burnout is different: persistent exhaustion, cynicism about your work, and feeling ineffective. It deserves action, not endurance."),
                    h("Warning signs"),
                    b(
                        "Sleep that does not restore you, weeks on end",
                        "Dreading the ward or losing empathy for patients",
                        "Withdrawing from friends and things you enjoy",
                        "Persistent low mood, anxiety, or feeling hopeless",
                        "Using alcohol or other substances to cope"
                    ),
                    h("What helps"),
                    b(
                        "Talk early: a friend, mentor, student support service or your doctor.",
                        "Protect the basics: sleep, food, movement, daylight, people.",
                        "Boundaries: one full rest block per week is training, not laziness.",
                        "Your university's counselling and occupational health services exist for exactly this.",
                        "If you ever have thoughts of harming yourself, seek help the same day - emergency services, a crisis line, or someone you trust. You matter more than any exam."
                    ),
                    tip("Seeking help is a professional strength. Regulators and schools want supported students, not silent suffering.")
                )
            ),
            Topic(
                id = "time-management",
                title = "Time Management",
                summary = "A weekly system that survives contact with a busy rotation.",
                blocks = listOf(
                    b(
                        "Plan weekly, not daily: block lectures, ward time, then 2-3 focused study blocks per day maximum.",
                        "Timebox: 50 minutes on, 10 off. Three honest hours beat eight fake ones.",
                        "Daily top-3: each morning pick the three outcomes that would make the day a win.",
                        "Batch admin (emails, logbook, forms) into one slot.",
                        "Use dead time: flashcards on the bus, podcasts between clinics.",
                        "Say no strategically: every yes to a committee is a no to something else.",
                        "Review Friday: what worked, what slipped, adjust next week."
                    ),
                    tip("Schedule rest FIRST, like a lecture. What is left is your real study capacity - plan within it and you will actually finish the plan.")
                )
            )
        )
    )

    // ------------------------------------------------------------------
    // 9. Resources & career
    // ------------------------------------------------------------------
    private val resourcesSection = Section(
        id = "resources",
        title = "Resources & Career",
        description = "Textbooks, digital tools and the road after MBBS",
        topics = listOf(
            Topic(
                id = "textbooks",
                title = "The Classic Textbook Shelf",
                summary = "One core book per subject - the widely used standards.",
                blocks = listOf(
                    b(
                        "Anatomy: Gray's Anatomy for Students + Netter Atlas (Moore's Clinically Oriented Anatomy as an alternative)",
                        "Physiology: Guyton and Hall; Costanzo for review",
                        "Biochemistry: Lippincott Illustrated Reviews; Harper's for reference",
                        "Pathology: Robbins and Cotran Pathologic Basis of Disease",
                        "Pharmacology: Rang and Dale; Katzung for reference; Lippincott for review",
                        "Microbiology: Jawetz or Murray; clinical infectious disease texts for the ward",
                        "Medicine: Kumar and Clark or Davidson's; Oxford Handbook for the pocket",
                        "Surgery: Bailey and Love; SRB's Manual for exam-season revision",
                        "OBGYN: Ten Teachers (Obstetrics and Gynaecology) or Williams",
                        "Paediatrics: Nelson Essentials; Illustrated Textbook of Paediatrics",
                        "Community Medicine: Park's Textbook (where used)",
                        "Clinical examination: Macleod's or Hutchinson's"
                    ),
                    tip("Library first, buy second. Editions one behind the latest are usually 90 percent identical and much cheaper.")
                )
            ),
            Topic(
                id = "digital-tools",
                title = "Digital Tools That Earn Their Place",
                summary = "A short, honest list of app categories that help.",
                blocks = listOf(
                    b(
                        "Spaced-repetition flashcards (e.g. Anki) - the backbone of many students' revision.",
                        "Question banks aligned to your school or licensing exam - start early, review errors.",
                        "Point-of-care references (e.g. UpToDate, BMJ Best Practice) - often free via your university.",
                        "Drug formularies (e.g. BNF where applicable) - always check doses against local policy.",
                        "Video libraries for concepts you cannot crack from text (Osmosis-style platforms, YouTube channels run by clinicians).",
                        "Anatomy 3D apps - useful for spatial relationships, not a replacement for dissection.",
                        "A reference manager (Zotero) from your first research project."
                    ),
                    tip("Tools multiply effort; they do not replace it. One flashcard habit plus one question bank beats ten half-used apps.")
                )
            ),
            Topic(
                id = "career-paths",
                title = "Life After MBBS",
                summary = "Internship, licensing exams and choosing a specialty.",
                blocks = listOf(
                    h("The immediate steps"),
                    b(
                        "Internship/housemanship: choose rotations that test specialties you are curious about.",
                        "Full registration with your national medical council after internship.",
                        "Keep your logbook, certificates and references organised from day one - future you will be grateful."
                    ),
                    h("Common pathways"),
                    b(
                        "Residency/specialty training at home - research entry requirements early.",
                        "Licensing exams abroad: USMLE (USA), PLAB/UKMLA (UK), AMC (Australia), MCC (Canada) - each has multi-year timelines and costs; plan from mid-school if interested.",
                        "Research and academia: start with a small audit or case report with a supportive supervisor.",
                        "Public health, health policy and global health routes (MPH and beyond).",
                        "Non-clinical careers: pharma, health tech, education, informatics."
                    ),
                    h("Choosing a specialty"),
                    p("Notice which patients you read about voluntarily, which rotations you would repeat, and which consultants' lives you would want. Try taster weeks, electives and student societies before deciding."),
                    tip("Careers are built from small, early investments: one audit, one presentation, one mentor. Start collecting them now.")
                )
            )
        )
    )

    // Declared last: object properties initialize in declaration order, so the
    // aggregate lists must come after all the private section vals above.
    val sections: List<Section> = listOf(
        startSection,
        preclinicalSection,
        paraclinicalSection,
        clinicalSection,
        skillsSection,
        examsSection,
        ethicsSection,
        wellbeingSection,
        resourcesSection
    )

    val allTopics: List<Topic> = sections.flatMap { it.topics }
}
