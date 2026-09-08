package com.sigma.view.motherPages;

public class VideoModel {

    private final String title;
    private final String category;
    private final String videoId;
    private final String description;
    private final String quality;

    public VideoModel(
            String title,
            String category,
            String videoId,
            String description,
            String quality
    ) {
        this.title = title;
        this.category = category;
        this.videoId = videoId;
        this.description = description;
        this.quality = quality;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getDescription() {
        return description;
    }

    public String getQuality() {
        return quality;
    }

    public String getThumbnailUrl() {
        return "https://img.youtube.com/vi/"
                + videoId
                + "/hqdefault.jpg";
    }

    public String getYouTubeUrl() {
        return "https://www.youtube.com/watch?v="
                + videoId;
    }

    public String getEmbedUrl() {
        return "https://www.youtube.com/embed/"
                + videoId
                + "?autoplay=1"
                + "&playsinline=1"
                + "&rel=0"
                + "&modestbranding=1";
    }
}