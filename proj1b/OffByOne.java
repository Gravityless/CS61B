public class OffByOne implements CharacterComparator{
    @Override
    public boolean equalChars(char x, char y) {
        int t = x - y;
        return t == 1 || t == -1;
    }
}
