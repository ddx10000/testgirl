package com.example.demo.offer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author DongDexuan
 * @date 2018-5-30 17:16
 * @desc
 */
public class Test3 {

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    private TreeNode getTreeNode() {
        TreeNode treeNode = new TreeNode(8);
        treeNode.left = new TreeNode(4);
        treeNode.right = new TreeNode(12);
        treeNode.left.left = new TreeNode(2);
        treeNode.left.right = new TreeNode(6);
        treeNode.right.left = new TreeNode(10);
        treeNode.right.right = new TreeNode(15);
        return treeNode;
    }

    @Test
    public void test() {
        Integer integer = new Integer(2);
        Boolean b = new Boolean(false);
        String str = "" + integer + b;
        System.out.println(str);
        TreeNode treeNode = getTreeNode();
        Test3 test3 = new Test3();
        TreeNode convert = test3.Convert(treeNode);
        System.out.println(convert);
    }

    //    输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return pRootOfTree;
        ArrayList<TreeNode> list = new ArrayList<>();
        addList(pRootOfTree, list);
        TreeNode treeNode = list.get(0);
        treeNode.left = null;
        for (int i = 1; i < list.size(); i++) {
            TreeNode currentNode = list.get(i);
            treeNode.right = currentNode;
            currentNode.left = treeNode;
            treeNode = currentNode;
        }
        treeNode.right = null;
        return list.get(0);
    }

    private void addList(TreeNode pRootOfTree, ArrayList<TreeNode> list) {
        if (pRootOfTree == null) return;
        addList(pRootOfTree.left, list);
        list.add(pRootOfTree);
        addList(pRootOfTree.right, list);
    }

    //    输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
//    输入描述:
//    输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> result = new ArrayList<String>();
        if (str == null || str.length() == 0) {
            return result;
        }

        char[] chars = str.toCharArray();
        TreeSet<String> temp = new TreeSet<>();
        Permutation(chars, 0, temp);
        result.addAll(temp);
        return result;
    }

    public void Permutation(char[] chars, int begin, TreeSet<String> result) {
        if (chars == null || chars.length == 0 || begin < 0 || begin > chars.length - 1) {
            return;
        }

        if (begin == chars.length - 1) {
            result.add(String.valueOf(chars));
        } else {
            for (int i = begin; i < chars.length; i++) {
                swap(chars, begin, i);

                Permutation(chars, begin + 1, result);

                swap(chars, begin, i);
            }
        }
    }

    public void swap(char[] x, int a, int b) {
        char t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    @Test
    public void test3() {
        System.out.println(Permutation("abcde"));
    }

    @Test
    public void test4() {
        int[] array = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(MoreThanHalfNum_Solution(array));
    }

    //    数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
    // 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
    public int MoreThanHalfNum_Solution(int[] array) {
        int start = 0;
        int end = array.length - 1;
        int partition = 0;
        while (end - start > array.length / 2) {
            partition = partition(array, start, end);
            if (partition - start < end - partition) {
                start = partition + 1;
            } else {
                end = partition - 1;
            }
        }
        int sum = 0;
        int value = array[array.length / 2];
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                sum++;
            }
        }
        if (sum > array.length / 2) {
            return value;
        } else return 0;
    }

    private int partition(int[] array, int start, int end) {

        int i = start;
        while (i < end) {
            if (array[i] >= array[end]) {
                i++;
            } else {
                swap(array, start++, i++);
            }
        }
        swap(array, start, end);
        return start;
    }

    public void swap(int[] x, int a, int b) {
        if (a == b) return;
        int t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    @Test
    public void test5() {
        int[] input = {4, 5, 1, 6, 2, 7, 3, 8};
        ArrayList<Integer> integers = GetLeastNumbers_Solution(input, 4);
        System.out.println(integers);
    }

    //    输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input.length < k || k == 0) return list;
        HashSet<Integer> integers = new HashSet<>();
        int value = input[0];
        int valueI = 0;
        for (int i = 0; i < k; i++) {
            boolean isInite = false;
            for (int j = 0; j < input.length; j++) {
                if (integers.contains(j)) continue;
                if (!isInite) {
                    isInite = true;
                    value = input[j];
                    valueI = j;
                    continue;
                }
                if (value > input[j]) {
                    value = input[j];
                    valueI = j;
                }
            }
            list.add(value);
            integers.add(valueI);
        }
        return list;
    }

    @Test
    public void test6() {
        int[] array = {6, -3, -2, 7, -15, 1, 2, 2};
        int i = FindGreatestSumOfSubArray(array);
        System.out.println(i);
    }

    //    HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。你会不会被他忽悠住？(子向量的长度至少是1)
    public int FindGreatestSumOfSubArray(int[] array) {
        int sum = Integer.MIN_VALUE;
        int sumOrg = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            if (sumOrg < 0) {
                sumOrg = value;
            } else {
                sumOrg += value;
            }
            if (sumOrg > sum) {
                sum = sumOrg;
            }
        }
        return sum;
    }

    @Test
    public void test7() {

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MIN_VALUE - 1);
        int i = NumberOf1Between1AndN_Solution(20);
        int i1 = NumberOf1Between1AndN_Solution1(20);
        System.out.println(i);
        System.out.println(i1);
    }

    //    求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数。
    public int NumberOf1Between1AndN_Solution(int n) {

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int value = i;
            while (value > 0) {
                if (value % 10 == 1) {
                    sum++;
                }
                value /= 10;
            }
        }
        return sum;
    }

    int NumberOf1Between1AndN_Solution1(int n) {
        //主要思路：设定整数点（如1、10、100等等）作为位置点i（对应n的各位、十位、百位等等），分别对每个数位上有多少包含1的点进行分析
//根据设定的整数位置，对n进行分割，分为两部分，高位n/i，低位n%i
        //当i表示百位，且百位对应的数>=2,如n=31456,i=100，则a=314,b=56，此时百位为1的次数有a/10+1=32（最高两位0~31），每一次都包含100个连续的点，即共有(a%10+1)*100个点的百位为1
        //当i表示百位，且百位对应的数为1，如n=31156,i=100，则a=311,b=56，此时百位对应的就是1，则共有a%10(最高两位0-30)次是包含100个连续点，当最高两位为31（即a=311），本次只对应局部点00~56，共b+1次，所有点加起来共有（a%10*100）+(b+1)，这些点百位对应为1
//当i表示百位，且百位对应的数为0,如n=31056,i=100，则a=310,b=56，此时百位为1的次数有a/10=31（最高两位0~30）
//综合以上三种情况，当百位对应0或>=2时，有(a+8)/10次包含所有100个点，还有当百位为1(a%10==1)，需要增加局部点b+1
        //之所以补8，是因为当百位为0，则a/10==(a+8)/10，当百位>=2，补8会产生进位位，效果等同于(a/10+1)
        int count = 0;
        long i = 1;
        for (i = 1; i <= n; i *= 10) {
//i表示当前分析的是哪一个数位
            int a = (int) (n / i);
            int b = (int) (n % i);
            int x = a % 10 == 1 ? 1 : 0;
            count = (int) (count + (a + 8) / 10 * i + x * (b + 1));
        }
        return count;
    }

    //    输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
    public String PrintMinNumber(int[] numbers) {
        List<String> strings = Arrays.stream(numbers).mapToObj(value -> "" + value).collect(Collectors.toList());
        Collections.sort(strings, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        return strings.stream().collect(Collectors.joining());
    }

    @Test
    public void test8() {
        int[] ints = {3, 32, 321};
        String s = PrintMinNumber(ints);
        System.out.println(s);
    }

    //    把只包含因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。:::该思路： 我们只用比较3个数：用于乘2的最小的数、用于乘3的最小的数，用于乘5的最小的
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0) {
            return 0;
        }
        int[] Ugly = new int[index];
        int x1 = 0;
        int x2 = 0;
        int x3 = 0;
        Ugly[0] = 1;
        int temp = 0;
        for (int i = 1; i < index; i++) {
            temp = Math.min(Ugly[x1] * 2, Math.min(Ugly[x2] * 3, Ugly[x3] * 5));
            if (temp == Ugly[x1] * 2) x1++;
            if (temp == Ugly[x2] * 3) x2++;
            if (temp == Ugly[x3] * 5) x3++;
            Ugly[i] = temp;
        }
        return Ugly[index - 1];
    }

    @Test
    public void test9() {
        int i = GetUglyNumber_Solution(10);
        System.out.println(i);
    }

    //    在一个字符串(1<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置
    public int FirstNotRepeatingChar(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (str.indexOf(c) == str.lastIndexOf(c)) {
                return i;
            }
        }
        return 0;
    }

    @Test
    public void test10() {
        int[] ints = {1, 2, 3, 4, 5, 6, 7, 0};
        int i = InversePairs(ints);
        System.out.println(i);
    }

    //    在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
    int cnt;

    public int InversePairs(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        cnt = 0;
        mergeSort(array, new int[array.length], 0, array.length - 1);
        return cnt;
    }

    public void mergeSort(int[] array, int[] temp, int start, int end) {
        if (start >= end) return;
        int mid = (start + end) / 2;
        mergeSort(array, temp, start, mid);
        mergeSort(array, temp, mid + 1, end);
        merge(array, temp, start, mid, end);
    }

    public void merge(int[] array, int[] temp, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int k = start;
        while (i <= mid && j <= end) {
            if (array[i] > array[j]) {
                temp[k++] = array[i++];
                cnt += end - j + 1;
                if (cnt >= 1000000007)//数值过大求余
                {
                    cnt %= 1000000007;
                }
            } else {
                temp[k++] = array[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= end) {
            temp[k++] = array[j++];
        }
        for (i = start; i <= end; i++) {
            array[i] = temp[i];
        }
    }

    public int InversePairs2(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int[] copy = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        int count = InversePairsCore(array, copy, 0, array.length - 1);//数值过大求余
        return count;

    }

    private int InversePairsCore(int[] array, int[] copy, int low, int high) {
        if (low == high) {
            return 0;
        }
        int mid = (low + high) >> 1;
        int leftCount = InversePairsCore(array, copy, low, mid) % 1000000007;
        int rightCount = InversePairsCore(array, copy, mid + 1, high) % 1000000007;
        int count = 0;
        int i = mid;
        int j = high;
        int locCopy = high;
        while (i >= low && j > mid) {
            if (array[i] > array[j]) {
                count += j - mid;
                copy[locCopy--] = array[i--];
                if (count >= 1000000007)//数值过大求余
                {
                    count %= 1000000007;
                }
            } else {
                copy[locCopy--] = array[j--];
            }
        }
        for (; i >= low; i--) {
            copy[locCopy--] = array[i];
        }
        for (; j > mid; j--) {
            copy[locCopy--] = array[j];
        }
        for (int s = low; s <= high; s++) {
            array[s] = copy[s];
        }
        return (leftCount + rightCount + count) % 1000000007;
    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    //    输入两个链表，找出它们的第一个公共结点。
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {

        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        ArrayList<ListNode> listNodes1 = new ArrayList<>();
        ArrayList<ListNode> listNodes2 = new ArrayList<>();
        while (pHead1 != null) {
            listNodes1.add(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            listNodes2.add(pHead2);
            pHead2 = pHead2.next;
        }
        ListNode node = null;
        for (int i = listNodes1.size() - 1, j = listNodes2.size() - 1; i >= 0 && j >= 0; i--, j--) {
            if (listNodes1.get(i) != listNodes2.get(j)) {
                return node;
            }
            node = listNodes1.get(i);
        }
        return node;
    }

    @Test
    public void test11() {
        int[] array = {1, 3, 3, 3, 3, 4, 5};
        int i = GetNumberOfK(array, 6);
        System.out.println(i);
    }

    //    统计一个数字在排序数组中出现的次数。
    public int GetNumberOfK(int[] array, int k) {

        int index = findIndex(array, k, 0, array.length - 1);
        if (index == -1) return 0;
        int sum = 1;
        for (int i = index - 1; i >= 0; i--) {
            if (array[i] != k) break;
            sum++;
        }
        for (int i = index + 1; i < array.length; i++) {
            if (array[i] != k) break;
            sum++;
        }
        return sum;

    }

    public int findIndex(int[] array, int k, int start, int end) {
        if (start > end) return -1;
        if (start == end) {
            if (array[start] == k) return start;
            return -1;
        }
        int mid = (start + end) >> 1;
        int currentValue = array[mid];
        if (currentValue == k) return mid;
        if (currentValue > k) {
            return findIndex(array, k, start, mid - 1);
        } else {
            return findIndex(array, k, mid + 1, end);
        }
    }

    //    输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
    public int TreeDepth(TreeNode root) {
        return TreeDepth(root, 0);
    }

    public int TreeDepth(TreeNode root, int length) {
        if (root == null) return length;
        length++;
        int left = TreeDepth(root.left, length);
        int right = TreeDepth(root.right, length);
        return left > right ? left : right;
    }

    //    @Test
//    public void test12(){
//        getTreeNode2();
//    }
    //    输入一棵二叉树，判断该二叉树是否是平衡二叉树。
    public boolean IsBalanced_Solution(TreeNode root) {
        IsBalanced_Solution(root, 0);
        return isBalancedSolution;
    }

    private boolean isBalancedSolution = true;

    public int IsBalanced_Solution(TreeNode root, int length) {
        if (root == null) return length;
        length++;
        int left = IsBalanced_Solution(root.left, length);
        int right = IsBalanced_Solution(root.right, length);
        if (Math.abs(left - right) > 1) {
            isBalancedSolution = false;
        }
        return Math.max(left, right);
    }

    //    一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {

        int eo = EO(array, array.length);
        int k = 1;
        while ((eo & k) == 0) {
            k <<= 1;
        }

        int[] ints1 = new int[array.length];
        int[] ints2 = new int[array.length];
        int x = 0;
        int y = 0;
        for (int i = 0; i < array.length; i++) {
            if ((k & array[i]) != 0) {
                ints1[x++] = array[i];
            } else {
                ints2[y++] = array[i];
            }
        }
        num1[0] = EO(ints1, x);
        num2[0] = EO(ints2, y);
    }

    public int EO(int[] array, int end) {
        int result = 0;
        for (int i = 0; i < end; i++) {
            result ^= array[i];
        }
        return result;
    }

//    小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!

    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        int sumTemp = 0;
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 1; i < sum; i++) {
            integers.add(i);
            while ((sumTemp = sum(integers)) > sum) {
                integers.remove(0);
            }
            if (sumTemp == sum) {
                list.add(integers);
                integers = (ArrayList<Integer>) integers.clone();
            }
        }
        return list;
    }

    private int sum(ArrayList<Integer> integers) {
        int sum = 0;
        for (int i : integers) {
            sum += i;
        }
        return sum;
    }

    //    输入一个递增排序的数组和一个数字S，在数组中查找两个数，是的他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
//    对应每个测试案例，输出两个数，小的先输出。
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        int start = 0;
        int end = array.length - 1;
        while (end > start) {
            int s = array[start] + array[end];
            if (s == sum) {
                list.add(array[start]);
                list.add(array[end]);
                break;
            } else if (s < sum) {
                start++;
            } else {
                end--;
            }
        }
        return list;
    }

    @Test
    public void test12() {
        String string = LeftRotateString("abcXYZdef", 3);
        System.out.println(string);
    }

    //    汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
    public String LeftRotateString(String str, int n) {
        if (str == null || str.length() == 0) return str;
        n %= str.length();
        String substringE = str.substring(0, n);
        String substringS = str.substring(n, str.length());
        return substringS + substringE;
    }

    @Test
    public void test13() {
        String string = ReverseSentence("   a   ab  ");
        System.out.println(string);
    }

    //    牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
    public String ReverseSentence(String str) {
        if (str == null || str.length() == 0) return str;
        String[] split = str.split(" ");
        String result = "";
        int i = str.length() - 1;
        int j = split.length - 1;
        while (i >= 0) {
            if (str.charAt(i) == ' ') {
                result += " ";
                i--;
            } else {
                String s = split[j--];
                result += s;
                i -= s.length();
            }
        }
        return result;
    }

    //    LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何。为了方便起见,你可以认为大小王是0。
    public boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length == 0) return false;
        Arrays.sort(numbers);
        int numZero = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == 0) {
                numZero++;
            } else if ((numbers[i + 1] == numbers[i])) {
                return false;
            } else {
                numZero = numZero - (numbers[i + 1] - numbers[i]) + 1;
            }
        }
        return numZero >= 0;
    }

    @Test
    public void test14() {
        int string = LastRemaining_Solution(5, 3);
        System.out.println(string);
    }

    //    每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
    public int LastRemaining_Solution(int n, int m) {

        if (n == 0 || m == 0) return -1;
        LinkedList<Integer> integers = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            integers.add(i);
        }
        int m_temp = m;
        while (integers.size() > 1) {
            int i = (m_temp - 1) % n;
            integers.remove(i);
            n--;
            m_temp = m + i;
        }
        return integers.get(0);
    }

    @Test
    public void test15() {
        int i = Sum_Solution(2);
        System.out.println(i);
    }

    //    求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
    public int Sum_Solution(int n) {
        int sum = n;
        boolean b = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
        return sum;
    }

    @Test
    public void test16() {
        int i = Add(3, 2);
        System.out.println(i);
    }

    //    写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
    public int Add(int num1, int num2) {
        int sum = num1;
        while (num2 != 0) {
            sum = num1 ^ num2;
            num2 = ((num1 | num2) ^ sum) << 1;
            num1 = sum;
        }
        return sum;
    }

    @Test
    public void test17() {
//        int i1 = Integer.parseInt("13");
//        System.out.println(i1);
        int i = StrToInt("123");
        System.out.println(i);
    }

    //    将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
    public int StrToInt(String str) {

        if (str.length() == 0) {
            return 0;
        }
        char c = str.charAt(0);
        boolean negative = false;
        int result = 0;
        if (c == '-') {
            negative = true;
            if (str.length() == 1) return 0;
        } else if (c == '+') {
            if (str.length() == 1) return 0;
        } else {
            result = c - '0';
            if (result < 0 || result > 9) return 0;
        }
        for (int i = 1; i < str.length(); i++) {
            int i1 = str.charAt(i) - '0';
            if (i1 < 0 || i1 > 9) return 0;
            result *= 10;
            result += i1;
        }
        return negative ? -result : result;
    }

    //    在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2
// Parameters:
//    numbers:     an array of integers
//    length:      the length of array numbers
//    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
//                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
//    这里要特别注意~返回任意重复的一个，赋值duplication[0]
// Return value:       true if the input is valid, and there are some duplications in the array number
//                     otherwise false
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        for (int i = 0; i < length; i++) {
            int index = numbers[i];
            if (index >= length) {
                index -= length;
            }
            if (numbers[index] >= length) {
                duplication[0] = index;
                return true;
            }
            numbers[index] = numbers[index] + length;
        }
        return false;
    }

    @Test
    public void test18() {
        int[] in = {2, 3, 1, 0, 2, 5, 3};
        int[] out = new int[1];
        boolean i = duplicate(in, in.length, out);
        System.out.println(i + " " + out[0]);
    }

    //    给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。剑指的思路：
    //B[i]的值可以看作下图的矩阵中每行的乘积。
    //下三角用连乘可以很容求得，上三角，从下向上也是连乘。
    //因此我们的思路就很清晰了，先算下三角中的连乘，即我们先算出B[i]中的一部分，然后倒过来按上三角中的分布规律，把另一部分也乘进去。

    public int[] multiply(int[] A) {
        int[] B = new int[A.length];
        int[] B1 = new int[A.length];
        int[] B2 = new int[A.length];
        if (A.length == 0) return B;
        B1[0] = 1;
        if (A.length == 1) return B1;
        B2[A.length - 1] = 1;
        for (int i = 1; i < A.length; i++) {
            B1[i] = B1[i - 1] * A[i - 1];
        }
        for (int i = A.length - 2; i >= 0; i--) {
            B2[i] = B2[i + 1] * A[i + 1];
        }
        for (int i = 0; i < A.length; i++) {
            B[i] = B1[i] * B2[i];
        }
        return B;
    }

    public int[] multiply2(int[] A) {
        int length = A.length;
        int[] B = new int[length];
        if (length != 0) {
            B[0] = 1;
            //计算下三角连乘
            for (int i = 1; i < length; i++) {
                B[i] = B[i - 1] * A[i - 1];
            }
            int temp = 1;
            //计算上三角
            for (int j = length - 2; j >= 0; j--) {
                temp *= A[j + 1];
                B[j] *= temp;
            }
        }
        return B;
    }

    @Test
    public void test19() {
        System.out.println('0' + 0);
        System.out.println('9' + 0);
        String str = "a";
        String pattern = "aa";
        boolean match = match(str.toCharArray(), pattern.toCharArray());
//        match = match(pattern.toCharArray(), str.toCharArray());
        System.out.println(match);
    }

    //    请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
//    当模式中的第二个字符不是“*”时：
//            1、如果字符串第一个字符和模式中的第一个字符相匹配，那么字符串和模式都后移一个字符，然后匹配剩余的。
//            2、如果 字符串第一个字符和模式中的第一个字符相不匹配，直接返回false。
//
//    而当模式中的第二个字符是“*”时：
//    如果字符串第一个字符跟模式第一个字符不匹配，则模式后移2个字符，继续匹配。如果字符串第一个字符跟模式第一个字符匹配，可以有3种匹配方式：
//            1、模式后移2字符，相当于x*被忽略；
//            2、字符串后移1字符，模式后移2字符；
//            3、字符串后移1字符，模式不变，即继续匹配字符下一位，因为*可以匹配多位；

    public boolean match(char[] str, char[] pattern) {
        int strIndex = 0;
        int patternIndex = 0;
        return matchCore2(str, strIndex, pattern, patternIndex);
    }

    private boolean matchCore2(char[] str, int strIndex, char[] pattern, int patternIndex) {

        if (strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }
        if (strIndex != str.length && patternIndex == pattern.length) {
            return false;
        }
        if (strIndex > str.length || patternIndex > pattern.length) {
            return false;
        }
        if (strIndex < str.length && charEquals(str[strIndex], pattern[patternIndex])
                && (patternIndex + 1 >= pattern.length || pattern[patternIndex + 1] != '*')) {
            return matchCore2(str, strIndex + 1, pattern, patternIndex + 1);
        }
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            if (strIndex < str.length && !charEquals(str[strIndex], pattern[patternIndex])) {
                return matchCore2(str, strIndex, pattern, patternIndex + 2);
            } else {
                return matchCore2(str, strIndex, pattern, patternIndex + 2) ||
                        matchCore2(str, strIndex + 1, pattern, patternIndex) ||
                        matchCore2(str, strIndex + 1, pattern, patternIndex + 2);
            }
        }
        return false;
    }

    private boolean charEquals(char s, char p) {
        if (s == p) {
            return true;
        } else if (p == '.') {
            return true;
        } else return false;
    }

    //    请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
    boolean hasD = false;
    boolean hasE = false;

    public boolean isNumeric(char[] str) {
        if (str.length == 0) return false;
        if (!isNumeric(str[0]) && str.length < 2 && !(str[0] == '+' || str[0] == '-')) {
            return false;
        }
        for (int i = 1; i < str.length; i++) {
            if (isNumeric(str[i])) {
                continue;
            }
            if (str[i] == '.') {
                if (hasD) {
                    return false;
                } else {
                    hasD = true;
                }
            } else if (str[i] == 'e' || str[i] == 'E') {
                if (hasE) {
                    return false;
                } else {
                    hasE = true;
                    hasD = true;
                    return isNumeric(Arrays.copyOfRange(str, i + 1, str.length));
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isNumeric(char c) {
        return c >= '0' && c <= '9';
    }

    //    请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
//Insert one char from stringstream
    StringBuilder stringBuilder = new StringBuilder();
    int index = 0;

    public void Insert(char ch) {
        stringBuilder.append(ch);
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        for (; index < stringBuilder.length(); ) {
            char c = stringBuilder.charAt(index);
            if (stringBuilder.indexOf(String.valueOf(c)) != stringBuilder.lastIndexOf(String.valueOf(c))) {
                index++;
            } else {
                return c;
            }
        }
        return '#';
    }

    //    一个链表中包含环，请找出该链表的环的入口结点。
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ArrayList<ListNode> listNodes = new ArrayList<>();
        while (pHead != null && !listNodes.contains(pHead)) {
            listNodes.add(pHead);
            pHead = pHead.next;
        }
        return pHead;
    }


    @Test
    public void test20() {
        ListNode node = new ListNode(1);
        node.next = new ListNode(1);
        ListNode next = node.next;
        next.next = new ListNode(1);
        next = next.next;
        next.next = new ListNode(1);
        next = next.next;
        next.next = new ListNode(1);
//        next = next.next;
//        next.next = new ListNode(4);
//        next = next.next;
//        next.next = new ListNode(5);
//        next = next.next;
        ListNode node1 = deleteDuplication(node);
        System.out.println(node1.val);
    }

    //    在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) return pHead;
        if (pHead.val == pHead.next.val && pHead.next.next == null) return null;
        ListNode node = pHead;
        ListNode start = pHead;
        ListNode end = pHead.next;
        while (end != null) {
            if (start.val == end.val) {
                end = end.next;
            } else {
                if (start.next.val != end.val && node.val == start.val) {
                    node = end;
                    start = end;
                    pHead = end;
                    end = end.next;
                } else if (node.val == start.val) {
                    start = end;
                    end = end.next;
                } else if (start.val == start.next.val) {
                    node.next = end;
                    start = end;
                    end = end.next;
                } else {
                    node = node.next;
                    start = end;
                    end = end.next;
                }
            }
        }
        if (start.next != null && start.val == start.next.val) {
            if (node.val == start.val) {
                if (node.val == pHead.val) return null;
                node = null;
            } else {
                node.next = null;
            }
        }
        return pHead;
    }

    public ListNode deleteDuplication2(ListNode pHead) {

        if (pHead == null || pHead.next == null) { // 只有0个或1个结点，则返回
            return pHead;
        }
        if (pHead.val == pHead.next.val) { // 当前结点是重复结点
            ListNode pNode = pHead.next;
            while (pNode != null && pNode.val == pHead.val) {
// 跳过值与当前结点相同的全部结点,找到第一个与当前结点不同的结点
                pNode = pNode.next;
            }
            return deleteDuplication(pNode); // 从第一个与当前结点不同的结点开始递归
        } else { // 当前结点不是重复结点
            pHead.next = deleteDuplication(pHead.next); // 保留当前结点，从下一个结点开始递归
            return pHead;
        }
    }

    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    @Test
    public void test21() {
        TreeLinkNode treeLinkNode = new TreeLinkNode(8);
        treeLinkNode.left = new TreeLinkNode(6);
        treeLinkNode.right = new TreeLinkNode(10);
        treeLinkNode.right.left = new TreeLinkNode(9);
        treeLinkNode.right.right = new TreeLinkNode(11);
        GetNext(treeLinkNode);
    }

    //    给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。

    public TreeLinkNode GetNext(TreeLinkNode pNode) {

        TreeLinkNode root = null;
        TreeLinkNode temp = pNode;
        while (temp.next != null) {
            temp = temp.next;
        }
        root = temp;
        ArrayList<TreeLinkNode> treeLinkNodes = new ArrayList<>();
        centerForEach(root, treeLinkNodes);
        int i = treeLinkNodes.indexOf(pNode);
        if (i == treeLinkNodes.size() - 1) {
            return null;
        }
        return treeLinkNodes.get(i + 1);
    }

    private void centerForEach(TreeLinkNode root, ArrayList<TreeLinkNode> treeLinkNodes) {
        if (root == null) return;
        centerForEach(root.left, treeLinkNodes);
        treeLinkNodes.add(root);
        centerForEach(root.right, treeLinkNodes);
    }

    //    请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) return false;
        return isSym(pRoot.left, pRoot.right);
    }

    private boolean isSym(TreeNode pLeft, TreeNode pRight) {
        if (pLeft == null) return pRight == null;
        if (pRight == null) return false;
        if (pLeft.val != pRight.val) return false;
        return isSym(pLeft.left, pRight.right) && isSym(pLeft.right, pRight.left);
    }

    @Test
    public void test22() {
        TreeNode treeNode = getTreeNode();
        ArrayList<ArrayList<Integer>> print = Print2(treeNode);
        System.out.println(print);
    }

    //    请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。

    /**
     * 大家的实现很多都是将每层的数据存进ArrayList中，偶数层时进行reverse操作，
     * 在海量数据时，这样效率太低了。
     * （我有一次面试，算法考的就是之字形打印二叉树，用了reverse，
     * 直接被鄙视了，面试官说海量数据时效率根本就不行。）
     * <p>
     * 下面的实现：不必将每层的数据存进ArrayList中，偶数层时进行reverse操作，直接按打印顺序存入
     * 思路：利用Java中的LinkedList的底层实现是双向链表的特点。
     * 1)可用做队列,实现树的层次遍历
     * 2)可双向遍历,奇数层时从前向后遍历，偶数层时从后向前遍历
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        if (pRoot == null) {
            return ret;
        }
        ArrayList<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(null);//层分隔符
        queue.addLast(pRoot);
        boolean leftToRight = true;

        while (queue.size() != 1) {
            TreeNode node = queue.removeFirst();
            if (node == null) {//到达层分隔符
                Iterator<TreeNode> iter = null;
                if (leftToRight) {
                    iter = queue.iterator();//从前往后遍历
                } else {
                    iter = queue.descendingIterator();//从后往前遍历
                }
                leftToRight = !leftToRight;
                while (iter.hasNext()) {
                    TreeNode temp = iter.next();
                    list.add(temp.val);
                }
                ret.add(new ArrayList<>(list));
                list.clear();
                queue.addLast(null);//添加层分隔符
                continue;//一定要continue
            }
            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }
        }

        return ret;
    }

    public static ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) return result;
        ArrayList<Integer> integers = new ArrayList<>();
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.addLast(null);
        treeNodes.addLast(pRoot);
        boolean leftRightFlag = true;
        while (treeNodes.size() != 1) {
            TreeNode treeNode = treeNodes.pollFirst();
            if (treeNode == null) {
                Iterator<TreeNode> iterator;
                if (leftRightFlag) {
                    iterator = treeNodes.iterator();
                } else {
                    iterator = treeNodes.descendingIterator();
                }
                leftRightFlag = !leftRightFlag;
                while (iterator.hasNext()) {
                    integers.add(iterator.next().val);
                }
                result.add(new ArrayList<>(integers));
                integers.clear();
                treeNodes.addLast(null);
                continue;
            }
            if (treeNode.left != null) {
                treeNodes.add(treeNode.left);
            }
            if (treeNode.right != null) {
                treeNodes.add(treeNode.right);
            }
        }
        return result;
    }

    //    从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
    ArrayList<ArrayList<Integer>> Print4(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        depth(pRoot, 1, list);
        return list;
    }

    private void depth(TreeNode root, int depth, ArrayList<ArrayList<Integer>> list) {
        if (root == null) return;
        if (depth > list.size())
            list.add(new ArrayList<Integer>());
        list.get(depth - 1).add(root.val);

        depth(root.left, depth + 1, list);
        depth(root.right, depth + 1, list);
    }

    //    请实现两个函数，分别用来序列化和反序列化二叉树
    String Serialize(TreeNode root) {

        if (root == null) return "";
        StringBuilder stringBuilder = new StringBuilder();
        Serialize2Buf(root, stringBuilder);

        return stringBuilder.toString();
    }

    private void Serialize2Buf(TreeNode root, StringBuilder stringBuilder) {
        if (root == null) {
            stringBuilder.append("#,");
            return;
        }
        stringBuilder.append(root.val + ",");
        Serialize2Buf(root.left, stringBuilder);
        Serialize2Buf(root.right, stringBuilder);
    }

    TreeNode Deserialize(String str) {

        if (str.equals("")) return null;
        String[] split = str.split(",");
        return Deserialize2TreeNode(split);
    }

    int treeIndex = -1;

    private TreeNode Deserialize2TreeNode(String[] split) {
        if (++treeIndex == split.length || split[treeIndex].equals("#")) {
            return null;
        }
        int value = Integer.parseInt(split[treeIndex]);
        TreeNode treeNode = new TreeNode(value);
        treeNode.left = Deserialize2TreeNode(split);
        treeNode.right = Deserialize2TreeNode(split);
        return treeNode;
    }

    //    给定一颗二叉搜索树，请找出其中的第k小的结点。例如， 5 / \ 3 7 /\ /\ 2 4 6 8 中，按结点数值大小顺序第三个结点的值为4。
    TreeNode KthNode(TreeNode pRoot, int k) {

        k--;
        if (k < 0) return null;
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        centerTree(pRoot, treeNodes, k);
        if (k >= treeNodes.size()) return null;
        return treeNodes.get(k);
    }

    private void centerTree(TreeNode pRoot, ArrayList<TreeNode> treeNodes, int k) {
        if (pRoot == null) return;
        if (treeNodes.size() > k) return;
        if (pRoot.left != null) {
            centerTree(pRoot.left, treeNodes, k);
        }
        treeNodes.add(pRoot);
        if (pRoot.right != null) {
            centerTree(pRoot.right, treeNodes, k);
        }
    }

    @Test
    public void test23() {
        int[] a = {5, 2, 3, 4, 1, 6, 7, 0, 8};
        for (int i = 0; i < a.length; i++) {
            Insert(a[i]);
            System.out.println(GetMedian());
        }

    }

    //    如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
    LinkedList<Integer> list = new LinkedList<>();

    public void Insert(Integer num) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).compareTo(num) > 0) {
                list.add(i, num);
                return;
            }
        }
        list.add(num);
    }

    public Double GetMedian() {
        int size = list.size();
        if (size == 0) return 0d;
        if ((size & 1) == 1) {
            return Double.valueOf(list.get(size / 2));
        } else {
            return (double) (list.get(size / 2 - 1) + list.get(size / 2)) / 2;
        }
    }

    @Test
    public void test24() {
        int[] a = {2, 3, 4, 2, 6, 2, 5, 1};
        ArrayList<Integer> list = maxInWindows(a, 3);
        System.out.println(list);

    }

    //    给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (size < 1 || size > num.length) return list;
        int start = 0;
        int end = size - 1;
        int maxIndex = findMaxIndex(num, start, end);
        int maxValue = num[maxIndex];
        while (end < num.length) {
            if (maxIndex < start) {
                maxIndex = findMaxIndex(num, start, end);
                maxValue = num[maxIndex];
            }
            if (num[end] > maxValue) {
                maxIndex = end;
                maxValue = num[end];
            }
            list.add(maxValue);
            start++;
            end++;
        }
        return list;
    }

    private int findMaxIndex(int[] num, int start, int end) {
        int index = start;
        int value = num[start];
        for (int i = start; i <= end; i++) {
            if (num[i] > value) {
                index = i;
                value = num[i];
            }
        }
        return index;
    }

    @Test
    public void test25() {
//        "ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS",5,8,"SLHECCEIDEJFGGFIE"
        char[] a = {'a', 'b', 'c', 'e', 's', 'f', 'c', 's', 'a', 'd', 'e', 'e'};
//        char[] b = {'b', 'c', 'c', 'e', 'd'};
        char[] b = {'a', 'b', 'c', 'b'};
        String astr = "ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS";
        String bstr = "SLHECCEIDEJFGGFIE";
//        boolean b1 = hasPath(a, 3, 4, b);
//        System.out.println(b1);
        boolean b2 = hasPath(astr.toCharArray(), 5, 8, bstr.toCharArray());
        System.out.println(b2);

    }

    //    请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 例如 a b c e s f c s a d e e 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        ArrayList<Integer> rowList = new ArrayList<>();
        ArrayList<Integer> colsList = new ArrayList<>();
        char start = str[0];
        char[][] chars = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char matrix1 = matrix[j + i * cols];
                chars[i][j] = matrix1;
                if (matrix1 == start) {
                    rowList.add(i);
                    colsList.add(j);
                }
            }
        }
        boolean flag = false;
        for (int i = 0; i < rowList.size(); i++) {
            char[][] clone = chars.clone();
            for (int k = 0; k < chars.length; k++) {
                clone[k] = chars[k].clone();
            }
            flag = flag || findPath(clone, rowList.get(i), colsList.get(i), str, 0);
            if (flag) break;
        }
        return flag;
    }

    private boolean findPath(char[][] chars, int row, int col, char[] str, int index) {

        if (index == str.length - 1 && chars[row][col] == str[index]) {
            return true;
        } else if (chars[row][col] != str[index]) {
            return false;
        }
        chars[row][col] = '#';
        index++;
        boolean flag = false;
        if (row + 1 < chars.length) {
            flag = flag || findPath(chars, row + 1, col, str, index);
        }
        if (row - 1 >= 0) {
            flag = flag || findPath(chars, row - 1, col, str, index);
        }
        if (col + 1 < chars[0].length) {
            flag = flag || findPath(chars, row, col + 1, str, index);
        }
        if (col - 1 >= 0) {
            flag = flag || findPath(chars, row, col - 1, str, index);
        }
        return flag;
    }

    //    地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
    public int movingCount(int threshold, int rows, int cols) {
        int flag[][] = new int[rows][cols]; //记录是否已经走过
        return helper(0, 0, rows, cols, flag, threshold);
    }

    private int helper(int row, int col, int rows, int cols, int[][] flag, int threshold) {
        if (row < 0 || row >= rows || col < 0 || col >= cols
                || flag[row][col] == 1 || sum(row) + sum(col) > threshold) {
            return 0;
        }
        flag[row][col] = 1;
        return helper(row + 1, col, rows, cols, flag, threshold)
                + helper(row - 1, col, rows, cols, flag, threshold)
                + helper(row, col + 1, rows, cols, flag, threshold)
                + helper(row, col - 1, rows, cols, flag, threshold)
                + 1;
    }

    private int sum(int row) {
        int sum = 0;
        while (row > 0) {
            sum += row % 10;
            row /= 10;
        }
        return sum;
    }


}
