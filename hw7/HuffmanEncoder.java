import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> freqTable = new HashMap<>();
        int[] freq = new int[256];
        for (char ch : inputSymbols) {
            freq[(int) ch]++;
        }
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0)
                freqTable.put((char) i, freq[i]);
        }
        return freqTable;
    }
    public static void main(String[] args) {
        char[] input = FileUtils.readFile("watermelonsugar.txt");
        Map<Character, Integer> freqTable = buildFrequencyTable(input);
        BinaryTrie trie = new BinaryTrie(freqTable);

        ObjectWriter ow = new ObjectWriter("watermelonsugarEncode.txt");
        ow.writeObject(trie);

        Map<Character, BitSequence> lut = trie.buildLookupTable();
        List<BitSequence> bitSequenceList = new ArrayList<>();
        for (char ch : input) {
            bitSequenceList.add(lut.get(ch));
        }
        BitSequence bits = BitSequence.assemble(bitSequenceList);
        ow.writeObject(bits);
    }
}