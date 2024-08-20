import be.KULeuven.model.BoardSize;
import be.KULeuven.model.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static be.KULeuven.model.Position.fromIndex;
import static org.junit.jupiter.api.Assertions.*;

public class BoardSizeTests {
    //Tests voor positions()
    @Test
    public void gegeven2Bij2Bord_wanneerIndexenOplopend(){
        var bord = new BoardSize(2, 2); //Maximum index is dan 3
        ArrayList<Position> posities = new ArrayList<>();
        for(int i = 0; i<=3; i++){
            var pos = fromIndex(i, bord);
            posities.add(pos);
        }
        var getestePosities = bord.positions();
        assertEquals(posities, getestePosities);
    }
    @Test
    public void gegeven4Bij4Bord_wanneerIndexenOplopend(){
        var bord = new BoardSize(2, 2); //Maximum index is dan 3
        ArrayList<Position> posities = new ArrayList<>();
        for(int i = 0; i<=3; i++){
            var pos = fromIndex(i, bord);
            posities.add(pos);
        }
        var getestePosities = bord.positions();
        assertEquals(posities, getestePosities);
    }
}