import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Ta0uf19
 */
public class Utils {

    public static Coords parseCoords(String string) {
        String regex = "X:(?<x>[0-9]*\\.?[0-9]+),Y:(?<y>[0-9]*\\.?[0-9]+),T:(?<t>[-+]?[0-9]*\\.?[0-9]+)";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        if(matcher.find()) {
            double x = Double.parseDouble(matcher.group("x"));
            double y = Double.parseDouble(matcher.group("y"));
            double theta = Double.parseDouble(matcher.group("t"));

            return new Coords(x, y ,theta);
        }
        else
        {
            return null;
        }
    }
}
