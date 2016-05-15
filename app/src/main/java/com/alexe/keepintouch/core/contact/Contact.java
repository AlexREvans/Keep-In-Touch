package com.alexe.keepintouch.core.contact;

public class Contact {
    private String name;
    private String pictureUri;
    private String id;

    public Contact(String id, String name, String pictureUri) {
        this.name = name;
        this.pictureUri = pictureUri;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPictureUri() {
        return pictureUri;
    }

    public String getId() {
        return id;
    }
}
