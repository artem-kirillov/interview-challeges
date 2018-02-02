class MinStack {
    private Node head;

    public void push(int x) {
        head = new Node(x, head != null ?  Math.min(x, head.min) : x, head);
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.value;
    }

    public int getMin() {
        return head.min;
    }

    private static class Node {
        final int value;
        final int min;
        final Node next;

        Node(final int value, final int min, final Node next) {
            this.value = value;
            this.min = min;
            this.next = next;
        }
    }
}
