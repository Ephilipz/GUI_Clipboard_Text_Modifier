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
        Pattern p = Pattern.compile("(.{" + lineLength + "})", Pattern.DOTALL);
        Matcher m = p.matcher(parsedStr);
        parsedStr = m.replaceAll("$1" + "\n");
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
}