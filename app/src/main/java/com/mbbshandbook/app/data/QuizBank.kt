package com.mbbshandbook.app.data

/**
 * Extra questions merged into the base quiz categories by StudyTools.
 * Kept in a separate file so the bank can grow without touching the originals.
 */
object QuizBank {

    fun extraQuestions(categoryId: String): List<QuizQuestion> = when (categoryId) {
        "anatomy" -> anatomyExtra
        "physiology" -> physiologyExtra
        "pharmacology" -> pharmacologyExtra
        "microbiology" -> microbiologyExtra
        "clinical" -> clinicalExtra
        "ethics" -> ethicsExtra
        else -> emptyList()
    }

    private val anatomyExtra = listOf(
        QuizQuestion(
            "A mid-shaft humeral fracture most commonly injures which nerve?",
            listOf("Ulnar nerve", "Median nerve", "Radial nerve", "Axillary nerve"),
            2,
            "The radial nerve runs in the spiral (radial) groove of the humeral shaft. Injury causes wrist drop and sensory loss over the dorsal first web space."
        ),
        QuizQuestion(
            "A fracture of the neck of the fibula risks injury to which nerve?",
            listOf("Tibial nerve", "Common peroneal (fibular) nerve", "Sural nerve", "Saphenous nerve"),
            1,
            "The common peroneal nerve winds around the fibular neck. Injury causes foot drop (loss of dorsiflexion/eversion) and sensory loss over the dorsum of the foot."
        ),
        QuizQuestion(
            "Which structure passes through the foramen ovale?",
            listOf("Maxillary nerve (V2)", "Mandibular nerve (V3)", "Ophthalmic nerve (V1)", "Facial nerve"),
            1,
            "V3 (mandibular division of trigeminal) exits via foramen ovale. V2 uses foramen rotundum; V1 uses the superior orbital fissure."
        ),
        QuizQuestion(
            "The most lateral structure in the femoral triangle is the:",
            listOf("Femoral vein", "Femoral artery", "Femoral nerve", "Femoral canal"),
            2,
            "Lateral to medial: Nerve, Artery, Vein, Empty space/canal, Lymphatics (NAVEL). The femoral nerve lies outside the femoral sheath."
        ),
        QuizQuestion(
            "Lymph from the testes drains primarily to:",
            listOf("Superficial inguinal nodes", "Deep inguinal nodes", "Para-aortic (lumbar) nodes", "Internal iliac nodes"),
            2,
            "Testes descend from the posterior abdominal wall, so lymph follows the testicular vessels to para-aortic nodes. Scrotal skin drains to inguinal nodes - a classic exam distinction."
        ),
        QuizQuestion(
            "The recurrent laryngeal nerves are branches of the:",
            listOf("Glossopharyngeal nerve", "Vagus nerve", "Accessory nerve", "Hypoglossal nerve"),
            1,
            "Both recurrent laryngeal nerves branch from the vagus. The right hooks around the subclavian artery; the left around the aortic arch (ligamentum arteriosum). They supply all intrinsic laryngeal muscles except cricothyroid."
        ),
        QuizQuestion(
            "The umbilicus corresponds to which dermatome?",
            listOf("T4", "T7", "T10", "L1"),
            2,
            "T10 = umbilicus ('T10 at the belly-butTEN'). T4 = nipple line, T7 ≈ xiphisternum, L1 ≈ inguinal region."
        ),
        QuizQuestion(
            "Which rotator cuff muscle initiates the first 15 degrees of arm abduction?",
            listOf("Deltoid", "Supraspinatus", "Infraspinatus", "Teres minor"),
            1,
            "Supraspinatus initiates abduction (0-15°); deltoid takes over to ~90°; serratus anterior and trapezius rotate the scapula above that."
        ),
        QuizQuestion(
            "The foregut is supplied by the:",
            listOf("Superior mesenteric artery", "Inferior mesenteric artery", "Coeliac trunk", "Internal iliac artery"),
            2,
            "Coeliac trunk = foregut (to the 2nd part of duodenum); SMA = midgut; IMA = hindgut. The watershed areas (splenic flexure, rectosigmoid) are vulnerable to ischaemic colitis."
        ),
        QuizQuestion(
            "The cystic artery is usually a branch of the:",
            listOf("Left hepatic artery", "Right hepatic artery", "Gastroduodenal artery", "Common hepatic artery"),
            1,
            "The cystic artery typically arises from the right hepatic artery within Calot's triangle (cystic duct, common hepatic duct, inferior liver edge) - critical anatomy in cholecystectomy."
        ),
        QuizQuestion(
            "Which cranial nerve runs THROUGH the cavernous sinus (not in its wall)?",
            listOf("Oculomotor (III)", "Trochlear (IV)", "Abducens (VI)", "Ophthalmic (V1)"),
            2,
            "CN VI runs through the sinus alongside the internal carotid artery; III, IV, V1 and V2 lie in the lateral wall. This is why VI is often first affected in cavernous sinus pathology."
        ),
        QuizQuestion(
            "The great saphenous vein drains into the:",
            listOf("Popliteal vein", "Femoral vein", "External iliac vein", "Small saphenous vein"),
            1,
            "The great saphenous vein passes through the saphenous opening to join the femoral vein. The small saphenous vein drains into the popliteal vein."
        )
    )

    private val physiologyExtra = listOf(
        QuizQuestion(
            "Which hormone increases water reabsorption in the collecting duct?",
            listOf("Aldosterone", "ADH (vasopressin)", "Atrial natriuretic peptide", "Cortisol"),
            1,
            "ADH inserts aquaporin-2 channels into the collecting duct, allowing water reabsorption. Aldosterone acts on sodium (and potassium) handling instead."
        ),
        QuizQuestion(
            "Which causes a RIGHT shift of the oxygen-haemoglobin dissociation curve?",
            listOf("Decreased temperature", "Decreased 2,3-BPG", "Increased pH", "Increased CO2"),
            3,
            "Right shift = easier unloading to tissues: increased CO2, H+, temperature and 2,3-BPG ('CADET, face Right'). Left shift holds oxygen tighter."
        ),
        QuizQuestion(
            "The normal pacemaker of the heart is the:",
            listOf("AV node", "Bundle of His", "SA node", "Purkinje fibres"),
            2,
            "The SA node has the fastest intrinsic rate (~60-100/min) and sets the rhythm. AV node ~40-60, ventricular escape ~20-40 if higher pacemakers fail."
        ),
        QuizQuestion(
            "Most CO2 in blood is transported as:",
            listOf("Dissolved CO2", "Carbaminohaemoglobin", "Bicarbonate", "Carbonic acid"),
            2,
            "~70% as bicarbonate (via carbonic anhydrase in red cells), ~20-25% bound to haemoglobin, ~5-10% dissolved."
        ),
        QuizQuestion(
            "Aldosterone acts on the distal nephron to:",
            listOf("Reabsorb Na+ and secrete K+", "Reabsorb K+ and secrete Na+", "Reabsorb water only", "Secrete both Na+ and K+"),
            0,
            "Aldosterone increases Na+ reabsorption (with water following) and K+/H+ secretion in the principal and intercalated cells of the distal tubule/collecting duct."
        ),
        QuizQuestion(
            "The main force OPPOSING glomerular filtration is:",
            listOf("Glomerular capillary hydrostatic pressure", "Plasma colloid osmotic pressure", "Negative pressure in Bowman's space", "Afferent arteriolar tone"),
            1,
            "Starling forces: capillary hydrostatic pressure drives filtration; plasma oncotic pressure (proteins) and Bowman's capsule hydrostatic pressure oppose it. Oncotic pressure is the larger opposing force."
        ),
        QuizQuestion(
            "Pulmonary surfactant is produced by:",
            listOf("Type I pneumocytes", "Type II pneumocytes", "Clara (club) cells", "Alveolar macrophages"),
            1,
            "Type II pneumocytes secrete surfactant, reducing surface tension and preventing alveolar collapse. Deficiency causes neonatal respiratory distress syndrome."
        ),
        QuizQuestion(
            "Insulin is secreted by which pancreatic cells?",
            listOf("Alpha cells", "Beta cells", "Delta cells", "Acinar cells"),
            1,
            "Beta cells (islets of Langerhans) secrete insulin; alpha cells secrete glucagon; delta cells somatostatin; acinar cells digestive enzymes."
        ),
        QuizQuestion(
            "The depolarisation phase of a neuronal action potential is caused by:",
            listOf("K+ efflux", "Na+ influx", "Cl- influx", "Ca2+ efflux"),
            1,
            "Voltage-gated Na+ channels open → rapid Na+ influx → depolarisation. Repolarisation follows via K+ efflux."
        ),
        QuizQuestion(
            "Arterial baroreceptors are located in the:",
            listOf("Carotid sinus and aortic arch", "Carotid body and aortic body", "Renal afferent arterioles", "Right atrium only"),
            0,
            "Baroreceptors (stretch) sit in the carotid sinus and aortic arch. The carotid/aortic BODIES are chemoreceptors sensing O2, CO2 and pH."
        ),
        QuizQuestion(
            "Intrinsic factor is required for absorption of:",
            listOf("Iron", "Folate", "Vitamin B12", "Calcium"),
            2,
            "Parietal cells secrete intrinsic factor; the B12-IF complex is absorbed in the terminal ileum. Loss (pernicious anaemia, gastrectomy, ileal disease) causes B12 deficiency."
        ),
        QuizQuestion(
            "The countercurrent multiplier that concentrates urine is located in the:",
            listOf("Proximal tubule", "Loop of Henle", "Distal tubule", "Collecting duct only"),
            1,
            "The loop of Henle multiplies the medullary osmotic gradient; the vasa recta preserve it (countercurrent exchange); ADH then uses it to concentrate urine."
        )
    )

    private val pharmacologyExtra = listOf(
        QuizQuestion(
            "A persistent dry cough is a classic side effect of:",
            listOf("Beta blockers", "ACE inhibitors", "Calcium channel blockers", "Statins"),
            1,
            "ACE inhibitors raise bradykinin (ACE normally degrades it), causing cough in ~10% and rarely angioedema. Switch to an ARB if intolerable."
        ),
        QuizQuestion(
            "Warfarin works by inhibiting:",
            listOf("Antithrombin III", "Vitamin K epoxide reductase", "Cyclo-oxygenase", "Factor Xa directly"),
            1,
            "Warfarin blocks vitamin K recycling, reducing functional factors II, VII, IX, X and proteins C/S. Monitor with INR; onset takes days."
        ),
        QuizQuestion(
            "First-line drug treatment for anaphylaxis is:",
            listOf("IV chlorphenamine", "IM adrenaline", "IV hydrocortisone", "Nebulised salbutamol"),
            1,
            "IM adrenaline (0.5 mg = 0.5 mL of 1:1000 in adults) into the anterolateral thigh is FIRST line. Antihistamines and steroids are adjuncts only."
        ),
        QuizQuestion(
            "Aspirin's antiplatelet effect comes from:",
            listOf("Reversible COX-2 inhibition", "Irreversible COX-1 inhibition", "ADP receptor blockade", "Glycoprotein IIb/IIIa blockade"),
            1,
            "Aspirin irreversibly acetylates COX-1, blocking thromboxane A2 for the platelet's lifespan (~7-10 days). Platelets cannot make new enzyme."
        ),
        QuizQuestion(
            "Which beta blocker is most dangerous in asthma?",
            listOf("Bisoprolol", "Atenolol", "Propranolol", "Metoprolol"),
            2,
            "Propranolol is non-selective (blocks β2 as well as β1) and can trigger bronchospasm. Cardioselective agents are safer but still used cautiously."
        ),
        QuizQuestion(
            "Gingival hyperplasia is a recognised side effect of:",
            listOf("Phenytoin", "Carbamazepine", "Valproate", "Levetiracetam"),
            0,
            "Phenytoin causes gingival hyperplasia, hirsutism, coarsened facies, folate deficiency and osteomalacia. Good dental hygiene mitigates the gum effects."
        ),
        QuizQuestion(
            "The two hallmark toxicities of aminoglycosides (e.g. gentamicin) are:",
            listOf("Hepatotoxicity and rash", "Nephrotoxicity and ototoxicity", "Cardiotoxicity and myopathy", "Pulmonary fibrosis and neuropathy"),
            1,
            "Aminoglycosides damage renal tubules and cochlear/vestibular hair cells. Monitor levels and renal function; avoid prolonged courses where possible."
        ),
        QuizQuestion(
            "Methotrexate inhibits:",
            listOf("Thymidylate synthase directly", "Dihydrofolate reductase", "RNA polymerase", "Topoisomerase II"),
            1,
            "Methotrexate inhibits dihydrofolate reductase, blocking purine/pyrimidine synthesis. Folinic acid rescue and weekly (not daily!) dosing in rheumatology are critical safety points."
        ),
        QuizQuestion(
            "Statins lower LDL cholesterol by inhibiting:",
            listOf("HMG-CoA reductase", "Lipoprotein lipase", "PCSK9", "Cholesterol esterase"),
            0,
            "Statins inhibit HMG-CoA reductase (rate-limiting step of cholesterol synthesis), upregulating hepatic LDL receptors. Watch for myopathy; check CK if symptomatic."
        ),
        QuizQuestion(
            "The antidote for opioid overdose is:",
            listOf("Flumazenil", "Naloxone", "N-acetylcysteine", "Atropine"),
            1,
            "Naloxone competitively reverses opioid receptors. Its short half-life means re-sedation can occur - observe and repeat doses/infusion as needed."
        ),
        QuizQuestion(
            "Which antihypertensive is contraindicated in pregnancy?",
            listOf("Labetalol", "Nifedipine", "Methyldopa", "Enalapril"),
            3,
            "ACE inhibitors (and ARBs) are teratogenic (renal dysgenesis, oligohydramnios). Labetalol, nifedipine and methyldopa are the pregnancy-safe mainstays."
        ),
        QuizQuestion(
            "Metformin's most feared (rare) adverse effect is:",
            listOf("Hypoglycaemia", "Lactic acidosis", "Pancreatitis", "Agranulocytosis"),
            1,
            "Metformin-associated lactic acidosis is rare but serious - risk rises with renal impairment, hypoxia and sepsis. It does NOT usually cause hypoglycaemia alone."
        )
    )

    private val microbiologyExtra = listOf(
        QuizQuestion(
            "The most common cause of community-acquired pneumonia is:",
            listOf("Staphylococcus aureus", "Streptococcus pneumoniae", "Klebsiella pneumoniae", "Mycoplasma pneumoniae"),
            1,
            "Pneumococcus remains the commonest typical CAP pathogen worldwide. Rusty sputum and lobar consolidation are classic clues."
        ),
        QuizQuestion(
            "The most severe form of malaria is caused by:",
            listOf("Plasmodium vivax", "Plasmodium ovale", "Plasmodium falciparum", "Plasmodium malariae"),
            2,
            "P. falciparum causes cerebral malaria, severe anaemia and organ failure because it infects red cells of all ages and sequesters in microvasculature."
        ),
        QuizQuestion(
            "Gram-positive cocci in CLUSTERS suggest:",
            listOf("Streptococcus pyogenes", "Staphylococcus aureus", "Enterococcus faecalis", "Streptococcus pneumoniae"),
            1,
            "Staphylococci form grape-like clusters (catalase positive). Streptococci form chains/pairs (catalase negative). Coagulase then separates S. aureus from other staph."
        ),
        QuizQuestion(
            "The most common cause of urinary tract infection is:",
            listOf("Proteus mirabilis", "Escherichia coli", "Klebsiella pneumoniae", "Pseudomonas aeruginosa"),
            1,
            "E. coli causes ~70-80% of uncomplicated UTIs. Proteus is associated with struvite stones; Pseudomonas with catheters and instrumentation."
        ),
        QuizQuestion(
            "Mycobacterium tuberculosis is best demonstrated with which stain?",
            listOf("Gram stain", "Ziehl-Neelsen (acid-fast) stain", "India ink", "Silver stain"),
            1,
            "Mycolic acids in the cell wall make TB acid-fast: it retains carbol fuchsin despite acid-alcohol wash (red bacilli on blue background)."
        ),
        QuizQuestion(
            "The characteristic necrosis of tuberculosis is:",
            listOf("Coagulative", "Liquefactive", "Caseous", "Fat"),
            2,
            "Caseous ('cheese-like') necrosis with granulomas is the hallmark of TB. Coagulative = infarcts; liquefactive = brain/abscess; fat = pancreatitis."
        ),
        QuizQuestion(
            "The dominant cell of ACUTE inflammation is the:",
            listOf("Lymphocyte", "Macrophage", "Neutrophil", "Eosinophil"),
            2,
            "Neutrophils dominate the first 24-48 hours. Macrophages/lymphocytes characterise chronic inflammation; eosinophils suggest parasites or allergy."
        ),
        QuizQuestion(
            "The most common cause of bacterial meningitis in neonates is:",
            listOf("Neisseria meningitidis", "Group B Streptococcus", "Haemophilus influenzae", "Listeria monocytogenes"),
            1,
            "Neonatal meningitis: Group B Strep and E. coli lead (acquired at birth). Listeria is also possible. Older children/adults: pneumococcus and meningococcus."
        ),
        QuizQuestion(
            "Infectious mononucleosis (glandular fever) is caused by:",
            listOf("Cytomegalovirus", "Epstein-Barr virus", "HIV", "Adenovirus"),
            1,
            "EBV causes fever, pharyngitis, lymphadenopathy and atypical lymphocytes. Avoid ampicillin/amoxicillin - it triggers a florid rash. CMV can mimic it."
        ),
        QuizQuestion(
            "Alpha-fetoprotein (AFP) is a tumour marker for:",
            listOf("Colorectal cancer", "Hepatocellular carcinoma", "Prostate cancer", "Ovarian epithelial cancer"),
            1,
            "AFP rises in hepatocellular carcinoma and non-seminomatous germ cell tumours (yolk sac). CEA = colorectal; PSA = prostate; CA-125 = ovarian epithelial."
        ),
        QuizQuestion(
            "A granuloma is best defined as a collection of:",
            listOf("Neutrophils", "Epithelioid macrophages", "Plasma cells", "Mast cells"),
            1,
            "Granulomas are organised collections of activated (epithelioid) macrophages, often with giant cells, seen in TB, sarcoidosis, fungal infection and foreign-body reactions."
        ),
        QuizQuestion(
            "The most common cause of traveller's diarrhoea is:",
            listOf("Enterotoxigenic E. coli (ETEC)", "Giardia lamblia", "Salmonella typhi", "Rotavirus"),
            0,
            "ETEC is the classic traveller's diarrhoea pathogen (watery, self-limiting). Giardia causes prolonged bloating/steatorrhoea; rotavirus dominates paediatric diarrhoea."
        )
    )

    private val clinicalExtra = listOf(
        QuizQuestion(
            "The first investigation for suspected acute coronary syndrome is:",
            listOf("Troponin", "12-lead ECG", "Chest X-ray", "Echocardiogram"),
            1,
            "A 12-lead ECG within 10 minutes identifies STEMI and triggers reperfusion. Troponins support the diagnosis of NSTEMI but must never delay the ECG."
        ),
        QuizQuestion(
            "The classic triad of meningitis is:",
            listOf("Fever, headache, neck stiffness", "Fever, rash, vomiting", "Headache, photophobia, seizures", "Fever, confusion, hypotension"),
            0,
            "Fever, headache and neck stiffness are the classic triad (the full triad is present in under half of cases). Non-blanching rash suggests meningococcaemia."
        ),
        QuizQuestion(
            "The most common cause of hypercalcaemia in OUTPATIENTS is:",
            listOf("Malignancy", "Primary hyperparathyroidism", "Sarcoidosis", "Vitamin D toxicity"),
            1,
            "Primary hyperparathyroidism dominates in ambulatory patients; malignancy dominates in hospitalised patients. Check PTH to separate them."
        ),
        QuizQuestion(
            "Board-like rigidity of the abdomen suggests:",
            listOf("Gastroenteritis", "Generalised peritonitis", "Irritable bowel syndrome", "Constipation"),
            1,
            "Involuntary guarding/rigidity indicates peritoneal inflammation - think perforated viscus. This is a surgical emergency, not a 'wait and see'."
        ),
        QuizQuestion(
            "The FIRST step in managing diabetic ketoacidosis is:",
            listOf("IV insulin bolus", "IV 0.9% saline", "IV bicarbonate", "Oral potassium"),
            1,
            "Fluid resuscitation comes first - DKA patients are profoundly dehydrated. Then fixed-rate insulin with potassium monitoring/replacement per protocol."
        ),
        QuizQuestion(
            "The Wells score is used to estimate the probability of:",
            listOf("Stroke", "DVT and pulmonary embolism", "Appendicitis", "Upper GI bleeding"),
            1,
            "Wells scores stratify pre-test probability of DVT/PE and guide D-dimer vs imaging pathways. (Different Wells criteria exist for DVT and PE.)"
        ),
        QuizQuestion(
            "Atrial fibrillation on ECG shows:",
            listOf("Regular rhythm with sawtooth waves", "Irregularly irregular rhythm, absent P waves", "Delta waves", "Prolonged PR interval"),
            1,
            "AF = irregularly irregular rhythm with no organised P waves. Sawtooth flutter waves = atrial flutter; delta wave = WPW; long PR = first-degree block."
        ),
        QuizQuestion(
            "The most common cause of Cushing's SYNDROME overall is:",
            listOf("Pituitary adenoma", "Adrenal adenoma", "Exogenous glucocorticoids", "Ectopic ACTH"),
            2,
            "Iatrogenic (prescribed steroids) is by far the commonest cause. Among endogenous causes, pituitary ACTH adenoma (Cushing's DISEASE) leads."
        ),
        QuizQuestion(
            "The key diagnostic test for asthma is:",
            listOf("Chest X-ray", "Spirometry with reversibility testing", "Peak flow once", "Skin prick testing"),
            1,
            "Obstructive spirometry (reduced FEV1/FVC) with significant bronchodilator reversibility supports asthma. FeNO and peak-flow variability add support."
        ),
        QuizQuestion(
            "First-line drug treatment for convulsive status epilepticus is:",
            listOf("IV phenytoin", "IV benzodiazepine (e.g. lorazepam)", "Oral carbamazepine", "IV propofol immediately"),
            1,
            "Benzodiazepines first (IV lorazepam, or buccal midazolam/rectal diazepam if no access), then a second-line agent such as levetiracetam/phenytoin per protocol."
        ),
        QuizQuestion(
            "The target oxygen saturation in an acute COPD exacerbation is:",
            listOf("94-98%", "88-92%", "100%", "80-85%"),
            1,
            "88-92% until blood gases exclude CO2 retention - uncontrolled high-flow oxygen can worsen hypercapnia in COPD. Use controlled oxygen (Venturi) and ABGs."
        ),
        QuizQuestion(
            "The earliest and most sensitive sign of compartment syndrome is:",
            listOf("Absent pulses", "Pain out of proportion / on passive stretch", "Pallor", "Paralysis"),
            1,
            "Pain out of proportion and pain on passive stretch come FIRST. Pulselessness and paralysis are LATE signs - do not wait for them. Fasciotomy is limb-saving."
        )
    )

    private val ethicsExtra = listOf(
        QuizQuestion(
            "Respecting a competent patient's informed refusal of treatment upholds:",
            listOf("Beneficence", "Non-maleficence", "Autonomy", "Justice"),
            2,
            "Autonomy means competent adults may refuse even life-saving treatment. Beneficence (doing good) does not override a valid refusal."
        ),
        QuizQuestion(
            "Valid consent requires:",
            listOf("Capacity, information, voluntariness", "A signature only", "Family agreement", "A doctor's decision"),
            0,
            "Consent is valid when the patient has capacity, receives adequate information (including alternatives and risks), and decides freely without coercion."
        ),
        QuizQuestion(
            "Breaking confidentiality may be justified when:",
            listOf("A relative asks for information", "There is a serious risk of harm to others / public interest", "The patient is famous", "It is convenient for the team"),
            1,
            "Confidentiality can be breached for serious public-interest reasons (e.g. risk to others, certain notifiable diseases, court orders) - disclose the minimum necessary and document."
        ),
        QuizQuestion(
            "SBAR handover stands for:",
            listOf("Situation, Background, Assessment, Recommendation", "Signs, Bloods, Actions, Results", "Summary, Basics, Analysis, Review", "Safety, Bed, Alert, Response"),
            0,
            "SBAR structures urgent communication: Situation (who/what), Background (context), Assessment (your findings), Recommendation (what you need)."
        ),
        QuizQuestion(
            "GCS: eyes open to pain, confused conversation, localises to pain. The score is:",
            listOf("9", "10", "11", "12"),
            2,
            "E2 (to pain) + V4 (confused) + M5 (localises) = 11. Remember: E4 V5 M6 = 15; E1 V1 M1 = 3 is the minimum."
        ),
        QuizQuestion(
            "The duty of candour requires clinicians to:",
            listOf("Hide errors to avoid distress", "Be open and honest when things go wrong", "Only inform managers", "Wait for a complaint"),
            1,
            "Duty of candour: tell the patient (or advocate) when something has gone wrong, apologise, explain fully, and document. It is professional and legal in many systems."
        ),
        QuizQuestion(
            "Gillick competence refers to:",
            listOf("Elderly patients' capacity", "A child under 16 consenting if they understand sufficiently", "Emergency treatment without consent", "Research ethics approval"),
            1,
            "A child under 16 can consent to treatment if they have sufficient understanding and intelligence to fully grasp what is proposed (UK-derived concept used widely)."
        ),
        QuizQuestion(
            "Giving opioids to relieve pain, foreseeing it may hasten death, is ethically supported by:",
            listOf("The principle of double effect", "Utilitarianism only", "The duty of candour", "Paternalism"),
            0,
            "Double effect: an action with a good intended effect (pain relief) may be permissible even with a foreseen but unintended bad effect (hastened death), if proportionate."
        ),
        QuizQuestion(
            "The single most effective measure to prevent hospital-acquired infection is:",
            listOf("Prophylactic antibiotics", "Hand hygiene", "Isolation of all patients", "Air filtration"),
            1,
            "Hand hygiene (WHO 5 moments) is the most effective, cheapest infection-control measure. Antibiotic stewardship supports it but does not replace it."
        ),
        QuizQuestion(
            "For intimate examinations, a chaperone should be:",
            listOf("Used only for female patients", "Offered regardless of the genders involved", "Used only if the patient asks", "Never necessary for students"),
            1,
            "Offer a chaperone for all intimate examinations regardless of clinician/patient gender; document the offer and who was present. It protects both parties."
        ),
        QuizQuestion(
            "Decision-making capacity is:",
            listOf("Global and permanent once assessed", "Decision-specific and time-specific", "The same as consciousness", "Determined by age alone"),
            1,
            "Capacity is assessed for EACH decision at THAT time: can the person understand, retain, weigh and communicate the choice? A person may have capacity for some decisions but not others."
        ),
        QuizQuestion(
            "When an adult lacks capacity, treatment decisions should be based on:",
            listOf("What the doctor prefers", "The patient's best interests, considering their known wishes", "The cheapest option", "Family convenience"),
            1,
            "Best-interests decision-making considers the patient's past and present wishes, beliefs and values, with input from family/advocates - not simply what the team wants."
        )
    )
}
