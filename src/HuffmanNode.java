public class HuffmanNode implements Comparable
{
    public String letter;
    public Double frequency;
    public HuffmanNode left, right;

    public HuffmanNode(String letter, Double frequency)
    {
        this.letter = letter;
        this.frequency = frequency;
        left = right = null;
    }

    public HuffmanNode(HuffmanNode left, HuffmanNode right)
    {
        this.left = left;
        this.right = right;
        letter = left.letter + right.letter;
        frequency = left.frequency + right.frequency;
    }

    @Override
    public int compareTo(Object o)
    {
        HuffmanNode huff = (HuffmanNode) o;
        return frequency.compareTo(huff.frequency);
    }

    @Override
    public String toString()
    {
        return "<" + letter + ", " + frequency + ">";
    }
}
