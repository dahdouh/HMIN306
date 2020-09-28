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
            com.supanadit.restsuite.logger.LogWriter.out("getDefaultFont", "createFont");
            com.supanadit.restsuite.logger.LogWriter.out("getDefaultFont", "createFont");
            com.supanadit.restsuite.logger.LogWriter.out("getDefaultFont", "getClassLoader");
            com.supanadit.restsuite.logger.LogWriter.out("getDefaultFont", "getClassLoader");
            InputStream inputFile = Main.class.getClassLoader().getResourceAsStream("font/ui/NotoSans-Regular.ttf");
            assert inputFile != null;
            font = Font.createFont(Font.TRUETYPE_FONT, inputFile).deriveFont(13.0F);
        } catch (FontFormatException | IOException e) {
            com.supanadit.restsuite.logger.LogWriter.out("getDefaultFont", "printStackTrace");
            e.printStackTrace();
        }
        if (font == null) {
            com.supanadit.restsuite.logger.LogWriter.out("getDefaultFont", "getFont");
            font = Font.getFont(Font.MONOSPACED);
        }
        return font;
    }

    public static Font getCodeFont() {
        Font font = null;
        try {
            com.supanadit.restsuite.logger.LogWriter.out("getCodeFont", "createFont");
            com.supanadit.restsuite.logger.LogWriter.out("getCodeFont", "createFont");
            com.supanadit.restsuite.logger.LogWriter.out("getCodeFont", "getClassLoader");
            com.supanadit.restsuite.logger.LogWriter.out("getCodeFont", "getClassLoader");
            InputStream inputFile = Main.class.getClassLoader().getResourceAsStream("font/code/JetBrainsMono-Regular.ttf");
            assert inputFile != null;
            font = Font.createFont(Font.TRUETYPE_FONT, inputFile).deriveFont(13.0F);
        } catch (FontFormatException | IOException e) {
            com.supanadit.restsuite.logger.LogWriter.out("getCodeFont", "printStackTrace");
            e.printStackTrace();
        }
        if (font == null) {
            com.supanadit.restsuite.logger.LogWriter.out("getCodeFont", "getFont");
            font = Font.getFont(Font.MONOSPACED);
        }
        return font;
    }
}