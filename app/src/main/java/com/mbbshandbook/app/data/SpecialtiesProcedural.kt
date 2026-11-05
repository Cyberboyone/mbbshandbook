package com.mbbshandbook.app.data

/** Surgical and procedural specialties. */
internal val proceduralSpecialties: List<Specialty> = listOf(
    Specialty(
        id = "general-surgery",
        name = "General Surgery",
        emoji = "🔪",
        category = "Surgical & procedural",
        tagline = "See it, fix it, with your hands",
        trainingLength = "≈5-6 years residency after internship, +1-3 years subspecialty",
        overview = "Surgeons treat disease by operating: acute abdomens, trauma, hernias, gallbladders, bowels, breast and endocrine disease, plus the whole perioperative journey. General surgery is the parent of colorectal, upper GI, hepatobiliary, breast, endocrine, vascular, paediatric and transplant surgery. If you want definitive, tangible fixes and can stay calm when anatomy gets difficult, surgery rewards you like nothing else.",
        dayInLife = listOf(
            "Early ward round: post-op checks, drains, wounds, pain and feeding plans.",
            "Theatre list: laparoscopic cholecystectomies, hernia repairs, bowel resections.",
            "Emergency take: appendicitis, obstruction, perforation, trauma - assess, resuscitate, operate.",
            "Clinic: lumps, hernias, gallstone disease, cancer follow-up; consent conversations.",
            "MDTs for cancer staging; teaching juniors suturing and acute assessment."
        ),
        keySkills = listOf(
            "Technical craft: knot-tying, suturing, tissue handling, laparoscopic skills",
            "Decisive assessment of the acute abdomen",
            "Perioperative care: fluids, analgesia, complications recognition",
            "Stamina and calm under pressure",
            "Team leadership in theatre"
        ),
        rewards = listOf(
            "Immediate, visible cures - the appendix is out, the problem is gone",
            "Craftsmanship that improves visibly with practice",
            "Adrenaline and variety of emergency surgery",
            "Clear hierarchy of skills to master - satisfying progression"
        ),
        challenges = listOf(
            "Long training with demanding hours and nights",
            "Complications are personal - you carry them",
            "Physical toll: standing for hours, radiation in some cases",
            "Competitive entry and subspecialty selection"
        ),
        lifestyle = "Training is intense with emergency takes; consultant life combines elective lists (predictable) with on-call. Subspecialties like breast/endocrine are more lifestyle-friendly than emergency/trauma. Competitiveness: high.",
        exams = listOf(
            "Nigeria: WACS (West African College of Surgeons) or NPMCN Fellowship - Primary, Part I & Part II",
            "UK: MRCS (Parts A & B) then FRCS in your subspecialty",
            "US: USMLE → general surgery residency → ABS boards",
            "ATLS provider, CCrISP, basic laparoscopic skills courses"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Live in the surgical rotation: scrub in, hold retractors, learn theatre etiquette.",
                "Practise suturing and knot-tying on a kit until your hands do it without thinking.",
                "Learn the acute abdomen assessment and hernia anatomy cold."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Take the surgery rotation; own the post-op ward jobs.",
                "Assist in theatre as often as allowed; log every case.",
                "Do ATLS and a basic surgical skills course."
            )),
            RoadmapStage("Pre-residency / service years", "0-2 years (varies)", listOf(
                "Nigeria: NYSC + surgical medical officer posts; start WACS Primary reading.",
                "UK: Foundation then Core Surgical Training (CT1-2) + MRCS. US: surgery residency via the Match (some take research years).",
                "Build a logbook and portfolio: audits, presentations, courses."
            )),
            RoadmapStage("Residency / Higher surgical training", "4-6 years", listOf(
                "Junior: emergencies, endoscopy, basic procedures; pass Part I / MRCS.",
                "Senior: major elective and emergency operating, lead the take; pass Part II / FRCS.",
                "Research or audit cycle; choose a subspecialty lane."
            )),
            RoadmapStage("Fellowship / Consultant", "1-3 years fellowship", listOf(
                "Subspecialise: colorectal, upper GI, HPB, breast, endocrine, vascular, trauma, transplant.",
                "Consultant life: elective lists, emergency on-call, MDTs, training the next generation."
            ))
        ),
        startNow = listOf(
            "Buy a suture practice kit and do 15 minutes daily.",
            "Learn theatre etiquette: where to stand, what not to touch, how to scrub.",
            "Follow one surgical patient from admission to discharge and write their journey.",
            "Ask a registrar to teach you one knot properly - then teach someone else."
        )
    ),
    Specialty(
        id = "orthopaedics",
        name = "Orthopaedic Surgery",
        emoji = "🦴",
        category = "Surgical & procedural",
        tagline = "Bones, joints and getting people moving",
        trainingLength = "≈5-6 years residency after internship, +1-2 years fellowship",
        overview = "Orthopaedic surgeons treat the musculoskeletal system: fractures and trauma, arthritis, spine disease, sports injuries, deformity and bone tumours. It is practical, mechanical and satisfying - fix the fracture, replace the joint, restore movement. Subspecialties include trauma, arthroplasty, sports/knee, shoulder, hand, spine, paediatric orthopaedics and foot & ankle.",
        dayInLife = listOf(
            "Trauma meeting: review overnight fractures, plan the day's fixation list.",
            "Theatre: hip hemiarthroplasty, ankle ORIF, intramedullary nailing, arthroscopy.",
            "Fracture clinic: cast checks, X-ray reviews, rehab plans.",
            "Elective clinic: arthritic hips/knees, sports injuries; consent for replacements.",
            "On-call: reduce and splint acute fractures and dislocations in ED."
        ),
        keySkills = listOf(
            "Reading fracture X-rays and classification systems",
            "Mechanical thinking: forces, fixation constructs, implant choice",
            "Practical hands: reduction, casting, drilling, plating",
            "Physical stamina (orthopaedics is athletic surgery)",
            "Rehabilitation mindset - the operation is half the treatment"
        ),
        rewards = listOf(
            "Tangible results - patients walk out on joints you built",
            "Clear technical skill ladder with satisfying mastery",
            "Mix of emergency trauma and planned elective work",
            "Strong demand and private practice potential"
        ),
        challenges = listOf(
            "Physically demanding operating (heavy limbs, lead aprons, power tools)",
            "Trauma on-calls are busy and unpredictable",
            "Competitive training entry",
            "Complications (infection, non-union) can mean long revision journeys"
        ),
        lifestyle = "Trauma-heavy training with busy on-calls; elective arthroplasty/sports consultants have more controlled schedules. Competitiveness: high - portfolio and exams early matter.",
        exams = listOf(
            "Nigeria: WACS/NPMCN orthopaedic fellowship (Primary, Part I & II)",
            "UK: MRCS then FRCS (Tr & Orth)",
            "US: USMLE → orthopaedic residency (5 yrs) → ABOS boards (very competitive)",
            "ATLS, fracture management and arthroplasty courses"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Learn fracture descriptions: site, pattern, displacement, angulation.",
                "Master the neurovascular exam of limbs - compartment syndrome saves limbs.",
                "Scrub into trauma lists; learn to apply a cast properly."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Surgery rotation: manage fracture admissions, reductions and traction.",
                "Log cases; assist in fixations when allowed.",
                "Do ATLS; learn splinting principles."
            )),
            RoadmapStage("Pre-residency / core training", "1-3 years", listOf(
                "Nigeria: surgical MO posts, WACS Primary preparation.",
                "UK: Core Surgical Training + MRCS, build ortho portfolio (audits, courses, prizes). US: ortho residency via the Match - research years common.",
                "Present at orthopaedic meetings; get known in the department."
            )),
            RoadmapStage("Residency / Higher training", "4-6 years", listOf(
                "Trauma and elective rotations across subspecialties.",
                "Pass Part I/II or FRCS (Tr & Orth) / boards.",
                "Operate progressively: from fixations to arthroplasty and arthroscopy."
            )),
            RoadmapStage("Fellowship / Consultant", "1-2 years fellowship", listOf(
                "Subspecialise: arthroplasty, sports, hand, spine, paediatrics, foot & ankle, trauma.",
                "Consultant practice: trauma on-call plus elective subspecialty lists."
            ))
        ),
        startNow = listOf(
            "Learn to describe any fracture X-ray in one structured sentence.",
            "Practise the limb neurovascular exam until it is automatic.",
            "Learn compartment syndrome signs - the 6 Ps - and the urgency of fasciotomy.",
            "Attend a fracture clinic and follow patients from cast to rehab."
        )
    ),
    Specialty(
        id = "obgyn",
        name = "Obstetrics & Gynaecology",
        emoji = "🤰",
        category = "Surgical & procedural",
        tagline = "Two patients, surgery and medicine, life and death",
        trainingLength = "≈5-6 years residency after internship, +1-2 years subspecialty",
        overview = "O&G combines the joy of birth with serious surgery and acute medicine: antenatal care, labour ward emergencies, caesarean sections, hysterectomies, fertility, menstrual disorders, gynae-oncology and urogynaecology. It is the only specialty where you routinely care for two patients at once, and where a normal day can turn into a life-saving emergency in minutes. Subspecialties: maternal-fetal medicine, reproductive medicine, gynae-oncology, urogynaecology.",
        dayInLife = listOf(
            "Labour ward round: review progress, CTGs, inductions, plan deliveries.",
            "Theatre: caesarean sections (elective and emergency), ectopic surgery, hysterectomies.",
            "Antenatal clinic: risk assessment, scans, pre-eclampsia surveillance.",
            "Gynae clinic: bleeding disorders, fibroids, prolapse, contraception, fertility workups.",
            "Emergencies: postpartum haemorrhage, shoulder dystocia, eclampsia - drill-trained responses."
        ),
        keySkills = listOf(
            "Labour ward triage and CTG interpretation",
            "Obstetric emergencies: PPH, eclampsia, shoulder dystocia drills",
            "Surgical skill: caesarean, laparoscopy, hysteroscopy",
            "Ultrasound scanning (increasingly core)",
            "Sensitive communication: loss, fertility, intimate examinations"
        ),
        rewards = listOf(
            "Births - the happiest moments in medicine, most days",
            "True mix of medicine, surgery, imaging and prevention",
            "Drill-based emergencies where teamwork visibly saves lives",
            "Lifelong patient relationships across generations"
        ),
        challenges = listOf(
            "Labour ward never sleeps - nights and weekends are intense",
            "High medicolegal stakes; outcomes scrutinised closely",
            "Bereavement care is emotionally heavy",
            "Emergency interruptions to elective work"
        ),
        lifestyle = "Training and consultant life include labour ward on-calls; some subspecialties (fertility, urogynae, outpatient gynae) are more predictable. Competitiveness: moderate to high.",
        exams = listOf(
            "Nigeria: WACP/NPMCN Faculty of O&G Fellowship (Part I & II)",
            "UK: MRCOG (Parts 1, 2, 3)",
            "US: USMLE → OB/GYN residency → ABOG boards",
            "Also: ALSO/EMMOE obstetric emergency courses, ultrasound certifications"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Own the O&G posting: deliveries, antenatal clinics, labour ward nights.",
                "Learn the mechanisms of labour and PPH management algorithm.",
                "Practise respectful intimate examinations and chaperone use."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Take the O&G rotation; assist caesareans, manage early labour assessments.",
                "Learn magnesium sulphate for eclampsia and active management of third stage.",
                "Log deliveries and procedures."
            )),
            RoadmapStage("Pre-residency / service years", "0-2 years (varies)", listOf(
                "Nigeria: NYSC + O&G medical officer posts; start Part I reading.",
                "UK: Foundation then ST1-7 O&G training; MRCOG along the way. US: OB/GYN residency via the Match.",
                "Do an obstetric emergencies course; audit something on labour ward."
            )),
            RoadmapStage("Residency", "4-6 years", listOf(
                "Junior: labour ward, gynae emergencies, early operating; pass Part I.",
                "Senior: major surgery, high-risk obstetrics, lead emergencies; pass Part II.",
                "Develop subspecialty interest and complete research."
            )),
            RoadmapStage("Subspecialty / Consultant", "1-2 years (optional ATSM/fellowship)", listOf(
                "Maternal-fetal medicine, reproductive medicine, gynae-oncology, urogynaecology.",
                "Consultant life: labour ward leadership, elective surgery, clinics, teaching."
            ))
        ),
        startNow = listOf(
            "Learn the PPH algorithm (massage, uterotonics, tranexamic acid, escalation) cold.",
            "Practise CTG interpretation with real traces.",
            "Learn magnesium sulphate dosing for eclampsia - it saves lives.",
            "Attend a delivery (with consent) and debrief what you saw with a midwife."
        )
    ),
    Specialty(
        id = "ophthalmology",
        name = "Ophthalmology",
        emoji = "👁️",
        category = "Surgical & procedural",
        tagline = "Microsurgery that gives sight back",
        trainingLength = "≈4-6 years residency after internship",
        overview = "Ophthalmologists are physicians AND microsurgeons of the eye: cataract, glaucoma, retinal disease (including diabetic retinopathy), cornea, paediatric eye disease and neuro-ophthalmology. Cataract surgery is one of the most cost-effective operations in all of medicine - minutes of surgery restore sight for years. The field is technology-rich (OCT, lasers, intravitreal injections) and hugely impactful in preventing blindness, especially in underserved regions.",
        dayInLife = listOf(
            "Clinic: slit-lamp examinations, refraction, glaucoma checks, diabetic retinopathy screening.",
            "Theatre: cataract lists (phacoemulsification), corneal or retinal procedures.",
            "Injection clinic: anti-VEGF for macular degeneration and diabetic macular oedema.",
            "Laser sessions: YAG capsulotomy, retinal laser for diabetic disease.",
            "Emergency consults: acute glaucoma, retinal detachment, trauma, sudden vision loss."
        ),
        keySkills = listOf(
            "Slit-lamp examination and fundoscopy mastery",
            "Fine microsurgical hand skills and patience",
            "Interpreting OCT, visual fields and retinal imaging",
            "Systemic disease knowledge - the eye shows diabetes, hypertension, autoimmune disease",
            "Efficient, high-volume clinic workflow"
        ),
        rewards = listOf(
            "Sight restored = life restored; gratitude is immediate and profound",
            "Beautiful microsurgery with visible mastery curve",
            "Excellent lifestyle at consultant level in most systems",
            "Massive global-health impact (cataract blindness campaigns)"
        ),
        challenges = listOf(
            "Very competitive entry in many countries",
            "Steep early learning curve - the microscope world is unforgiving",
            "High patient volumes and expectations",
            "Equipment-dependent practice; resource limits in some settings"
        ),
        lifestyle = "Clinic and elective theatre based; emergencies exist but are lighter than most surgical fields. Strong private practice potential. Competitiveness: high.",
        exams = listOf(
            "Nigeria: WACP/NPMCN ophthalmology fellowship",
            "UK: FRCOphth (Parts 1 & 2)",
            "US: USMLE → ophthalmology residency → ABO boards (highly competitive)",
            "ICO exams widely recognised internationally; microsurgical skills courses"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Learn direct ophthalmoscopy properly - it is a dying skill you will value.",
                "Master the red-eye differential and sudden vision loss triage.",
                "Attend eye clinic; watch a cataract list."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Seek ophthalmology exposure; learn visual acuity and pupil assessment.",
                "Recognise emergencies: acute angle-closure, retinal detachment, chemical burns.",
                "Start building a portfolio if eye surgery interests you."
            )),
            RoadmapStage("Pre-residency / core experience", "1-3 years", listOf(
                "Nigeria: medical officer posts, fellowship Part I preparation.",
                "UK: core training/run-through ST1 entry with portfolio; US: dedicated ophtho residency via the Match.",
                "Research or audit in eye care strengthens applications."
            )),
            RoadmapStage("Residency", "4-6 years", listOf(
                "Clinics, subspecialty rotations, progressive microsurgical training.",
                "Master phacoemulsification; pass fellowship/board exams.",
                "Choose interest: retina, glaucoma, cornea, paediatrics, oculoplastics."
            )),
            RoadmapStage("Fellowship / Consultant", "1-2 years fellowship", listOf(
                "Subspecialty fellowships: vitreoretinal, glaucoma, cornea, medical retina.",
                "Consultant life: high-volume cataract lists, subspecialty clinics, outreach campaigns."
            ))
        ),
        startNow = listOf(
            "Practise fundoscopy on every patient until you can find the disc in seconds.",
            "Learn the red eye algorithm: pain + photophobia + vision loss = urgent.",
            "Learn the causes of sudden painless vs painful vision loss.",
            "Volunteer with a community eye-screening outreach."
        )
    ),
    Specialty(
        id = "ent",
        name = "ENT / Otorhinolaryngology",
        emoji = "👂",
        category = "Surgical & procedural",
        tagline = "Head, neck and the senses that connect us",
        trainingLength = "≈5-6 years residency after internship",
        overview = "ENT surgeons treat the ear, nose, throat, head and neck: hearing loss, sinus disease, tonsils, voice disorders, vertigo, head & neck cancers, facial plastics and airway emergencies. It is a compact anatomy with elegant surgery - microsurgery of the ear, endoscopic sinus and skull-base work, thyroid and laryngeal cancer surgery. All ages, clinic and theatre mixed, and airway emergencies that keep you sharp.",
        dayInLife = listOf(
            "Clinic: otoscopy, nasal endoscopy, laryngoscopy; hearing tests and vertigo assessment.",
            "Theatre: tonsillectomies, septoplasty/FESS, grommets, thyroidectomy, neck dissections.",
            "Paediatric list: glue ear, adenoids, airway assessment.",
            "Head & neck cancer MDT and reconstruction planning.",
            "Emergencies: airway obstruction, epistaxis, post-tonsillectomy bleeds, sudden hearing loss."
        ),
        keySkills = listOf(
            "Endoscopic and microscopic examination technique",
            "Fine surgical skills in small, critical anatomy (facial nerve!)",
            "Audiology and vestibular assessment basics",
            "Airway emergency management",
            "Head & neck cancer staging and reconstruction principles"
        ),
        rewards = listOf(
            "Hearing, voice and breathing restored - senses are identity",
            "Elegant, varied surgery across all ages",
            "Generally good lifestyle for a surgical specialty",
            "Clear subspecialty paths: otology, rhinology, laryngology, head & neck, plastics"
        ),
        challenges = listOf(
            "Airway emergencies are high-stakes and fast",
            "Competitive entry with limited posts",
            "Cancer work carries emotional weight",
            "Some procedures (tonsils) are high-volume and repetitive"
        ),
        lifestyle = "Mix of elective lists and emergency on-call; generally more controllable than general surgery trauma. Competitiveness: high in most systems.",
        exams = listOf(
            "Nigeria: WACS/NPMCN ENT fellowship (Primary, Part I & II)",
            "UK: MRCS then FRCS (ORL-HNS)",
            "US: USMLE → otolaryngology residency (5 yrs) → board certification",
            "Temporal bone and endoscopic sinus dissection courses"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Learn otoscopy and the cranial nerve exam of the head and neck.",
                "Master epistaxis first aid and stridor recognition.",
                "Attend ENT clinic; watch a nasal endoscopy."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Surgery rotation; seek ENT lists and airway emergencies.",
                "Learn tracheostomy basics and post-op care.",
                "Log cases and assist where possible."
            )),
            RoadmapStage("Pre-residency / core training", "1-3 years", listOf(
                "Nigeria: surgical MO posts + WACS Primary; UK: CST + MRCS with ENT portfolio; US: ENT residency via the Match.",
                "Anatomy prizes, audits and courses build the application.",
                "Do a head & neck anatomy revision course."
            )),
            RoadmapStage("Residency", "4-6 years", listOf(
                "Rotations across otology, rhinology, laryngology, paediatrics, head & neck.",
                "Progressive operative training; pass Part II / FRCS / boards.",
                "Choose subspecialty direction."
            )),
            RoadmapStage("Fellowship / Consultant", "1-2 years fellowship", listOf(
                "Head & neck oncology/reconstruction, otology/neurotology, rhinology/skull base, facial plastics, paediatric ENT.",
                "Consultant practice: clinics, lists, MDTs, on-call."
            ))
        ),
        startNow = listOf(
            "Learn to use an otoscope properly - most students never do.",
            "Learn the difference between conductive and sensorineural loss (Rinne/Weber).",
            "Know the airway emergency ladder: recognise stridor, call early.",
            "Revise the triangles of the neck - they come back in every ENT exam."
        )
    ),
    Specialty(
        id = "anaesthesiology",
        name = "Anaesthesiology",
        emoji = "💤",
        category = "Surgical & procedural",
        tagline = "The guardian of the unconscious patient",
        trainingLength = "≈4-6 years residency after internship",
        overview = "Anaesthesiologists keep patients safe through surgery: induce and maintain anaesthesia, manage airways, control physiology in real time, and lead resuscitation. Beyond theatre: intensive care, chronic pain clinics, regional anaesthesia, pre-assessment, obstetric anaesthesia and retrieval/transport medicine. It is applied physiology at its purest - you see the effect of every drug within seconds - and the specialty that owns the airway when everything goes wrong.",
        dayInLife = listOf(
            "Pre-op assessment: airway evaluation, risk stratification, optimisation plans.",
            "Theatre: induce, intubate, maintain, emerge - monitoring physiology beat by beat.",
            "Regional blocks: spinals, epidurals, ultrasound-guided nerve blocks.",
            "Obstetric anaesthesia: labour epidurals, category-1 caesarean spinals.",
            "ICU rounds or pain clinic on allocated sessions; cardiac arrest team leadership."
        ),
        keySkills = listOf(
            "Airway mastery: bag-mask, intubation, difficult airway drills",
            "Applied pharmacology and physiology in real time",
            "Calm crisis management (can't intubate, can't oxygenate; anaphylaxis; malignant hyperthermia)",
            "Procedural precision: lines, blocks, neuraxial technique",
            "Vigilance - hours of monitoring punctuated by seconds of crisis"
        ),
        rewards = listOf(
            "Immediate pharmacology feedback - the most 'alive' physiology in medicine",
            "Every list is different; procedures all day",
            "Central role in resuscitation and ICU",
            "Strong demand, flexible careers, good remuneration in most systems"
        ),
        challenges = listOf(
            "High responsibility with little glory - patients remember the surgeon",
            "Vigilance fatigue; early starts and emergency calls",
            "Crisis moments are genuinely high-stakes",
            "Medicolegal exposure around airway and awareness"
        ),
        lifestyle = "Shift-friendly specialty with clear handovers; on-calls include obstetrics and emergencies. ICU dual training is common. Competitiveness: moderate to high, rising.",
        exams = listOf(
            "Nigeria: WACP/NPMCN Faculty of Anaesthesiology Fellowship (Part I & II)",
            "UK: FRCA (Primary & Final)",
            "US: USMLE → anaesthesiology residency → ABA boards",
            "APLS/ALS, difficult airway and ultrasound-guided regional courses"
        ),
        roadmap = listOf(
            RoadmapStage("MBBS", "5-6 years", listOf(
                "Learn airway assessment (LEMON), basic bag-mask technique in skills lab.",
                "Pharmacology of induction agents, relaxants and local anaesthetics.",
                "Attach to theatre anaesthetists; watch an induction and emergence."
            )),
            RoadmapStage("Internship / Housemanship", "1 year", listOf(
                "Medicine/surgery rotations: practise IV access, oxygen therapy, resuscitation.",
                "Do ALS provider course; join the arrest team as observer where allowed.",
                "Spend elective time in theatre and ICU."
            )),
            RoadmapStage("Pre-residency / service years", "0-2 years (varies)", listOf(
                "Nigeria: NYSC + MO posts; start Part I physiology/pharmacology reading.",
                "UK: Foundation then ACCS/Core Anaesthetics (CT1-3) + Primary FRCA. US: anaesthesia residency via the Match.",
                "Log airway management experience; audit perioperative care."
            )),
            RoadmapStage("Residency", "4-6 years", listOf(
                "General, obstetric, paediatric, cardiac, neuro, regional and ICU modules.",
                "Pass Final FRCA / Part II / boards; become the airway person on call.",
                "Choose emphasis: ICU, pain, cardiac, paediatric, obstetric, regional."
            )),
            RoadmapStage("Fellowship / Consultant", "1-2 years fellowship", listOf(
                "ICM dual training, chronic pain, cardiac, paediatric or retrieval fellowships.",
                "Consultant life: lists, ICU weeks, pain clinics, resuscitation leadership, teaching."
            ))
        ),
        startNow = listOf(
            "Learn the difficult airway algorithm conceptually - know the escalation ladder.",
            "Master bag-valve-mask technique in the skills lab with a partner.",
            "Learn local anaesthetic maximum doses and toxicity signs.",
            "Shadow an anaesthetist for a full list - watch the monitoring, not just the tube."
        )
    )
)
