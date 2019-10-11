import java.util.ArrayList;

/**
 * 静态方法类
 */
public class Utils {
    public static void main(String args[]) {

    }

    /**
     * 输出一个int类型的一维数组
     * @param arr
     */
    public static void outputIntArray(int[] arr) {
        if (arr.length==0) {
            System.out.println("【Utils数组输出函数】数组为空");
            return;
        }
        System.out.println("输出数组元素:");
        for(int i=0;i<arr.length;i++) {
            System.out.println(arr[i]);
        }
    }
    // 输出Tree节点的双向链表
    public static void outputTreeDoubleListNode(TreeNode head) {
        while(head!=null) {
            System.out.println(head.val);
            head = head.right;
        }
    }


    // 输出ListNode
    public static void outputListNode(ListNode head){
        System.out.println("【Utils】输出链表节点");
        while(head!=null){
            System.out.println(head.val);
            head = head.next;
        }
        System.out.println("【Utils】输出结束");
    }
    // 输出ArrayList
    public static void outputArrayList(ArrayList<Integer> A){
        System.out.println("【Utils】开始输出ArrayList");
        for(int i=0;i<A.size();i++){
            if(i==A.size()-1){
                System.out.print(A.get(i)+";");
            }else{
                System.out.print(A.get(i)+",");
            }
        }
        System.out.println("【Utils】输出结束");
    }

    // 输出Charactor数组
    public static void outputCharArray(char[] C) {
        System.out.println("【utils】开始输出字符数组");
        for(int i=0;i<C.length;i++) {
            System.out.print(C[i]+",");
        }
        System.out.println();
        System.out.println("输出结束");
    }

    // 输出二维数组
    public static void outputDoubleList(ArrayList<ArrayList<Integer>> list) {
        System.out.println("【Utils】输出二维数组");
        for(int i=0;i<list.size();i++) {
            for(int j=0;j<list.get(i).size();j++) {
                System.out.print(list.get(i).get(j)+",");
            }
            System.out.println();
        }
        System.out.println("【Utils】输出完毕");
    }
}
