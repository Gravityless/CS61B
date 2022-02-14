public class HuffmanDecoder {
    public static void main(String[] args) {
        ObjectReader or = new ObjectReader("watermelonsugarEncode.txt");
        BinaryTrie trie = (BinaryTrie) or.readObject();
        BitSequence bits = (BitSequence) or.readObject();
        StringBuilder stringBuilder = new StringBuilder();

        while (bits.length() > 0) {
            if (bits.length() > 20) {
                BitSequence bitsHead = bits.firstNBits(20);
                Match node = trie.longestPrefixMatch(bitsHead);
                stringBuilder.append(node.getSymbol());
                bits = bits.allButFirstNBits(node.getSequence().length());
            }
            Match node = trie.longestPrefixMatch(bits);
            stringBuilder.append(node.getSymbol());
            bits = bits.allButFirstNBits(node.getSequence().length());
        }

        FileUtils.writeCharArray("watermelonsugarDecode.txt", stringBuilder.toString().toCharArray());
    }
}