package com.example.projetoids;

public class ConquistaItem {
    private int mImageResource;
    private String mText1;
    private String mText2;

    public ConquistaItem(int imageResource, String text1, String text2){
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
    }

    public int getImageResource(){
        return mImageResource;
    }

    public String getmText1(){
        return mText1;
    }

    public String getmText2(){
        return mText2;
    }
}
