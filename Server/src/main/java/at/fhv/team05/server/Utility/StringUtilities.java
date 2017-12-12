package at.fhv.team05.server.Utility;

public class StringUtilities {
    public static boolean containsIgnoreCase(String src, String input) {
        final int length = input.length();
        if (length == 0) {
            return true; // Empty string is contained
        }

        final char firstLo = Character.toLowerCase(input.charAt(0));
        final char firstUp = Character.toUpperCase(input.charAt(0));

        for (int i = src.length() - length; i >= 0; i--) {
            // Quick check before calling the more expensive regionMatches() method:
            final char ch = src.charAt(i);
            if (ch != firstLo && ch != firstUp) {
                continue;
            }

            if (src.regionMatches(true, i, input, 0, length)) {
                return true;
            }
        }

        return false;
    }
}
