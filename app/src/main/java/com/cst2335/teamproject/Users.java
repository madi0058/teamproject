package com.cst2335.teamproject;

public class Users {

    //Android Studio hint: to create getter and setter, put mouse on variable and
    // click "alt+insert" in Windows, "control+return" on Macintosh
    protected String email, name, score;
    protected long id;

    /**Constructor:*/
    public Users(String n, String e, String s, long i)
    {
        name =n;
        email = e;
        score =s;
        id = i;
    }

    public void update(String n, String e, String s)
    {
        name = n;
        email = e;
        score =s;
    }

    /**Chaining constructor: */
    public Users(String n, String e, String s) { this(n, e,s, 0);}


    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
    public String getScore(){return  score;}
    public long getId() {
        return id;
    }


}
