
public class Word {
    private String description;
    private String leksi;
    
    
    public Word(String description,String leksi){
        this.description=description;
        this.leksi=leksi;
    }
    
    public void setDescription(String description){
        this.description=description;
    }
    
    public void setLeksi(String leksi){
        this.leksi=leksi;
    }
    
    public String getDescription(){return description;}
    public String getLeksi(){return leksi;}
    
    public String toString(){
        return description;
    }
}
