package main.java.be.KULeuven.model;

public record BoardSize(int aantalKolommen, int aantalRijen){
    public boolean isMogelijkBord(int aantalKolommen, int aantalRijen) {
        if (aantalKolommen <= 0 || aantalRijen <= 0) {
            throw new IllegalArgumentException("aantal rijen en kolommen moet groter zijn dan 0");
        }
        else return true;
    }
    public int getAantalKolommen(){
        return this.aantalKolommen;
    }
    public int getAantalRijen(){
        return this.aantalRijen;
    }
}