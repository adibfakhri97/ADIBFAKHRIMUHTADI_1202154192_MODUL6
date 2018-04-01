package com.adib.adibfakhrimuhtadi_1202154192_modul6;

/**
 * Created by adib on 1/04/2018.
 */

public class methodUploadInfo {

    public String imageName;

    public String imageURL;
    public String userImage;

    public methodUploadInfo() {

    }

    public methodUploadInfo(String name, String url, String user) {

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
