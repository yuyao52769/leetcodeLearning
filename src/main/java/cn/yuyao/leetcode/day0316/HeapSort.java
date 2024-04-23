package cn.yuyao.leetcode.day0316;

public class HeapSort {

    private int[] data;
    private int size;

    private int originalSize;

    public HeapSort(int[] nums) {
        this.data = nums;
        this.size = nums.length;
        this.originalSize = nums.length;
    }

    public  void sort() {
        int n = this.data.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            adjust(i);
        }
    }

    public int poll() throws Exception {
        if (this.size == 0) throw new Exception("no element");
        swap(0, size - 1);
        size--;
        adjust(0);
        return this.data[size];
    }

    public void adjust(int i) {
        int lChild = i * 2 + 1;
        int rChild = i * 2 + 2;
        int largest = i;
        if (lChild < this.size && this.data[lChild] > this.data[i]) {
            largest = lChild;
        }
        if (rChild < this.size && this.data[rChild] > this.data[i]) {
            largest = rChild;
        }
        if (largest != i) {
            swap(largest, i);
        }
        adjust(largest);
    }

    public void swap(int i, int j) {
        int temp = this.data[i];
        this.data[i] = this.data[j];
        this.data[j] = temp;
    }
}
