package org.prth.model;

public class SPAA {
    private Long id;
    private String text;
    private String tag;

    public SPAA() {
    }

    public SPAA(Long id, String text, String tag) {
        this.id = id;
        this.text = text;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
