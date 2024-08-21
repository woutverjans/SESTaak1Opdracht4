package be.KULeuven.model;

import java.awt.*;

public sealed interface Candy permits Candy.NormalCandy, Candy.Gummybeer, Candy.Haaientand, Candy.Lekstok, Candy.Zuurstok {
    record NormalCandy(Shape cirkel, int kleur) implements Candy {};
    record Gummybeer(Shape rechthoek, int kleur) implements Candy {};
    record Haaientand(Shape rechthoek, int kleur) implements Candy {};
    record Lekstok(Shape rechthoek, int kleur) implements Candy {};
    record Zuurstok(Shape rechthoek, int kleur) implements Candy {};
}
