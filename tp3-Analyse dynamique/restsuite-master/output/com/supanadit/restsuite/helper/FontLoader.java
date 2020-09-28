package com.supanadit.restsuite.helper;
import com.supanadit.restsuite.Main;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.awt.*;
public class FontLoader {
    public static Font getDefaultFont() {
        Font font = null;
        try {
            System.out.println("getDefaultFont -> createFont");
            System.out.println("getDefaultFont -> createFont");
            System.out.println("getDefaultFont -> getClassLoader");
            System.out.println("getDefaultFont -> getClassLoader");
            InputStream inputFile = Main.class.getClassLoader().getResourceAsStream("font/ui/NotoSans-Regular.ttf");
            assert inputFile != null;
            font = Font.createFont(Font.TRUETYPE_FONT, inputFile).deriveFont(13.0F);
        } catch (FontFormatException | IOException e) {
            System.out.println("getDefaultFont -> printStackTrace");
            e.printStackTrace();
        }
        if (font == null) {
            System.out.println("getDefaultFont -> getFont");
            font = Font.getFont(Font.MONOSPACED);
        }
        return font;
    }

    public static Font getCodeFont() {
        Font font = null;
        try {
            System.out.println("getCodeFont -> createFont");
            System.out.println("getCodeFont -> createFont");
            System.out.println("getCodeFont -> getClassLoader");
            System.out.println("getCodeFont -> getClassLoader");
            InputStream inputFile = Main.class.getClassLoader().getResourceAsStream("font/code/JetBrainsMono-Regular.ttf");
            assert inputFile != null;
            font = Font.createFont(Font.TRUETYPE_FONT, inputFile).deriveFont(13.0F);
        } catch (FontFormatException | IOException e) {
            System.out.println("getCodeFont -> printStackTrace");
            e.printStackTrace();
        }
        if (font == null) {
            System.out.println("getCodeFont -> getFont");
            font = Font.getFont(Font.MONOSPACED);
        }
        return font;
    }
}