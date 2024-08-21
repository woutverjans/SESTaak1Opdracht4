import be.KULeuven.model.Board;
import be.KULeuven.model.BoardSize;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTests {
    /*@Test
    public void gegevengelijkBoardSizesEn4Cellen_wanneerCopyTo_danZijnBordenGelijk() throws Exception {
        ArrayList<String> cellenEersteBord = new ArrayList<>();
        ArrayList<String> cellenTweedeBord = new ArrayList<>();
        for(int i = 0; i<4; i++){
            cellenEersteBord.add(String.valueOf(i));
        }
        for(int i = 0; i<4; i++){
            cellenTweedeBord.add("0");
        }
        var boardSize = new BoardSize(2, 2);
        var eersteBord = new Board(cellenEersteBord, boardSize);
        var tweedeBord = new Board(cellenTweedeBord, boardSize);
        eersteBord.copyTo(tweedeBord);
        assert(eersteBord.equals(tweedeBord));
    }*/
}
