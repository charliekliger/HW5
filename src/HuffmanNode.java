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
    }


    @Override
    public int compareTo(Object o)
    {
        return 0;
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
