package asciiPanel;

/**
 * This class holds provides all available Fonts for the AsciiPanel. Some
 * graphics are from the Dwarf Fortress Tileset Wiki Page
 * 
 * @author zn80
 *
 */
public class AsciiFont {

    public static final AsciiFont CP437_9x16 = new AsciiFont("resources/cp437_9x16.png", 9, 16);
    public static final AsciiFont Oddball_16x16 = new AsciiFont("resources/Oddball_16x16.png", 16, 16);//cute
    public static final AsciiFont MyOddball_16x16 = new AsciiFont("resources/Oddball_16x16_after.png", 16, 16);//cute！！！
    public static final AsciiFont MyOddballW_16x16 = new AsciiFont("resources/Oddball_16x16_wh.png", 16, 16);//cute！！！
    public static final AsciiFont MyOddballB_16x16 = new AsciiFont("resources/Oddball_16x16_bl.png", 16, 16);//cute！！！
    public static final AsciiFont MyTest = new AsciiFont("resources/Oddball_16x16_bw.png", 16, 16);//test

    private String fontFilename;

    public String getFontFilename() {
        return fontFilename;
    }

    private int width;

    public int getWidth() {
        return width;
    }

    private int height;

    public int getHeight() {
        return height;
    }

    public AsciiFont(String filename, int width, int height) {
        this.fontFilename = filename;
        this.width = width;
        this.height = height;
    }
}