package be.kuleuven.candycrush.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CandycrushModelTests {
    @Test
    void maakBordAan_CandyCrushModel_widthIs4() {
        CandycrushModel model = new CandycrushModel("Test");
        assertEquals("TestPlayer", model.getSpeler());
        assertEquals(4, model.getWidth());
    }
    @Test
    void maakBordAan_CandyCrushModel_heightIs4() {
        CandycrushModel model = new CandycrushModel("Test");
        assertEquals("TestPlayer", model.getSpeler());
        assertEquals(4, model.getHeight());
        assertNotNull(model.getSpeelbord());
    }
    @Test
    void maakBordAan_CandyCrushModel_bordIsNietNULL() {
        CandycrushModel model = new CandycrushModel("Test");
        assertNotNull(model.getSpeelbord());
    }
    @Test
    void maakBordAan_CandyCrushModel_beginScoreIs0() {
        CandycrushModel model = new CandycrushModel("Test");
        assertEquals(0, model.getScore());
    }
    @Test
    void maakBordAan_CandyCrushModel_beginScoreIsNietNULL() {
        CandycrushModel model = new CandycrushModel("Test");
        assertNotNull(model.getScore());
    }
    @Test
    void maakSpelerTestAan_CandyCrushModel_spelerIsNietNULL() {
        CandycrushModel model = new CandycrushModel("Test");
        assertNotNull(model.getSpeler());
    }
    @Test
    void maakSpelerTestAan_CandyCrushModel_spelerIsGelijkAanTest() {
        CandycrushModel model = new CandycrushModel("Test");
        assertEquals("Test", model.getSpeler());
    }
    @Test
    void haalIndexVanBord_getIndexFromRowColumn_rij1Kolom1Op4x4IsIndex5() {
        CandycrushModel model = new CandycrushModel("Test");
        assertEquals(5, model.getIndexFromRowColumn(1, 1));
    }
    @Test
    void haalIndexVanBord_getIndexFromRowColumn_rij0Kolom0Op4x4IsIndex0() {
        CandycrushModel model = new CandycrushModel("Test");
        assertEquals(0, model.getIndexFromRowColumn(0, 0));
    }
    @Test
    void haalIndexVanBord_getIndexFromRowColumn_rij4Kolom4Op4x4IsIndex15() {
        CandycrushModel model = new CandycrushModel("Test");
        assertEquals(15, model.getIndexFromRowColumn(4, 4));
    }
}