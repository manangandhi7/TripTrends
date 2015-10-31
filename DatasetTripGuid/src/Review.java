class Review {
	
    private int curClass;
    private String comment;
    private int numPeople;
    private int days;
    private String city;
    public enum Classes {
        CLASSIC, ADVENTUROUS, UNEXPLORED, WELLNESS, PANORAMICVIEW,
    }
    
    //List<Review> mylist = new ArrayList<Review>();
    ////////////
    public void setCity(String ct){
    	city = ct;
    }

    public String getCt(){
    	return city;
    	
    }
    ///////////
    public void setClass(int cl) {
         curClass = cl;
    }

    public int getc() {
    	return curClass;
    }
 
    ////////////
    public void setComment(String cm) {
        comment = cm;
   }

   public String getCm() {
   	return comment;
   }
   ////////////
   public void setNumpeople(int num) {
       numPeople = num;
   }

   public int getNp() {
  	return numPeople;
   }
   
   ////////////
   public void setDuration(int day) {
      days = day;
   }

   public int getd() {
 	return days;
 	
 	
 }
    
    
}