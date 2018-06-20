package com.example.demo.offer;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Test1 {

    public boolean Find(int target, int[][] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == target) {
                    return true;
                } else if (array[i][j] > target) {
                    continue;
                }
            }
        }
        return false;
    }

    public String replaceSpace(StringBuffer str) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(str.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        ArrayList<Integer> objects = new ArrayList<>();
        while (listNode != null) {
            objects.add(listNode.val);
            listNode = listNode.next;
        }
        Collections.reverse(objects);
        return objects;
    }

    /**
     * Definition for binary tree
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

    }

    @Test
    public void test() {

        int[] preorder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inorder = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode treeNode = reConstructBinaryTree(preorder, inorder);
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return ConstructCore2(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public TreeNode ConstructCore2(int[] preOrder, int startPreOrder, int endPreOrder, int[] inOrder, int startInOrder, int endInOrder) {
        int rootValue = preOrder[startPreOrder];
        TreeNode root = new TreeNode(rootValue);
        if (startPreOrder == endPreOrder) {
            return root;
        }
        int rootInOrderI = 0;
        for (int i = startInOrder; i <= endInOrder; i++) {
            if (inOrder[i] == rootValue) {
                rootInOrderI = i;
                break;
            }
        }
        int orderLength = rootInOrderI - startInOrder;
        if (orderLength > 0) {
            root.left = ConstructCore2(preOrder, startPreOrder + 1, startPreOrder + orderLength, inOrder, startInOrder, startInOrder + orderLength - 1);
        }
        if (rootInOrderI < endInOrder) {
            root.right = ConstructCore2(preOrder, startPreOrder + orderLength + 1, endPreOrder, inOrder, startInOrder + orderLength + 1, endInOrder);
        }
        return root;
    }

    public TreeNode ConstructCore(int[] preOrder, int startPreOrder, int endPreOrder, int[] inOrder, int startInOrder, int endInOrder) {
        // 前序遍历序列的第一个数字是根结点的值
        int rootValue = preOrder[startPreOrder];
        TreeNode root = new TreeNode(rootValue);
        if (startPreOrder == endPreOrder) {
            return root;
        }
        // 在中序遍历中找到根结点的值
        int rootInOrder = startInOrder;
        while (rootInOrder <= endInOrder && inOrder[rootInOrder] != rootValue) {
            rootInOrder++;
        }
        int leftLength = rootInOrder - startInOrder;
        int leftPreOrderEnd = startPreOrder + leftLength;
        if (leftLength > 0) {
            // 构建左子树
            root.left = ConstructCore(preOrder, startPreOrder + 1, leftPreOrderEnd, inOrder, startInOrder, rootInOrder - 1);
        }
        if (leftLength < endPreOrder - startPreOrder) {
            // 构建右子树
            root.right = ConstructCore(preOrder, leftPreOrderEnd + 1, endPreOrder, inOrder, rootInOrder + 1, endInOrder);
        }
        return root;
    }

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public int Fibonacci(int n) {

        if (n <= 0) {
            return 0;
        }
        int start1 = 1;
        int start2 = 1;
        for (int i = 1; i < n; i++) {
            int temp = start1 + start2;
            start1 = start2;
            start2 = temp;
        }
        return start2;
    }

    @Test
    public void test2() {
        System.out.println(Fibonacci(0));
        System.out.println(Fibonacci(1));
        System.out.println(Fibonacci(2));
        System.out.println(Fibonacci(3));
        System.out.println(Fibonacci(4));
        System.out.println(Fibonacci(5));
    }

    public int JumpFloorII(int target) {

        return (int) Math.pow(2, target - 1);
    }

    @Test
    public void test3() {
        System.out.println(NumberOf1(1));
        System.out.println(NumberOf1(3));
        System.out.println(NumberOf1(-1));
    }

    public int NumberOf1(int n) {

        int result = 0;
        while (n != 0) {
            result += n & 1;
            n >>>= 1;
        }
        return result;
    }

    @Test
    public void test4() {
        System.out.println(Power(3, 3));
        System.out.println(Power(0.1, 2));
        System.out.println(Power(2, 5));
        System.out.println(Math.pow(0.1, 0.2));
    }

    double Power(double base, int exponent) {
        if (exponent == 0) return 1;
        if (exponent == 1) return base;
        int exponentTemp = exponent / 2;
        return Power(base * base, exponent / 2) * Power(base, exponent % 2);
    }

    @Test
    public void test5() {

        int[] ints = {1, 4, 6, 8, 2, 9, 3, 5};
        reOrderArray(ints);
        System.out.println(Arrays.toString(ints));
    }

    public void reOrderArray(int[] array) {
        ArrayList<Integer> odd = new ArrayList<>();
        ArrayList<Integer> even = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                odd.add(array[i]);
            } else {
                even.add(array[i]);
            }
        }
        int i = 0;
        for (int value : odd) {
            array[i++] = value;
        }
        for (int value : even) {
            array[i++] = value;
        }
    }

    public ListNode FindKthToTail(ListNode head, int k) {

        if (k <= 0) return null;
        ListNode kNode = head;
        int n = 1;
        while (head != null && head.next != null) {
            head = head.next;
            n++;
            if (n > k) {
                kNode = kNode.next;
            }
        }
        return n >= k ? kNode : null;

    }

    public ListNode ReverseList(ListNode head) {

        if (head == null || head.next == null) return head;
        ListNode tail = head;
        head = head.next;
        tail.next = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = tail;
            tail = head;
            head = temp;
        }
        return tail;
    }

    public ListNode Merge(ListNode list1, ListNode list2) {

        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode newHead = null;
        if (list1.val < list2.val) {
            newHead = list1;
            list1 = list1.next;
        } else {
            newHead = list2;
            list2 = list2.next;
        }
        ListNode head = newHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                newHead.next = list1;
                list1 = list1.next;
            } else {
                newHead.next = list2;
                list2 = list2.next;
            }
            newHead = newHead.next;
        }
        if (list1 != null) {
            newHead.next = list1;
        } else {
            newHead.next = list2;
        }
        return head;
    }

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        if (isSubtree(root1, root2)) {
            return true;
        }
        boolean leftIs = HasSubtree(root1.left, root2);
        boolean rightIs = HasSubtree(root1.right, root2);
        return leftIs || rightIs;
    }

    public boolean isSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        } else if (root1 == null) {
            return false;
        }
        if (root1.val == root2.val) {
            boolean leftIs = isSubtree(root1.left, root2.left);
            boolean rightIs = isSubtree(root1.right, root2.right);
            return leftIs && rightIs;
        }
        return false;
    }

    @Test
    public void test6() {

        TreeNode treeNode = getTreeNode();

        TreeNode treeNode2 = new TreeNode(8);
        treeNode2.left = new TreeNode(9);
        treeNode2.right = new TreeNode(2);

        System.out.println(HasSubtree(treeNode, treeNode2));

    }

    private TreeNode getTreeNode() {
        TreeNode treeNode = new TreeNode(8);
        treeNode.left = new TreeNode(8);
        treeNode.right = new TreeNode(7);
        treeNode.left.left = new TreeNode(9);
        treeNode.left.right = new TreeNode(2);
        return treeNode;
    }
    private TreeNode getTreeNode2() {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(4);
        treeNode.left.left = new TreeNode(3);
        treeNode.left.left.left = new TreeNode(2);
        treeNode.left.left.left.left = new TreeNode(1);
        return treeNode;
    }

    private TreeNode getTreeNode3() {
        TreeNode treeNode = new TreeNode(10);
        treeNode.left = new TreeNode(5);
        treeNode.right = new TreeNode(12);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(7);
        return treeNode;
    }

    public static List<TreeNode> list = new ArrayList<>();

    public void createTree(int[] array) {
        for (int i = 0; i < array.length; i++) {
            TreeNode node = new TreeNode(array[i]);    //创建结点，每一个结点的左结点和右结点为null
            list.add(node); // list中存着每一个结点
        }
        // 构建二叉树
        if (list.size() > 0) {
            for (int i = 0; i < array.length / 2 - 1; i++) {       // i表示的是根节点的索引，从0开始
                if (list.get(2 * i + 1) != null) {
                    // 左结点
                    list.get(i).left = list.get(2 * i + 1);
                }
                if (list.get(2 * i + 2) != null) {
                    // 右结点
                    list.get(i).right = list.get(2 * i + 2);
                }
            }
            // 判断最后一个根结点：因为最后一个根结点可能没有右结点，所以单独拿出来处理
            int lastIndex = array.length / 2 - 1;
            // 左结点
            list.get(lastIndex).left = list.get(lastIndex * 2 + 1);
            // 右结点，如果数组的长度为奇数才有右结点
            if (array.length % 2 == 1) {
                list.get(lastIndex).right = list.get(lastIndex * 2 + 2);
            }
        }
    }

    public static void print(TreeNode node) {
        if (node != null) {
            System.out.print(node.val + " ");
            print(node.left);
            print(node.right);
        }
    }

    @Test
    public void test7() throws UnsupportedEncodingException {

//        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode = getTreeNode2();
        System.out.println(PrintFromTopToBottom(treeNode));
    }

    private ArrayList<TreeNode> list2 = new ArrayList<>();
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        List<TreeNode> list2 = new ArrayList<>();
        list2.add(root);
        int i = 0;
        TopToBottom(root,list2, i);
        list2.forEach(treeNode -> {
            if (treeNode != null){
                list.add(treeNode.val);
            }
        });
        return list;
    }

    private int TopToBottom(TreeNode root,List<TreeNode> list, int i) {
        if (root == null || (root.right == null && root.left == null)){
            return i;
        }
        list.add(root.left);
        list.add(root.right);
        while ((i = TopToBottom(list.get(++i),list, i)) < list.size() - 1){

        }
        return i;
    }


    public void Mirror(TreeNode root) {
        if (root == null || (root.right == null && root.left == null)){
            return;
        }
        list2.add(root);
        int i = 0;
        MirrorTree(root, i);
        TreeNode root1 = root;
    }

    private int MirrorTree(TreeNode root, int i) {
        if (root == null || (root.right == null && root.left == null)){
            return i;
        }
        TreeNode treeNode = root.left;
        root.left = root.right;
        root.right = treeNode;
        list2.add(root.left);
        list2.add(root.right);
        while ((i = MirrorTree(list2.get(++i), i)) < list2.size() - 1){

        }
        return i;
    }
    @Test
    public void test8() throws UnsupportedEncodingException {

        TreeNode treeNode = getTreeNode();
        Mirror(treeNode);
    }

    @Test
    public void test9() throws UnsupportedEncodingException {

//        int[][] ints = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
//        int[][] ints = {{1},{2},{3},{4},{5}};
        int[][] ints = {{1,2},{3,4},{5,6},{7,8},{9,10}};
        ArrayList<Integer> integers = printMatrix(ints);
        System.out.println(integers);
    }

    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> integers = new ArrayList<>();
        if (matrix == null)return integers;
        int rowS = 0;
        int colS = 0;
        int rowE = matrix.length - 1;
        int colE = matrix[0].length - 1;
        while (rowS <= rowE && colS <= colE && rowE >= 0 && colE >= 0){
            addRow(integers,matrix, rowS, colS, colE);
            addCol(integers,matrix, rowS + 1, colE, rowE);
            if (rowS != rowE)
            addRevRow(integers,matrix, rowE, colS, colE - 1);
            if (colE != colS)
            addRevCol(integers,matrix, rowS + 1, colS, rowE - 1);
            rowS ++;
            rowE --;
            colS ++;
            colE --;
        }
        return integers;

    }

    private void addRow(ArrayList<Integer> integers,int [][] matrix, int rowS, int colS, int colE) {
        for (;colS <= colE && rowS >= 0; colS++){
            integers.add(matrix[rowS][colS]);
        }
    }
    private void addCol(ArrayList<Integer> integers,int [][] matrix, int rowS, int colS, int rowE) {
        for (;rowS <= rowE && colS >= 0; rowS++){
            integers.add(matrix[rowS][colS]);
        }
    }
    private void addRevRow(ArrayList<Integer> integers,int [][] matrix, int rowS, int colS, int colE) {
        for (;colS <= colE && rowS >= 0; colE--){
            integers.add(matrix[rowS][colE]);
        }
    }
    private void addRevCol(ArrayList<Integer> integers,int [][] matrix, int rowS, int colS, int rowE) {
        for (;rowS <= rowE && colS >= 0; rowE--){
            integers.add(matrix[rowE][colS]);
        }
    }


    public boolean IsPopOrder(int [] pushA,int [] popA) {

        if (pushA.length != popA.length)return false;
        int i = 0;
        int j = 0;
        Stack<Integer> stack = new Stack<>();
        while (i <= pushA.length && j < popA.length){
            if (stack.isEmpty() && i < pushA.length){
                stack.push(pushA[i++]);
            }
            if (popA[j] == stack.peek()){
                stack.pop();
                j++;
                continue;
            }
            while (popA[j] != stack.peek() && i < pushA.length){
                stack.push(pushA[i++]);
            }
            if (popA[j] != stack.peek()){
                return false;
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void test10(){
        int[] pushA = {1,2,3,4,5};
        int[] popA = {4,5,3,2,1};
        System.out.println(IsPopOrder(pushA,popA));
    }

    public boolean VerifySquenceOfBST(int [] sequence) {

        if (sequence.length == 0)return false;
        int start = 0;
        int end = sequence.length - 1;
        return VerifySquenceOfBST(sequence, start, end);
    }

    private boolean VerifySquenceOfBST(int[] sequence, int start, int end) {

        int leftEnd = end - 1;
        int root = sequence[end];
        for (int i = start; i < end; i++){
            if (sequence[i] > root){
                leftEnd = i - 1;
                break;
            }
        }
        for (int i = leftEnd + 1; i < end; i++){
            if (sequence[i] < root){
                return false;
            }
        }
        boolean leftIs = true;
        boolean rightIs = true;
        if (leftEnd > start){
            leftIs = VerifySquenceOfBST(sequence, start, leftEnd);
        }
        if (leftEnd + 1 < end - 1){
            rightIs = VerifySquenceOfBST(sequence, leftEnd + 1, end - 1);
        }
        return leftIs && rightIs;
    }

    @Test
    public void test11(){
        int[] a = {1,2,3,4,5};
//        int[] a = {4,8,6,12,16,14,10};
        System.out.println(VerifySquenceOfBST(a));
    }

    private ArrayList<ArrayList<Integer>> listArrayList = new ArrayList<>();
    private ArrayList<Integer> list1 = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {

        FindPathTemp(root,target);
        return listArrayList;
    }

    private void FindPathTemp(TreeNode root, int target) {
        if (root == null || root.val > target){
            return;
        }
        list1.add(root.val);
        int sum = 0;
        for (int i : list1){
            sum += i;
        }
        if (sum == target){
            if (root.right == null && root.left == null){
                listArrayList.add(list1);
                list1 = new ArrayList<>(list1.subList(0,list1.size() - 1));
            }
            return;
        }else if (sum > target){
            return;
        }
        FindPath(root.left,target);
        FindPath(root.right,target);
        list1.remove(list1.size() - 1);
    }

    @Test
    public void test12(){

        TreeNode treeNode3 = getTreeNode3();
        System.out.println(FindPath(treeNode3, 22));
    }

    @Test
    public void test13(){

        TreeNode treeNode3 = getTreeNode3();
        System.out.println(FindPath(treeNode3, 22));
    }

    public ArrayList<ArrayList<Integer>> FindPath2(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> arr=new ArrayList<ArrayList<Integer>>();
        if(root==null)
            return arr;
        ArrayList<Integer> a1=new ArrayList<Integer>();
        int sum=0;
        pa(root,target,arr,a1,sum);
        return arr;
    }
    public void pa(TreeNode root,int target,ArrayList<ArrayList<Integer>> arr, ArrayList<Integer> a1,int sum){
        if(root==null)
            return ;
        sum+=root.val;
        if(root.left==null&&root.right==null){
            if(sum==target)
            { a1.add(root.val);
                arr.add(new ArrayList<Integer>(a1));
                a1.remove(a1.size()-1);

            }
            return ;
        }
        a1.add(root.val);
        pa(root.left,target,arr,a1,sum);
        pa(root.right,target,arr,a1,sum);
        a1.remove(a1.size()-1);

    }

    class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    public RandomListNode Clone(RandomListNode pHead)
    {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode pHead1 = pHead;
        while (pHead1 != null){
            RandomListNode randomListNode = new RandomListNode(pHead1.label);
            map.put(pHead1, randomListNode);
            pHead1 = pHead1.next;
        }
        pHead1 = pHead;
        RandomListNode temp = null;
        while (pHead1 != null){
            temp = map.get(pHead1);
            temp.next = map.get(pHead1.next);
            temp.random = map.get(pHead1.random);
            pHead1 = pHead1.next;
        }
        return map.get(pHead);
    }
}
