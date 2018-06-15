package util;

//import org.intelligentjava.algos.trees.TreeAVL.Node;
//import org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node;
import org.junit.Assert;
import org.junit.Test;
import util.TreeAVL.Node;

/**
 * Tests for AVL tree.
 * @author Ignas Lelys
 * @created Jun 29, 2011
 *
 */
public class TreeAVLTest{
    @Test
    public void testDelete() {
        TreeAVL tree = new TreeAVL();
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);
        tree.insert(23);
        Assert.assertEquals(5, tree.size());//4
        tree.delete(15); // raiz is now unbalanced rotation performed
        Assert.assertEquals(3, tree.size());
        Assert.assertEquals(tree.raiz.getDados(), (Integer)23); // new raiz
        Assert.assertEquals(1, tree.altura((Node)tree.raiz)); // new raiz
        Assert.assertEquals(tree.raiz.getEsquerda().getDados(), (Integer)20);
        Assert.assertEquals(tree.raiz.getDireita().getDados(), (Integer)25);
        
        //testTreeBSTProperties(tree.raiz);
    }
    
    @Test
    public void testInsert() {
        TreeAVL tree = new TreeAVL();
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);
        tree.insert(22);
        tree.insert(21);
        Assert.assertEquals(8, tree.size());//5
        Assert.assertEquals(20, (int) tree.raiz.getDados());
        Assert.assertEquals(15, (int) tree.raiz.getEsquerda().getDados());
        Node rightSubtree = (Node) tree.raiz.getDireita();
        // rotation performed and height+balance updated
        Assert.assertEquals(22, (int) rightSubtree.getDados());
        Assert.assertEquals(1, (int) tree.altura(rightSubtree));
        Assert.assertEquals(25, (int) rightSubtree.getDireita().getDados());
        Assert.assertEquals(0, ((int) tree.altura(rightSubtree.getDireita())));
        Assert.assertEquals(21, (int) rightSubtree.getEsquerda().getDados());
        Assert.assertEquals(0, ((int) tree.altura(rightSubtree.getEsquerda())));
        
        //testTreeBSTProperties(tree.raiz);
    }

    @Test
    public void testRotateLeft() {
        Node raiz = new Node(4, null, null, null);
        Node rightChild = new Node(6, raiz, null, null);
        raiz.setDireita(rightChild);
        Node leftGrandChild = new Node(5, rightChild, null, null);
        Node rightGrandChild = new Node(7, rightChild, null, null);
        rightChild.setEsquerda(leftGrandChild);
        rightChild.setDireita(rightGrandChild);

        TreeAVL tree = new TreeAVL();
        Node rotated = tree.rotacaoEsquerda(raiz);

        Assert.assertEquals(6, (int) rotated.getDados());
        Assert.assertEquals(4, (int) rotated.getEsquerda().getDados());
        Assert.assertEquals(7, (int) rotated.getDireita().getDados());
        Assert.assertEquals(5, (int) rotated.getEsquerda().getDireita().getDados());

        Assert.assertNull(rotated.getPai());
        Assert.assertEquals(rotated.getEsquerda().getPai().getDados(), rotated.getDados());
        Assert.assertEquals(rotated.getDireita().getPai().getDados(), rotated.getDados());
        Assert.assertEquals(rotated.getEsquerda().getDireita().getPai().getDados(), rotated.getEsquerda().getDados());
    }

    @Test
    public void testRotateRight() {
        Node raiz = new Node(8, null, null, null);
        Node leftChild = new Node(6, raiz, null, null);
        raiz.setEsquerda(leftChild);
        Node leftGrandChild = new Node(5, leftChild, null, null);
        Node rightGrandChild = new Node(7, leftChild, null, null);
        leftChild.setEsquerda(leftGrandChild);
        leftChild.setDireita(rightGrandChild);

        TreeAVL tree = new TreeAVL();
        Node rotated = tree.rotacaoDireita(raiz);

        Assert.assertEquals(6, (int) rotated.getDados());
        Assert.assertEquals(5, (int) rotated.getEsquerda().getDados());
        Assert.assertEquals(8, (int) rotated.getDireita().getDados());
        Assert.assertEquals(7, (int) rotated.getDireita().getEsquerda().getDados());

        Assert.assertNull(rotated.getPai());
        Assert.assertEquals(rotated.getEsquerda().getPai().getDados(), rotated.getDados());
        Assert.assertEquals(rotated.getDireita().getPai().getDados(), rotated.getDados());
        Assert.assertEquals(rotated.getDireita().getEsquerda().getPai().getDados(), rotated.getDireita().getDados());
    }

    @Test
    public void testDoubleRotateRightLeft() {
        Node raiz = new Node(5, null, null, null);
        Node rightChild = new Node(8, raiz, null, null);
        raiz.setDireita(rightChild);
        Node leftGrandChild = new Node(7, rightChild, null, null);
        Node rightGrandChild = new Node(10, rightChild, null, null);
        rightChild.setEsquerda(leftGrandChild);
        rightChild.setDireita(rightGrandChild);

        TreeAVL tree = new TreeAVL();
        Node rotated = tree.duplaRotacaoDireitaEsquerda(raiz);

        Assert.assertEquals(7, (int) rotated.getDados());
        Assert.assertEquals(5, (int) rotated.getEsquerda().getDados());
        Assert.assertEquals(8, (int) rotated.getDireita().getDados());
        Assert.assertEquals(10, (int) rotated.getDireita().getDireita().getDados());

        Assert.assertNull(rotated.getPai());
        Assert.assertEquals(rotated.getEsquerda().getPai().getDados(), rotated.getDados());
        Assert.assertEquals(rotated.getDireita().getPai().getDados(), rotated.getDados());
        Assert.assertEquals(rotated.getDireita().getDireita().getPai().getDados(), rotated.getDireita().getDados());
    }

    @Test
    public void testDoubleRotateLeftRight() {
        Node raiz = new Node(5, null, null, null);
        Node leftChild = new Node(3, raiz, null, null);
        raiz.setEsquerda(leftChild);
        Node leftGrandChild = new Node(1, leftChild, null, null);
        Node rightGrandChild = new Node(4, leftChild, null, null);
        leftChild.setEsquerda(leftGrandChild);
        leftChild.setDireita(rightGrandChild);

        TreeAVL tree = new TreeAVL();
        Node rotated = tree.duplaRotacaoEsquerdaDireita(raiz);

        Assert.assertEquals(4, (int) rotated.getDados());
        Assert.assertEquals(3, (int) rotated.getEsquerda().getDados());
        Assert.assertEquals(5, (int) rotated.getDireita().getDados());
        Assert.assertEquals(1, (int) rotated.getEsquerda().getEsquerda().getDados());

        Assert.assertNull(rotated.getPai());
        Assert.assertEquals(rotated.getEsquerda().getPai().getDados(), rotated.getDados());
        Assert.assertEquals(rotated.getDireita().getPai().getDados(), rotated.getDados());
        Assert.assertEquals(rotated.getEsquerda().getEsquerda().getPai().getDados(), rotated.getEsquerda().getDados());
    }
}