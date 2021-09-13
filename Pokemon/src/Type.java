import acm.graphics.GImage;

import java.awt.*;

public abstract class Type extends PokemonDetails{
    protected String typeName;

    public abstract GImage getPokemonImage();

    public abstract GImage getBackgroundImage();

    public abstract Color getBarColor();

    public abstract Color getTextColor();

    public abstract Color getTypeColor();

    public abstract String getTypeName();

    public abstract String getPokemonName();

}
