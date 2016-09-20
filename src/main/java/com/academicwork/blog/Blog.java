package com.academicwork.blog;

public class Blog {

    public final long id;
    public final String title;
    public long blogid;

    public Blog(long id, String title) {
        this.id = id;
        this.title = title;
        this.blogid = id/5;
    }

}
