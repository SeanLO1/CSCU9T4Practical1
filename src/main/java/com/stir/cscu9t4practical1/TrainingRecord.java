// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;




import java.sql.ClientInfoStatus;
import java.util.*;


public class TrainingRecord {
    private List<Entry> tr;

    public TrainingRecord() {
        tr = new ArrayList<Entry>();
    } //constructor

    // add a record to the list
    public void addEntry(Entry e){
        tr.add(e);
    } // addClass

   // look up the entry of a given day and month
   public String lookupEntry (int d, int m, int y) {
       ListIterator<Entry> iter = tr.listIterator();
       String result = "";
       while (iter.hasNext()) {
          Entry current = iter.next();
          if (current.getDay()==d && current.getMonth()==m && current.getYear()==y)
             result = current.getEntry();
            }
       if(result.isEmpty()){
           result = "No Entries is found";
       }
       return result;
   } // lookupEntry

    public String FindAllByDate(int d, int m, int y){
        ListIterator<Entry> iter = tr.listIterator();
        String result = "";
        while(iter.hasNext()){
            Entry current = iter.next();
            if (current.getDay()==d && current.getMonth()==m && current.getYear()==y){
                result += (current.getEntry() + "\n");
            }
            if(result.isEmpty()){
                result ="No entries found" ;
            }
            }
        return result;
    }
    public String delete(String name,int d, int m, int y){
        for(int i=0;i<tr.size();i++){
            if(tr.get(i).getDay()==d && tr.get(i).getMonth()==m && tr.get(i).getYear()==y &&tr.get(i).getName().equals(name)){
                tr.remove(i);
                return "Entry Successfully deleted";
            }
        }
        return "Entry not found";
    }


   // Count the number of entries
   public int getNumberOfEntries(){
       return tr.size();
   }
   // Clear all entries
   public void clearAllEntries(){
       tr.clear();
   }

} // TrainingRecord