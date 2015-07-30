package androidhive.info.materialdesign.model;


import android.content.res.Resources;

public class NavDrawerItem {
    private boolean showNotify;
    private String title;
    private Resources icon;


    public NavDrawerItem() {

    }

    public NavDrawerItem(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(Resources icon){
        this.icon=icon;
    }
}
