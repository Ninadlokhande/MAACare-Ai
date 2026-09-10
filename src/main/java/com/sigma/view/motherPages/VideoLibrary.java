package com.sigma.view.motherPages;

import java.util.ArrayList;
import java.util.List;

public class VideoLibrary {

    private final List<VideoModel> videos;

    public VideoLibrary() {
        videos = new ArrayList<>();
        loadVideos();
    }

    private void loadVideos() {

        // =====================================================
        // DIET & NUTRITION
        // =====================================================

        videos.add(
                new VideoModel(
                        "Diet During Pregnancy",
                        "Diet & Nutrition",
                        "kuMInwXpOUg",
                        "Important nutrition information for pregnancy.",
                        "HD"
                )
        );

        videos.add(
                new VideoModel(
                        "Healthy Pregnancy Diet",
                        "Diet & Nutrition",
                        "4l9GE_eaMSs",
                        "Helpful pregnancy and baby-development information.",
                        "HD"
                )
        );

        videos.add(
                new VideoModel(
                        "Pregnancy Nutrition Tips",
                        "Diet & Nutrition",
                        "4l9GE_eaMSs",
                        "Useful information for expecting mothers.",
                        "HD"
                )
        );

        // =====================================================
        // PREGNANCY
        // =====================================================

        videos.add(
                new VideoModel(
                        "Pregnancy Week by Week",
                        "Pregnancy",
                        "4l9GE_eaMSs",
                        "Follow early pregnancy development from "
                                + "conception through week 9.",
                        "HD"
                )
        );

        videos.add(
                new VideoModel(
                        "Pregnancy Week by Week - Full Guide",
                        "Pregnancy",
                        "VktZZEeGdSs",
                        "Learn about fetal development from week "
                                + "3 through the later stages of pregnancy.",
                        "HD"
                )
        );

        videos.add(
                new VideoModel(
                        "Early Pregnancy Symptoms",
                        "Pregnancy",
                        "GBWaCSw-DlA",
                        "Learn how early pregnancy symptoms can begin.",
                        "HD"
                )
        );

        // =====================================================
        // MOTHER HEALTH
        // =====================================================

        videos.add(
                new VideoModel(
                        "Exercise During Pregnancy",
                        "Mother Health",
                        "Hy4NpKhf5dk",
                        "Information about safe exercise and physical "
                                + "activity during pregnancy.",
                        "HD"
                )
        );

        videos.add(
                new VideoModel(
                        "Pregnancy Exercise",
                        "Mother Health",
                        "CRpfORhoi2A",
                        "Pregnancy movement and exercise information "
                                + "for expecting mothers.",
                        "HD"
                )
        );

        videos.add(
                new VideoModel(
                        "Pregnancy Health Tips",
                        "Mother Health",
                        "4l9GE_eaMSs",
                        "Helpful information about pregnancy and "
                                + "maternal changes.",
                        "HD"
                )
        );

        // =====================================================
        // BABY CARE
        // =====================================================

        videos.add(
                new VideoModel(
                        "Newborn Care Immediately After Birth",
                        "Baby Care",
                        "LhH5tFsiYHU",
                        "Learn about the care a newborn typically "
                                + "receives immediately after birth.",
                        "HD"
                )
        );

        videos.add(
                new VideoModel(
                        "Newborn Care Guide",
                        "Baby Care",
                        "LhH5tFsiYHU",
                        "Basic information about newborn care "
                                + "after delivery.",
                        "HD"
                )
        );

        videos.add(
                new VideoModel(
                        "Baby Care for New Mothers",
                        "Baby Care",
                        "LhH5tFsiYHU",
                        "Useful newborn-care information for new mothers.",
                        "HD"
                )
        );

        // =====================================================
        // BREASTFEEDING
        // =====================================================

        videos.add(
                new VideoModel(
                        "Breastfeeding How To",
                        "Breastfeeding",
                        "8kTKnZOmJXU",
                        "Experts explain basic breastfeeding steps "
                                + "and techniques.",
                        "HD"
                )
        );

        videos.add(
                new VideoModel(
                        "Breastfeeding Positions",
                        "Breastfeeding",
                        "TNbqxGiY0Kw",
                        "Learn about commonly used breastfeeding "
                                + "positions.",
                        "HD"
                )
        );

        videos.add(
                new VideoModel(
                        "Breastfeeding Tips",
                        "Breastfeeding",
                        "or4OnMxihUg",
                        "Learn about common breastfeeding positions "
                                + "and techniques.",
                        "HD"
                )
        );

        // =====================================================
        // FAQs
        // =====================================================

        videos.add(
                new VideoModel(
                        "How Early Do Pregnancy Symptoms Start?",
                        "FAQs",
                        "GBWaCSw-DlA",
                        "An OBGYN explains when early pregnancy "
                                + "symptoms can begin.",
                        "HD"
                )
        );

        videos.add(
                new VideoModel(
                        "Pregnancy Week by Week Questions",
                        "FAQs",
                        "4l9GE_eaMSs",
                        "Common pregnancy-development information "
                                + "for expecting mothers.",
                        "HD"
                )
        );

        videos.add(
                new VideoModel(
                        "Common Pregnancy Information",
                        "FAQs",
                        "VktZZEeGdSs",
                        "Pregnancy development information covering "
                                + "multiple stages.",
                        "HD"
                )
        );

        // =====================================================
        // LULLABIES
        // =====================================================

        videos.add(
                new VideoModel(
                        "The Lullaby Song",
                        "Lullabies",
                        "jHiRN02j4ks",
                        "A calming bedtime lullaby for babies.",
                        "HD"
                )
        );

        videos.add(
                new VideoModel(
                        "Baby Bedtime Lullaby",
                        "Lullabies",
                        "jHiRN02j4ks",
                        "Relaxing music and visuals designed "
                                + "for baby's bedtime.",
                        "HD"
                )
        );
    }

    public List<VideoModel> getAllVideos() {
        return videos;
    }

    public List<String> getCategories() {

        List<String> categories =
                new ArrayList<>();

        for (VideoModel video : videos) {

            if (!categories.contains(
                    video.getCategory()
            )) {

                categories.add(
                        video.getCategory()
                );
            }
        }

        return categories;
    }

    public List<VideoModel> getVideosByCategory(
            String category
    ) {

        List<VideoModel> result =
                new ArrayList<>();

        for (VideoModel video : videos) {

            if (video.getCategory()
                    .equalsIgnoreCase(category)) {

                result.add(video);
            }
        }

        return result;
    }
}