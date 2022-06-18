package com.example.formdata;

import com.example.data.Event;

public class FormData {
    private Event event;
    private int valid;

    public FormData(){
        
    }

    public Event getEvent() {
        return this.event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getValid(){
        return this.valid;
    }

    public void setValid(int valid){
        this.valid = valid;
    }

}
