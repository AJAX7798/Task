package com.tasks.ajax.tasks;


public class Group {
    private String name;
    private String workCount;
    public Group(){
        //Empty
    }
    public Group(String id,String name){
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkCount() {
        return workCount;
    }

    public void setWorkCount(String workCount) {
        this.workCount = workCount;
    }
}
