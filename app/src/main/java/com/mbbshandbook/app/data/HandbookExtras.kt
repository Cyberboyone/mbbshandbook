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
 * Additional topics appended to each handbook section. Same educational
 * guidance rules apply: general student education, not clinical protocols.
 */
object HandbookExtras {

    fun forSection(sectionId: String): List<Topic> = when (sectionId) {
        "start" -> startExtra
        "preclinical" -> preclinicalExtra
        "paraclinical" -> paraclinicalExtra
        "clinical" -> clinicalExtra
        "skills" -> skillsExtra
        "exams" -> examsExtra
        "ethics" -> ethicsExtra
        "wellbeing" -> wellbeingExtra
        "resources" -> resourcesExtra
        "mnemonics" -> mnemonicsExtra
        "emergencies" -> emergenciesExtra
        else -> emptyList()
    }

    private val startExtra = listOf(
        Topic(
            id = "clinical-etiquette",
            title = "Clinical Etiquette 101",
            summary = "How to behave on the wards so teams want you around.",
            blocks = listOf(
                p("Clinical placements are assessed by humans. Being useful, respectful and safe matters as much as what you know. These unwritten rules will carry you through every rotation."),
                h("Before you arrive"),
                b(
                    "Find out the team's routine: round times, handover, theatre days, teaching slots.",
                    "Introduce yourself to the nurse in charge - they run the ward and can make your placement.",
                    "Dress to your hospital's code: clean, professional, ID visible, bare below the elbows where required."
                ),
                h("On the ward"),
                b(
                    "Always introduce yourself to patients as a student and ask permission before examining.",
                    "Never examine without consent; intimate examinations need a chaperone - offer one every time.",
                    "Ask before touching equipment, notes or samples. When unsure, ASK - never guess.",
                    "Offer to help: observations, blood forms, presenting a patient, fetching results.",
                    "Hand hygiene before and after every patient - visibly, so the team sees you do it."
                ),
                h("In theatre"),
                b(
                    "Introduce yourself to the scrub nurse and theatre team BEFORE the list starts.",
                    "Ask where to stand and what you may touch. Sterile fields are sacred.",
                    "If you contaminate anything, say so immediately - honesty is respected, concealment is not.",
                    "Do not lean on the patient, the table or the sterile trolley. Keep hands above waist when scrubbed."
                ),
                h("Professional habits"),
                b(
                    "Phones away and silent during rounds, teaching and patient conversations.",
                    "Never discuss patients in lifts, canteens or social media - confidentiality is absolute.",
                    "If you make a mistake, tell your supervisor early. Cover-ups end careers; honesty rarely does.",
                    "Thank the team at the end of the placement. Medicine is a small world."
                ),
                tip("The student who is punctual, honest, helpful and curious gets taught. The one who is arrogant or unsafe gets ignored. Be the first kind.")
            )
        ),
        Topic(
            id = "peer-learning",
            title = "Peer Learning & Teaching",
            summary = "Teaching others is the fastest way to learn - and it builds your career.",
            blocks = listOf(
                p("The best way to learn something is to teach it. Study groups, near-peer teaching and journal clubs turn passive reading into active mastery - and teaching experience counts in every specialty application."),
                h("Build a study group that works"),
                b(
                    "3-5 people, same year, meeting weekly with a fixed agenda.",
                    "Rotate topics: each person teaches one topic for 10 minutes, then the group questions them.",
                    "Do questions together: past papers, question banks, OSCE stations - explain WHY each answer is right.",
                    "Keep it kind: the group is for learning, not competing."
                ),
                h("Near-peer teaching"),
                b(
                    "Offer to teach junior students skills you have mastered (suturing, examinations, ECG basics).",
                    "Prepare properly: a teaching session needs objectives, a plan and a way to check learning.",
                    "Collect feedback forms and a teaching log - evidence for your portfolio.",
                    "Consider a teaching qualification or course if your school offers one."
                ),
                h("Journal clubs and case discussions"),
                b(
                    "Pick one paper or case per meeting; one person presents, everyone critiques.",
                    "Learn critical appraisal basics: study design, bias, endpoints, statistics.",
                    "Presenting cases teaches you to think aloud - exactly what exams and ward rounds demand."
                ),
                tip("Start a small teaching session this month. Even teaching one friend one topic counts - and you will remember it forever.")
            )
        )
    )

    private val preclinicalExtra = listOf(
        Topic(
            id = "embryology",
            title = "Embryology Essentials",
            summary = "Development explains adult anatomy - and congenital disease.",
            blocks = listOf(
                p("Embryology is not trivia: it explains why structures lie where they do, why nerves take strange routes, and how congenital anomalies arise. Learn the big events, then attach one clinical correlation to each."),
                h("The first weeks"),
                b(
                    "Week 1: fertilisation, cleavage, blastocyst, implantation.",
                    "Week 2: bilaminar disc (epiblast/hypoblast); 'the week of twos'.",
                    "Week 3: gastrulation (three germ layers), notochord, neurulation begins; 'the week of threes'.",
                    "Weeks 4-8: organogenesis - the period most vulnerable to teratogens."
                ),
                h("Germ layer derivatives"),
                b(
                    "Ectoderm: skin epidermis, nervous system, neural crest derivatives.",
                    "Mesoderm: muscle, bone, connective tissue, cardiovascular, kidneys, gonads.",
                    "Endoderm: gut lining, liver, pancreas, respiratory epithelium, bladder lining."
                ),
                h("High-yield clinical links"),
                b(
                    "Neural tube defects (spina bifida, anencephaly) - folate before conception prevents most.",
                    "Pharyngeal arches: arch 1 = mandible/mastication (V3), arch 2 = facial expression (VII), arch 3 = stylopharyngeus (IX), arches 4/6 = larynx (X).",
                    "Recurrent laryngeal nerve's loop is explained by arch artery development.",
                    "Midgut rotation explains malrotation and volvulus; testicular descent explains inguinal hernias and referred pain.",
                    "Fetal shunts: ductus venosus, foramen ovale, ductus arteriosus - and what happens when they fail to close."
                ),
                tip("For every embryology fact, ask: 'What goes wrong if this fails?' That question turns memorisation into understanding.")
            )
        ),
        Topic(
            id = "histology",
            title = "Histology Made Simple",
            summary = "Recognise tissues by pattern, not by memorising every slide.",
            blocks = listOf(
                p("Histology exams test pattern recognition. Learn the four basic tissues, then the signature features of each organ. Always orient yourself: low power first, then high power."),
                h("The four basic tissues"),
                b(
                    "Epithelium: classified by layers (simple/stratified) and shape (squamous/cuboidal/columnar); look for surface specialisations (cilia, keratin, microvilli).",
                    "Connective tissue: cells scattered in matrix - loose, dense, adipose, cartilage, bone, blood.",
                    "Muscle: skeletal (striated, peripheral nuclei), cardiac (striated, central nuclei, intercalated discs), smooth (no striations, spindle cells).",
                    "Nervous tissue: neurons with processes, glial support cells."
                ),
                h("Organ signatures"),
                b(
                    "Thyroid: follicles of colloid. Adrenal: cortex zones + medulla.",
                    "Liver: hexagonal lobules, central vein, portal triads. Pancreas: acini + islets.",
                    "Kidney: glomeruli, tubules, collecting ducts; cortex vs medulla.",
                    "Lung: alveoli, bronchioles; trachea has C-shaped cartilage and respiratory epithelium.",
                    "GI tract: four layers (mucosa, submucosa, muscularis, serosa/adventitia) - oesophagus vs stomach vs intestine differ in mucosa.",
                    "Lymph node: cortex follicles + medulla; spleen: white/red pulp; thymus: Hassall's corpuscles."
                ),
                h("How to study slides"),
                b(
                    "Draw each slide schematically from memory - labels are the learning.",
                    "Compare look-alikes side by side (e.g. cardiac vs skeletal muscle).",
                    "Use your school's virtual microscopy or a histology atlas quiz mode.",
                    "In exams, describe what you SEE before naming the organ."
                ),
                tip("If you can name the tissue type and one special feature, you can usually identify the organ. Pattern first, detail second.")
            )
        ),
        Topic(
            id = "neuroanatomy",
            title = "Neuroanatomy Basics",
            summary = "Tracts, cranial nerves and blood supply - the map of neurology.",
            blocks = listOf(
                p("Neuroanatomy becomes easy once you learn three maps: the major tracts, the cranial nerves, and the brain's blood supply. Every neurological deficit can be localised with them."),
                h("The big tracts"),
                b(
                    "Corticospinal (motor): cortex → internal capsule → brainstem (decussates in medulla) → cord. UMN lesion = weakness, spasticity, hyperreflexia, upgoing plantar.",
                    "Dorsal columns (fine touch, vibration, proprioception): ascend ipsilaterally, decussate in medulla.",
                    "Spinothalamic (pain, temperature): decussate at the cord level, ascend contralaterally.",
                    "Brown-Séquard (hemisection): ipsilateral motor + dorsal column loss, contralateral pain/temperature loss below the lesion."
                ),
                h("Cranial nerve quick map"),
                b(
                    "Midbrain: III, IV. Pons: V, VI, VII, VIII. Medulla: IX, X, XI, XII.",
                    "Pure sensory: I, II, VIII. Pure motor: III, IV, VI, XI, XII. Mixed: V, VII, IX, X.",
                    "Learn each nerve's test and its lesion pattern - they are exam favourites."
                ),
                h("Blood supply and stroke syndromes"),
                b(
                    "ACA: contralateral leg weakness/sensory loss. MCA: face/arm weakness, aphasia (dominant), neglect (non-dominant). PCA: contralateral homonymous hemianopia.",
                    "Brainstem strokes: crossed signs - ipsilateral cranial nerve palsy with contralateral limb signs.",
                    "Circle of Willis and berry aneurysms: thunderclap headache = subarachnoid haemorrhage until proven otherwise."
                ),
                h("Localisation drill"),
                b(
                    "Ask four questions: Is it UMN or LMN? Which side? Which level (cortex, brainstem, cord, root, plexus, nerve, muscle)? Which tract pattern?",
                    "Practise on every neurology patient you meet - present your localisation out loud."
                ),
                tip("Draw the sensory and motor pathways as three-neuron chains from memory. If you can draw them, you can localise any lesion.")
            )
        )
    )

    private val paraclinicalExtra = listOf(
        Topic(
            id = "immunology",
            title = "Immunology Basics",
            summary = "Innate vs adaptive, hypersensitivity and the clinical links.",
            blocks = listOf(
                p("Immunology explains infection defence, autoimmunity, allergy, transplant rejection and vaccination. Learn the architecture first, then the four hypersensitivity types - they appear in every exam."),
                h("Innate vs adaptive"),
                b(
                    "Innate: fast, non-specific - barriers, neutrophils, macrophages, complement, NK cells.",
                    "Adaptive: slow, specific, memory - T cells (cell-mediated) and B cells/antibodies (humoral).",
                    "Antigen-presenting cells (dendritic cells) bridge the two systems.",
                    "Primary response is slow (IgM first); secondary response is fast and strong (IgG) - the basis of vaccination."
                ),
                h("The four hypersensitivity types (ACID)"),
                b(
                    "Type I - Anaphylactic (IgE, mast cells): anaphylaxis, asthma, urticaria.",
                    "Type II - Cytotoxic (IgG/IgM against cells): autoimmune haemolytic anaemia, Goodpasture's, rheumatic fever.",
                    "Type III - Immune complex: SLE, post-streptococcal GN, serum sickness.",
                    "Type IV - Delayed (T-cell mediated): contact dermatitis, TB granulomas, transplant rejection, Mantoux test."
                ),
                h("Clinical immunology you must know"),
                b(
                    "Immunodeficiency patterns: recurrent bacterial infections suggest antibody/complement problems; viral/fungal suggest T-cell problems.",
                    "Autoimmunity: loss of self-tolerance; examples - type 1 diabetes, rheumatoid arthritis, SLE, Hashimoto's.",
                    "Transplant rejection: hyperacute (preformed antibodies), acute (T-cell), chronic (fibrosis).",
                    "Vaccines: live attenuated vs inactivated vs subunit/mRNA - and why immunosuppressed patients avoid live vaccines."
                ),
                tip("Mnemonic for hypersensitivity: ACID - Anaphylactic, Cytotoxic, Immune complex, Delayed. Attach two examples to each and you are exam-ready.")
            )
        ),
        Topic(
            id = "genetics",
            title = "Genetics & Inheritance",
            summary = "Inheritance patterns, common disorders and genetic testing basics.",
            blocks = listOf(
                p("Genetics questions follow patterns. Learn the inheritance modes, recognise the classic disorders, and understand what modern genetic tests can and cannot tell you."),
                h("Inheritance patterns"),
                b(
                    "Autosomal dominant: every generation affected, 50% risk to offspring - Huntington's, Marfan's, achondroplasia, familial hypercholesterolaemia, ADPKD.",
                    "Autosomal recessive: skips generations, consanguinity increases risk, 25% risk - cystic fibrosis, sickle cell, thalassaemia, phenylketonuria, Tay-Sachs.",
                    "X-linked recessive: males affected, carrier mothers - haemophilia A/B, Duchenne muscular dystrophy, G6PD deficiency, red-green colour blindness.",
                    "Mitochondrial: maternal transmission only - MELAS, Leber's optic neuropathy.",
                    "Chromosomal: trisomy 21 (Down), 18 (Edwards), 13 (Patau); Turner (45,X), Klinefelter (47,XXY)."
                ),
                h("Key concepts"),
                b(
                    "Penetrance (does the genotype show?) vs expressivity (how severely?).",
                    "Anticipation: earlier/severe disease in successive generations (trinucleotide repeats - Huntington's, myotonic dystrophy).",
                    "Imprinting: expression depends on parent of origin (Prader-Willi vs Angelman).",
                    "Multifactorial: genes + environment - diabetes, hypertension, cleft lip, neural tube defects."
                ),
                h("Genetic testing in practice"),
                b(
                    "Karyotype: large chromosomal changes. FISH/microarray: deletions/duplications. Sequencing: single genes to whole exome/genome.",
                    "PCR amplifies DNA; used in infection diagnostics too.",
                    "Genetic counselling: non-directive, consent, implications for family members.",
                    "Screening ethics: who to test, what to do with uncertain results, insurance/privacy concerns."
                ),
                tip("Draw a pedigree for every genetics question. Squares/circles, shaded/unshaded - the inheritance pattern usually reveals itself.")
            )
        ),
        Topic(
            id = "lab-results",
            title = "Interpreting Lab Results",
            summary = "Read FBC, U&Es, LFTs and CRP like a clinician, not a list.",
            blocks = listOf(
                p("Lab results tell stories. Learn the normal ranges, then the PATTERNS - microcytic vs macrocytic anaemia, pre-renal vs renal injury, hepatocellular vs cholestatic liver tests."),
                h("Full blood count (FBC)"),
                b(
                    "Haemoglobin: low = anaemia. Classify by MCV: microcytic (iron deficiency, thalassaemia), normocytic (chronic disease, acute blood loss, haemolysis), macrocytic (B12/folate, alcohol, hypothyroid).",
                    "WCC: high = infection/inflammation/stress/steroids; neutrophilia = bacterial, lymphocytosis = viral, eosinophilia = parasites/allergy.",
                    "Platelets: low = bleeding risk (ITP, DIC, marrow failure); high = reactive (infection, iron deficiency) or myeloproliferative."
                ),
                h("Urea & electrolytes (U&Es)"),
                b(
                    "Creatinine/eGFR: kidney function; trend matters more than a single value.",
                    "Urea:creatinine ratio suggests pre-renal (dehydration, GI bleed) vs intrinsic renal injury.",
                    "Sodium: water balance (think volume status). Potassium: cardiac risk - ECG changes with hyperkalaemia are an emergency.",
                    "Always interpret with the clinical picture: vomiting, diarrhoea, drugs (ACEi, diuretics), fluids."
                ),
                h("Liver function tests (LFTs)"),
                b(
                    "Hepatocellular pattern: ALT/AST dominate - viral hepatitis, drugs (paracetamol), ischaemia.",
                    "Cholestatic pattern: ALP/GGT dominate - gallstones, tumours, primary biliary disease.",
                    "Bilirubin: pre-hepatic (haemolysis, unconjugated), hepatic, post-hepatic (obstruction, conjugated, pale stools/dark urine).",
                    "Albumin and INR reflect synthetic function - the liver's real performance."
                ),
                h("Inflammatory markers and more"),
                b(
                    "CRP rises fast with inflammation/infection; ESR slower and less specific.",
                    "Troponin: myocardial injury - interpret with ECG and story, and with serial values.",
                    "Glucose/HbA1c: diagnosis vs long-term control.",
                    "Coagulation: PT/INR (extrinsic - warfarin, liver), APTT (intrinsic - heparin, haemophilia)."
                ),
                tip("Never interpret a result alone: patient + trend + pattern. A 'normal' result in a sick patient deserves a second look.")
            )
        )
    )

    private val clinicalExtra = listOf(
        Topic(
            id = "ecg-basics",
            title = "ECG Basics",
            summary = "A systematic approach to any 12-lead ECG.",
            blocks = listOf(
                p("Every doctor must read ECGs systematically. Use the same sequence every time so nothing is missed: rate, rhythm, axis, intervals, then waves and segments."),
                h("The systematic read"),
                b(
                    "1. Check calibration and leads (25 mm/s, 10 mm/mV; aVR should be negative).",
                    "2. Rate: 300 ÷ large squares between R waves (regular); count R waves in 10 s × 6 (irregular).",
                    "3. Rhythm: P before every QRS? Regular? Sinus if yes.",
                    "4. Axis: I and aVF positive = normal; look at I/II/III for deviation.",
                    "5. Intervals: PR 120-200 ms; QRS <120 ms; QTc <440 ms (men) / <460 ms (women).",
                    "6. Waves: P (atrial), Q (pathological if deep/wide), R progression, T (inversion ischaemia), U (hypokalaemia).",
                    "7. Segments: ST elevation/depression, T changes - territory matters (inferior II/III/aVF; lateral I/aVL/V5-6; anterior V1-4)."
                ),
                h("Must-not-miss patterns"),
                b(
                    "STEMI: ST elevation in contiguous leads - activate the cath lab pathway.",
                    "AF: irregularly irregular, no P waves. Flutter: sawtooth baseline.",
                    "Heart blocks: 1st (long PR), 2nd Mobitz I (progressive PR then drop), Mobitz II (fixed PR, sudden drop), 3rd (complete dissociation).",
                    "VT: broad complex tachycardia - treat as VT until proven otherwise.",
                    "Hyperkalaemia: tall tented T waves → flat P → wide QRS → sine wave.",
                    "PE: sinus tachycardia most common; S1Q3T3 is classic but insensitive."
                ),
                h("How to get good"),
                b(
                    "Read 5 ECGs a week with a systematic approach and check against the report.",
                    "Use an ECG book or app with real tracings and explanations.",
                    "In OSCEs, say your sequence out loud - it earns marks even if you miss a finding."
                ),
                tip("If you remember one rule: a broad-complex tachycardia is VT until proven otherwise. Never treat it as SVT with aberrancy casually.")
            )
        ),
        Topic(
            id = "cxr-basics",
            title = "Chest X-Ray Basics",
            summary = "ABCDE review of the chest film - every time.",
            blocks = listOf(
                p("The chest X-ray is the most requested image in medicine. Read it the same way every time so you never miss the pneumothorax hiding behind the 'normal' label."),
                h("Before you look"),
                b(
                    "Check: patient name, date, projection (PA/AP), rotation (clavicles symmetric), inspiration (8-10 posterior ribs), penetration (vertebrae just visible behind heart)."
                ),
                h("ABCDE review"),
                b(
                    "A - Airway: trachea central? Deviated TOWARD collapse/fibrosis, AWAY from effusion/tension pneumothorax.",
                    "B - Breathing: lung fields symmetric? Compare zones; look for consolidation, masses, pneumothorax (absent lung markings, visible edge), effusion (meniscus, blunted angle).",
                    "C - Circulation: heart size (CTR <50% on PA), mediastinum (widened?), hila, aortic knuckle.",
                    "D - Diaphragm: both domes, costophrenic angles sharp; free air under diaphragm = perforation.",
                    "E - Everything else: bones (rib fractures, metastases), soft tissues, lines/tubes/ECG leads, review areas (apices, behind heart, below diaphragm)."
                ),
                h("Classic patterns"),
                b(
                    "Consolidation: air bronchograms, lobar opacity - pneumonia.",
                    "Pulmonary oedema: bat-wing opacities, Kerley B lines, upper-lobe diversion, cardiomegaly, effusions ('ABCDE' of heart failure).",
                    "Pneumothorax: visceral pleural line with no markings beyond; tension = mediastinal shift away.",
                    "Effusion: white-out with meniscus; massive effusion shifts mediastinum away.",
                    "Collapse: volume loss, shift toward the lesion, elevated hemidiaphragm."
                ),
                tip("Always check the review areas last: apices (Pancoast, TB), behind the heart (left lower lobe collapse), and under the diaphragm (free air). Misses live there.")
            )
        ),
        Topic(
            id = "abg-interpretation",
            title = "ABG Interpretation",
            summary = "Acid-base in five steps, with compensation and causes.",
            blocks = listOf(
                p("Arterial blood gases look intimidating but follow a fixed five-step method. Practise on real results until it becomes automatic."),
                h("Normal values"),
                b(
                    "pH 7.35-7.45; PaCO2 4.7-6.0 kPa (35-45 mmHg); HCO3 22-26 mmol/L; PaO2 10-13 kPa (75-100 mmHg) on air; base excess -2 to +2."
                ),
                h("The five steps"),
                b(
                    "1. pH: acidaemia (<7.35) or alkalaemia (>7.45)?",
                    "2. PaCO2: respiratory component - high CO2 = acidosis, low CO2 = alkalosis.",
                    "3. HCO3: metabolic component - low = acidosis, high = alkalosis.",
                    "4. Which matches the pH? That is the primary disorder.",
                    "5. Compensation: is the other system moving in the expected direction? Fully compensated = pH normal but both abnormal; mixed disorders exist."
                ),
                h("Causes"),
                b(
                    "Respiratory acidosis (high CO2): COPD, severe asthma, opioids, neuromuscular failure, obesity hypoventilation.",
                    "Respiratory alkalosis (low CO2): anxiety/hyperventilation, PE, early sepsis, pregnancy, altitude.",
                    "Metabolic acidosis (low HCO3): check anion gap - high gap = lactate (sepsis, shock), ketones (DKA), renal failure, toxins; normal gap = diarrhoea, renal tubular acidosis.",
                    "Metabolic alkalosis (high HCO3): vomiting, diuretics, hyperaldosteronism."
                ),
                h("Oxygenation"),
                b(
                    "Type 1 respiratory failure: low O2, normal/low CO2 (V/Q mismatch - pneumonia, PE, oedema).",
                    "Type 2 respiratory failure: low O2 AND high CO2 (ventilatory failure - COPD, exhaustion, neuromuscular)."
                ),
                tip("Anion gap = Na+ - (Cl- + HCO3-); normal ~8-12. A high-gap acidosis has MUDPILES-style causes; a normal-gap acidosis is usually diarrhoea or RTA.")
            )
        ),
        Topic(
            id = "ward-calls",
            title = "Common Ward Calls",
            summary = "What to do when the nurse pages you at 2 am.",
            blocks = listOf(
                p("Sooner or later you will be the first responder to a ward problem. These frameworks keep you safe: assess the patient yourself, use ABCDE, and escalate early. Students act within their competence and always involve a senior."),
                h("The universal approach"),
                b(
                    "See the patient yourself - never manage by phone alone.",
                    "ABCDE assessment and full observations first.",
                    "Check the chart: recent events, drugs, fluids, trends.",
                    "Do the basics: oxygen if hypoxic, IV access if unwell, glucose if altered, ECG if cardiac symptoms.",
                    "Escalate with SBAR: who you are, the problem, what you found, what you need."
                ),
                h("Classic calls"),
                b(
                    "'Patient is breathless': sit up, SpO2, examine chest, consider pulmonary oedema/PE/pneumonia/asthma; oxygen, ECG, CXR, escalate.",
                    "'Chest pain': ABCDE, ECG within minutes, troponin, analgesia per protocol, think ACS/PE/dissection.",
                    "'Low urine output': assess volume status, check catheter for blockage, fluid challenge if appropriate, review nephrotoxics, escalate if persistent.",
                    "'Fever/rigors': cultures (blood, urine, lines), examine for source, sepsis screen, antibiotics per local policy if septic.",
                    "'Agitated/confused': check glucose, oxygen, pain, retention, drugs; consider delirium causes; de-escalate, never restrain alone.",
                    "'Fallen patient': assess for injury (head, hip), observations, neuro checks if head strike, document and escalate."
                ),
                tip("The safest sentence in medicine: 'I'm concerned about this patient and I need a senior review.' No one will criticise you for saying it early.")
            )
        )
    )

    private val skillsExtra = listOf(
        Topic(
            id = "suturing-wound-care",
            title = "Suturing & Wound Care",
            summary = "Close wounds safely: assessment, technique and aftercare.",
            blocks = listOf(
                p("Wound closure is a core student skill. The suturing is the easy part - assessment, anaesthesia, cleaning and follow-up are where safety lives. Practise on a kit, then do supervised real closures."),
                h("Assess before you close"),
                b(
                    "History: mechanism, time since injury, contamination, tetanus status, comorbidities (diabetes, steroids, anticoagulants).",
                    "Examine: depth, foreign bodies, involvement of tendons/nerves/vessels/joints - distal neurovascular status ALWAYS documented.",
                    "Do not primarily close: heavily contaminated wounds, bites (many), delayed presentations, infected wounds - consider delayed closure.",
                    "Imaging if foreign body or fracture suspected."
                ),
                h("The procedure"),
                b(
                    "Consent, position, lighting, assistant, equipment ready.",
                    "Clean and irrigate thoroughly - dilution is the solution to pollution.",
                    "Local anaesthetic (e.g. lidocaine) - know max doses and toxicity signs; adrenaline avoided in end-artery areas per local policy.",
                    "Debride devitalised tissue; explore for foreign bodies.",
                    "Close in layers if deep; simple interrupted sutures for skin; evert edges; equal spacing and tension.",
                    "Dress, document (including neurovascular status), give wound care advice."
                ),
                h("Aftercare and removal"),
                b(
                    "Keep clean and dry initially; watch for infection (redness, swelling, pus, fever).",
                    "Tetanus prophylaxis per status and wound type.",
                    "Suture removal: face ~5 days, scalp ~7, trunk ~7-10, limbs ~10-14, over joints ~14 (local guidance varies).",
                    "Return sooner if signs of infection, wound breakdown or numbness."
                ),
                tip("Practise instrument handling and knot-tying on a suture pad until your hands are bored. Then every real wound feels familiar.")
            )
        ),
        Topic(
            id = "catheters-ng-tubes",
            title = "Catheters & NG Tubes",
            summary = "Indications, insertion principles and safety checks.",
            blocks = listOf(
                p("Urinary catheters and nasogastric tubes are common procedures with serious complications if done carelessly. Learn indications, aseptic technique and the mandatory safety checks."),
                h("Urinary catheters"),
                b(
                    "Indications: retention, strict fluid balance in critical illness, perioperative care, palliative comfort, some surgeries. NOT for convenience or incontinence alone.",
                    "Aseptic technique, appropriate size (usually 12-14 Fr), generous lubricant/anaesthetic gel.",
                    "Male: stretch the penis upward, insert to the hilt before inflating the balloon - urine must flow first.",
                    "Female: identify the urethra carefully; if unsure, ask for help - never force.",
                    "NEVER inflate the balloon without urine return; if resistance or pain, stop and call urology.",
                    "Document indication, size, balloon volume, residual urine; review daily and remove as soon as possible (CAUTI risk)."
                ),
                h("Nasogastric tubes"),
                b(
                    "Indications: feeding when swallowing unsafe, gastric decompression in obstruction, drug administration.",
                    "Measure NEX (nose-earlobe-xiphisternum) for insertion length; lubricate; advance with sips of water if safe.",
                    "Confirm position BEFORE any use: pH of aspirate ≤5.5 (first line) or X-ray if uncertain/no aspirate - the tube tip must be below the diaphragm, in the stomach, midline.",
                    "NEVER use the 'whoosh' test or litmus alone - they are unreliable and dangerous.",
                    "Check position before each feed/medication and after vomiting/coughing episodes.",
                    "Contraindications: base of skull fracture, severe facial trauma, oesophageal varices (caution), coagulopathy (relative)."
                ),
                tip("Two rules that prevent disasters: no balloon inflation without urine; no NG use without confirmed position. When in doubt, stop and ask.")
            )
        ),
        Topic(
            id = "documentation-sbar",
            title = "Documentation & SBAR Handover",
            summary = "Write notes and give handovers that keep patients safe.",
            blocks = listOf(
                p("Good documentation is a clinical skill: it communicates, protects patients and protects you. Handover is where most communication failures happen - structure saves lives."),
                h("Writing in the notes"),
                b(
                    "Every entry: date, time, your name and grade, signature (and contact if needed).",
                    "Structured: presenting problem → history/exam findings → impression → plan.",
                    "Be factual and objective; quote the patient where useful; avoid blame or speculation.",
                    "Document consent discussions, capacity assessments, refusals, escalation and who you spoke to.",
                    "Never alter records retrospectively without clear dating and explanation."
                ),
                h("SBAR for urgent communication"),
                b(
                    "S - Situation: 'I'm [name], student on [ward]. I'm calling about [patient, bed]. The problem is…'",
                    "B - Background: admission diagnosis, relevant history, recent events, current treatment.",
                    "A - Assessment: observations, your findings, what you think is happening.",
                    "R - Recommendation: 'I need a review now / advice on… / please come urgently.'"
                ),
                h("Handover hygiene"),
                b(
                    "Use a written list: patient ID, diagnosis, issues, outstanding jobs, escalation plans.",
                    "Prioritise: sickest first, flag 'watch' patients explicitly.",
                    "Closed-loop communication: the receiver repeats back key actions.",
                    "Never hand over a task you have not started without saying so."
                ),
                tip("Practise SBAR out loud on every patient you present. In a real emergency at 3 am, the structure will carry you.")
            )
        )
    )

    private val examsExtra = listOf(
        Topic(
            id = "licensing-exams",
            title = "Licensing & Board Exams",
            summary = "The big postgraduate exams: PLAB, USMLE, NEXT and college fellowships.",
            blocks = listOf(
                p("After (or during) MBBS, licensing and postgraduate exams open doors to practice and training abroad or at home. Know the landscape early - preparation takes months to years."),
                h("United Kingdom"),
                b(
                    "PLAB (soon UKMLA): for international graduates seeking GMC registration - PLAB 1 (MCQ) + PLAB 2 (OSCE-style).",
                    "Foundation Programme after registration; then specialty training with Royal College exams (MRCP, MRCS, MRCGP, MRCOG, MRCPsych, FRCR…).",
                    "English requirement: IELTS/OET."
                ),
                h("United States"),
                b(
                    "USMLE Step 1 (pass/fail), Step 2 CK (scored - the key number), Step 3 (during residency).",
                    "ECFMG certification for international graduates; then the Match into residency.",
                    "Clinical experience (observerships/electives) and research strengthen applications."
                ),
                h("Nigeria / West Africa"),
                b(
                    "MDCN registration after MBBS + internship; NYSC service year.",
                    "Residency via WACP/WACS/NPMCN faculties: Primary/Part I then Part II fellowship exams.",
                    "Medical officer posts build experience and exam readiness."
                ),
                h("India and others"),
                b(
                    "India: NExT (replacing NEET-PG/FMGE pathways) - check current NMC rules.",
                    "Canada: MCCQE + provincial requirements; Australia: AMC exams; Gulf states: local licensing exams (DHA, HAAD, MOH, SCFHS)."
                ),
                h("Planning tips"),
                b(
                    "Decide your target country by final year - timelines and costs differ hugely.",
                    "Budget: exam fees, courses, travel, verification - plan finances early.",
                    "Use question banks and official practice materials; join study groups.",
                    "Keep a portfolio: audits, teaching, electives, references - every system values them."
                ),
                tip("Rules change frequently (UKMLA, NExT, USMLE scoring). Always verify current requirements on the official regulator/college websites before committing.")
            )
        )
    )

    private val ethicsExtra = listOf(
        Topic(
            id = "breaking-bad-news",
            title = "Breaking Bad News (SPIKES)",
            summary = "A six-step framework for the hardest conversations.",
            blocks = listOf(
                p("Breaking bad news is a learnable skill, not a personality trait. SPIKES gives you a structure that reduces distress for patients and for you. As a student, observe first, then practise supervised."),
                h("SPIKES"),
                b(
                    "S - Setting: private room, sit down, tissues available, involve family if the patient wishes, minimise interruptions.",
                    "P - Perception: 'What have you been told so far?' Find out what they know and believe.",
                    "I - Invitation: 'How much would you like me to tell you?' Some patients want details, others do not.",
                    "K - Knowledge: warning shot ('I'm afraid the results are not what we hoped'), then clear, simple information in small chunks. Avoid jargon.",
                    "E - Emotions: name and acknowledge feelings ('I can see this is a shock'). Allow silence. Respond with empathy before more facts.",
                    "S - Strategy & Summary: agree the next steps, check understanding, summarise, arrange follow-up and support."
                ),
                h("Practical rules"),
                b(
                    "Never give bad news standing in a corridor or by phone unless unavoidable.",
                    "Use the word the disease needs (say 'cancer' if it is cancer) - euphemisms confuse.",
                    "Pause after the headline. Let the patient speak.",
                    "Do not say 'I know how you feel' - you don't. Say 'I can see this is difficult.'",
                    "Document the conversation: who was present, what was said, the plan."
                ),
                tip("Practise SPIKES in role-play until the structure is automatic. In the real moment, the framework holds you when emotions run high.")
            )
        ),
        Topic(
            id = "end-of-life-care",
            title = "End-of-Life Care & DNACPR",
            summary = "Comfort, dignity and decisions at the end of life.",
            blocks = listOf(
                p("Caring for dying patients is a core medical duty. The goals shift from cure to comfort, dignity and support for the family. DNACPR decisions are about CPR only - never about stopping other care."),
                h("Recognising dying"),
                b(
                    "Progressive weakness, reduced intake, changing breathing patterns, reduced consciousness.",
                    "Diagnosis of dying is clinical and senior-led; communicate uncertainty honestly to families.",
                    "Review: is anything reversible? If not, shift to comfort-focused care."
                ),
                h("Comfort care essentials"),
                b(
                    "Pain: regular analgesia including opioids, titrated to comfort; breakthrough doses available.",
                    "Breathlessness: opioids, positioning, fans, oxygen if hypoxic and helpful.",
                    "Secretions: repositioning, antimuscarinics if distressing ('death rattle' often disturbs family more than patient).",
                    "Agitation: look for causes (retention, pain, hypoxia); medications per palliative guidance.",
                    "Mouth care, skin care, dignity; stop unnecessary observations, bloods and interventions.",
                    "Support family: explain what to expect, allow presence, spiritual/cultural needs."
                ),
                h("DNACPR decisions"),
                b(
                    "DNACPR = do not attempt CPR if cardiac/respiratory arrest occurs; it does NOT mean 'do not treat'.",
                    "Decisions are made by senior clinicians with the patient (if capacity) or best-interests process (if not); family are consulted, not decision-makers unless appointed.",
                    "Document clearly, communicate to the whole team, review when condition changes.",
                    "CPR is a medical treatment - it is not offered when it will not work or will cause more harm than benefit."
                ),
                h("After death"),
                b(
                    "Confirm death per local protocol; document time and findings.",
                    "Inform family compassionately; offer viewing and support.",
                    "Complete certification, notify relevant teams, consider organ/tissue donation pathways and coroner referral rules.",
                    "Debrief with the team - deaths affect clinicians too."
                ),
                tip("A good death is remembered by families for decades. Your presence, honesty and comfort measures matter more than any procedure.")
            )
        )
    )

    private val wellbeingExtra = listOf(
        Topic(
            id = "mental-health-for-medics",
            title = "Mental Health for Medics",
            summary = "Recognising struggle in yourself and colleagues - and getting help.",
            blocks = listOf(
                p("Medical students and doctors have higher rates of depression, anxiety and burnout than the general population. Struggling is common, treatable and NOT a career-ending weakness. Knowing the signs - in yourself and others - is a professional skill."),
                h("Warning signs in yourself"),
                b(
                    "Persistent low mood, loss of interest, exhaustion that sleep doesn't fix.",
                    "Dread of placements, cynicism, feeling ineffective ('nothing I do matters').",
                    "Sleep disturbance, appetite changes, irritability, withdrawal from friends.",
                    "Increased alcohol/substance use; thoughts of self-harm or that others would be better off without you."
                ),
                h("What helps"),
                b(
                    "Talk early: a trusted friend, mentor, family member, or your school's counselling service.",
                    "See your own doctor - you deserve the same care you give patients.",
                    "Protect basics: sleep, food, movement, time off duty. They are treatment, not luxuries.",
                    "Confidential practitioner health services exist in many countries (e.g. Practitioner Health in the UK) - use them.",
                    "If you have thoughts of suicide: tell someone TODAY, contact emergency services or a crisis line. This is an emergency, not a weakness."
                ),
                h("Looking out for colleagues"),
                b(
                    "Notice changes: withdrawal, mistakes, absence, smell of alcohol, unusual irritability.",
                    "Ask directly and kindly: 'You seem to be having a hard time - how are you really?'",
                    "Listen without fixing; offer to go with them to get help.",
                    "If there is immediate risk to them or patients, escalate to a supervisor - safety first.",
                    "Follow up. One conversation is a start, not a solution."
                ),
                tip("Seeking help early is professionalism, not failure. The system needs you well - and you deserve a life outside medicine.")
            )
        ),
        Topic(
            id = "money-life-admin",
            title = "Money & Life Admin",
            summary = "Budgets, debt, insurance and the paperwork of being a doctor.",
            blocks = listOf(
                p("Nobody teaches the business side of medicine. A little planning early prevents years of stress: budgets, debt strategy, indemnity, registration and taxes."),
                h("During medical school"),
                b(
                    "Track spending for one month - most leaks are small and frequent.",
                    "Understand your loans: interest, repayment triggers, forgiveness options where they exist.",
                    "Student discounts, library resources and free tools beat paid subscriptions.",
                    "Electives and exams cost money - start a dedicated fund early."
                ),
                h("As a new doctor"),
                b(
                    "Budget on your actual take-home pay; build a small emergency fund first.",
                    "Understand indemnity/insurance requirements in your country - you cannot practise without appropriate cover.",
                    "Keep registration, licences and certifications current; calendar the renewal dates.",
                    "Learn your tax situation: employment vs locum/self-employed income differs.",
                    "Avoid lifestyle inflation in the first years - the jump in income is temporary relief, not wealth."
                ),
                h("Career-long habits"),
                b(
                    "Read every contract: on-call commitments, notice periods, indemnity, private practice clauses.",
                    "Get independent financial advice for big decisions (pensions, mortgages, investments).",
                    "Keep a portfolio and CV updated - opportunities appear suddenly.",
                    "Protect your most valuable asset: your ability to work (income protection insurance where available)."
                ),
                tip("Financial stress is a major driver of burnout. An hour of planning now buys years of peace later.")
            )
        )
    )

    private val resourcesExtra = listOf(
        Topic(
            id = "cv-portfolio",
            title = "Building a CV & Portfolio",
            summary = "Evidence beats adjectives: collect it from year one.",
            blocks = listOf(
                p("Specialty applications are scored on evidence. The students who match into competitive fields started collecting proof early - not because they are geniuses, but because they kept records."),
                h("What selectors look for"),
                b(
                    "Academic: prizes, publications, presentations, research experience.",
                    "Teaching: sessions delivered, feedback, qualifications.",
                    "Leadership: society roles, projects, initiatives.",
                    "Clinical: electives, audits/QI, courses (ALS, ATLS, skills).",
                    "Commitment to specialty: taster weeks, relevant projects, mentors."
                ),
                h("Build the habit"),
                b(
                    "Keep a 'brag file': after every activity, save the certificate, feedback form, photo or email.",
                    "Log teaching and procedures as you do them - memories fade, applications arrive.",
                    "Ask for feedback forms immediately after teaching or clinics.",
                    "Update your CV every 3 months, not every 3 years.",
                    "Get a mentor in your target specialty and ask what their application looked like."
                ),
                h("CV basics"),
                b(
                    "Clear structure: education, qualifications, clinical experience, research, teaching, leadership, courses, references.",
                    "Evidence everything: 'taught 12 sessions to 40 students, mean feedback 4.8/5' beats 'good teacher'.",
                    "Tailor to the application; follow the exact format requested.",
                    "No typos. Ever. Ask two people to proofread."
                ),
                tip("The portfolio you build in 10 minutes a week beats the panic-built portfolio in final year. Start the brag file today.")
            )
        ),
        Topic(
            id = "research-audits-electives",
            title = "Research, Audits & Electives",
            summary = "How to get involved - and make it count.",
            blocks = listOf(
                p("Research, audit and electives are the three great portfolio builders. They teach critical thinking, expose you to specialties and give you stories for interviews."),
                h("Audit and quality improvement (QI)"),
                b(
                    "Audit = measure practice against a standard, change something, re-measure (close the loop).",
                    "Find a supervisor and a real local problem: hand hygiene, VTE prophylaxis, discharge summaries, antibiotic timing.",
                    "Small and finished beats big and abandoned. A completed audit cycle is gold.",
                    "Present locally, then at a conference; write it up for publication if possible."
                ),
                h("Research"),
                b(
                    "Start by joining an existing project - data collection, literature reviews, case reports.",
                    "Learn the basics: study design, ethics approval, consent, statistics.",
                    "Case reports are a realistic first publication: rare presentation, novel management, important lesson.",
                    "Authorship rules: contribute meaningfully, agree order early, follow journal ethics."
                ),
                h("Electives"),
                b(
                    "Plan 6-12 months ahead: destination, specialty, supervisor, funding, insurance, vaccinations, visa.",
                    "Choose electives that answer a question: 'Is this specialty for me?' or 'How is this done elsewhere?'",
                    "Set learning objectives before you go; keep a reflective log during.",
                    "Be useful and humble: the best elective students help the team, not just watch.",
                    "Write it up: a report, presentation or poster - and send a thank-you with your outcomes."
                ),
                tip("One completed audit, one case report and one well-chosen elective will transform your application. Start with the smallest possible project.")
            )
        )
    )

    private val mnemonicsExtra = listOf(
        Topic(
            id = "mnemonics-emergencies-obgyn",
            title = "Mnemonics: Emergencies & O&G",
            summary = "Life-saving recall aids for acute care and obstetrics.",
            blocks = listOf(
                h("Resuscitation"),
                b(
                    "4 Hs & 4 Ts (reversible arrest causes): Hypoxia, Hypovolaemia, Hypo/hyperkalaemia (+ metabolic), Hypothermia; Tension pneumothorax, Tamponade, Toxins, Thrombosis (coronary/pulmonary).",
                    "DOPE (sudden deterioration in intubated patient): Displacement, Obstruction, Pneumothorax, Equipment failure.",
                    "SAMPLE history: Symptoms, Allergies, Medications, Past history, Last meal, Events.",
                    "OPQRST pain: Onset, Provocation, Quality, Radiation, Severity, Time."
                ),
                h("Obstetric emergencies"),
                b(
                    "PPH 4 Ts: Tone (atony), Tissue (retained), Trauma (lacerations), Thrombin (coagulopathy).",
                    "Shoulder dystocia - HELPERR: Help, Evaluate for episiotomy, Legs (McRoberts), Pressure (suprapubic), Enter (internal manoeuvres), Remove posterior arm, Roll (all fours).",
                    "Pre-eclampsia severe features: BP ≥160/110, headache/visual changes, epigastric pain, low platelets, rising creatinine, pulmonary oedema.",
                    "Eclampsia treatment: Magnesium sulphate - 'Mag saves mums'."
                ),
                h("Paediatric & neonatal"),
                b(
                    "APGAR: Appearance, Pulse, Grimace, Activity, Respiration (0-2 each).",
                    "Paediatric assessment triangle: Appearance, Work of breathing, Circulation to skin.",
                    "TORCH infections: Toxoplasma, Other (syphilis, varicella, parvovirus), Rubella, CMV, Herpes."
                ),
                tip("Mnemonics are retrieval hooks, not understanding. Learn the algorithm first, then use the mnemonic to recall it under pressure.")
            )
        ),
        Topic(
            id = "mnemonics-paeds-development",
            title = "Mnemonics: Paediatrics & Development",
            summary = "Milestones, rashes and the numbers you must know.",
            blocks = listOf(
                h("Developmental milestones (rough)"),
                b(
                    "2 months: social smile, lifts head. 4 months: rolls, laughs, reaches.",
                    "6 months: sits with support, transfers objects, babbles.",
                    "9 months: sits unsupported, crawls, pincer grasp, stranger anxiety.",
                    "12 months: stands/walks with help, 1-2 words, points.",
                    "18 months: walks well, ~10 words, scribbles, uses spoon.",
                    "2 years: runs, 2-word sentences, parallel play.",
                    "3 years: tricycle, 3-word sentences, knows name/age."
                ),
                h("Childhood rashes"),
                b(
                    "Measles: 3 Cs (Cough, Coryza, Conjunctivitis) + Koplik spots, then maculopapular rash from head down.",
                    "Rubella: mild fever, postauricular nodes, fine rash.",
                    "Roseola (HHV-6): high fever 3-5 days, rash appears AS fever breaks.",
                    "Erythema infectiosum (parvovirus B19): 'slapped cheek'.",
                    "Scarlet fever: sandpaper rash, strawberry tongue, circumoral pallor.",
                    "Hand-foot-mouth (Coxsackie): vesicles on hands, feet, mouth."
                ),
                h("Numbers to know"),
                b(
                    "Weight estimates: birth ~3.5 kg; doubles by 4-5 months; triples by 1 year.",
                    "Fluids 4-2-1 rule: 4 mL/kg/hr first 10 kg, 2 next 10, 1 thereafter.",
                    "Paracetamol 15 mg/kg/dose; ibuprofen 10 mg/kg/dose (check local formularies).",
                    "APGAR at 1 and 5 minutes; vitamin K at birth; hearing screen before discharge."
                ),
                tip("Milestones are ranges, not deadlines. Red flags matter more than exact dates: no smile by 3 months, no sitting by 9, no words by 18 - refer.")
            )
        )
    )

    private val emergenciesExtra = listOf(
        Topic(
            id = "emergency-drug-doses",
            title = "Emergency Drug Doses",
            summary = "The doses you must know - always verify locally.",
            blocks = listOf(
                p("These are commonly taught adult emergency doses for education. ALWAYS confirm against your hospital's current protocol, formulary and senior guidance before use. Paediatric doses are weight-based and must be calculated carefully."),
                h("Cardiac arrest (adult ALS)"),
                b(
                    "Adrenaline 1 mg IV/IO every 3-5 minutes.",
                    "Amiodarone 300 mg IV after the 3rd shock for VF/pVT (further 150 mg if needed).",
                    "Defibrillation: biphasic per device (commonly 150-200 J), escalate per protocol."
                ),
                h("Anaphylaxis (adult)"),
                b(
                    "Adrenaline 0.5 mg IM (0.5 mL of 1:1000) into anterolateral thigh; repeat every 5 minutes if needed.",
                    "High-flow oxygen, IV fluids for hypotension, position flat with legs raised (sit up if breathing difficult).",
                    "Adjuncts AFTER adrenaline: antihistamine, hydrocortisone per local protocol.",
                    "Refractory cases: senior-led IV adrenaline infusion - never IV bolus outside arrest/expert settings."
                ),
                h("Seizures"),
                b(
                    "Status epilepticus: IV lorazepam 4 mg (repeat once after 10 min) or buccal midazolam 10 mg / rectal diazepam 10-20 mg if no access.",
                    "Second line per protocol: levetiracetam, phenytoin or valproate.",
                    "Always check glucose; give thiamine before glucose if alcohol misuse/malnutrition suspected."
                ),
                h("Other essentials"),
                b(
                    "Hypoglycaemia: conscious → 15-20 g oral glucose; unconscious → IV dextrose (e.g. 150-200 mL of 10%) or IM glucagon 1 mg.",
                    "Opioid overdose: naloxone 400 micrograms IV/IM, titrate and repeat (infusion may be needed).",
                    "Eclampsia: magnesium sulphate 4 g IV loading then 1 g/hr infusion (local protocols vary); calcium gluconate for toxicity.",
                    "Paracetamol overdose: N-acetylcysteine per timed-level nomogram.",
                    "Sepsis: broad-spectrum IV antibiotics per local policy within the first hour + fluids."
                ),
                tip("Write these on a card and test yourself weekly. In the moment, you will not have time to look them up - but you MUST still verify locally.")
            )
        ),
        Topic(
            id = "trauma-primary-survey",
            title = "Trauma & the Primary Survey",
            summary = "ATLS-style ABCDE for the injured patient.",
            blocks = listOf(
                p("Trauma care follows a fixed sequence: find and treat life threats in order, reassess constantly. Students: recognise, call for help, assist within competence. The primary survey is the same everywhere."),
                h("Primary survey (ABCDE)"),
                b(
                    "A - Airway + C-spine: talk to the patient; look for obstruction, facial injury, expanding neck haematoma. Inline stabilisation; airway manoeuvres/adjuncts per training; definitive airway if needed.",
                    "B - Breathing: expose chest, look/feel/listen. Life threats: tension pneumothorax, open pneumothorax, massive haemothorax, flail chest. Decompress/drain per protocol.",
                    "C - Circulation + haemorrhage control: direct pressure, tourniquet for catastrophic limb bleeding, pelvic binder if suspected pelvic fracture, IV access, bloods/crossmatch, balanced resuscitation (blood products early in major trauma), tranexamic acid within 3 hours per protocol.",
                    "D - Disability: GCS/AVPU, pupils, glucose, lateralising signs.",
                    "E - Exposure: fully expose to find injuries, then keep warm (hypothermia worsens bleeding)."
                ),
                h("Adjuncts and next steps"),
                b(
                    "FAST/eFAST ultrasound in trained hands; chest/pelvis X-rays; CT when stable.",
                    "Secondary survey: head-to-toe examination, log roll, history (AMPLE: Allergies, Medications, Past history, Last meal, Events).",
                    "Reassess after every intervention; trauma patients declare themselves late.",
                    "Analgesia, tetanus, antibiotics for open wounds/fractures; document everything."
                ),
                h("Student role"),
                b(
                    "Call for help early; do not work alone.",
                    "Help with exposure, observations, documentation, fetching equipment.",
                    "Learn the sequence so well you can anticipate the next step."
                ),
                tip("Killer phrase: 'Treat first what kills first.' Airway before breathing before bleeding - except catastrophic external haemorrhage, which you compress immediately.")
            )
        ),
        Topic(
            id = "poisoning-overdose",
            title = "Poisoning & Overdose",
            summary = "General approach, common toxins and antidotes.",
            blocks = listOf(
                p("Poisoning management is mostly supportive care plus a few specific antidotes. The history (what, how much, when, co-ingestants) drives everything. Students: assess ABCDE, call for help, contact poisons information/toxicology per local system."),
                h("General approach"),
                b(
                    "ABCDE first - most deaths are from airway, breathing or circulation failure.",
                    "History: substance, dose, time, route, intent, co-ingestants (especially alcohol), past psychiatric history.",
                    "Examination: pupils, skin (dry/sweaty), bowel sounds, tremor, smell - toxidromes help.",
                    "Investigations: glucose always, ECG (QRS/QT), paracetamol/salicylate levels where indicated, specific levels per substance.",
                    "Decontamination: activated charcoal only if appropriate (usually within 1 hour, protected airway) - NOT for acids/alkalis, hydrocarbons, iron, lithium, alcohols.",
                    "Contact poisons information centre/toxicology early."
                ),
                h("Toxidromes"),
                b(
                    "Opioid: coma, pinpoint pupils, slow breathing → naloxone.",
                    "Anticholinergic: hot, dry, dilated pupils, urinary retention, delirium ('mad as a hatter…') → supportive, physostigmine in expert hands.",
                    "Cholinergic (organophosphates): DUMBBELLS (diarrhoea, urination, miosis, bradycardia, bronchorrhoea, emesis, lacrimation, salivation) → atropine + pralidoxime.",
                    "Sympathomimetic (cocaine/amphetamines): agitation, dilated pupils, tachycardia, hypertension, sweating → benzodiazepines, cooling.",
                    "Sedative-hypnotic: drowsiness, ataxia, normal pupils → supportive; flumazenil rarely (seizure risk)."
                ),
                h("Key antidotes"),
                b(
                    "Paracetamol → N-acetylcysteine (nomogram-guided).",
                    "Opioids → naloxone (short half-life - observe for re-sedation).",
                    "Benzodiazepines → flumazenil (caution: seizures in dependent patients).",
                    "Beta blockers → glucagon; digoxin → digoxin-specific antibodies.",
                    "Methanol/ethylene glycol → fomepizole; CO → 100% oxygen; iron → deferoxamine.",
                    "Tricyclics → sodium bicarbonate for wide QRS; avoid flumazenil in mixed overdoses."
                ),
                tip("Every overdose patient needs a risk assessment before discharge - the medical clearance is only half the job. Involve psychiatry per local pathway.")
            )
        )
    )
}
