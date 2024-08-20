package be.KULeuven.model;

import java.util.ArrayList;

import static be.KULeuven.model.Position.fromIndex;

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

    public BoardSize(int aantalKolommen, int aantalRijen){
        this.aantalKolommen = aantalKolommen;
        this.aantalRijen = aantalRijen;
    }

    public Iterable<Position> positions(){
        int maxIndex = aantalRijen * aantalKolommen - 1; // = het aantal indexen (posities) op het bord
        ArrayList<Position> posities = new ArrayList<>();
        BoardSize bord = new BoardSize(aantalKolommen, aantalRijen);
        for(int i = 0; i<=maxIndex; i++){
            var pos = fromIndex(i, bord);
            posities.add(pos);
        }
        return posities;
    }
}