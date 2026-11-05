package com.mbbshandbook.app.data

/** Medical and longitudinal-care specialties. */
internal val medicalSpecialties: List<Specialty> = listOf(
    Specialty(
        id = "internal-medicine",
        name = "Internal Medicine",
        emoji = "🩺",
        category = "Medical & longitudinal",
        tagline = "The diagnostic heart of hospital care",
        trainingLength = "≈4-6 years residency after internship, +1-3 years for subspecialty",
        overview = "Physicians diagnose and manage adult disease across every organ system - from sepsis and heart failure to diabetes, stroke and complex multisystem puzzles. It is the broadest hospital specialty and the parent of cardiology, gastroenterology, nephrology, endocrinology, respiratory medicine, rheumatology, haematology and infectious diseases. If you love reasoning through a differential more than operating, this is home.",
        dayInLife = listOf(
            "Ward round with the team: review overnight events, examine patients, adjust plans.",
            "Admit new patients from the emergency department - history, examination, differential, initial management.",
            "Chase and interpret results: bloods, cultures, ECGs, imaging; discuss with radiology and the lab.",
            "Procedures on some days: lumbar puncture, ascitic tap, pleural tap, central lines.",
            "Family conversations, discharge planning, referrals to subspecialties, and teaching juniors."
        ),
        keySkills = listOf(
            "Structured clinical reasoning and broad differentials",
            "Interpreting ECGs, chest X-rays, ABGs and lab patterns",
            "Managing multimorbidity and polypharmacy safely",
            "Clear communication with patients, families and teams",
            "Knowing when to escalate to ICU or a subspecialist"
        ),
        rewards = listOf(
            "Intellectual variety - no two days are identical",
            "Deep, lasting relationships with patients",
            "Gateway to many fellowships and subspecialties",
            "Skills transfer everywhere in the world"
        ),
        challenges = listOf(
            "Heavy admissions, bed pressure and administrative load",
            "Chronic disease can feel like holding back the tide",
            "Frequent on-calls and night shifts during training",
            "Emotional weight of end-of-life care"
        ),
        lifestyle = "Training is busy with frequent on-calls; consultant life varies - acute physicians work shift patterns, while clinic-based subspecialists (endocrine, rheumatology) often have predictable hours. Competitiveness: moderate to enter, higher for popular fellowships.",
        exams = listOf(
            "Nigeria: WACP (West African College of Physicians) or NPMCN Fellowship - Part I & Part II",
            "UK: MRCP(UK) - PACES + written; then Internal Medicine Training and Specialty Certificate Exams",
            "US: USMLE Steps 1-3 → ABIM board certification after residency",
            "Membership/Fellowship exams of your national physician college"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Excel in medicine rotations - the internal medicine posting is your audition.",
                "Build strong physiology and pharmacology; they are the language of the specialty.",
                "Sit in on medical admissions and follow patients from door to diagnosis."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Choose a medicine rotation if you can; own your patients' workups.",
                "Master practical skills: cannulation, ABGs, ECGs, prescribing, discharge summaries.",
                "Ask registrars to teach you their reasoning out loud."
            )),
            RoadmapStage("Pre-residency / service years", "0-2 years (varies)", listOf(
                "Nigeria: NYSC year, then medical officer posts - often the best learning years.",
                "Start Part I exam reading groups early; log cases and procedures.",
                "UK: Foundation Programme (2 years) then apply to Internal Medicine Training. US: apply to categorical IM residency via the Match."
            )),
            RoadmapStage("Residency / Specialty training", "4-6 years", listOf(
                "Junior residency: acute medicine, ICU, emergency admissions; pass Part I.",
                "Senior residency: lead the team, run clinics, supervise juniors; pass Part II.",
                "Complete a research project or audit and present at college meetings."
            )),
            RoadmapStage("Fellowship / Consultant", "1-3 years fellowship (optional)", listOf(
                "Subspecialise: cardiology, gastro, nephrology, endocrine, respiratory, ID, haematology…",
                "Or practise as a general physician / hospitalist - in huge demand.",
                "Consultant life: clinics, ward weeks, teaching, and a subspecialty interest."
            ))
        ),
        startNow = listOf(
            "Learn to read ECGs and chest X-rays systematically this month.",
            "Present one patient properly at every ward round - problem list, differential, plan.",
            "Read around your patients the same day you see them.",
            "Find a physician mentor and ask what they wish they had known as students."
        )
    ),
    Specialty(
        id = "cardiology",
        name = "Cardiology",
        emoji = "❤️",
        category = "Medical & longitudinal",
        tagline = "High-stakes hearts, dramatic saves",
        trainingLength = "≈4-6 years internal medicine + 2-3 years cardiology fellowship",
        overview = "Cardiologists manage the heart and vessels: acute coronary syndromes, heart failure, arrhythmias, valve disease and prevention. It blends acute drama (primary PCI for STEMI) with long-term clinic care, and is one of the most procedure-heavy medical subspecialties - angiography, stenting, pacemakers, electrophysiology studies and echocardiography.",
        dayInLife = listOf(
            "CCU round: review overnight MIs, arrhythmias and heart failure admissions.",
            "Cath lab sessions: coronary angiograms, angioplasty and stents.",
            "Echo lists: perform and report transthoracic (± transoesophageal) echoes.",
            "Clinic: chest pain, palpitations, heart failure follow-up, device checks.",
            "STEMI call: activate the lab and reperfuse - minutes matter."
        ),
        keySkills = listOf(
            "ECG mastery - ischaemia, arrhythmias, blocks, axis",
            "Echocardiography interpretation (and eventually performing it)",
            "Steady hands and calm under pressure for catheter procedures",
            "Risk-factor counselling and secondary prevention",
            "Rapid decision-making in acute coronary care"
        ),
        rewards = listOf(
            "Visible, immediate impact - opening an artery saves a life today",
            "Perfect mix of procedures, imaging and clinic medicine",
            "Strong evidence base with satisfying, measurable outcomes",
            "Excellent career demand worldwide"
        ),
        challenges = listOf(
            "Long training pathway and competitive fellowship entry",
            "STEMI on-calls are genuinely exhausting",
            "Radiation exposure in the lab (mitigated by protection)",
            "High expectations and medicolegal exposure"
        ),
        lifestyle = "Interventional cardiologists carry STEMI call rosters; electrophysiologists and imaging cardiologists have more predictable schedules. One of the most competitive medical fellowships - research and exam performance matter early.",
        exams = listOf(
            "Nigeria: WACP/NPMCN Faculty of Internal Medicine, then cardiology subspecialty fellowship",
            "UK: MRCP(UK) + Cardiology Specialty Certificate Exam; CSCST in cardiology",
            "US: ABIM Internal Medicine boards, then ABIM Cardiovascular Disease boards",
            "Echo accreditation (e.g. BSE) and device/EP certifications"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Own the cardiac exam: murmurs, JVP, signs of failure.",
                "Learn ECG basics early - it pays off in every rotation.",
                "Do a cardiology elective or observership if available."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Rotate through medicine; see acute coronary care in action.",
                "Get comfortable managing hypertension, heart failure and AF basics.",
                "Start collecting audits or case reports."
            )),
            RoadmapStage("Internal medicine training", "4-6 years", listOf(
                "Complete physician residency / IM training and membership exams.",
                "Signal interest early: cardiology rotations, echo exposure, research.",
                "Publish or present - fellowship selection is competitive."
            )),
            RoadmapStage("Cardiology fellowship", "2-3 years", listOf(
                "Core cardiology: CCU, echo, cath lab, devices, nuclear/CMR imaging.",
                "Choose a lane: interventional, electrophysiology, heart failure, imaging.",
                "Pass the subspecialty board / fellowship exit exam."
            )),
            RoadmapStage("Consultant / Advanced fellowship", "ongoing", listOf(
                "Optional extra year in complex PCI, EP ablation, structural heart or transplant.",
                "Consultant practice: lab sessions, clinics, on-call and teaching."
            ))
        ),
        startNow = listOf(
            "Work through an ECG book with real tracings - 15 minutes a day.",
            "Attend the cardiac clinic and listen to every murmur you can.",
            "Learn the STEMI pathway in your teaching hospital.",
            "Ask about student echo or cath-lab observation sessions."
        )
    ),
    Specialty(
        id = "neurology",
        name = "Neurology",
        emoji = "⚡",
        category = "Medical & longitudinal",
        tagline = "Localise the lesion, solve the puzzle",
        trainingLength = "≈4-6 years internal medicine + 2-3 years neurology training",
        overview = "Neurologists diagnose and treat disease of the brain, spine, nerves and muscle: stroke, epilepsy, headache, movement disorders, multiple sclerosis, neuropathies and neuromuscular disease. It is the most localisation-driven specialty in medicine - the examination tells you WHERE the problem is before any scan. Increasingly treatable conditions make it one of the fastest-moving fields.",
        dayInLife = listOf(
            "Clinic: headache, epilepsy follow-up, movement disorders, MS biologics reviews.",
            "Stroke rounds: thrombolysis decisions, thrombectomy referrals, secondary prevention.",
            "Neurophysiology sessions: EMG and nerve conduction studies.",
            "Consults for the hospital: altered consciousness, weakness, first seizures.",
            "LP lists, botulinum toxin clinics, and reviewing MRI brains with neuroradiology."
        ),
        keySkills = listOf(
            "Flawless neurological examination and lesion localisation",
            "Patience with diagnostic uncertainty and chronic disease",
            "Interpreting neuroimaging, EEG and nerve conduction reports",
            "Sensitive communication - many diagnoses change lives permanently",
            "Attention to detail in history (the story IS the diagnosis)"
        ),
        rewards = listOf(
            "The purest diagnostic reasoning in medicine",
            "Therapeutic revolution - stroke thrombectomy, MS and migraine biologics",
            "Long-term patient relationships",
            "Clinic-heavy consultant life suits many lifestyles"
        ),
        challenges = listOf(
            "Many conditions remain incurable; breaking bad news is frequent",
            "Stroke on-calls are intense and time-critical",
            "Fewer neurologists than needed - workload can be heavy",
            "Functional neurological disorders can be demanding consultations"
        ),
        lifestyle = "Mostly outpatient with stroke/acute neurology on-call in larger centres. Generally one of the more lifestyle-friendly medical subspecialties at consultant level; training includes general medicine. Competitiveness: moderately high.",
        exams = listOf(
            "Nigeria: WACP/NPMCN Internal Medicine, then neurology subspecialty fellowship",
            "UK: MRCP(UK) + Neurology Specialty Certificate Exam",
            "US: ABIM IM boards → ABPN Neurology boards (US route is a dedicated neurology residency)",
            "EEG/EMG competency certifications"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Learn the neuro exam properly - cranial nerves, limbs, gait - and practise on everyone.",
                "Neuroanatomy is your friend: tracts, nuclei, vascular territories.",
                "See stroke and epilepsy patients during the medicine posting."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Medicine rotation: clerk every neuro admission you can find.",
                "Learn to describe deficits precisely (power grading, reflexes, sensory levels).",
                "Observe a thrombolysis decision if your centre offers it."
            )),
            RoadmapStage("Internal medicine training", "4-6 years", listOf(
                "Complete physician training and membership exams (Nigeria/UK route).",
                "US route: apply directly to a 4-year neurology residency (1 year IM + 3 neurology).",
                "Build a neurology portfolio: audits, case reports, neurophysiology exposure."
            )),
            RoadmapStage("Neurology specialty training", "2-3 years", listOf(
                "General neurology clinics, stroke unit, neurophysiology, epilepsy, neuromuscular.",
                "Pass the subspecialty exit examination.",
                "Choose an interest: stroke, epilepsy, movement disorders, MS, headache, neuromuscular."
            )),
            RoadmapStage("Consultant / Fellowship", "1-2 years optional fellowship", listOf(
                "Advanced fellowships: interventional stroke, epilepsy monitoring, neuromuscular.",
                "Consultant practice: clinics, acute neurology, MDTs and teaching."
            ))
        ),
        startNow = listOf(
            "Do a full neuro exam on every medicine patient until it takes you under 5 minutes.",
            "Learn stroke syndromes by vascular territory.",
            "Sit in a neurology clinic and watch how the history cracks the case.",
            "Practise presenting: 'This is a right UMN lesion at the level of…'."
        )
    ),
    Specialty(
        id = "psychiatry",
        name = "Psychiatry",
        emoji = "🧠",
        category = "Medical & longitudinal",
        tagline = "Medicine where the story is the illness",
        trainingLength = "≈4-6 years residency after internship",
        overview = "Psychiatrists diagnose and treat mental illness: depression, bipolar disorder, psychosis, anxiety disorders, addiction, eating disorders and the mental health of children, older adults and forensic patients. It combines neuroscience, pharmacology and psychotherapy with the deepest communication skills in medicine. Stigma is falling, services are expanding, and the need - especially in low-resource settings - is enormous.",
        dayInLife = listOf(
            "Ward round on the acute inpatient unit: risk assessment, mental state exams, treatment plans.",
            "Outpatient clinic: medication reviews, therapy-informed consultations, family meetings.",
            "Liaison work: assess self-harm presentations in ED, capacity questions on the wards.",
            "Multidisciplinary meetings with psychologists, nurses, social workers and OTs.",
            "Tribunals/court reports where the law meets mental health."
        ),
        keySkills = listOf(
            "Advanced interviewing: building rapport, eliciting psychopathology",
            "Risk assessment (suicide, self-neglect, harm) and safety planning",
            "Psychopharmacology and managing side effects",
            "Comfort with uncertainty, ambiguity and slow progress",
            "Teamwork - psychiatry is never a solo sport"
        ),
        rewards = listOf(
            "Profound impact - recovery stories stay with you for life",
            "Time to actually know your patients (longer consultations)",
            "Flexible working patterns and strong trainee wellbeing culture in many systems",
            "Huge unmet need = job security everywhere, plus global health opportunities"
        ),
        challenges = listOf(
            "Stigma and underfunded services in many countries",
            "Emotional load: suicide, trauma histories, involuntary treatment",
            "Progress can be slow and non-linear",
            "Risk decisions carry real weight and scrutiny"
        ),
        lifestyle = "Among the most flexible specialties: part-time and less-than-full-time training are common, on-calls are lighter than acute medicine, and private practice is possible. Competitiveness: moderate and rising as lifestyle awareness grows.",
        exams = listOf(
            "Nigeria: WACP Faculty of Psychiatry or NPMCN Fellowship (Part I & II)",
            "UK: MRCPsych (Papers A, B and CASC)",
            "US: USMLE → ACGME psychiatry residency → ABPN boards",
            "Subspecialty endorsements: child & adolescent, forensic, addiction, old age"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Take the psychiatry posting seriously - mental illness appears in EVERY specialty.",
                "Learn the mental state examination and practise presenting it.",
                "Learn basic screening tools: PHQ-9, GAD-7, suicide risk questions."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Seek a psychiatry rotation if offered; otherwise liaise with psych services from other wards.",
                "Practise de-escalation and empathetic communication daily.",
                "Notice how often mental health drives physical admissions."
            )),
            RoadmapStage("Pre-residency / service years", "0-2 years (varies)", listOf(
                "Nigeria: NYSC + medical officer posts; psychiatric hospital experience helps.",
                "UK: Foundation then Core Psychiatry Training (CT1-3). US: psychiatry residency via the Match.",
                "Read around psychopharmacology; join journal clubs."
            )),
            RoadmapStage("Residency / Higher training", "4-6 years total", listOf(
                "Rotations: general adult, old age, child & adolescent, addiction, forensic, liaison.",
                "Pass membership/fellowship exams (MRCPsych / WACP Parts / boards).",
                "Develop therapy skills (CBT basics, family interventions) and a research or QI project."
            )),
            RoadmapStage("Consultant / Subspecialty", "1-2 years optional higher endorsement", listOf(
                "Subspecialise: child & adolescent, forensic, addiction, perinatal, liaison, neuropsychiatry.",
                "Consultant life: clinics, community teams, teaching and service leadership."
            ))
        ),
        startNow = listOf(
            "Practise the mental state examination on your next patient - anyone.",
            "Read one classic psychotherapy or memoir text to understand the patient's view.",
            "Learn to ask about mood and suicide safely and without fear.",
            "Shadow a psychiatric liaison nurse for a day."
        )
    ),
    Specialty(
        id = "dermatology",
        name = "Dermatology",
        emoji = "🧴",
        category = "Medical & longitudinal",
        tagline = "The visible organ, the visual specialty",
        trainingLength = "≈4-6 years residency/training after internship",
        overview = "Dermatologists manage disease of skin, hair, nails and mucous membranes - over 2,000 conditions from eczema, psoriasis and acne to skin cancer, autoimmune blistering disease and cutaneous signs of systemic illness. It is highly visual (pattern recognition rules), increasingly therapeutic (biologics transformed psoriasis and eczema), and includes procedural work: biopsies, excisions, cryotherapy, lasers and Mohs surgery in some systems.",
        dayInLife = listOf(
            "General clinic: rashes, acne, hair loss, pigmentation - high volume, fast pattern recognition.",
            "Skin cancer clinic: dermoscopy, biopsies, excisions, MDT with pathology.",
            "Biologic clinic: monitoring severe psoriasis/eczema on modern therapies.",
            "Procedures list: curettage, cryotherapy, minor surgery, patch testing.",
            "Inpatient consults: drug eruptions, erythroderma, blistering emergencies."
        ),
        keySkills = listOf(
            "Describing lesions precisely (morphology, distribution, configuration)",
            "Visual pattern recognition and dermoscopy",
            "Minor surgical technique and wound cosmesis",
            "Explaining chronic conditions and topical treatment technique",
            "Connecting skin signs to systemic disease"
        ),
        rewards = listOf(
            "Excellent work-life balance and mostly daytime work",
            "Visible results - patients see improvement, and so do you",
            "Mix of medicine, surgery, immunology and pathology",
            "Strong private practice potential worldwide"
        ),
        challenges = listOf(
            "Very competitive entry with few training posts in many countries",
            "High clinic volumes and chronic-disease repetition",
            "Cosmetic expectations can be unrealistic",
            "Training posts may require relocation"
        ),
        lifestyle = "One of the best lifestyle specialties: clinic-based, rare emergencies, minimal nights (some systems have none at consultant level). Competitiveness: high - portfolio, exams and research matter from early on.",
        exams = listOf(
            "Nigeria: WACP/NPMCN dermatology fellowship (often after internal medicine experience)",
            "UK: MRCP(UK) then Dermatology Specialty Certificate Exam",
            "US: USMLE → dermatology residency → ABD boards (among the most competitive matches)",
            "Dermoscopy and dermato-surgery certifications"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Learn lesion vocabulary: macule, papule, plaque, vesicle, scale, lichenification.",
                "Photograph (with consent) and describe interesting rashes; build a personal atlas.",
                "Attend derm clinic whenever your rotation allows."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Medicine rotation first - derm in most systems builds on internal medicine.",
                "Practise describing rashes to colleagues by phone: morphology + distribution.",
                "Start a portfolio: derm audits, teaching, case reports."
            )),
            RoadmapStage("Internal medicine / core experience", "2-4 years", listOf(
                "Nigeria/UK: complete core medical training and membership exams.",
                "US: preliminary/transitional year then dedicated derm residency.",
                "Publish dermatology research - selection committees notice."
            )),
            RoadmapStage("Dermatology residency / registrar training", "3-4 years", listOf(
                "General derm, skin cancer surgery, paediatric derm, phototherapy, contact dermatitis.",
                "Master dermoscopy and biopsy technique; pass fellowship/board exit exams.",
                "Develop an interest: inflammatory disease, skin cancer, immunobullous, paediatric."
            )),
            RoadmapStage("Consultant / Fellowship", "1-2 years optional fellowship", listOf(
                "Fellowships: Mohs micrographic surgery, laser, paediatric dermatology, dermatopathology.",
                "Consultant life: clinics, surgery lists, MDTs, teaching; private practice options."
            ))
        ),
        startNow = listOf(
            "Learn to describe any rash in one structured sentence.",
            "Study the ten most common presentations cold: eczema, psoriasis, acne, tinea, urticaria…",
            "Get a pocket dermoscopy/dermatology atlas and use it in clinic.",
            "Ask a dermatologist about their training route - posts are scarce, plan early."
        )
    ),
    Specialty(
        id = "family-medicine",
        name = "Family Medicine / General Practice",
        emoji = "🏡",
        category = "Medical & longitudinal",
        tagline = "Whole-person care, cradle to grave",
        trainingLength = "≈3-5 years residency after internship",
        overview = "Family physicians are the first contact and the constant thread in a patient's life: undifferentiated symptoms, chronic disease, prevention, mental health, minor procedures and coordination of everything else. You see all ages, all systems and all stages of life, often across generations of one family. It is the backbone of every functioning health system and the specialty with the clearest population impact.",
        dayInLife = listOf(
            "Consultations every 10-20 minutes: coughs, hypertension reviews, diabetes, rashes, low mood, antenatal checks.",
            "Spotting the needle in the haystack - the 'cough' that is lung cancer, the 'tiredness' that is depression.",
            "Minor procedures: joint injections, cryotherapy, suturing, contraceptive implants.",
            "Home visits for the housebound; chronic disease and prevention clinics.",
            "Paperwork, referrals, results, and coordinating with community teams."
        ),
        keySkills = listOf(
            "Managing uncertainty safely - undifferentiated illness is the norm",
            "Broad knowledge across every specialty at first-contact level",
            "Communication, continuity and shared decision-making",
            "Time management and safety-netting",
            "Prevention and population-health thinking"
        ),
        rewards = listOf(
            "Real continuity - you deliver the baby and treat the grandparent",
            "Variety: no organ system or age group is off-limits",
            "Flexible careers: part-time, portfolio roles, rural or urban, global options",
            "High demand everywhere; strong private practice potential"
        ),
        challenges = listOf(
            "Time-pressured consultations and administrative burden",
            "Less prestige in some hospital-centric cultures (unfairly)",
            "Diagnostic uncertainty without immediate tests",
            "Carrying responsibility for what you did NOT refer"
        ),
        lifestyle = "Among the most flexible careers in medicine: salaried, partner or locum models; predictable hours are achievable; out-of-hours work is usually optional or shift-based. Competitiveness: moderate, with rising interest.",
        exams = listOf(
            "Nigeria: WACP Faculty of Family Medicine or NPMCN Fellowship; MCGP distinctions",
            "UK: MRCGP (AKT + RCA/SCA)",
            "US: USMLE → family medicine residency → ABFM boards",
            " diplomas: DRCOG, DCH, diabetes, minor surgery depending on system"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Value EVERY rotation - you will use all of it at the front door.",
                "Practise concise consultations and safety-netting language.",
                "Spend time in a community clinic or primary health centre."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Broad rotations: medicine, surgery, paeds, O&G - all core to family practice.",
                "Learn chronic disease management: hypertension, diabetes, asthma.",
                "Notice how social context changes every management plan."
            )),
            RoadmapStage("Pre-residency / service years", "0-2 years (varies)", listOf(
                "Nigeria: NYSC often places you in community/rural practice - ideal exposure.",
                "UK: Foundation then apply to 3-year GP Specialty Training. US: FM residency via the Match.",
                "Collect community-health or QI projects."
            )),
            RoadmapStage("Residency / GP training", "3-5 years", listOf(
                "Hospital rotations plus long primary-care placements with supervised consulting.",
                "Pass membership/fellowship exams (MRCGP / WACP Parts / ABFM).",
                "Build skills: minor surgery, contraception, joint injections, point-of-care ultrasound."
            )),
            RoadmapStage("Attending / GP partner", "career", listOf(
                "Practice as a full-scope family physician; add interests: sports medicine, palliative care, obstetrics, rural emergency.",
                "Teach, lead clinics, or combine with public health and policy work."
            ))
        ),
        startNow = listOf(
            "Volunteer at a primary health centre or community outreach.",
            "Practise the 10-minute focused history.",
            "Learn the common chronic-disease guidelines (HTN, DM, asthma).",
            "Ask a family physician what proportion of their day is 'undifferentiated' - then watch them work."
        )
    ),
    Specialty(
        id = "paediatrics",
        name = "Paediatrics",
        emoji = "🧸",
        category = "Medical & longitudinal",
        tagline = "Small patients, big physiology",
        trainingLength = "≈4-6 years residency after internship",
        overview = "Paediatricians care for neonates, infants, children and adolescents - from resuscitating a 600 g preterm baby to managing asthma, epilepsy, diabetes, infections and safeguarding concerns. Children are not small adults: physiology, pharmacology and disease patterns change with age, and you treat the family as much as the child. Subspecialties include neonatology, paediatric intensive care, cardiology, neurology, oncology and community paediatrics.",
        dayInLife = listOf(
            "Ward round: review the sick children, adjust fluids/feeds/meds by weight.",
            "Acute admissions: bronchiolitis, gastroenteritis, asthma, febrile children, seizures.",
            "Neonatal unit rounds (if on rotation): preterm feeds, jaundice, respiratory support.",
            "Clinic: growth and development checks, chronic disease follow-up, immunisation questions.",
            "Talking with parents - explaining, reassuring, and recognising safeguarding concerns."
        ),
        keySkills = listOf(
            "Age-specific assessment: the paediatric ABCDE and assessment triangle",
            "Weight-based prescribing and fluid calculations",
            "Communicating with children AND anxious parents simultaneously",
            "Recognising the seriously ill child early (kids compensate, then crash)",
            "Developmental and safeguarding awareness"
        ),
        rewards = listOf(
            "Most children recover fully - deeply satisfying outcomes",
            "Playful, human moments no other specialty offers",
            "Huge variety: acute, chronic, community, neonatal, intensive care",
            "Advocacy role - you speak for patients who cannot speak for themselves"
        ),
        challenges = listOf(
            "Sick children are emotionally hard, and parents' distress is real",
            "Safeguarding cases carry heavy responsibility",
            "Night and weekend work during training (kids get sick at 2 am)",
            "Procedural difficulty - tiny veins, moving targets"
        ),
        lifestyle = "Training involves shifts and nights; consultant patterns vary from acute general paeds to clinic-based community or subspecialty roles. Flexible training is increasingly common. Competitiveness: moderate.",
        exams = listOf(
            "Nigeria: WACP Faculty of Paediatrics or NPMCN Fellowship (Part I & II)",
            "UK: MRCPCH (theory papers + clinical exam)",
            "US: USMLE → paediatrics residency → ABP boards",
            "Neonatal resuscitation / APLS / PALS provider courses"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Master child development milestones and weight-based drug doses.",
                "Learn to examine a crying toddler - distraction is a clinical skill.",
                "Attend paediatric A&E sessions; see bronchiolitis, asthma and dehydration managed."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Take the paediatrics rotation; practise cannulation and fluid prescribing by weight.",
                "Learn neonatal basics: feeding, jaundice thresholds, danger signs.",
                "Do an APLS/paediatric life support course if available."
            )),
            RoadmapStage("Pre-residency / service years", "0-2 years (varies)", listOf(
                "Nigeria: NYSC + medical officer experience in paeds strengthens applications.",
                "UK: Foundation then ST1-8 paediatric training pathway. US: paeds residency via the Match.",
                "Start MRCPCH/WACP Part I reading; audit something paediatric."
            )),
            RoadmapStage("Residency", "4-6 years", listOf(
                "General paeds, neonatology, PICU, community child health, subspecialty tasters.",
                "Pass Part I/II or board exams; lead resuscitations as senior trainee.",
                "Complete research/QI and develop a subspecialty interest."
            )),
            RoadmapStage("Consultant / Subspecialty fellowship", "1-3 years fellowship (optional)", listOf(
                "Neonatology, PICU, paediatric cardiology/neurology/oncology, allergy, gastro…",
                "Or general/community paediatrics with a special interest.",
                "Consultant life: acute rota or clinics, MDTs, teaching and advocacy."
            ))
        ),
        startNow = listOf(
            "Memorise paediatric vital sign ranges by age and the 4-2-1 fluid rule.",
            "Learn developmental milestones with a mnemonic and test yourself weekly.",
            "Practise explaining a diagnosis to a worried parent in plain language.",
            "Carry a paediatric drug dose reference and use it until you know the common ones."
        )
    ),
    Specialty(
        id = "community-medicine",
        name = "Community Medicine / Public Health",
        emoji = "🌍",
        category = "Medical & longitudinal",
        tagline = "The patient is the population",
        trainingLength = "≈4-6 years residency; MPH often alongside",
        overview = "Public health physicians prevent disease and prolong life at population scale: outbreak control, immunisation programmes, maternal-child health, epidemiology, health policy, screening, environmental and occupational health. In much of Africa and Asia this is a core clinical college specialty; in the UK/US it is a distinct training pathway. If you want your decisions to protect thousands rather than treat one at a time, this is it.",
        dayInLife = listOf(
            "Surveillance: analyse disease data, spot outbreaks, coordinate response teams.",
            "Programme work: immunisation campaigns, TB/HIV/malaria programmes, maternal health initiatives.",
            "Field visits: water and sanitation inspections, school health, community outreach.",
            "Policy and advisory: brief ministries, write guidance, design screening programmes.",
            "Research: surveys, epidemiological studies, health-system evaluations."
        ),
        keySkills = listOf(
            "Epidemiology and biostatistics - study design, bias, interpretation",
            "Data analysis and clear visual communication",
            "Leadership, negotiation and working with governments/NGOs",
            "Health economics and priority-setting with limited resources",
            "Writing: reports, policy briefs, papers"
        ),
        rewards = listOf(
            "Impact at scale - a vaccination policy saves more lives than any single clinic",
            "Intellectual breadth: statistics, policy, sociology, clinical medicine",
            "Generally predictable hours with fieldwork variety",
            "Global careers: WHO, ministries, NGOs, academia"
        ),
        challenges = listOf(
            "Results are slow and invisible - prevention is a hard sell",
            "Politics and bureaucracy shape what is possible",
            "Less direct patient contact (some miss it)",
            "Funding cycles and competing priorities"
        ),
        lifestyle = "Mostly office/field hours with outbreak-response surges. Excellent for combining with research, teaching or global health consultancies. Competitiveness: moderate; strong quantitative skills help.",
        exams = listOf(
            "Nigeria: WACP Faculty of Community Medicine / NPMCN Fellowship (Part I & II)",
            "UK: Faculty of Public Health MFPH → FFPH (Diploma + Final Membership)",
            "US: MPH + preventive medicine residency → ABPM boards",
            "MPH/MSc Epidemiology widely valued across all routes"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Take community medicine postings and field visits seriously.",
                "Learn basic biostatistics: sensitivity/specificity, RR/OR, p-values, bias.",
                "Join outreach or vaccination campaigns as a student."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Complete clinical rotations - credibility in public health needs clinical grounding.",
                "Notice system failures: stock-outs, referral delays, data gaps.",
                "Do a community-oriented audit or small survey."
            )),
            RoadmapStage("Service years / MPH", "1-3 years", listOf(
                "Nigeria: NYSC in community settings; consider an MPH early.",
                "Work with programme teams (TB, HIV, immunisation) or research units.",
                "UK: apply to public health specialty training after 2+ years postgraduate experience. US: MPH then preventive medicine residency."
            )),
            RoadmapStage("Residency / Specialty training", "4-6 years", listOf(
                "Rotations: health protection, health improvement, health services research, policy.",
                "Pass Part I/II or MFPH/boards; lead an outbreak investigation or programme evaluation.",
                "Publish; present at public health conferences."
            )),
            RoadmapStage("Consultant / Director", "career", listOf(
                "Roles: consultant public health physician, director of public health, epidemiologist, global health adviser.",
                "Combine with academia, ministry leadership or international agencies."
            ))
        ),
        startNow = listOf(
            "Learn to calculate and interpret sensitivity, specificity, RR and OR today.",
            "Volunteer for a vaccination or outreach campaign.",
            "Read one epidemiology classic or WHO report per month.",
            "Take a free online biostatistics course and actually finish it."
        )
    ),
    Specialty(
        id = "medical-oncology",
        name = "Medical Oncology",
        emoji = "🎗️",
        category = "Medical & longitudinal",
        tagline = "Science at the edge, care at the centre",
        trainingLength = "≈4-6 years internal medicine + 2-3 years oncology training",
        overview = "Medical oncologists treat cancer with systemic therapy - chemotherapy, targeted agents, immunotherapy and hormones - while guiding patients through diagnosis, response, survivorship and palliative care. It is the fastest-evolving field in medicine (new drugs and trials constantly), paired with some of the most meaningful doctor-patient relationships anywhere. Works hand-in-glove with surgical and radiation oncology.",
        dayInLife = listOf(
            "Clinic: new cancer diagnoses, treatment consent discussions, response reviews.",
            "Day-unit rounds: check patients on chemo/immunotherapy, manage toxicities.",
            "Tumour board MDT: stage, plan and sequence treatments with surgeons, radiologists, pathologists.",
            "Trial work: screen patients for studies, review protocols, collect data.",
            "Palliative conversations: symptom control, goals of care, family support."
        ),
        keySkills = listOf(
            "Oncological emergencies: neutropenic sepsis, cord compression, tumour lysis",
            "Reading trial data critically - survival curves, endpoints, toxicity",
            "Communicating prognosis honestly and compassionately",
            "Symptom control and palliative care principles",
            "Staging systems (TNM) and treatment pathways"
        ),
        rewards = listOf(
            "Front row to a scientific revolution - immunotherapy cures that were impossible 15 years ago",
            "Deep longitudinal relationships through the hardest time of patients' lives",
            "Research opportunities are everywhere",
            "Multidisciplinary teamwork at its best"
        ),
        challenges = listOf(
            "Emotional toll - many patients still die despite excellent care",
            "Keeping up with a firehose of new evidence",
            "Drug access and cost barriers, especially in low-resource settings",
            "Breaking bad news regularly"
        ),
        lifestyle = "Clinic and day-unit based with oncology on-call (acute oncology emergencies). Generally predictable consultant hours; research time is common. Competitiveness: moderately high.",
        exams = listOf(
            "Nigeria: WACP/NPMCN Internal Medicine then oncology subspecialty fellowship",
            "UK: MRCP(UK) + Medical Oncology Specialty Certificate Exam",
            "US: ABIM IM boards → ABIM Medical Oncology boards",
            "GCP (Good Clinical Practice) certification for trial work"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Learn cancer basics: staging, common regimens, oncological emergencies.",
                "Attend an oncology clinic or day unit; observe a consent conversation.",
                "Study palliative care communication - SPIKES is a start."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Medicine rotation: manage neutropenic fever as an emergency.",
                "Learn pain ladders and antiemetic protocols.",
                "Follow one cancer patient's whole journey if you can."
            )),
            RoadmapStage("Internal medicine training", "4-6 years", listOf(
                "Complete physician training and membership exams.",
                "Oncology taster rotations, audits, and research attachments.",
                "Present a case or poster at an oncology meeting."
            )),
            RoadmapStage("Medical oncology training", "2-3 years", listOf(
                "Systemic therapy clinics, acute oncology, trials unit, palliative care.",
                "Pass subspecialty boards/SCE; choose solid-tumour or haem focus.",
                "Develop a site specialisation: breast, GI, lung, GU, melanoma…"
            )),
            RoadmapStage("Consultant / Academic career", "career", listOf(
                "Site-specialised consultant practice with MDT leadership.",
                "Academic track: run trials, build research groups, teach."
            ))
        ),
        startNow = listOf(
            "Learn the oncological emergencies - they appear in every exam.",
            "Practise SPIKES with a friend until it feels natural.",
            "Read one landmark trial summary per month (start with immunotherapy in melanoma).",
            "Spend a day in a palliative care team - it changes how you see medicine."
        )
    )
)
