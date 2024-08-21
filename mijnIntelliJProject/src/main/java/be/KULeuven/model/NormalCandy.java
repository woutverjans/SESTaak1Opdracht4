package be.KULeuven.model;

import java.awt.*;

public record NormalCandy (Shape vorm, int kleur){
    public NormalCandy(Shape vorm, int kleur){
        this.vorm = vorm;
        this.kleur = kleur;
    }
}
