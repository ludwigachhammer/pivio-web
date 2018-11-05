package io.pivio.view.app.endpoints;

public class Date{
	
    private String date;
    
    public Date(){
    }
    
    public Date(String date) {
    	this.date = date;
    }
    
    public String getDate(){
        return this.date;
    }
    public void setDate(String value){
         this.date = value;
    } 
}