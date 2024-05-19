package main.java.be.KULeuven.model;
public record Position(int kolom, int rij) {
    public boolean isGeldigePositie(int rij, int kolom, BoardSize boardSize) {
        if (kolom < 0 || kolom > boardSize.getAantalKolommen() || rij < 0 || rij > boardSize.getAantalRijen()) {
            throw new IllegalArgumentException("positie moet binnen het bord vallen");
        } else return true;
    }

    public int toIndex(int kolom, int rij) {
        int index = kolom + rij * kolom;
        return index;
    }
    public static Position fromIndex(int index, BoardSize boardSize){
        int rij = 0;
        int kolom = 0;
        while(index > boardSize.getAantalKolommen()){
            index = index - boardSize.getAantalKolommen();
            kolom = kolom + 1;
        }
        rij = index;
        if(rij > boardSize.getAantalKolommen() || kolom > boardSize.getAantalRijen()){
            throw new IllegalStateException("geef geldige index");
        }
        return new Position(rij, kolom);
    }
}