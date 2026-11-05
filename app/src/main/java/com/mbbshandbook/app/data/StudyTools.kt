package com.mbbshandbook.app.data

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class QuizCategory(
    val id: String,
    val title: String,
    val emoji: String,
    val questions: List<QuizQuestion>
) {
    init {
        require(questions.isNotEmpty())
        questions.forEach { q ->
            require(q.options.size == 4)
            require(q.correctIndex in 0..3)
        }
    }
}

data class Flashcard(val front: String, val back: String)

data class FlashcardDeck(
    val id: String,
    val title: String,
    val emoji: String,
    val cards: List<Flashcard>
)

data class GlossaryEntry(val term: String, val definition: String)

object StudyTools {

    val quizCategories: List<QuizCategory> = listOf(
        QuizCategory(
            id = "anatomy", title = "Anatomy", emoji = "🦴",
            questions = listOf(
                QuizQuestion(
                    "A fracture of the surgical neck of the humerus most commonly injures which nerve?",
                    listOf("Radial nerve", "Axillary nerve", "Median nerve", "Ulnar nerve"),
                    1,
                    "The axillary nerve wraps around the surgical neck of the humerus with the posterior circumflex humeral artery. Injury weakens shoulder abduction (deltoid) and causes sensory loss over the regimental badge area."
                ),
                QuizQuestion(
                    "Which structure passes through the carpal tunnel?",
                    listOf("Ulnar nerve", "Radial artery", "Median nerve", "Ulnar artery"),
                    2,
                    "The median nerve passes through the carpal tunnel with the nine flexor tendons. The ulnar nerve and artery pass superficially through Guyon's canal."
                ),
                QuizQuestion(
                    "The coronary arteries arise from:",
                    listOf("The arch of the aorta", "The ascending aorta", "The pulmonary trunk", "The descending aorta"),
                    1,
                    "The right and left coronary arteries arise from the aortic sinuses of the ascending aorta, just above the aortic valve."
                ),
                QuizQuestion(
                    "Which cranial nerve supplies the muscles of facial expression?",
                    listOf("CN V (Trigeminal)", "CN VII (Facial)", "CN IX (Glossopharyngeal)", "CN XII (Hypoglossal)"),
                    1,
                    "The facial nerve (CN VII) supplies the muscles of facial expression. The trigeminal nerve (CN V) supplies the muscles of mastication."
                ),
                QuizQuestion(
                    "The femoral triangle contains, from lateral to medial:",
                    listOf("Nerve, artery, vein", "Vein, artery, nerve", "Artery, nerve, vein", "Nerve, vein, artery"),
                    0,
                    "Remember NAVEL from lateral to medial: femoral Nerve, Artery, Vein, Empty space (femoral canal), Lymphatics."
                ),
                QuizQuestion(
                    "A 'foot drop' after a fibular neck fracture suggests injury to the:",
                    listOf("Tibial nerve", "Common peroneal (fibular) nerve", "Femoral nerve", "Obturator nerve"),
                    1,
                    "The common peroneal nerve winds around the fibular neck and is easily injured there, causing loss of dorsiflexion (foot drop) and eversion."
                ),
                QuizQuestion(
                    "Which vessel is most commonly implicated in an epidural haematoma?",
                    listOf("Middle meningeal artery", "Anterior cerebral artery", "Bridging veins", "Basilar artery"),
                    0,
                    "The middle meningeal artery (a branch of the maxillary artery) runs deep to the pterion and is classically torn in temporal bone fractures. Bridging veins cause subdural haematomas."
                ),
                QuizQuestion(
                    "The diaphragm receives its motor supply from:",
                    listOf("Vagus nerve", "Intercostal nerves", "Phrenic nerve (C3-C5)", "Greater splanchnic nerve"),
                    2,
                    "C3, 4, 5 keep the diaphragm alive - the phrenic nerve provides the sole motor supply to the diaphragm."
                )
            )
        ),
        QuizCategory(
            id = "physiology", title = "Physiology", emoji = "⚡",
            questions = listOf(
                QuizQuestion(
                    "Which shift of the oxygen-haemoglobin dissociation curve occurs with increased CO2?",
                    listOf("Left shift", "Right shift", "No shift", "Upward shift only"),
                    1,
                    "Raised CO2, H+, temperature and 2,3-DPG shift the curve RIGHT (Bohr effect), unloading oxygen to tissues. Remember: right = release."
                ),
                QuizQuestion(
                    "Most filtered sodium is reabsorbed in the:",
                    listOf("Proximal convoluted tubule", "Loop of Henle", "Distal convoluted tubule", "Collecting duct"),
                    0,
                    "About 65-70% of filtered sodium and water is reabsorbed in the proximal tubule; the loop reabsorbs ~25%, with fine-tuning distally."
                ),
                QuizQuestion(
                    "The pacemaker of the heart is the:",
                    listOf("AV node", "Bundle of His", "SA node", "Purkinje fibres"),
                    2,
                    "The sinoatrial node has the fastest intrinsic rate (~60-100/min) and sets the heart's rhythm."
                ),
                QuizQuestion(
                    "Insulin secretion is primarily stimulated by:",
                    listOf("Low blood glucose", "High blood glucose", "High blood fatty acids only", "Low blood amino acids"),
                    1,
                    "Rising blood glucose enters beta cells via GLUT2, increasing ATP, closing K-ATP channels and triggering insulin release."
                ),
                QuizQuestion(
                    "Which hormone is the main regulator of extracellular fluid osmolality?",
                    listOf("Aldosterone", "ADH (vasopressin)", "Cortisol", "Atrial natriuretic peptide"),
                    1,
                    "ADH controls water reabsorption in the collecting ducts, regulating osmolality. Aldosterone primarily regulates sodium (and thus volume)."
                ),
                QuizQuestion(
                    "A normal adult glomerular filtration rate is approximately:",
                    listOf("25 mL/min", "60 mL/min", "125 mL/min", "250 mL/min"),
                    2,
                    "Normal GFR is about 125 mL/min (~180 L/day of filtrate), of which over 99% is reabsorbed."
                ),
                QuizQuestion(
                    "During inspiration, the diaphragm:",
                    listOf("Relaxes and rises", "Contracts and flattens", "Does not move", "Contracts and rises"),
                    1,
                    "Diaphragmatic contraction flattens the dome, increasing thoracic volume and lowering intrathoracic pressure so air flows in."
                ),
                QuizQuestion(
                    "The first heart sound (S1) is caused by closure of the:",
                    listOf("Aortic and pulmonary valves", "Mitral and tricuspid valves", "Mitral valve only", "Aortic valve only"),
                    1,
                    "S1 marks the start of systole with AV (mitral and tricuspid) valve closure; S2 marks semilunar valve closure."
                )
            )
        ),
        QuizCategory(
            id = "pharmacology", title = "Pharmacology", emoji = "💊",
            questions = listOf(
                QuizQuestion(
                    "The antidote for paracetamol (acetaminophen) overdose is:",
                    listOf("Naloxone", "N-acetylcysteine", "Flumazenil", "Atropine"),
                    1,
                    "N-acetylcysteine replenishes glutathione, detoxifying the reactive metabolite NAPQI. Naloxone reverses opioids; flumazenil reverses benzodiazepines."
                ),
                QuizQuestion(
                    "ACE inhibitors commonly cause which side effect?",
                    listOf("Dry cough", "Hearing loss", "Hair loss", "Blue urine"),
                    0,
                    "ACE inhibitors raise bradykinin levels, causing a dry cough in up to 20% of patients (and rarely angioedema)."
                ),
                QuizQuestion(
                    "Warfarin acts by inhibiting:",
                    listOf("Platelet COX-1", "Vitamin K epoxide reductase", "Antithrombin III", "Factor Xa directly"),
                    1,
                    "Warfarin blocks vitamin K recycling, reducing functional factors II, VII, IX, X and proteins C and S. Monitor with INR."
                ),
                QuizQuestion(
                    "Which drug class ends in the suffix '-olol'?",
                    listOf("ACE inhibitors", "Calcium channel blockers", "Beta blockers", "Statins"),
                    2,
                    "Beta blockers end in -olol (atenolol, metoprolol). ACE inhibitors end in -pril, statins in -statin, dihydropyridine CCBs in -dipine."
                ),
                QuizQuestion(
                    "The first-line treatment for anaphylaxis is:",
                    listOf("IV hydrocortisone", "Oral antihistamine", "IM adrenaline (epinephrine)", "Nebulised salbutamol"),
                    2,
                    "IM adrenaline into the anterolateral thigh is first-line and life-saving. Steroids and antihistamines are adjuncts, never substitutes."
                ),
                QuizQuestion(
                    "Loop diuretics like furosemide act on the:",
                    listOf("Proximal tubule", "Thick ascending limb", "Distal tubule", "Collecting duct"),
                    1,
                    "Loop diuretics inhibit the Na-K-2Cl cotransporter in the thick ascending limb - the most potent diuretic site. They can cause hypokalaemia."
                ),
                QuizQuestion(
                    "Which antibiotic class is associated with tendon rupture?",
                    listOf("Penicillins", "Macrolides", "Fluoroquinolones", "Aminoglycosides"),
                    2,
                    "Fluoroquinolones (e.g. ciprofloxacin) carry risks of tendinopathy/tendon rupture, QT prolongation and are generally avoided in children and pregnancy."
                ),
                QuizQuestion(
                    "Aminoglycosides' two classic toxicities are:",
                    listOf("Hepatic and cardiac", "Renal and ototoxicity", "Pulmonary and skin", "Neurological and ocular"),
                    1,
                    "Aminoglycosides (gentamicin, amikacin) cause nephrotoxicity and ototoxicity - monitor levels and renal function."
                )
            )
        ),
        QuizCategory(
            id = "microbiology", title = "Micro & Pathology", emoji = "🦠",
            questions = listOf(
                QuizQuestion(
                    "The stain used to identify Mycobacterium tuberculosis is:",
                    listOf("Gram stain", "Ziehl-Neelsen stain", "India ink", "Giemsa stain"),
                    1,
                    "TB's mycolic-acid-rich wall makes it acid-fast: it retains carbol fuchsin in the Ziehl-Neelsen stain despite acid-alcohol washing."
                ),
                QuizQuestion(
                    "Type I hypersensitivity is mediated by:",
                    listOf("IgG", "IgM", "IgE", "T cells"),
                    2,
                    "IgE on mast cells triggers immediate histamine release (anaphylaxis, allergy). Type IV is the delayed, T-cell-mediated type."
                ),
                QuizQuestion(
                    "The most common cause of community-acquired pneumonia is:",
                    listOf("Staphylococcus aureus", "Streptococcus pneumoniae", "Klebsiella pneumoniae", "Mycoplasma pneumoniae"),
                    1,
                    "Pneumococcus remains the classic leading cause, with rust-coloured sputum and lobar consolidation."
                ),
                QuizQuestion(
                    "Caseating granulomas are characteristic of:",
                    listOf("Sarcoidosis", "Tuberculosis", "Crohn's disease", "Foreign body reaction"),
                    1,
                    "TB classically produces caseating (cheese-like central necrosis) granulomas; sarcoidosis produces non-caseating granulomas."
                ),
                QuizQuestion(
                    "Which cell is the hallmark of acute inflammation?",
                    listOf("Lymphocyte", "Plasma cell", "Neutrophil", "Macrophage"),
                    2,
                    "Neutrophils dominate acute inflammation (first 24-48h); macrophages and lymphocytes characterise chronic inflammation."
                ),
                QuizQuestion(
                    "Grading of a tumour describes its:",
                    listOf("Anatomical spread", "Degree of differentiation", "Size only", "Lymph node count"),
                    1,
                    "Grade = how abnormal the cells look (differentiation). Stage = how far it has spread (e.g. TNM). Stage usually matters more for prognosis."
                ),
                QuizQuestion(
                    "The vector for malaria is:",
                    listOf("Male Anopheles mosquito", "Female Anopheles mosquito", "Aedes mosquito", "Culex mosquito"),
                    1,
                    "Only the female Anopheles mosquito takes blood meals and transmits Plasmodium. Aedes transmits dengue/yellow fever; Culex transmits filariasis."
                ),
                QuizQuestion(
                    "A transudate pleural effusion is typically caused by:",
                    listOf("Pneumonia", "Malignancy", "Heart failure", "Tuberculosis"),
                    2,
                    "Transudates (low protein) come from pressure imbalances - heart failure, cirrhosis, nephrotic syndrome. Exudates (high protein) come from inflammation or malignancy (Light's criteria)."
                )
            )
        ),
        QuizCategory(
            id = "clinical", title = "Clinical Medicine", emoji = "🩺",
            questions = listOf(
                QuizQuestion(
                    "The classic ECG change in hyperkalaemia is:",
                    listOf("U waves", "Tented (peaked) T waves", "Delta waves", "Prolonged QT only"),
                    1,
                    "Hyperkalaemia: peaked T waves, then flattened P waves, widened QRS, sine wave. Hypokalaemia causes flat T waves and U waves."
                ),
                QuizQuestion(
                    "Central crushing chest pain radiating to the jaw with ST elevation indicates:",
                    listOf("Stable angina", "STEMI", "Pericarditis only", "Costochondritis"),
                    1,
                    "ST elevation in contiguous territories with typical pain = STEMI until proven otherwise: emergency reperfusion pathway."
                ),
                QuizQuestion(
                    "Kussmaul breathing (deep, sighing) is classically seen in:",
                    listOf("Opioid overdose", "Diabetic ketoacidosis", "Panic attack", "Myasthenia gravis"),
                    1,
                    "Deep rapid breathing compensates for metabolic acidosis in DKA. Opioids cause slow, shallow breathing."
                ),
                QuizQuestion(
                    "The most sensitive early vital sign of clinical deterioration is:",
                    listOf("Blood pressure", "Heart rate", "Respiratory rate", "Temperature"),
                    2,
                    "Respiratory rate rises early in deterioration and is the most predictive single vital sign - yet it is the most often omitted."
                ),
                QuizQuestion(
                    "HbA1c reflects average blood glucose over approximately:",
                    listOf("1 week", "1 month", "3 months", "1 year"),
                    2,
                    "HbA1c reflects glycation over the ~120-day red cell lifespan, weighted to the last 8-12 weeks."
                ),
                QuizQuestion(
                    "A patient with COPD has target oxygen saturations of:",
                    listOf("94-98%", "88-92%", "100%", "80-85%"),
                    1,
                    "In COPD patients at risk of CO2 retention, target 88-92% pending blood gases; most other acutely ill patients target 94-98%."
                ),
                QuizQuestion(
                    "Beck's triad (hypotension, raised JVP, muffled heart sounds) indicates:",
                    listOf("Cardiac tamponade", "Tension pneumothorax", "Massive PE", "Aortic dissection"),
                    0,
                    "Fluid in the pericardial sac compresses the heart: Beck's triad = tamponade, a medical emergency requiring drainage."
                ),
                QuizQuestion(
                    "First-line investigation for suspected acute stroke is:",
                    listOf("MRI with contrast", "Non-contrast CT head", "Lumbar puncture", "Carotid Doppler"),
                    1,
                    "Urgent non-contrast CT distinguishes ischaemic from haemorrhagic stroke, which determines whether thrombolysis is possible."
                )
            )
        ),
        QuizCategory(
            id = "ethics", title = "Ethics & Skills", emoji = "⚖️",
            questions = listOf(
                QuizQuestion(
                    "A competent adult refusing a life-saving blood transfusion should:",
                    listOf("Be transfused anyway", "Have their decision respected", "Be referred to psychiatry automatically", "Have family override the decision"),
                    1,
                    "Autonomy: a patient with capacity may refuse any treatment, even life-saving ones. Assess capacity, explore reasons, document - never coerce."
                ),
                QuizQuestion(
                    "In adult CPR, the compression-to-ventilation ratio is:",
                    listOf("15:2", "30:2", "5:1", "50:2"),
                    1,
                    "30 compressions to 2 breaths for adult BLS (single or two rescuer). Rate 100-120/min, depth 5-6 cm."
                ),
                QuizQuestion(
                    "Valid consent must be:",
                    listOf("Written in all cases", "Informed, voluntary and from a patient with capacity", "Obtained only by a consultant", "Witnessed by two doctors"),
                    1,
                    "The three pillars: information, voluntariness, capacity. Written consent is good practice for procedures but the signature itself is not the consent."
                ),
                QuizQuestion(
                    "Confidentiality may be breached without consent when:",
                    listOf("A relative asks for information", "There is a serious risk of harm to others", "A journalist requests it", "The patient is famous"),
                    1,
                    "Serious preventable harm (and certain legal duties) can justify disclosure - disclose the minimum necessary and involve seniors first."
                ),
                QuizQuestion(
                    "The 'C' in the ABCDE assessment stands for:",
                    listOf("Consciousness", "Circulation", "Cervical spine", "Compressions"),
                    1,
                    "Airway, Breathing, Circulation, Disability, Exposure - treat each life threat before moving on."
                ),
                QuizQuestion(
                    "SBAR handover stands for:",
                    listOf("Situation, Background, Assessment, Recommendation", "Signs, Bloods, Actions, Review", "Summary, Baseline, Analysis, Result", "Safety, Brief, Aim, Response"),
                    0,
                    "SBAR structures urgent communication: what's happening, the context, what you think it is, and what you need."
                ),
                QuizQuestion(
                    "A patient lacks capacity when they cannot:",
                    listOf("Make the decision the doctor prefers", "Understand, retain, weigh information or communicate a choice", "Read the consent form", "Speak the local language"),
                    1,
                    "Capacity is functional and decision-specific - not about agreeing with the doctor. Provide information accessibly before judging capacity."
                ),
                QuizQuestion(
                    "Before touching a patient you should FIRST:",
                    listOf("Order blood tests", "Introduce yourself, gain consent and wash hands", "Ask the nurse to examine", "Document findings"),
                    1,
                    "Every examination: identify yourself, confirm patient identity, explain, consent, hand hygiene, position, expose appropriately."
                )
            )
        )
    )

    val flashcardDecks: List<FlashcardDeck> = listOf(
        FlashcardDeck(
            id = "cranial-nerves", title = "Cranial Nerves", emoji = "🧠",
            cards = listOf(
                Flashcard("CN I - Olfactory", "Smell. Sensory only. Test with familiar scents; lost in cribriform plate fractures."),
                Flashcard("CN II - Optic", "Vision. Sensory. Test acuity, fields, pupils, fundoscopy. Afferent limb of light reflex."),
                Flashcard("CN III - Oculomotor", "Most eye movements, levator palpebrae, pupil constriction. Palsy: 'down and out' eye, ptosis, dilated pupil."),
                Flashcard("CN IV - Trochlear", "Superior oblique - looks down and in. Palsy: vertical diplopia, head tilt away from lesion."),
                Flashcard("CN V - Trigeminal", "Facial sensation + muscles of mastication. Three divisions: V1 ophthalmic, V2 maxillary, V3 mandibular."),
                Flashcard("CN VI - Abducens", "Lateral rectus - abducts eye. Palsy: cannot look laterally, horizontal diplopia."),
                Flashcard("CN VII - Facial", "Facial expression, taste anterior 2/3 tongue, lacrimation, salivation. Bell's palsy = LMN lesion, whole half of face."),
                Flashcard("CN VIII - Vestibulocochlear", "Hearing and balance. Test with whisper test, Rinne and Weber."),
                Flashcard("CN IX - Glossopharyngeal", "Taste posterior 1/3, pharyngeal sensation, parotid. Afferent limb of gag reflex."),
                Flashcard("CN X - Vagus", "Parasympathetic to thorax/abdomen, laryngeal muscles. Efferent gag reflex; uvula deviates AWAY from lesion."),
                Flashcard("CN XI - Accessory", "Sternocleidomastoid and trapezius. Test: shrug shoulders, turn head against resistance."),
                Flashcard("CN XII - Hypoglossal", "Tongue movement. LMN lesion: tongue deviates TOWARD the lesion ('lick your wounds').")
            )
        ),
        FlashcardDeck(
            id = "vitals-labs", title = "Normal Values", emoji = "📊",
            cards = listOf(
                Flashcard("Adult heart rate", "60-100 beats/min at rest"),
                Flashcard("Adult respiratory rate", "12-20 breaths/min"),
                Flashcard("Normal SpO2 (on air)", "95-100% (88-92% target in COPD at risk of CO2 retention)"),
                Flashcard("Serum sodium", "135-145 mmol/L"),
                Flashcard("Serum potassium", "3.5-5.0 mmol/L"),
                Flashcard("Fasting glucose", "3.9-5.5 mmol/L (70-99 mg/dL)"),
                Flashcard("Haemoglobin", "Men ~13.5-17.5 g/dL; Women ~12.0-15.5 g/dL"),
                Flashcard("White cell count", "4-11 x10^9/L"),
                Flashcard("Platelets", "150-400 x10^9/L"),
                Flashcard("Minimum urine output", "0.5 mL/kg/hour in adults"),
                Flashcard("Normal GFR", "~90-120 mL/min/1.73m2"),
                Flashcard("INR on warfarin (most indications)", "2.0-3.0 (mechanical valves often 2.5-3.5)")
            )
        ),
        FlashcardDeck(
            id = "pharm-antidotes", title = "Antidotes & Tox", emoji = "🧪",
            cards = listOf(
                Flashcard("Paracetamol overdose", "N-acetylcysteine (replenishes glutathione)"),
                Flashcard("Opioid overdose", "Naloxone (short half-life - may need repeat doses)"),
                Flashcard("Benzodiazepine overdose", "Flumazenil (caution: can precipitate seizures in dependent patients)"),
                Flashcard("Beta blocker overdose", "Glucagon (bypasses beta receptor to raise cAMP)"),
                Flashcard("Organophosphate poisoning", "Atropine + pralidoxime"),
                Flashcard("Heparin reversal", "Protamine sulfate"),
                Flashcard("Warfarin reversal", "Vitamin K (slow) + PCC or FFP (fast, per severity)"),
                Flashcard("Iron poisoning", "Deferoxamine"),
                Flashcard("Methanol / ethylene glycol", "Fomepizole (or ethanol) - blocks alcohol dehydrogenase"),
                Flashcard("Digoxin toxicity", "Digoxin-specific antibody fragments (Digibind)"),
                Flashcard("Carbon monoxide", "High-flow 100% oxygen (hyperbaric in severe cases)"),
                Flashcard("Isoniazid-induced seizures", "Pyridoxine (vitamin B6)")
            )
        ),
        FlashcardDeck(
            id = "dermatology-terms", title = "Clinical Vocabulary", emoji = "📖",
            cards = listOf(
                Flashcard("Dyspnoea", "Subjective sensation of difficult or laboured breathing"),
                Flashcard("Orthopnoea", "Breathlessness when lying flat, relieved by sitting up - classic in heart failure"),
                Flashcard("Haematemesis", "Vomiting blood - upper GI bleeding"),
                Flashcard("Melaena", "Black tarry stool from digested blood - usually upper GI bleed"),
                Flashcard("Dysphagia", "Difficulty swallowing - always investigate (alarm symptom)"),
                Flashcard("Odynophagia", "Painful swallowing"),
                Flashcard("Syncope", "Transient loss of consciousness from global cerebral hypoperfusion"),
                Flashcard("Pyrexia", "Fever - elevated body temperature"),
                Flashcard("Oedema", "Palpable excess of interstitial fluid - pitting vs non-pitting matters"),
                Flashcard("Cyanosis", "Bluish discolouration from deoxygenated haemoglobin (central vs peripheral)"),
                Flashcard("Clubbing", "Loss of nail-fold angle, bulbous fingertips - think chronic hypoxia, lung cancer, IBD, endocarditis"),
                Flashcard("Ascites", "Fluid in the peritoneal cavity - shifting dullness, fluid thrill")
            )
        )
    )

    val glossary: List<GlossaryEntry> = listOf(
        GlossaryEntry("ABCDE", "Airway, Breathing, Circulation, Disability, Exposure - the systematic emergency assessment."),
        GlossaryEntry("Aetiology", "The cause of a disease."),
        GlossaryEntry("Auscultation", "Listening to body sounds, usually with a stethoscope."),
        GlossaryEntry("Bradycardia", "Heart rate below 60 beats/min in adults."),
        GlossaryEntry("Bradykinesia", "Slowness of movement - a core feature of parkinsonism."),
        GlossaryEntry("Crepitations", "Crackling lung sounds on inspiration - think fluid (oedema, pneumonia) or fibrosis."),
        GlossaryEntry("Diaphoresis", "Profuse sweating, often accompanying shock, MI or hypoglycaemia."),
        GlossaryEntry("Differential diagnosis", "The ranked list of possible conditions explaining a presentation."),
        GlossaryEntry("Distal", "Further from the trunk or point of origin (opposite: proximal)."),
        GlossaryEntry("Dysarthria", "Difficulty articulating speech due to muscle/neurological problems - language is intact."),
        GlossaryEntry("Dysphasia", "Impaired language production or comprehension - usually a cortical lesion."),
        GlossaryEntry("Embolus", "A mass (clot, air, fat) travelling in the bloodstream that lodges downstream."),
        GlossaryEntry("Erythema", "Redness of skin from increased blood flow."),
        GlossaryEntry("Exacerbation", "A worsening of a chronic condition's symptoms."),
        GlossaryEntry("Febrile", "Having a fever."),
        GlossaryEntry("Haemodynamic", "Relating to blood flow and pressure; 'unstable' means perfusion is failing."),
        GlossaryEntry("Hyper- / Hypo-", "Above normal / below normal (e.g. hyperkalaemia = high potassium)."),
        GlossaryEntry("Iatrogenic", "Caused by medical examination or treatment."),
        GlossaryEntry("Idiopathic", "Of unknown cause."),
        GlossaryEntry("Infarction", "Tissue death from loss of blood supply."),
        GlossaryEntry("Ischaemia", "Reduced blood supply to tissue - reversible if restored quickly."),
        GlossaryEntry("Lateral / Medial", "Away from / toward the midline."),
        GlossaryEntry("Lesion", "Any abnormal area of tissue - the most general word in medicine."),
        GlossaryEntry("Morbidity", "Disease or illness burden (as opposed to mortality = death)."),
        GlossaryEntry("Nosocomial", "Acquired in hospital (e.g. nosocomial infection)."),
        GlossaryEntry("Pallor", "Unusual paleness - think anaemia or poor perfusion."),
        GlossaryEntry("Palpation", "Examining by touch."),
        GlossaryEntry("Pathognomonic", "A sign so characteristic it confirms a diagnosis by itself (rare!)."),
        GlossaryEntry("Percussion", "Tapping the body to assess underlying structures by sound."),
        GlossaryEntry("Polyuria / Polydipsia", "Excessive urination / excessive thirst - classic diabetes pair."),
        GlossaryEntry("Prognosis", "The predicted course and outcome of a disease."),
        GlossaryEntry("Prophylaxis", "Treatment given to prevent disease (e.g. anticoagulants to prevent DVT)."),
        GlossaryEntry("Pruritus", "Itching."),
        GlossaryEntry("Purpura", "Purple non-blanching skin spots from bleeding under the skin."),
        GlossaryEntry("Remission", "Reduction or disappearance of disease activity."),
        GlossaryEntry("Sequelae", "Conditions resulting from a previous disease or injury."),
        GlossaryEntry("Sepsis", "Life-threatening organ dysfunction caused by a dysregulated response to infection."),
        GlossaryEntry("Sign vs Symptom", "A sign is observed by the clinician; a symptom is experienced by the patient."),
        GlossaryEntry("Stenosis", "Abnormal narrowing of a vessel or passage."),
        GlossaryEntry("Subcutaneous", "Under the skin."),
        GlossaryEntry("Tachycardia", "Heart rate above 100 beats/min in adults."),
        GlossaryEntry("Tachypnoea", "Respiratory rate above 20 breaths/min in adults."),
        GlossaryEntry("Triage", "Prioritising patients by urgency of need."),
        GlossaryEntry("Vertigo", "Illusion of movement/spinning - vestibular problem, not simple dizziness."),
        GlossaryEntry("Wheeze", "Whistling expiratory sound from narrowed airways - asthma, COPD.")
    ).sortedBy { it.term.lowercase() }
}
