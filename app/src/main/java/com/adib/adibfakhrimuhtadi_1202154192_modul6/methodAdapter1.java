package com.adib.adibfakhrimuhtadi_1202154192_modul6;

/**
 * Created by adib on 1/04/2018.
 */

public class methodAdapter1 {
    private static final methodAdapter1 ourInstance = new methodAdapter1();

    public String imageName;

    public String imageURL;
    public String userImage;

    public methodAdapter1() {

    }

    public methodAdapter1(String name, String url, String user) {

        this.imageName = name;
        this.imageURL= url;
        this.userImage = user;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageURL() {
        return imageURL;
    }
    public String getUserImage() {
        return userImage;
    }
}
