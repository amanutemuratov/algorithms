private class SegmentTree {
        SegmentTree leftChild;
        SegmentTree rightChild;
        int leftBound;
        int rightBound;
        int val;
        int pushValue;

        public SegmentTree(int left, int right) {
            leftBound = left;
            rightBound = right;
            pushValue = 0;
            if (left==right) {
                val = 0;
                return;
            }

            int mid = (left + right)/2;
            leftChild = new SegmentTree(left, mid);
            rightChild = new SegmentTree(mid+1, right);
            val = 0;
        }

        public int getMax(int l, int r) {
            push();
            if (rightBound < l || leftBound > r) return 0;
            if (l <= leftBound && rightBound <= r) return val;
            return Math.max(leftChild.getMax(l, r), rightChild.getMax(l, r));
        }

        public void update(int left, int right, int val) {
            push();
            if (left>rightBound || right<leftBound) {
                return;
            }
            if (left<=leftBound && rightBound<=right) {
                pushValue+=val;
                push();
            } else {
                push();
                leftChild.update(left, right, val);
                rightChild.update(left, right, val);
                this.val = Math.max(leftChild.val, rightChild.val);
            }
        }

        private void push() {
            if (pushValue!=0) {
                val += pushValue;
                if (leftBound != rightBound) {
                    leftChild.pushValue += pushValue;
                    rightChild.pushValue += pushValue;
                }
                pushValue = 0;
            }
        }

        public int getMaxPos(int curMax) {
            push();
            if (leftBound==rightBound) {
                return leftBound;
            }
            if (leftChild.getMax(0,posCounter) == curMax) {
                return leftChild.getMaxPos(curMax);
            } else {
                return rightChild.getMaxPos(curMax);
            }
        }
    }
