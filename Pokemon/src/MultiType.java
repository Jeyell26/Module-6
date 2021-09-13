import acm.graphics.GImage;

import java.awt.*;

public abstract class MultiType extends Type{
    protected String typeName;
    protected String typeName2;

    public abstract GImage getPokemonImage();

    public abstract GImage getBackgroundImage();

    public abstract Color getBarColor();

    public abstract Color getTextColor();

    public abstract Color getTypeColor();

    public abstract String getTypeName();

    public abstract String getPokemonName();

    public abstract Color getType2Color();

    public abstract String getType2Name();
}
