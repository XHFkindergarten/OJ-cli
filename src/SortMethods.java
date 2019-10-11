import org.w3c.dom.ranges.Range;

public class SortMethods {
    public static void main(String args[]) {
        int[] arr = {7,6,5,3,2,1};
        SortMethods sm = new SortMethods();
        sm.CountingSort(arr);
        Utils.outputIntArray(arr);
    }





    /**
     * 冒泡排序
     * @param arr
     * @description
     * 通过对数组依次进行两两比较，将数值较大的数字放在右边
     * 每次遍历都将前n个数中最大的数放到这n个数的最右侧
     * 最大的数像是冒泡泡一样移动到数组的最右侧，因此得名冒泡排序（Bubble Sort）
     * @BestTime    最优时间复杂度     O(n)
     * @WorstTime   最坏时间复杂度     O(n^2)
     * @AverageTime 平均时间复杂度     O(n^2)
     * @Space       空间复杂度        O(1)   记录是否发生数字交换flag
     * @isStable    是否稳定          true
     */
    public void BubbleSort(int[] arr) {
        // 定义一个flag用来记录是否发生过数字交换
        boolean flag;
        // i为每一轮最后一次比较的位置下标，共有i+1轮冒泡
        for (int i=arr.length-2;i>=0;i--) {
            flag = false;
            // 依次两两比较，将较大的数字放到右侧
            for (int j=0;j<=i;j++) {
                if (arr[j]>arr[j+1]){
                    change(arr, j, j+1);
                    flag = true;
                }
            }
            // 如果没有发生过数字位置交换，说明数组已经有序
            // 算法的最优时间复杂度为O(n)，就是第一轮冒泡没有发生交换的情况
            if (!flag) {
                return;
            }
        }
    }

    /**
     * 选择排序(Selection Sort)
     * @param arr
     * @description
     * 对数组进行n-1轮选择
     * 每一轮都将找到的n个数中最小的数字放在这n个数的第1位
     * @BestTime    最优时间复杂度     O(n^2)
     * @WorstTime   最坏时间复杂度     O(n^2)
     * @AverageTime 平均时间复杂度     O(n^2)
     * @Space       空间复杂度        O(1)   记录最小值的位置minIndex
     * @isStable    是否稳定          false     前面的数字很可能在交换的过程中被交换到较后的位置
     */
    public void SelectionSort(int[] arr) {
        int minIndex;
        // 第i+1趟排序，需要将最小的数字放在arr[i]处
        // 从第1个数字遍历到倒数第二个数字
        for(int i=0;i<arr.length-1;i++) {
            // 初始化最小值位置
            minIndex = i;
            for(int j=i+1;j<arr.length;j++) {
                if (arr[j]<arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 选择一轮之后进行交换
            change(arr, i, minIndex);
        }
    }

    /**
     * 插入排序(Insertion Sort)
     * @description
     * 从前到后逐渐扩大数组的有序区域
     * 认为第一个数字有序
     * 依次遍历第2-n个数字在有序数组中从后到前进行对比
     * 直到有序数组中找到比该数字小的数字，则将arr[i]插入到这个数字的后面
     * @param arr
     * @BestTime    最优时间复杂度     O(n)
     * @WorstTime   最坏时间复杂度     O(n^2)
     * @AverageTime 平均时间复杂度     O(n^2)
     * @Space       空间复杂度        O(1)
     * @isStable    是否稳定          true
     */
    public void InsertionSort(int[] arr) {

        // 要插入的数字
        int insertNum;
        // 寻找插入位置的下标
        int current;

        // 第2个数字->第n个数字 插入数字arr[i]
        for(int i=1;i<arr.length;i++) {
            // 从后向前遍历有序区域 对比数字arr[j]
            insertNum = arr[i];
            current = i-1;
            // 当指针没到头且指向的数字比insertNum要大时
            while (current>=0&&arr[current]>insertNum) {
                // 将指针的数字右移一位腾出插入空间
                arr[current+1] = arr[current];
                // 指针左移
                current--;
            }
            // 找到了插入位置或者current=-1
            arr[current+1] = insertNum;
        }
    }

    /**
     * 希尔排序(Shell Sort)
     * @param arr
     * @description
     * 确定一个间隔序列
     * 利用间隔序列对数组进行分组
     * 再对每个分组进行简单插入排序
     * 最后一次间隔为1，即将整个数组进行简单插入排序
     * 在大致有序的情况下，简单插入排序的时间复杂度约为O(n)
     * @BestTime    最优时间复杂度    O(n)
     * @WorstTime   最坏时间复杂度    O(n^2)
     * @AverageTime 平均时间复杂度    O(n^1.3)
     * @Space       空间复杂度       O(1)
     * @isStable    是否稳定         false  本身插入排序就不稳定
     */
    public void ShellSort(int[] arr) {
        int len = arr.length;
        // 初始化间隔
        int gap = len/2;
        int current, insertNum;
        while (gap>0) {
            // gap -> len-1刚好是每个分组的最后一个数字
            for(int i=gap;i<len;i++) {
                // 对每一组进行简单插入排序
                current = i - gap;
                insertNum = arr[i];
                while (current>=0 && arr[current]>insertNum) {
                    arr[current+gap] = arr[current];
                    current -= gap;
                }
                arr[current+gap] = insertNum;
            }
            // 间隔依次折半
            gap /= 2;
        }
    }

    /**
     * 归并排序(MergeSort)
     * @param arr
     * @description
     * 使用了分治的思想,
     * 将数组不断二分（也称2-路归并），不断合并
     * @BestTime    最优时间复杂度     O(N*logN)
     * @WorstTime   最坏时间复杂度     O(N*logN)
     * @AverageTime 平均时间复杂度     O(N*logN)
     * @Space       空间复杂度        O(n)
     * @isStable    是否稳定          true
     */
    public void MergeSort(int[] arr) {
        RangeMerge(arr, 0, arr.length-1);
    }

    /**
     * 排序主体，加入了范围参数
     * @param arr
     * @param start
     * @param end
     */
    public void RangeMerge(int[] arr, int start, int end) {
        int mid = (start+end) / 2;
        if (start == end) {
            return;
        }
        // 对数组1进行归并排序
        RangeMerge(arr, start, mid);
        // 对数组2进行归并排序
        RangeMerge(arr, mid+1, end);
        // 将左右数组合并到一起
        merge(arr, start, mid, mid+1, end);
    }

    /**
     * @param arr       数组本体
     * @param start1    数组1开始位置
     * @param end1      数组1结束位置
     * @param start2    数组2开始位置
     * @param end2      数组2结束位置
     */
    public void merge(int[] arr, int start1, int end1, int start2, int end2) {
        int[] temp = new int[end2 - start1 + 1];
        int index1 = start1, index2 = start2;
        int index = 0;
        // 合并数组
        while (index1<=end1 && index2<=end2) {
            if (arr[index1]<arr[index2]) {
                temp[index++] = arr[index1++];
            } else {
                temp[index++] = arr[index2++];
            }
        }
        // 将剩余的数组全部放入temp数组
        if (index1>end1) {
            while (index2<=end2) {
                temp[index++] = arr[index2++];
            }
        } else {
            while (index1<=end1) {
                temp[index++] = arr[index1++];
            }
        }
        // 确定数组的归并范围（未必数组1就在左边）
        int min = Math.min(start1, start2);
        // 将temp数组放入原数组
        for(int i=0;i<temp.length;i++) {
            arr[min+i] = temp[i];
        }
    }

    /**
     * 快速排序
     * @param arr
     * @description
     * 对数组进行分区：挑选一个数（此处为第一个数）作为基准
     * 将小于基准的数放在基准数左侧，大于的放在右侧
     * 然后对左侧和右侧的数字进行递归分区操作
     * @BestTime    最优时间复杂度     O(N*logN)
     * @WorstTime   最坏时间复杂度     O(N^2)
     * @AverageTime 平均时间复杂度     O(N*logN)
     * @Space       空间复杂度        O(1)
     * @isStable    是否稳定          false
     */
    public void QuickSort(int[] arr) {
        RangeQuick(arr, 0, arr.length-1);
    }

    /**
     * 范围快速排序
     * @param arr       排序数组
     * @param start     起始点
     * @param end       结束点
     */
    public void RangeQuick(int[] arr, int start, int end) {
        if (start>=end) {
            return;
        }
        // centerNum为分界数
        int centerNum = arr[start];
        int index = start+1;
        for(int i=index;i<=end;i++) {
            if (arr[i]<centerNum) {
                change(arr, i, index);
                // index左侧的数字都比centerNum要小
                // 所以每增加一个都要右移一位
                index++;
            }
        }
        // 将centerNum放在数组的中间位置
        change(arr, index-1, start);
        // 对完成了分区操作的两个数组递归进行分区
        RangeQuick(arr, start, index-1);
        RangeQuick(arr, index, end);
    }

    /**
     * 堆排序(Heap Sort)
     * @param arr
     * @description
     * 将数组看做二叉树，根据数字下标决定在二叉树中的位置
     * 下标为i的节点的子节点下标为2*i和2*i+1
     * 对二叉树进行构建大根堆操作，然后将堆顶元素放置到有序区域
     * 这样会不断将无须区域的最大值找到，构建有序数组
     * @BestTime    最优时间复杂度     O(N*logN)
     * @WorstTime   最坏时间复杂度     O(N*logN)
     * @AverageTime 平均时间复杂度     O(N*logN)
     * @Space       空间复杂度        O(1)
     * @isStable    是否稳定          false
     */
    public void HeapSort(int[] arr) {
        // 堆排序的最大值要输出的位置
        // 初始值为数组末端
        int index = arr.length-1;
        while (index>0) {
            // Heapify 构建大根堆操作，使得二叉树满足所有父节点 > 子节点
            for(int i=index;i>=0;i--) {
                // 左子节点下标
                int left = i * 2;
                // 右子节点下标
                int right = i * 2 + 1;
                // 如果左右子节点在排序范围内且大于父节点，则交换父子节点的位置
                if (left <= index && arr[left]>arr[i]) {
                    change(arr, left, i);
                }
                if (right<= index && arr[right]>arr[i]) {
                    change(arr, right, i);
                }
            }
            // 一次构建大根堆操作完成后，将堆顶元素放到无序数组的末尾
            // 然后再次进行构建大根堆操作，直到数组完全有序
            change(arr, 0, index--);
        }
    }

    /**
     * 计数排序(Counting Sort)
     * @param arr
     * @description
     * 通过先确定数组的上界和下界，
     * 新建一个记录数组来记录每个数字出现的次数，数字的值即是记录数组的数组下标
     * 最后根据记录数组来还原数组，实现有序
     * @BestTime    最优时间复杂度     O(n+k)
     * @WorstTime   最坏时间复杂度     O(n+k)
     * @AverageTime 平均时间复杂度     O(n+k)
     * @Space       空间复杂度        O(n+k)
     * @isStable    是否稳定          true
     */
    public void CountingSort(int[] arr) {
        // 确定数组的范围min,max
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++) {
            if (arr[i]>max) {
                max = arr[i];
            }
            if (arr[i]<min) {
                min = arr[i];
            }
        }
        // 根据范围生成一个数组记录每个数出现的次数
        int[] count = new int[max-min+1];
        for(int i=0;i<arr.length;i++) {
            count[arr[i]-min]++;
        }
        // 将记录数组中的数字填充到原数组中，此时数组有序
        int index = 0;
        for(int i=0;i<count.length;i++) {
            for(int j=count[i];j>0;j--) {
                arr[index++] = i+min;
            }
        }

    }





    /*
    交换数组两个数的位置
     */
    public void change(int[] arr, int a, int b) {
        if (a==b || arr.length==0) {
            return;
        }
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
