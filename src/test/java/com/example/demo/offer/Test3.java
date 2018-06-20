package com.example.demo.offer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
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

    //    给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
    public int[] multiply(int[] A) {

        return null;
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
        if (str == null || pattern == null) {
            return false;
        }
        int strIndex = 0;
        int patternIndex = 0;
        return matchCore(str, strIndex, pattern, patternIndex);
    }

    public boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
        //有效性检验：str到尾，pattern到尾，匹配成功
        if (strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }
        //pattern先到尾，匹配失败
        if (strIndex != str.length && patternIndex == pattern.length) {
            return false;
        }
        //模式第2个是*，且字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)) {
                return matchCore(str, strIndex, pattern, patternIndex + 2)//模式后移2，视为x*匹配0个字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex + 2)//视为模式匹配1个字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex);//*匹配1个，再匹配str中的下一个
            } else {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
        }
        //模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
        if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)) {
            return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
        }
        return false;
    }
}
