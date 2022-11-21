package com.uniajc.wikibird;

/* Modelo de la clase para RecyclerView*/
public class Ave {
    String id, name, image, bio, country, family;


    public Ave(String id, String name, String image, String bio, String country, String family) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.bio = bio;
        this.country = country;
        this.family = family;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

}
