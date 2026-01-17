package com.android.example.wordlistsql;



public class WordItem {
    private int mId;
    private String mWord;

    public WordItem() {
    }

    public int getId() {
        return mId;
    }

    public String getWord() {
        return mWord;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public void setWord(String mWord) {
        this.mWord = mWord;
    }

}
