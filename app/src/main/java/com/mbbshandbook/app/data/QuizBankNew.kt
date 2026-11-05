package com.mbbshandbook.app.data

/** New quiz categories added to StudyTools. */
internal val quizBankNewCategories: List<QuizCategory> = listOf(
    QuizCategory(
        id = "paediatrics", title = "Paediatrics", emoji = "🧸",
        questions = listOf(
            QuizQuestion(
                "The most common cause of bronchiolitis in infants is:",
                listOf("Influenza virus", "Respiratory syncytial virus (RSV)", "Adenovirus", "Streptococcus pneumoniae"),
                1,
                "RSV causes most bronchiolitis in under-2s: coryza, wheeze, crackles, feeding difficulty. Management is mainly supportive (oxygen, hydration)."
            ),
            QuizQuestion(
                "First-line treatment for Kawasaki disease is:",
                listOf("IV immunoglobulin + aspirin", "Oral prednisolone alone", "IV antibiotics", "Nebulised adrenaline"),
                0,
                "IVIG (plus aspirin) reduces the risk of coronary artery aneurysms. Suspect it with fever ≥5 days plus conjunctivitis, rash, mucosal changes, extremity changes or lymphadenopathy."
            ),
            QuizQuestion(
                "APGAR scoring is performed at:",
                listOf("Birth only", "1 and 5 minutes after birth", "10 and 20 minutes", "1 hour after birth"),
                1,
                "APGAR is assessed at 1 and 5 minutes (and at 10 if the score remains low). It describes the baby's condition - it does NOT guide resuscitation, which follows the ABC algorithm."
            ),
            QuizQuestion(
                "The most common congenital heart defect is:",
                listOf("Tetralogy of Fallot", "Ventricular septal defect (VSD)", "Transposition of the great arteries", "Coarctation of the aorta"),
                1,
                "VSD is the commonest congenital cardiac lesion. Small ones often close spontaneously; large ones cause heart failure and need repair."
            ),
            QuizQuestion(
                "First-line treatment for mild-moderate dehydration from gastroenteritis in children is:",
                listOf("IV 0.9% saline", "Oral rehydration solution (ORS)", "IV dextrose", "Antibiotics"),
                1,
                "ORS is first line for mild-moderate dehydration (e.g. 50 mL/kg over 4 hours for mild). IV fluids are reserved for shock, severe dehydration or failed ORS."
            ),
            QuizQuestion(
                "A SIMPLE febrile seizure is characterised by:",
                listOf("Focal features and duration >15 min", "Generalised seizure, <15 min, once in 24 h", "Recurring focal seizures", "Seizures without fever"),
                1,
                "Simple febrile convulsions (6 months-5 years) are generalised, brief (<15 min) and do not recur within 24 hours. Complex features warrant further investigation."
            ),
            QuizQuestion(
                "The classic triad of intussusception is:",
                listOf("Fever, rash, vomiting", "Colicky pain, redcurrant-jelly stool, sausage-shaped mass", "Diarrhoea, dehydration, shock", "Jaundice, pale stool, dark urine"),
                1,
                "Intussusception (usually 6-18 months): intermittent colic with drawing up legs, redcurrant-jelly stool (late), sausage-shaped mass. Ultrasound confirms; air enema reduces most cases."
            ),
            QuizQuestion(
                "Vitamin K given at birth prevents:",
                listOf("Neonatal jaundice", "Haemorrhagic disease of the newborn", "Rickets", "Neonatal sepsis"),
                1,
                "Newborns have low vitamin K stores and sterile guts; prophylactic vitamin K prevents vitamin K deficiency bleeding (early, classic and late forms)."
            ),
            QuizQuestion(
                "Croup (barking cough, stridor) is most commonly caused by:",
                listOf("Parainfluenza virus", "Haemophilus influenzae type b", "RSV", "Bordetella pertussis"),
                0,
                "Croup is usually parainfluenza. A single dose of dexamethasone helps all severities; nebulised adrenaline for moderate-severe cases. Hib causes epiglottitis - a different, more toxic picture."
            ),
            QuizQuestion(
                "The most common cause of nephrotic syndrome in children is:",
                listOf("Minimal change disease", "Membranous nephropathy", "IgA nephropathy", "Post-streptococcal GN"),
                0,
                "Minimal change disease accounts for ~90% of childhood nephrotic syndrome: oedema, heavy proteinuria, hypoalbuminaemia. Most respond to steroids."
            ),
            QuizQuestion(
                "A pincer grasp typically develops at around:",
                listOf("4 months", "6 months", "9-10 months", "15 months"),
                2,
                "Fine motor milestones: palmar grasp ~6 months, transferring objects ~6-7 months, pincer grasp ~9-10 months, scribbling ~15-18 months."
            ),
            QuizQuestion(
                "Using the 4-2-1 rule, maintenance fluids for a 25 kg child are:",
                listOf("55 mL/hr", "65 mL/hr", "75 mL/hr", "100 mL/hr"),
                1,
                "4 mL/kg/hr for the first 10 kg (40) + 2 for the next 10 (20) + 1 for the remaining 5 (5) = 65 mL/hr."
            )
        )
    ),
    QuizCategory(
        id = "obgyn", title = "OB/GYN", emoji = "🤰",
        questions = listOf(
            QuizQuestion(
                "The first-line drug for eclamptic seizures is:",
                listOf("Diazepam", "Magnesium sulphate", "Phenytoin", "Labetalol"),
                1,
                "IV magnesium sulphate is superior to diazepam/phenytoin for eclampsia (loading + maintenance infusion). Monitor reflexes and respiration; calcium gluconate reverses toxicity."
            ),
            QuizQuestion(
                "The most common cause of postpartum haemorrhage is:",
                listOf("Retained placenta", "Uterine atony", "Genital tract trauma", "Coagulopathy"),
                1,
                "Atony ('tone') causes ~70% of PPH - remember the 4 Ts: Tone, Tissue, Trauma, Thrombin. Start with uterine massage and uterotonics."
            ),
            QuizQuestion(
                "The classic triad of ectopic pregnancy is:",
                listOf("Amenorrhoea, abdominal pain, vaginal bleeding", "Fever, discharge, pain", "Hypertension, proteinuria, oedema", "Nausea, breast tenderness, fatigue"),
                0,
                "Amenorrhoea + unilateral pain + bleeding in early pregnancy = ectopic until proven otherwise. Rupture causes shoulder-tip pain and shock - a surgical emergency."
            ),
            QuizQuestion(
                "Pre-eclampsia is defined as new hypertension after 20 weeks PLUS:",
                listOf("Proteinuria or other organ dysfunction", "Oedema alone", "Headache alone", "Gestational diabetes"),
                0,
                "New hypertension (≥140/90) after 20 weeks with proteinuria OR maternal organ dysfunction (renal, hepatic, neurological, haematological) or uteroplacental dysfunction."
            ),
            QuizQuestion(
                "The most common cause of first-trimester vaginal bleeding is:",
                listOf("Ectopic pregnancy", "Miscarriage (threatened/inevitable)", "Placenta praevia", "Cervical cancer"),
                1,
                "Miscarriage is the commonest cause of early pregnancy bleeding. Always exclude ectopic pregnancy - it is the dangerous one."
            ),
            QuizQuestion(
                "The FIRST manoeuvre for shoulder dystocia is:",
                listOf("McRoberts manoeuvre", "Internal rotation manoeuvres", "Symphysiotomy", "Fundal pressure"),
                0,
                "McRoberts (hyperflexing maternal thighs onto abdomen) plus suprapubic pressure resolves most cases. NEVER apply fundal pressure - it worsens impaction."
            ),
            QuizQuestion(
                "The primary mechanism of the combined oral contraceptive pill is:",
                listOf("Thickening cervical mucus only", "Inhibition of ovulation", "Preventing implantation only", "Spermicidal action"),
                1,
                "The COCP primarily suppresses ovulation via negative feedback on gonadotrophins; cervical mucus and endometrial changes are secondary effects."
            ),
            QuizQuestion(
                "In many low- and middle-income countries, the most common gynaecological malignancy is:",
                listOf("Endometrial cancer", "Ovarian cancer", "Cervical cancer", "Vulval cancer"),
                2,
                "Cervical cancer (HPV-driven) leads in many LMICs due to limited screening. HPV vaccination and cervical screening are highly effective prevention."
            ),
            QuizQuestion(
                "Anti-D prophylaxis is given to rhesus-negative mothers at:",
                listOf("12 weeks only", "28 weeks (±34) and within 72 h of delivery if baby is Rh-positive", "Only after miscarriage", "Only if the baby is Rh-negative"),
                1,
                "Routine antenatal anti-D (around 28 weeks) plus postnatal anti-D within 72 hours if the baby is Rh-positive, and after any sensitising event (bleeding, procedures, trauma)."
            ),
            QuizQuestion(
                "The most common cause of secondary amenorrhoea is:",
                listOf("PCOS", "Pregnancy", "Hyperprolactinaemia", "Premature ovarian failure"),
                1,
                "ALWAYS exclude pregnancy first in secondary amenorrhoea - it is the commonest cause. Then consider PCOS, hypothalamic causes, prolactin, thyroid and ovarian failure."
            ),
            QuizQuestion(
                "The Rotterdam criteria for PCOS require 2 of 3 features. Which is NOT one of them?",
                listOf("Oligo/anovulation", "Clinical or biochemical hyperandrogenism", "Polycystic ovaries on ultrasound", "Elevated LH:FSH ratio"),
                3,
                "Rotterdam: oligo/anovulation, hyperandrogenism, polycystic ovarian morphology (2 of 3, after excluding other causes). LH:FSH ratio is supportive but not a criterion."
            ),
            QuizQuestion(
                "Active management of the third stage of labour includes:",
                listOf("Uterotonic, controlled cord traction, uterine massage", "Waiting for spontaneous delivery of the placenta", "Immediate cord clamping only", "Routine manual removal"),
                0,
                "AMTSL (uterotonic after delivery of the baby, controlled cord traction, uterine massage/tone assessment) reduces PPH risk compared with expectant management."
            )
        )
    ),
    QuizCategory(
        id = "psychiatry", title = "Psychiatry", emoji = "🧠",
        questions = listOf(
            QuizQuestion(
                "First-line pharmacotherapy for schizophrenia is usually:",
                listOf("A typical antipsychotic only", "An atypical antipsychotic (e.g. risperidone, olanzapine)", "Lithium", "A benzodiazepine long-term"),
                1,
                "Atypical antipsychotics are generally first line (individualised choice). Clozapine is reserved for treatment-resistant illness with blood monitoring."
            ),
            QuizQuestion(
                "SSRIs work primarily by:",
                listOf("Blocking dopamine receptors", "Inhibiting serotonin reuptake", "Inhibiting monoamine oxidase", "Blocking GABA receptors"),
                1,
                "SSRIs block the serotonin transporter, increasing synaptic serotonin. They take 2-6 weeks for full effect; warn about early GI effects and activation."
            ),
            QuizQuestion(
                "The most important initial assessment in a patient with depression is:",
                listOf("Sleep pattern", "Suicide risk", "Appetite", "Work performance"),
                1,
                "Always assess suicidal ideation, intent, plans and protective factors. Safety comes before symptom scales."
            ),
            QuizQuestion(
                "Patients on lithium need regular monitoring of:",
                listOf("Lithium levels, renal and thyroid function", "Liver function only", "Blood glucose only", "ECG weekly"),
                0,
                "Lithium has a narrow therapeutic index: check levels (12 h post-dose), U&Es/eGFR and TFTs regularly. Toxicity is precipitated by dehydration, NSAIDs, ACE inhibitors and diuretics."
            ),
            QuizQuestion(
                "Which feature favours DELIRIUM over dementia?",
                listOf("Insidious onset over years", "Acute onset with fluctuating attention", "Stable personality change", "Progressive memory loss without fluctuation"),
                1,
                "Delirium: acute, fluctuating course with inattention and altered consciousness - always look for a medical cause. Dementia: chronic, progressive, usually clear consciousness early."
            ),
            QuizQuestion(
                "First-line treatment for OCD is:",
                listOf("SSRI + CBT with exposure and response prevention", "Long-term benzodiazepines", "Antipsychotic monotherapy", "Electroconvulsive therapy"),
                0,
                "CBT with ERP is the psychological treatment of choice; SSRIs (often higher doses) are first-line medication. Combined treatment is common in moderate-severe cases."
            ),
            QuizQuestion(
                "Neuroleptic malignant syndrome presents with:",
                listOf("Fever, rigidity, autonomic instability, raised CK", "Diarrhoea and miosis", "Hypothermia and flaccidity", "Isolated tremor"),
                0,
                "NMS is a life-threatening reaction to dopamine blockade: hyperthermia, 'lead-pipe' rigidity, confusion, autonomic instability, raised CK. Stop the drug; supportive care ± dantrolene/bromocriptine."
            ),
            QuizQuestion(
                "Bipolar I disorder requires at least one:",
                listOf("Major depressive episode", "Manic episode", "Hypomanic episode only", "Panic attack"),
                1,
                "Bipolar I = at least one manic episode (depression is common but not required). Bipolar II = hypomania + major depression, never full mania."
            ),
            QuizQuestion(
                "Abrupt withdrawal from benzodiazepines can cause:",
                listOf("Seizures", "Permanent amnesia", "Hypertension only", "No symptoms if used <1 year"),
                0,
                "Benzodiazepine withdrawal can be life-threatening (seizures, delirium). Always taper gradually, especially after prolonged use."
            ),
            QuizQuestion(
                "Serotonin syndrome is characterised by:",
                listOf("Neuromuscular hyperactivity, autonomic instability, altered mental state", "Lead-pipe rigidity and hyporeflexia", "Dry skin and urinary retention", "Bradycardia and hypothermia"),
                0,
                "Serotonin syndrome: clonus/hyperreflexia, agitation, diaphoresis, hyperthermia - often after combining serotonergic drugs. Contrast with NMS (rigidity, hyporeflexia, slower onset)."
            ),
            QuizQuestion(
                "First-line pharmacotherapy for generalised anxiety disorder is usually:",
                listOf("An SSRI", "A benzodiazepine long-term", "A beta blocker", "An antipsychotic"),
                0,
                "SSRIs (e.g. sertraline) are first line for GAD, alongside CBT. Benzodiazepines are for short-term crisis use only due to dependence risk."
            ),
            QuizQuestion(
                "A fixed false belief held despite contrary evidence is a:",
                listOf("Hallucination", "Delusion", "Illusion", "Obsession"),
                1,
                "Delusion = fixed false belief (thought content). Hallucination = perception without stimulus. Obsession = intrusive, ego-dystonic thought recognised as one's own."
            )
        )
    ),
    QuizCategory(
        id = "emergency", title = "Emergency Medicine", emoji = "🚑",
        questions = listOf(
            QuizQuestion(
                "In adult CPR, the compression-to-ventilation ratio is:",
                listOf("15:2", "30:2", "5:1", "Continuous compressions only for lay rescuers with no training"),
                1,
                "Standard adult BLS/ALS uses 30 compressions to 2 breaths (100-120/min, 5-6 cm depth). Hands-only CPR is acceptable for untrained bystanders."
            ),
            QuizQuestion(
                "The adult IM adrenaline dose for anaphylaxis is:",
                listOf("0.5 mg (0.5 mL of 1:1000)", "1 mg IV", "0.1 mg IM", "5 mg nebulised"),
                0,
                "Adults: 0.5 mg IM (0.5 mL of 1:1000) into the anterolateral thigh, repeat every 5 minutes if needed. IV adrenaline is for peri-arrest experts only."
            ),
            QuizQuestion(
                "A tension pneumothorax should be treated by:",
                listOf("Waiting for a chest X-ray", "Immediate needle/finger decompression", "High-flow oxygen alone", "Intubation first"),
                1,
                "Tension pneumothorax is a CLINICAL diagnosis (hypotension, distended neck veins, tracheal deviation, absent breath sounds). Decompress immediately - do not wait for imaging."
            ),
            QuizQuestion(
                "The SHOCKABLE rhythms in cardiac arrest are:",
                listOf("Asystole and PEA", "VF and pulseless VT", "Sinus tachycardia and AF", "Complete heart block"),
                1,
                "VF/pulseless VT are defibrillated. Asystole and PEA are non-shockable - focus on CPR, adrenaline and reversible causes (4 Hs and 4 Ts)."
            ),
            QuizQuestion(
                "In suspected sepsis, the first-hour bundle includes:",
                listOf("Cultures, lactate, antibiotics and fluids", "CT scan before antibiotics", "Steroids for everyone", "Waiting for culture results"),
                0,
                "Sepsis bundles: measure lactate, take blood cultures, give broad-spectrum antibiotics and fluids for hypotension/lactate elevation - all urgently, without waiting for results."
            ),
            QuizQuestion(
                "The antidote for paracetamol (acetaminophen) overdose is:",
                listOf("Naloxone", "N-acetylcysteine", "Flumazenil", "Activated charcoal only"),
                1,
                "NAC replenishes glutathione and prevents hepatotoxicity - most effective within 8 hours but beneficial later. Use the treatment nomogram with timed levels."
            ),
            QuizQuestion(
                "A conscious hypoglycaemic patient who can swallow should receive:",
                listOf("IV dextrose immediately", "Fast-acting oral glucose", "IM glucagon first", "Insulin"),
                1,
                "Conscious and able to swallow: 15-20 g fast-acting oral carbohydrate, recheck in 15 minutes. Unconscious/unable to swallow: IV dextrose or IM glucagon."
            ),
            QuizQuestion(
                "The first imaging investigation in suspected acute stroke is:",
                listOf("MRI brain", "Non-contrast CT head", "CT angiography first", "Carotid Doppler"),
                1,
                "Urgent non-contrast CT excludes haemorrhage and determines thrombolysis/thrombectomy eligibility. Note the last-known-well time - it drives the pathway."
            ),
            QuizQuestion(
                "The first priority in trauma assessment is:",
                listOf("CT scan", "Airway with cervical spine control", "Tetanus prophylaxis", "Wound suturing"),
                1,
                "The primary survey starts with airway AND inline C-spine control, then breathing, circulation, disability, exposure. Life threats are treated as they are found."
            ),
            QuizQuestion(
                "The IV adrenaline dose in adult cardiac arrest is:",
                listOf("0.5 mg every 10 minutes", "1 mg every 3-5 minutes", "10 mg once", "0.1 mg every minute"),
                1,
                "ALS: adrenaline 1 mg IV/IO every 3-5 minutes (each alternate loop). For VF/pVT also give amiodarone 300 mg after the third shock."
            ),
            QuizQuestion(
                "For severe external limb haemorrhage not controlled by direct pressure, the next step is:",
                listOf("A tourniquet", "More gauze only", "Elevation alone", "Immediate amputation"),
                0,
                "Catastrophic limb bleeding: direct pressure, then a tourniquet if uncontrolled (note the time). Haemostatic dressings and early blood products support damage-control resuscitation."
            ),
            QuizQuestion(
                "The treatment for carbon monoxide poisoning is:",
                listOf("Methylene blue", "High-flow 100% oxygen", "IV bicarbonate", "N-acetylcysteine"),
                1,
                "100% oxygen displaces CO from haemoglobin (half-life falls from ~4-5 h to ~60-90 min). Consider hyperbaric oxygen for severe cases (neurological signs, cardiac involvement, pregnancy)."
            )
        )
    )
)
