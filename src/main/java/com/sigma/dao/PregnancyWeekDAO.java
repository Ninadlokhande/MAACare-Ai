package com.sigma.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteBatch;
import com.sigma.config.FirebaseConfig;
import com.sigma.model.PregnancyWeekModel;

import java.util.ArrayList;
import java.util.List;

public class PregnancyWeekDAO {

    private static final String COLLECTION_NAME = "pregnancyWeeks";

    private final Firestore firestore;

    public PregnancyWeekDAO() {
        firestore = FirebaseConfig.getFirestore();
    }

    // =========================================================
    // GET ALL WEEKS
    // =========================================================

    public List<PregnancyWeekModel> getAllWeeks() {

        try {

            ApiFuture<QuerySnapshot> future =
                    firestore.collection(COLLECTION_NAME)
                            .orderBy("week")
                            .get();

            QuerySnapshot snapshot = future.get();

            List<PregnancyWeekModel> weeks = new ArrayList<>();

            for (DocumentSnapshot document : snapshot.getDocuments()) {

                PregnancyWeekModel week =
                        document.toObject(PregnancyWeekModel.class);

                if (week != null) {
                    weeks.add(week);
                }
            }

            return weeks;

        } catch (Exception e) {

            System.out.println(
                    "Error fetching pregnancy weeks from Firebase"
            );

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // =========================================================
    // GET WEEK BY NUMBER
    // =========================================================

    public PregnancyWeekModel getWeekByNumber(int week) {

        try {

            String documentId = "week_" + week;

            DocumentSnapshot document =
                    firestore.collection(COLLECTION_NAME)
                            .document(documentId)
                            .get()
                            .get();

            if (!document.exists()) {
                return null;
            }

            return document.toObject(PregnancyWeekModel.class);

        } catch (Exception e) {

            System.out.println(
                    "Error fetching week " + week
                            + " from Firebase"
            );

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // SAVE WEEK
    // =========================================================

    public boolean saveWeek(PregnancyWeekModel week) {

        if (week == null) {
            return false;
        }

        try {

            String documentId =
                    "week_" + week.getWeek();

            firestore.collection(COLLECTION_NAME)
                    .document(documentId)
                    .set(week)
                    .get();

            System.out.println(
                    "Week " + week.getWeek()
                            + " saved successfully."
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error saving week to Firebase"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // UPDATE WEEK
    // =========================================================

    public boolean updateWeek(PregnancyWeekModel updatedWeek) {

        if (updatedWeek == null) {
            return false;
        }

        try {

            String documentId =
                    "week_" + updatedWeek.getWeek();

            DocumentSnapshot document =
                    firestore.collection(COLLECTION_NAME)
                            .document(documentId)
                            .get()
                            .get();

            if (!document.exists()) {
                return false;
            }

            firestore.collection(COLLECTION_NAME)
                    .document(documentId)
                    .set(updatedWeek)
                    .get();

            System.out.println(
                    "Week " + updatedWeek.getWeek()
                            + " updated successfully."
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error updating week in Firebase"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // DELETE WEEK
    // =========================================================

    public boolean deleteWeek(int week) {

        try {

            String documentId = "week_" + week;

            DocumentSnapshot document =
                    firestore.collection(COLLECTION_NAME)
                            .document(documentId)
                            .get()
                            .get();

            if (!document.exists()) {
                return false;
            }

            firestore.collection(COLLECTION_NAME)
                    .document(documentId)
                    .delete()
                    .get();

            System.out.println(
                    "Week " + week
                            + " deleted successfully."
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error deleting week from Firebase"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // CHECK IF FIRESTORE HAS DATA
    // =========================================================

    public boolean hasWeekData() {

        try {

            QuerySnapshot snapshot =
                    firestore.collection(COLLECTION_NAME)
                            .limit(1)
                            .get()
                            .get();

            return !snapshot.isEmpty();

        } catch (Exception e) {

            System.out.println(
                    "Error checking pregnancyWeeks collection"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // SAVE ALL DEFAULT WEEKS
    // =========================================================
    //
    // IMPORTANT:
    // Call this method ONCE to upload your existing
    // 40-week data to Firestore.
    //
    // =========================================================

    public boolean seedDefaultWeeks() {

        try {

            if (hasWeekData()) {

                System.out.println(
                        "Pregnancy week data already exists in Firebase."
                );

                return true;
            }

            List<PregnancyWeekModel> defaultWeeks =
                    createDefaultWeeks();

            WriteBatch batch =
                    firestore.batch();

            for (PregnancyWeekModel week : defaultWeeks) {

                String documentId =
                        "week_" + week.getWeek();

                batch.set(
                        firestore.collection(COLLECTION_NAME)
                                .document(documentId),
                        week
                );
            }

            batch.commit().get();

            System.out.println(
                    "All 40 pregnancy weeks uploaded successfully!"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error seeding pregnancy weeks."
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // DEFAULT DATA
    // =========================================================

    private List<PregnancyWeekModel> createDefaultWeeks() {

        List<PregnancyWeekModel> weeks =
                new ArrayList<>();

        weeks.add(new PregnancyWeekModel(
                1,
                "Pregnancy is just beginning and conception typically occurs around this time.",
                "You may not notice pregnancy symptoms yet.",
                "Start focusing on healthy habits and prenatal care.",
                "The fertilized egg begins its early journey toward implantation.",
                "Microscopic — too small to measure meaningfully.",
                "Early cell division and preparation for implantation begin."
        ));

        weeks.add(new PregnancyWeekModel(
                2,
                "Ovulation and fertilization may occur during this period.",
                "Most people still do not feel pregnancy-related changes.",
                "Track your cycle and begin pregnancy planning if appropriate.",
                "The fertilized egg continues dividing as it moves toward the uterus.",
                "Microscopic.",
                "Cell division continues and the early embryo prepares for implantation."
        ));

        weeks.add(new PregnancyWeekModel(
                3,
                "Implantation may occur and early pregnancy hormones begin rising.",
                "Some people notice mild tiredness or light changes.",
                "Begin prenatal care and discuss folic acid with your healthcare provider.",
                "The embryo starts developing the foundations of the nervous system and other structures.",
                "About a tiny seed.",
                "Implantation and early embryonic development take place."
        ));

        weeks.add(new PregnancyWeekModel(
                4,
                "Pregnancy hormones increase and a missed period may occur.",
                "Fatigue, breast tenderness or mild nausea may begin.",
                "Take prescribed prenatal supplements and arrange prenatal care.",
                "The embryo's basic layers begin forming and early development accelerates.",
                "About a poppy seed.",
                "Early structures that will become the baby's organs begin forming."
        ));

        weeks.add(new PregnancyWeekModel(
                5,
                "Hormonal changes may make you feel more tired or sensitive.",
                "Nausea, fatigue and breast tenderness can become more noticeable.",
                "Eat regular nutritious meals and stay hydrated.",
                "The neural tube and early heart structures continue developing.",
                "About a sesame seed.",
                "Early heart and nervous-system development progresses."
        ));

        weeks.add(new PregnancyWeekModel(
                6,
                "Your body is adjusting rapidly to increasing pregnancy hormones.",
                "Morning sickness, tiredness and food aversions may occur.",
                "Rest when needed and discuss persistent vomiting with your provider.",
                "The embryo develops early facial features and limb buds.",
                "About a lentil.",
                "Early heart activity may begin and the brain develops rapidly."
        ));

        weeks.add(new PregnancyWeekModel(
                7,
                "Hormonal changes can make fatigue and nausea stronger.",
                "You may experience nausea, tiredness or increased urination.",
                "Choose small frequent meals and maintain hydration.",
                "The brain continues developing and limb structures become more defined.",
                "About a blueberry.",
                "Arm and leg development continues."
        ));

        weeks.add(new PregnancyWeekModel(
                8,
                "Your uterus continues growing while pregnancy hormones remain high.",
                "Nausea, fatigue and breast changes may continue.",
                "Continue prenatal care, nutrition and adequate rest.",
                "Facial features and developing limbs become more recognizable.",
                "About a raspberry.",
                "The embryo's major body structures continue taking shape."
        ));

        weeks.add(new PregnancyWeekModel(
                9,
                "Your body continues adapting to pregnancy hormones.",
                "Fatigue and nausea may still be noticeable.",
                "Keep meals nutritious and follow your prenatal-care schedule.",
                "The embryo is developing rapidly and the basic body plan is becoming clearer.",
                "About a cherry.",
                "Fingers and toes begin becoming more defined."
        ));

        weeks.add(new PregnancyWeekModel(
                10,
                "Your uterus continues expanding and your body needs extra energy.",
                "You may feel tired, nauseated or emotionally sensitive.",
                "Continue healthy nutrition and prescribed supplements.",
                "Major organs have formed in early form and continue maturing.",
                "About a small strawberry.",
                "The embryo transitions toward the fetal stage."
        ));

        weeks.add(new PregnancyWeekModel(
                11,
                "Some early pregnancy symptoms may begin changing.",
                "Nausea may continue while energy may slowly improve.",
                "Include protein, fruits, vegetables and whole grains in meals.",
                "The baby's body continues growing and the head remains proportionally large.",
                "About a lime.",
                "Hands and feet continue developing."
        ));

        weeks.add(new PregnancyWeekModel(
                12,
                "Your uterus is growing and early pregnancy symptoms may start easing.",
                "Energy may begin improving, although symptoms vary.",
                "Keep prenatal appointments and maintain balanced nutrition.",
                "The baby's facial features and organs continue maturing.",
                "About a plum.",
                "Reflexive movements begin developing."
        ));

        weeks.add(new PregnancyWeekModel(
                13,
                "You are reaching the end of the first trimester.",
                "Nausea may reduce and appetite or energy may improve.",
                "Continue prenatal vitamins and prepare for the second trimester.",
                "The baby's bones and muscles continue developing.",
                "About a peach.",
                "The baby can make small movements even though you may not feel them yet."
        ));

        weeks.add(new PregnancyWeekModel(
                14,
                "The second trimester begins and many early symptoms may improve.",
                "You may feel more energetic than during early pregnancy.",
                "Continue balanced meals, hydration and regular checkups.",
                "The baby's facial muscles and body proportions continue developing.",
                "About a lemon.",
                "The baby continues moving and developing facial features."
        ));

        weeks.add(new PregnancyWeekModel(
                15,
                "Your uterus continues growing as your abdomen gradually changes.",
                "Mild stretching sensations may occur.",
                "Maintain comfortable activity and adequate hydration.",
                "The baby's bones continue developing and becoming stronger.",
                "About an apple.",
                "The baby's skeletal development progresses."
        ));

        weeks.add(new PregnancyWeekModel(
                16,
                "Your pregnancy may become more visible as your uterus grows.",
                "Some people begin noticing more energy and appetite.",
                "Follow your provider's guidance for nutrition and activity.",
                "The baby's facial muscles and movements continue developing.",
                "About an avocado.",
                "The baby can make facial movements and move the limbs."
        ));

        weeks.add(new PregnancyWeekModel(
                17,
                "Your belly may become more noticeable as the uterus grows.",
                "You may experience stretching sensations and changing posture.",
                "Focus on comfortable movement, hydration and balanced meals.",
                "The baby's skeleton continues developing and body fat begins gradually increasing later in pregnancy.",
                "About a pear.",
                "The baby continues growing rapidly and becoming more active."
        ));

        weeks.add(new PregnancyWeekModel(
                18,
                "Your uterus continues expanding and your center of gravity changes.",
                "Back discomfort or sleep changes may occur.",
                "Use comfortable sleeping positions and discuss significant pain with your provider.",
                "The baby's hearing structures continue developing.",
                "About a bell pepper.",
                "The baby may begin responding to sounds."
        ));

        weeks.add(new PregnancyWeekModel(
                19,
                "Your growing uterus may affect posture and comfort.",
                "You may notice skin changes or occasional aches.",
                "Stay hydrated and maintain gentle, provider-approved activity.",
                "The baby's senses continue developing and movement becomes stronger.",
                "About a mango.",
                "Sensory development continues, including touch and hearing."
        ));

        weeks.add(new PregnancyWeekModel(
                20,
                "You are around the halfway point of pregnancy.",
                "Your belly is more noticeable and movements may become clearer.",
                "Attend scheduled scans and continue nutritious meals.",
                "The baby's growth continues and movements may become easier to feel.",
                "About a banana.",
                "The baby's movement and sensory development continue."
        ));

        weeks.add(new PregnancyWeekModel(
                21,
                "Your growing uterus may increase pressure on your back and legs.",
                "You may feel stronger baby movements.",
                "Rest when needed and keep up with hydration.",
                "The baby's digestive system and sensory development continue.",
                "About a carrot.",
                "The baby continues swallowing amniotic fluid and practicing movements."
        ));

        weeks.add(new PregnancyWeekModel(
                22,
                "Your abdomen continues growing and your body needs increasing support.",
                "You may notice more movement and occasional leg discomfort.",
                "Maintain balanced nutrition and discuss persistent symptoms with your provider.",
                "The baby's facial features become more defined.",
                "About a papaya.",
                "The baby's senses continue developing."
        ));

        weeks.add(new PregnancyWeekModel(
                23,
                "Your uterus continues expanding as pregnancy progresses.",
                "Backache, leg cramps or sleep changes may occur.",
                "Keep hydrated and follow safe activity recommendations.",
                "The baby's lungs continue developing even though they are not yet mature.",
                "About a large grapefruit.",
                "The baby continues practicing breathing-like movements."
        ));

        weeks.add(new PregnancyWeekModel(
                24,
                "Your body is supporting rapid baby growth.",
                "You may notice increased movement and physical tiredness.",
                "Keep prenatal appointments and monitor your wellbeing.",
                "The baby's lungs and nervous system continue maturing.",
                "About an ear of corn.",
                "The baby's developing senses respond increasingly to the environment."
        ));

        weeks.add(new PregnancyWeekModel(
                25,
                "Your growing uterus can affect sleep, posture and comfort.",
                "You may notice stronger kicks and occasional heartburn.",
                "Eat smaller meals if needed and stay hydrated.",
                "The baby's brain and nervous system continue developing.",
                "About a rutabaga.",
                "The baby's movements become more coordinated."
        ));

        weeks.add(new PregnancyWeekModel(
                26,
                "Your pregnancy is progressing toward the third trimester.",
                "Back discomfort, leg cramps or swelling may occur.",
                "Discuss unusual swelling or symptoms with your healthcare provider.",
                "The baby's eyes and brain continue developing.",
                "About a cucumber.",
                "The baby may respond to familiar sounds."
        ));

        weeks.add(new PregnancyWeekModel(
                27,
                "You are finishing the second trimester.",
                "You may experience stronger movements and increasing tiredness.",
                "Prepare for the third trimester and keep regular checkups.",
                "The baby's brain and lungs continue maturing.",
                "About a cauliflower.",
                "The baby's sleep and wake patterns become more noticeable."
        ));

        weeks.add(new PregnancyWeekModel(
                28,
                "The third trimester begins and your body is preparing for later pregnancy.",
                "You may feel more tired and experience sleep changes.",
                "Prioritize rest, hydration and prenatal appointments.",
                "The baby's brain continues rapid development and the lungs mature further.",
                "About an eggplant.",
                "The baby's eyes can open and close."
        ));

        weeks.add(new PregnancyWeekModel(
                29,
                "Your growing uterus may create more pressure and discomfort.",
                "You may notice stronger kicks and occasional shortness of breath.",
                "Take comfortable breaks and discuss concerning symptoms with your provider.",
                "The baby continues gaining muscle and body fat.",
                "About a butternut squash.",
                "The baby's movements become stronger and more coordinated."
        ));

        weeks.add(new PregnancyWeekModel(
                30,
                "Your body is supporting rapid growth during the third trimester.",
                "Fatigue, back discomfort and sleep changes may continue.",
                "Continue prenatal care and prepare gradually for delivery.",
                "The baby's brain continues developing rapidly.",
                "About a cabbage.",
                "The baby continues gaining weight and developing brain connections."
        ));

        weeks.add(new PregnancyWeekModel(
                31,
                "Your uterus continues expanding and may affect your breathing and sleep.",
                "You may experience stronger movements and more frequent urination.",
                "Rest comfortably and maintain hydration.",
                "The baby's muscles, bones and brain continue maturing.",
                "About a coconut.",
                "The baby continues practicing movements and breathing-like motions."
        ));

        weeks.add(new PregnancyWeekModel(
                32,
                "Your body is preparing increasingly for birth.",
                "You may feel heavier and need more rest.",
                "Keep prenatal appointments and discuss your birth plan with your provider.",
                "The baby's bones continue hardening while the skull remains flexible.",
                "About a squash.",
                "The baby continues gaining fat and developing brain function."
        ));

        weeks.add(new PregnancyWeekModel(
                33,
                "Your growing belly may affect movement and sleeping comfort.",
                "Backache, pelvic pressure and fatigue may increase.",
                "Rest regularly and report concerning symptoms promptly.",
                "The baby's immune system and brain continue maturing.",
                "About a pineapple.",
                "The baby continues gaining strength and preparing for life outside the womb."
        ));

        weeks.add(new PregnancyWeekModel(
                34,
                "Your body is getting closer to full-term pregnancy.",
                "You may experience increased tiredness and pelvic pressure.",
                "Keep all prenatal appointments and follow your provider's guidance.",
                "The baby's lungs and nervous system continue maturing.",
                "About a cantaloupe.",
                "The baby continues gaining body fat and improving temperature regulation."
        ));

        weeks.add(new PregnancyWeekModel(
                35,
                "Your uterus is taking up more space and movement may feel harder.",
                "Frequent urination, fatigue and pelvic pressure may occur.",
                "Rest, stay hydrated and prepare essential items for delivery.",
                "The baby's brain and lungs continue maturing.",
                "About a honeydew melon.",
                "The baby continues gaining weight and practicing coordinated movements."
        ));

        weeks.add(new PregnancyWeekModel(
                36,
                "Your body is approaching the final weeks of pregnancy.",
                "You may feel increased pelvic pressure as the baby moves lower.",
                "Attend all remaining prenatal appointments and know when to contact your provider.",
                "The baby's organs are continuing to mature and the body is gaining fat.",
                "About a large melon.",
                "The baby continues preparing for birth."
        ));

        weeks.add(new PregnancyWeekModel(
                37,
                "Pregnancy is now considered early term.",
                "You may notice increased pelvic pressure and changes in comfort.",
                "Keep your healthcare provider's contact information available.",
                "The baby's organs continue functioning and the body continues maturing.",
                "About a bunch of Swiss chard.",
                "The baby continues gaining weight and preparing for birth."
        ));

        weeks.add(new PregnancyWeekModel(
                38,
                "Your body continues preparing for labor and birth.",
                "You may feel more pelvic pressure and tiredness.",
                "Continue monitoring your wellbeing and follow your birth plan.",
                "The baby continues gaining weight and maturing.",
                "About a leek-sized baby.",
                "The baby continues final preparation for life outside the uterus."
        ));

        weeks.add(new PregnancyWeekModel(
                39,
                "You are very close to meeting your baby.",
                "You may feel increased pressure and contractions may occur.",
                "Follow your healthcare provider's instructions about signs of labor.",
                "The baby's organs are mature enough for life outside the uterus in most cases.",
                "About a small watermelon.",
                "The baby continues final growth and preparation for birth."
        ));

        weeks.add(new PregnancyWeekModel(
                40,
                "This is the estimated due week for many pregnancies.",
                "You may experience increasing pressure and signs that labor is approaching.",
                "Stay in contact with your healthcare provider and follow your birth plan.",
                "Your baby has completed most major development and is ready for birth.",
                "About a small pumpkin.",
                "Final preparation for birth and transition to life outside the womb."
        ));

        return weeks;
    }
}