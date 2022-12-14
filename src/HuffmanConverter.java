import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class HuffmanConverter
{
    public static final int NUMBER_OF_CHARACTERS = 256;
    private String contents;
    private HuffmanTree huffmantree;
    private int count[];
    private String code[];
    private int uniqueChars = 0;

    public HuffmanConverter(String input)
    {
        contents = input;
        count = new int[NUMBER_OF_CHARACTERS];
        code = new String[NUMBER_OF_CHARACTERS];
    }

    public void recordFrequencies()
    {
        for (char c : contents.toCharArray())
            count[c]++;
    }

    public void recordUniqueChars()
    {
        for (int i : count)
            if (i == 1)
                uniqueChars++;
    }

    public void frequenciesToTree()
    {
        BinaryHeap<HuffmanNode> heap = new BinaryHeap<>();

        for (int i = 0; i < count.length; i++)
            if (count[i] > 0)
                heap.insert(new HuffmanNode(String.valueOf((char) i), (double) count[i]));

        heap.printHeap();
        huffmantree = HuffmanTree.createFromHeap(heap);
        huffmantree.printLegend();
    }

    public static String readContents(String filename)
    {
        StringBuilder contents = new StringBuilder(); // O(n)

        try
        {
            Scanner scanner = new Scanner(new FileReader(filename));
            scanner.useDelimiter("\\z");
            while (scanner.hasNext())
                contents.append(scanner.next());
            scanner.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return contents.toString();
    }

    public static void main(String[] args)
    {
        String filename = args[0];
        String contents = readContents(filename);
        HuffmanConverter huffmanConverter = new HuffmanConverter(contents);
        huffmanConverter.recordFrequencies();
        huffmanConverter.recordUniqueChars();
        huffmanConverter.frequenciesToTree();
    }
}
