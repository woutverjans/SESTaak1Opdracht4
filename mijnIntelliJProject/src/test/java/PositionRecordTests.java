import be.KULeuven.model.BoardSize;
import be.KULeuven.model.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static be.KULeuven.model.Position.fromIndex;
import static org.junit.jupiter.api.Assertions.*;

public class PositionRecordTests {
    //Tests voor toIndex()
    @Test
    public void gegevenOorsprongPunt_wanneerToIndex_danIs0(){
        var bord = new BoardSize(4, 4);
        var pos = new Position(0, 0, bord);
        assert(pos.toIndex() == 0);
    }
    @Test
    public void gegevenPuntDrieDrie_wanneerToIndex_danIs15(){
        var bord = new BoardSize(4, 4);
        var pos = new Position(3, 3, bord);
        assert(pos.toIndex() == 15);
    }

    //Tests voor fromIndex()
    @Test
    public void gegevenIndex0_wanneerFromIndex_danIsNulNul(){
        var bord = new BoardSize(4, 4);
        var pos = fromIndex(0, bord);
        assert(pos.rij() == 0 && pos.kolom() == 0);
    }
    @Test
    public void gegevenIndex15_wanneerFromIndex_danIsDrieDrie(){
        var bord = new BoardSize(4, 4);
        var pos = fromIndex(15, bord);
        assert(pos.rij() == 3 && pos.kolom() == 3);
    }

    //Tests voor neighborPositions
    @Test
    public void gegevenIndex0_wanneerNeighborPositions_danIsEenVierenVijf(){
        var bord = new BoardSize(4, 4);
        var pos = fromIndex(0, bord);
        Iterable<Position> buren = pos.neighborPositions(0);

        var buur1 = new Position(0,1, bord);
        var buur2 = new Position(1,0, bord);
        var buur3 = new Position(1,1, bord);
        ArrayList<Position> oplossingBuren = new ArrayList<>();
        oplossingBuren.add(buur1);
        oplossingBuren.add(buur2);
        oplossingBuren.add(buur3);

        assertEquals(oplossingBuren, buren);
    }
    @Test
    public void gegevenIndex10InBordSizeVierVier_wanneerNeighborPositions_danIsTienElfVeerTien(){
        var bord = new BoardSize(4, 4);
        var pos = fromIndex(10, bord);
        Iterable<Position> buren = pos.neighborPositions(10);

        var buur1 = new Position(2,1, bord); //Volgorde is belangrijk!
        var buur2 = new Position(2,3, bord);
        var buur3 = new Position(1,2, bord);
        var buur4 = new Position(3,2, bord);
        var buur5 = new Position(1,1, bord);
        var buur6 = new Position(1,3, bord);
        var buur7 = new Position(3,1, bord);
        var buur8 = new Position(3,3, bord);
        ArrayList<Position> oplossingBuren = new ArrayList<>();
        oplossingBuren.add(buur1);
        oplossingBuren.add(buur2);
        oplossingBuren.add(buur3);
        oplossingBuren.add(buur4);
        oplossingBuren.add(buur5);
        oplossingBuren.add(buur6);
        oplossingBuren.add(buur7);
        oplossingBuren.add(buur8);

        assertEquals(oplossingBuren, buren);
    }

    //Tests isLastsColumn
    @Test
    public void gegevenIsLaatste_wanneerIsLastColumn_isTrue(){
        var bord = new BoardSize(3, 4);
        var pos = fromIndex(7, bord);
        boolean isLaatste = pos.isLastColumn(7);
        assertTrue(isLaatste);
    }
    @Test
    public void gegevenIsNietLaatste_wanneerIsLastColumn_isTrue(){
        var bord = new BoardSize(3, 4);
        var pos = fromIndex(6, bord);
        boolean isLaatste = pos.isLastColumn(7);
        assertTrue(isLaatste);
    }
}