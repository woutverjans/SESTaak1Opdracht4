package be.KULeuven.model;

import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Position(int rij, int kolom, BoardSize boardSize) {
    public boolean isGeldigePositie(int rij, int kolom, BoardSize boardSize) {
        if (kolom < 0 || kolom > boardSize.getAantalKolommen() || rij < 0 || rij > boardSize.getAantalRijen()) {
            throw new IllegalArgumentException("positie moet binnen het bord vallen, coordinaten: " + rij + " , " + kolom);
        } else return true;
    }

    public Position(int rij, int kolom, BoardSize boardSize){
        this.rij = rij;
        this.kolom = kolom;
        this.boardSize = boardSize;

        if(!isGeldigePositie(rij, kolom, boardSize)) {
            throw new IllegalArgumentException("positie moet binnen het bord vallen, coordinaten: " + rij + " , " + kolom);
        }
    }

    public int toIndex() {
        int index = kolom + rij * boardSize.getAantalKolommen(); //Kolommen beginnen bij 0
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
        return new Position(rij, kolom, boardSize);
    }
    public Iterable<Position> neighborPositions(int index){
        ArrayList<Position> buren = new ArrayList<>();
        var pos = fromIndex(index, boardSize);
        try {
            var linkerBuur = new Position(pos.rij(), pos.kolom() - 1, boardSize);
            if(isGeldigePositie(linkerBuur.rij, linkerBuur.kolom, boardSize)){buren.add(linkerBuur);}
        } catch (IllegalArgumentException e) {}
        try {
            var rechterBuur = new Position(pos.rij(), pos.kolom() + 1, boardSize);
            if(isGeldigePositie(rechterBuur.rij, rechterBuur.kolom, boardSize)){buren.add(rechterBuur);}
        } catch (IllegalArgumentException e) {}
        try {
            var bovenBuur = new Position(pos.rij() - 1, pos.kolom(), boardSize);
            if(isGeldigePositie(bovenBuur.rij, bovenBuur.kolom, boardSize)){buren.add(bovenBuur);}
        } catch (IllegalArgumentException e) {}
        try {
            var onderBuur = new Position(pos.rij() + 1, pos.kolom(), boardSize);
            if(isGeldigePositie(onderBuur.rij, onderBuur.kolom, boardSize)){buren.add(onderBuur);}
        } catch (IllegalArgumentException e) {}
        try {
            var linksBovenBuur = new Position(pos.rij() - 1, pos.kolom() - 1, boardSize);
            if(isGeldigePositie(linksBovenBuur.rij, linksBovenBuur.kolom, boardSize)){buren.add(linksBovenBuur);}
        } catch (IllegalArgumentException e) {}
        try {
            var rechtsBovenBuur = new Position(pos.rij() - 1, pos.kolom() + 1, boardSize);
            if(isGeldigePositie(rechtsBovenBuur.rij, rechtsBovenBuur.kolom, boardSize)){buren.add(rechtsBovenBuur);}
        } catch (IllegalArgumentException e) {}
        try {
            var linksOnderBuur = new Position(pos.rij() + 1, pos.kolom() - 1, boardSize);
            if(isGeldigePositie(linksOnderBuur.rij, linksOnderBuur.kolom, boardSize)){buren.add(linksOnderBuur);}
        } catch (IllegalArgumentException e) {}
        try {
            var rechtsOnderBuur = new Position(pos.rij() + 1, pos.kolom() + 1, boardSize);
            if(isGeldigePositie(rechtsOnderBuur.rij, rechtsOnderBuur.kolom, boardSize)){buren.add(rechtsOnderBuur);}
        } catch (IllegalArgumentException e) {}
        return buren;
    }
    public boolean isLastColumn(int index){
        boolean isLast;
        var pos = fromIndex(index, boardSize);
        if(boardSize.aantalKolommen() -1 == pos.kolom){ isLast = true;}
        else isLast = false;
        return isLast;
    }

    public Stream<Position> walkLeft(Position position){
        return IntStream.range(position.kolom() - 1, -1) // -1 want -1 zit er dan niet in de range, 0 wel nog. De kolommen 0 tot en met direct links van de positie
                .mapToObj(nieuweKolom -> new Position(nieuweKolom, position.rij, boardSize))
                .filter(positie -> isGeldigePositie(positie.kolom, positie.rij, boardSize));
    }

    public Stream<Position> walkRight(Position position){
        return IntStream.range(position.kolom() + 1, boardSize.getAantalKolommen() - 1)
                .mapToObj(nieuweKolom -> new Position(nieuweKolom, position.rij, boardSize))
                .filter(positie -> isGeldigePositie(positie.kolom, positie.rij, boardSize));
    }

    public Stream<Position> walkUp(Position position){
        return IntStream.range(position.rij - 1, -1)
                .mapToObj(nieuweRij -> new Position(position.kolom, nieuweRij, boardSize))
                .filter(positie -> isGeldigePositie(positie.kolom, positie.rij, boardSize));
    }

    public Stream<Position> walkDown(Position position){
        return IntStream.range(position.rij + 1, boardSize.getAantalRijen() - 1)
                .mapToObj(nieuweRij -> new Position(position.kolom, nieuweRij, boardSize))
                .filter(positie -> isGeldigePositie(positie.kolom, positie.rij, boardSize));
    }
}