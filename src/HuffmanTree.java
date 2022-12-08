public class HuffmanTree
{
    HuffmanNode root;

    public HuffmanTree(HuffmanNode huff)
    {
        root = huff;
    }

    public void printLegend()
    {
        printLegend(root, "");
    }

    private void printLegend(HuffmanNode t, String s)
    {
        if (t.letter.length() > 1)
        {
            printLegend(t.left, s + "0");
            printLegend(t.right, s + "1");
        }

        if (t.letter.length() == 1)
            System.out.println(t.letter + "=" + s);
    }

    public static BinaryHeap<HuffmanNode> legendToHeap(String legend)
    {
        String[] legendArr = legend.split(" "); // ["A", "20", "E", "24"]
        BinaryHeap<HuffmanNode> heap = new BinaryHeap<>();

        for (int i = 0; i <= legendArr.length - 2; i += 2)
            heap.insert(new HuffmanNode(legendArr[i], Double.parseDouble(legendArr[i + 1])));

        return heap;
    }

    public static HuffmanTree createFromHeap(BinaryHeap<HuffmanNode> b)
    {
        return null;
    }

    public static void main(String[] args)
    {
        int num = (int) Math.pow(3, 2);
    }
}
