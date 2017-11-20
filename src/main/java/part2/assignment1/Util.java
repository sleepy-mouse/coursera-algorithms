package part2.assignment1;

import java.net.URL;

/**
 * @author Chris Qiu
 */
class Util {
    static URL getFilePath(String fileName) {
        return Util.class.getResource(String.format("/wordnet/%s", fileName));
    }
}
