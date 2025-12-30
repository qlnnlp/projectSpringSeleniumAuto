package exceptions;

public class PathForThePageNotFounded extends RuntimeException {
    public  PathForThePageNotFounded() {
        super("Path for the page is not founded");
    }
}
