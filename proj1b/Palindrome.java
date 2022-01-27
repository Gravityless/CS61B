public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> q = new ArrayDeque<>();
        for (int i=0; i<word.length(); i++) {
            q.addLast(word.charAt(i));
        }
        return q;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> q = wordToDeque(word);
        boolean flag = true;
        while (q.size() > 1) {
            if (q.removeFirst() != q.removeLast()) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> q = wordToDeque(word);
        boolean flag = true;
        while (q.size() > 1) {
            if (!cc.equalChars(q.removeFirst(), q.removeLast())) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
