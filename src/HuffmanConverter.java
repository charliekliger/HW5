import java.awt.*;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class HuffmanConverter
{
    public static final int BYTE_SIZE = 8;
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

    public void treeToCode()
    {
        treeToCode(huffmantree.root, "");
    }

    private void treeToCode(HuffmanNode t, String s)
    {
        if (t.letter.length() > 1)
        {
            treeToCode(t.left, s + "0");
            treeToCode(t.right, s + "1");
        }

        if (t.letter.length() == 1)
            code[t.letter.toCharArray()[0]] = s;
    }

    public String encodeMessage()
    {
        StringBuilder encodedMessage = new StringBuilder();

        for (char c : contents.toCharArray())
            encodedMessage.append(code[c]);

        return encodedMessage.toString();
    }

    public String decodedMessage(String encodedStr)
    {
        StringBuilder decodedStr = new StringBuilder();

        HuffmanNode curr = huffmantree.root;
        for (char c : encodedStr.toCharArray())
        {
            if (c == '0')
                curr = curr.left;
            else
                curr = curr.right;

            if (curr.left == null && curr.right == null)
            {
                decodedStr.append(curr.letter);
                curr = huffmantree.root;
            }
        }

        return decodedStr.toString();
    }

    public static void main(String[] args)
    {
        String filename = args[0];
        String contents = readContents(filename);
        int decodedBytes = contents.length() * BYTE_SIZE;


        HuffmanConverter huffmanConverter = new HuffmanConverter(contents);
        huffmanConverter.recordFrequencies();
        huffmanConverter.recordUniqueChars();
        huffmanConverter.frequenciesToTree();
        huffmanConverter.treeToCode();
        String encodedStr = huffmanConverter.encodeMessage();
        int encodedBytes = encodedStr.length();

        System.out.println("\nHuffman Encoding:");
        System.out.println(encodedStr);

        System.out.println("\nMessage size in ASCII encoding: " + decodedBytes);
        System.out.println("Message size in Huffman coding: " + encodedBytes);

        String decodedStr = huffmanConverter.decodedMessage(encodedStr);
        System.out.println("\n" + decodedStr);

    }
}
