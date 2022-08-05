package com.demo.algorithm;

public class BinaryTreeTest {

    /**
     * 前序遍历 根左右
     * @param root
     */
    static void pre(BinaryNode root){
        //根左右
        System.out.println(root.val);
        if(root.left != null){
            pre(root.left);
        }
        if(root.right != null){
            pre(root.right);
        }
    }

    /**
     * 中序遍历 左根右
     * 二叉搜索树的中序遍历正好就是从小到大排序了
     * @param root
     */
    static void in(BinaryNode root){
        if(root.left != null){
            in(root.left);
        }
        System.out.println(root.val);
        if(root.right != null){
            in(root.right);
        }
    }

    /**
     * 后序遍历 左右根
     * @param root
     */
    static void pos(BinaryNode root){
        if(root.left != null){
            pos(root.left);
        }
        if(root.right != null){
            pos(root.right);
        }
        System.out.println(root.val);
    }

    /**
     * 查找某个节点
     * @param root
     * @param v
     * @return
     */
    static BinaryNode find(BinaryNode root,int v){
        while (root != null){
            if(v == root.val){
                return root;
            }else if(v > root.val){
                root = root.right;
            }else{
                root = root.left;
            }
        }
        return null;
    }

    /**
     * 二叉搜索树的新增节点（新增肯定是成为叶子节点）
     * @param root
     * @param v
     */
    static void insert(BinaryNode root,int v){
        if(root == null){
            root = new BinaryNode(v);
            return;
        }
        if(v >= root.val){
            if(root.right != null){
                insert(root.right,v);
            }else{
                BinaryNode node = new BinaryNode(v);
                root.right = node;
                node.parent = root;
            }
        }else{
            if(root.left != null){
                insert(root.left,v);
            }else{
                BinaryNode node = new BinaryNode(v);
                root.left = node;
                node.parent = root;
            }
        }

    }

    /**
     * 查找node的后继节点，就是找右边子树最小的那个节点
     * 先往右走一步，然后一直往左找，找到最后的那个节点就是后继节点
     * @param node
     * @return
     */
    static BinaryNode successor(BinaryNode node){
        if(node.right == null){
            //没有右边则没有后继节点
            return null;
        }else{
            BinaryNode cur = node.right;
            while(cur.left != null){
                cur = cur.left;
            }
            return cur;
        }
    }

    /**
     * 查找前驱节点，就是找左边子树最大的那个节点
     * 先往左边走一步，然后一直往右走
     * @param node
     * @return
     */
    static BinaryNode precessor(BinaryNode node){
        if(node.left == null){
            //没有左边则没有前驱节点
            return null;
        }else{
            BinaryNode cur = node.left;
            while(cur.right != null){
                cur = cur.right;
            }
            return cur;
        }
    }

    /**
     * 删除节点，分3种情况（度=0,1,2）
     * @param root
     * @param v
     * @return
     */
    static BinaryNode delete(BinaryNode root,int v){
        BinaryNode delNode = find(root,v);
        if(delNode == null){
            //没找到
            System.out.println("树中没找到该删的节点");
            return root;
        }

            if (delNode.left == null && delNode.right == null) {
                if(delNode.parent == null){
                    //根节点，直接为空
                    return null;
                }else{
                    //节点度为0，叶子节点，直接删
                    if ( delNode.val < delNode.parent.val) {
                        delNode.parent.left = null;
                    } else {
                        delNode.parent.right = null;
                    }
                }
            } else if (delNode.left == null || delNode.right == null) {
                //节点度为1，将那个子节点提上来
                if ( delNode.val < delNode.parent.val) {
                    delNode.parent.left = delNode.left == null ? delNode.right:delNode.left;
                    delNode.parent.left.parent = delNode.parent;
                } else {
                    delNode.parent.right = delNode.left == null ? delNode.right:delNode.left;
                    delNode.parent.right.parent = delNode.parent;
                }

            } else {
                //节点度为2，找到前驱节点或后继节点提上来（这俩节点肯定是度<2的节点）,这里使用后继节点
                BinaryNode successor = successor(delNode);
                //开始指针变化
                //1、先将successor.parent和successor.right相互指定
                successor.parent.left = successor.right;
                if(successor.right != null){
                    successor.right.parent = successor.parent;
                }
                //2、再将successor的各种指针指向删除节点的对应指针指向的位置
                successor.parent = delNode.parent;
                successor.left = delNode.left;
                successor.right = delNode.right;
                //3、然后将原来指向删除节点的指针指向successor
                if(delNode.parent != null && delNode.val<delNode.parent.val){
                    delNode.parent.left = successor;
                }else if(delNode.parent != null && delNode.val > delNode.parent.val){
                    delNode.parent.right = successor;
                }
                delNode.left.parent = successor;
                delNode.right.parent = successor;
                //4、最后将删除节点各种指针置空
                if(delNode.parent == null){
                    //删的是根节点
                    delNode.parent = null;
                    delNode.left = null;
                    delNode.right = null;
                    return successor;
                }else{
                    //删的是普通节点
                    delNode.parent = null;
                    delNode.left = null;
                    delNode.right = null;
                }
            }
        return root;
    }

    /**
     * 模拟红黑树左旋（不管颜色）
     * @return
     */
    static BinaryNode rotateLeft(BinaryNode root,int v){
        //左旋涉及到3个node
        BinaryNode node = find(root,v);
        if(node == null){
            System.out.println("未找到节点"+v+"，无法左旋！");
            return root;
        }
        if(node.right == null){
            //没有右子树的节点不能左旋。
            System.out.println("节点"+v+"没有右子树，无法左旋！");
            return root;
        }
        BinaryNode rNode = node.right;
        BinaryNode rlNode = node.right.left;
        //改变parent
        if(node.parent != null){
          if(node.val > node.parent.val){
              node.parent.right = rNode;
          }else{
              node.parent.left = rNode;
          }
          rNode.parent = node.parent;
        }else{
            //左旋的是根节点
            rNode.parent = null;
            root = rNode;
        }
        //改变左右指针
        rNode.left = node;
        node.parent = rNode;
        node.right = rlNode;
        if(rlNode != null)rlNode.parent = node;

        return root;
    }

    /**
     * 模拟红黑树右旋（不管颜色）
     * @return
     */
    static BinaryNode rotateRight(BinaryNode root,int v){
        //原理类似左旋，也涉及到3个节点
        BinaryNode node = find(root,v);
        if(node == null){
            System.out.println("未找到节点"+v+"，无法右旋！");
            return root;
        }
        if(node.left == null){
            //没有左子树的节点不能右旋。
            System.out.println("节点"+v+"没有左子树，无法右旋！");
            return root;
        }
        BinaryNode lNode = node.left;
        BinaryNode lrNode = node.left.right;
        //改变parent
        if(node.parent != null){
            if(node.val > node.parent.val){
                node.parent.right = lNode;
            }else{
                node.parent.left = lNode;
            }
            lNode.parent = node.parent;
        }else{
            //右旋根节点
            lNode.parent = null;
            root = lNode;
        }
        //改变左右指针
        lNode.right = node;
        node.parent = lNode;
        node.left = lrNode;
        if(lrNode != null)lrNode.parent=node;

        return root;
    }

    // 用于获得树的层数
    public int getTreeDepth(BinaryNode root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    private void writeArray(BinaryNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null)
            return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.val);

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth)
            return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

    /**
     * 打印树（直观）
     * @param root
     */
    public void show(BinaryNode root) {
        if (root == null) {
            System.out.println("EMPTY!");
            return ;
        }
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        writeArray(root, 0, arrayWidth / 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line : res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        BinaryTreeTest test = new BinaryTreeTest();
        int[] arr = {4,10,2,6,1,3,9,15,12,18};
        BinaryNode root = new BinaryNode(8);
        for(int i=0;i<arr.length;i++){
            insert(root,arr[i]);
        }
        test.show(root);
        System.out.println();

//        root = delete(root,1);
//        test.show(root);
//        System.out.println();
//
//        root = delete(root,6);
//        test.show(root);
//        System.out.println();
//
//        root = delete(root,10);
//        test.show(root);
//        System.out.println();
//
//        root = delete(root,8);
//        test.show(root);
//        System.out.println();

//        root = rotateLeft(root,6);
        root = rotateRight(root,4);
        test.show(root);
        System.out.println();
    }

}

class BinaryNode{
    int val;
    BinaryNode left;
    BinaryNode right;
    BinaryNode parent;

    public BinaryNode() {
    }

    public BinaryNode(int val) {
        this.val = val;
    }

    public BinaryNode(int val, BinaryNode left, BinaryNode right, BinaryNode parent) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}
