package mainPackage.operations.modifyText;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class alignText {
    private int lineLength; //length of the line to align
    private String originalString; //original string to modify

    public alignText(int lineLength, String originalString) {
        this.lineLength = lineLength;
        this.originalString = originalString;
    }

    public String getFixedString() {
        //remove ending spaces
        String parsedStr = originalString.replaceAll("( )+", " ").trim();
        //add new lines every lineLength
        parsedStr = wrapString(parsedStr, lineLength);
        StringBuilder sb = new StringBuilder();
        Arrays.stream(parsedStr.split("\\r?\\n")).forEach(line -> sb.append(justifyString(line)+"\n"));
        return sb.toString().replaceAll("\\n$","");
    }

    private String justifyString(String line) {
        int paddingSize = lineLength - line.length();
        int paddingStart = line.length() + paddingSize / 2;
        line = String.format("%" + paddingStart + "s", line);
        line = String.format("%-" + lineLength + "s", line);
        return line;
    }

    public String wrapString(String string, int charWrap) {
        int lastBreak = 0;
        int nextBreak = charWrap;
        if (string.length() > charWrap) {
            String setString = "";
            do {
                while (string.charAt(nextBreak) != ' ' && nextBreak > lastBreak) {
                    nextBreak--;
                }
                if (nextBreak == lastBreak) {
                    nextBreak = lastBreak + charWrap;
                }
                setString += string.substring(lastBreak, nextBreak).trim() + "\n";
                lastBreak = nextBreak;
                nextBreak += charWrap;

            } while (nextBreak < string.length());
            setString += string.substring(lastBreak).trim();
            return setString;
        } else {
            return string;
        }
    }
}