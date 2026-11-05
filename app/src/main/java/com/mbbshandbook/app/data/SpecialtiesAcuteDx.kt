package com.mbbshandbook.app.data

/** Acute-care and diagnostic specialties. */
internal val acuteAndDiagnosticSpecialties: List<Specialty> = listOf(
    Specialty(
        id = "emergency-medicine",
        name = "Emergency Medicine",
        emoji = "🚑",
        category = "Acute & diagnostic",
        tagline = "Anyone, anything, anytime - undifferentiated and urgent",
        trainingLength = "≈4-6 years residency/training after internship",
        overview = "Emergency physicians assess and stabilise any undifferentiated acute problem: trauma, chest pain, sepsis, stroke, poisoning, psychiatric crisis, paediatric emergencies. The skill is rapid pattern recognition, resuscitation and disposition - sick/not sick, admit/discharge, which specialty - across the entire breadth of medicine in minutes. Shift-based, team-driven, and the front door of the hospital.",
        dayInLife = listOf(
            "Shift handover, then the floor: majors, minors, resus - whatever walks or is wheeled in.",
            "Resus: lead or assist ABCDE for cardiac arrest, trauma, sepsis, overdose.",
            "Procedures: suturing, reductions, chest drains, ultrasound (POCUS), intubation support.",
            "Rapid decisions with incomplete data: rule out killers, safety-net the rest.",
            "Refer, admit, discharge - and teach the juniors rotating through."
        ),
        keySkills = listOf(
            "ABCDE resuscitation and early escalation",
            "Rapid differential generation for undifferentiated presentations",
            "Procedural breadth: wounds, fractures, drains, POCUS",
            "Decision-making under uncertainty and time pressure",
            "Team leadership and calm communication in chaos"
        ),
        rewards = listOf(
            "Variety like no other - every specialty walks through your door",
            "Shift work: when you are off, you are truly off",
            "Immediate impact and visible teamwork",
            "Strong global demand; prehospital and retrieval options"
        ),
        challenges = listOf(
            "Nights, weekends and circadian disruption",
            "Crowding, boarding and system pressure",
            "Violence/abuse exposure and emotional load (deaths, trauma, abuse cases)",
            "Burnout risk - pacing and recovery matter"
        ),
        lifestyle = "Fully shift-based with defined hours - excellent for those who hate clinic continuity but love intensity. Training includes paeds, anaesthetics and ICU time. Competitiveness: moderate and rising.",
        exams = listOf(
            "Nigeria: WACP/NPMCN Faculty of Emergency Medicine Fellowship",
            "UK: MRCEM (Primary, Intermediate) then FRCEM",
            "US: USMLE → EM residency → ABEM boards",
            "ATLS, APLS, ALS provider; POCUS certifications"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Live in the ED during your rotation; see everything once.",
                "Master ABCDE, suturing and the primary survey.",
                "Do BLS/ALS provider courses early."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Seek ED/acute rotations; log resuscitations you assist in.",
                "Learn triage thinking: sick vs not sick in 30 seconds.",
                "Practise procedures at every opportunity."
            )),
            RoadmapStage("Pre-residency / service years", "0-2 years (varies)", listOf(
                "Nigeria: NYSC + ED medical officer posts; start fellowship primary reading.",
                "UK: Foundation then ACCS-EM (CT1-3) + MRCEM. US: EM residency via the Match.",
                "ATLS/APLS instructor potential; audits in acute care."
            )),
            RoadmapStage("Residency / Higher training", "4-6 years", listOf(
                "ED, ICU, anaesthetics, paeds EM, trauma, prehospital tasters.",
                "Pass intermediate/final exams; lead resus as senior trainee.",
                "Develop interests: POCUS, toxicology, paeds EM, retrieval, sports."
            )),
            RoadmapStage("Consultant / Fellowship", "1-2 years optional fellowship", listOf(
                "Fellowships: prehospital/retrieval, toxicology, POCUS, paediatric EM, sports medicine.",
                "Consultant life: shop-floor shifts, resus leadership, education, system design."
            ))
        ),
        startNow = listOf(
            "Learn the primary survey until you can run it half-asleep.",
            "Practise suturing on a kit; do a wound closure course.",
            "Learn POCUS basics: FAST scan, lung sliding, IVC.",
            "Debrief every resus you witness - what went well, what would you do?"
        )
    ),
    Specialty(
        id = "radiology",
        name = "Radiology",
        emoji = "🩻",
        category = "Acute & diagnostic",
        tagline = "The doctor's doctor - every diagnosis passes through you",
        trainingLength = "≈5 years residency/training after internship",
        overview = "Radiologists interpret imaging to diagnose and guide treatment: X-ray, CT, MRI, ultrasound, nuclear medicine - and increasingly perform image-guided procedures (biopsies, drainages, embolisation, tumour ablation in interventional radiology). Every specialty depends on you; you see the most interesting pathology in the hospital without owning the ward. AI is changing the field, but integration, procedures and clinical judgement keep radiologists central.",
        dayInLife = listOf(
            "Reporting lists: CT bodies, MRI brains, plain films - hundreds of studies, each a puzzle.",
            "MDTs: present imaging for cancer staging, surgical planning, stroke calls.",
            "Ultrasound lists: acute abdomen, obstetric, Doppler, guided procedures.",
            "Interventional sessions (IR): biopsies, drainages, embolisation, line placements.",
            "Protocol decisions, radiation safety, teaching clinicians what to order and why."
        ),
        keySkills = listOf(
            "Systematic image interpretation and normal-variant knowledge",
            "Cross-sectional anatomy mastery",
            "Choosing the right test (and knowing when imaging adds nothing)",
            "Clear, actionable reporting under volume pressure",
            "Procedural skill for IR: needles, wires, catheters under imaging"
        ),
        rewards = listOf(
            "See ALL of medicine's pathology - endless intellectual variety",
            "Central role in nearly every diagnosis",
            "Flexible work patterns; remote reporting increasingly possible",
            "Interventional radiology offers a procedural career without ward ownership"
        ),
        challenges = listOf(
            "High reporting volumes and turnaround pressure",
            "Errors are visible and consequential; vigilance fatigue",
            "Less patient contact in diagnostic radiology",
            "AI disruption anxiety (though it is more tool than replacement)"
        ),
        lifestyle = "Mostly daytime reporting with on-call for acute imaging/IR; increasing remote/hybrid options. Interventional radiology has emergency calls (bleeds, stroke thrombectomy). Competitiveness: high and rising.",
        exams = listOf(
            "Nigeria: WACS/NPMCN Faculty of Radiology Fellowship (Part I & II)",
            "UK: FRCR (Parts 1, 2A, 2B)",
            "US: USMLE → radiology residency → ABR boards",
            "Physics and anatomy components are heavy early - plan for them"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Learn cross-sectional anatomy alongside your dissection course.",
                "Practise systematic chest X-ray and trauma CT review.",
                "Sit with radiologists during reporting sessions."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Clinical year first - imaging decisions need clinical context.",
                "Learn what each modality answers: CT vs MRI vs US vs nuclear.",
                "Audit imaging requests; attend MDTs."
            )),
            RoadmapStage("Pre-residency / service years", "0-2 years (varies)", listOf(
                "Nigeria: MO posts + Part I (physics/anatomy) preparation.",
                "UK: Foundation then ST1 radiology run-through; FRCR during training. US: radiology residency via the Match.",
                "Teach anatomy; build a portfolio of imaging audits."
            )),
            RoadmapStage("Residency", "4-5 years", listOf(
                "Rotations: plain film, CT, MRI, US, nuclear, breast, paediatrics, IR tasters.",
                "Pass fellowship/board exams; report independently as senior.",
                "Choose diagnostic subspecialty or interventional radiology."
            )),
            RoadmapStage("Fellowship / Consultant", "1-2 years fellowship", listOf(
                "Neuroradiology, MSK, body, breast, paediatric, nuclear medicine, IR.",
                "Consultant life: reporting sessions, MDTs, procedures, teaching; hybrid/remote options."
            ))
        ),
        startNow = listOf(
            "Learn a systematic CXR approach (ABCDE) and use it on every film.",
            "Scroll a CT chest in your head: lungs, mediastinum, bones, upper abdomen.",
            "Attend one MDT per week during clinical years - watch how imaging drives decisions.",
            "Revise anatomy with cross-sectional images, not just diagrams."
        )
    ),
    Specialty(
        id = "pathology",
        name = "Pathology",
        emoji = "🔬",
        category = "Acute & diagnostic",
        tagline = "The final answer behind every diagnosis",
        trainingLength = "≈5 years residency/training after internship",
        overview = "Pathologists diagnose disease at tissue, cell and laboratory level: histopathology (cancer diagnosis, grading, margins), haematology (leukaemias, clotting), clinical chemistry, microbiology, immunology and transfusion medicine. The surgeon operates because the pathologist said cancer; the haematologist treats because the lab said leukaemia. It is diagnostic medicine with regular hours, deep science, and a front-row seat to every specialty's hardest cases.",
        dayInLife = listOf(
            "Microscopy sessions: biopsies and resections - is it cancer, what type, what stage?",
            "Frozen sections during surgery: rapid answers while the patient is open.",
            "Haematology: blood films, bone marrow reports, coagulation consults.",
            "Lab management: quality control, validation, transfusion decisions, microbiology plates.",
            "MDTs: present pathology findings that determine treatment for every cancer patient."
        ),
        keySkills = listOf(
            "Pattern recognition under the microscope",
            "Integration: clinical story + morphology + immunostains + molecular results",
            "Precision and honesty - your report is the diagnosis",
            "Laboratory science and quality systems",
            "Clear communication of uncertainty and urgency"
        ),
        rewards = listOf(
            "The definitive diagnostician - no one treats without your answer",
            "Regular hours and excellent work-life balance in most systems",
            "Deep science: molecular pathology and genomics are transforming the field",
            "Every specialty's interesting cases land on your desk"
        ),
        challenges = listOf(
            "Little direct patient contact (some miss the bedside)",
            "High responsibility with delayed feedback on outcomes",
            "Service pressures: turnaround times, staffing shortages",
            "Repetitive screening workload in some subspecialties"
        ),
        lifestyle = "Among the most lifestyle-friendly specialties: mostly daytime, minimal emergencies (frozen sections, transfusion on-call). Digital pathology enables remote reporting. Competitiveness: moderate - often undersubscribed relative to need.",
        exams = listOf(
            "Nigeria: WACP/NPMCN Faculty of Pathology Fellowship (Part I & II) by subspecialty",
            "UK: FRCPath (Part 1 & Part 2) in histopathology/haematology/microbiology etc.",
            "US: USMLE → pathology residency → ABP boards",
            "Subspecialty diplomas and molecular pathology certifications"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Fall in love with the pathology course - it is the bridge subject.",
                "Spend time in the histopathology lab; look down the microscope.",
                "Learn what happens to a biopsy after it leaves the patient."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Clinical rotations give you the context every pathologist needs.",
                "Elective time in the lab; attend MDTs from the pathology side.",
                "Learn transfusion basics and lab medicine essentials."
            )),
            RoadmapStage("Pre-residency / service years", "0-2 years (varies)", listOf(
                "Nigeria: MO posts, then choose faculty (histopath, haem, micro, chem).",
                "UK: Foundation then run-through ST1 histopathology etc.; US: pathology residency via the Match.",
                "Demonstrate interest: lab audits, case reports, teaching."
            )),
            RoadmapStage("Residency", "4-5 years", listOf(
                "Systematic training: surgical pathology, cytopathology, autopsy, subspecialty modules.",
                "Pass Part 1 then Part 2 fellowship/board exams.",
                "Choose subspecialty: GI, derm, gynae, haemato, renal, neuropathology, molecular."
            )),
            RoadmapStage("Fellowship / Consultant", "1-2 years fellowship", listOf(
                "Subspecialty fellowships; molecular/genomic pathology training.",
                "Consultant life: reporting, MDTs, frozen sections, lab leadership, research and teaching."
            ))
        ),
        startNow = listOf(
            "Book a session in the histopathology lab and look at real slides.",
            "Follow one biopsy's journey from patient to report to MDT.",
            "Learn the basics of immunohistochemistry - it is the grammar of modern pathology.",
            "Ask a pathologist which subspecialty they chose and why."
        )
    )
)
