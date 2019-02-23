package com.smart.pojo;

//人体或人脸矩形框
public class Location {
        private double width;//矩形框宽度
        private double top;//左上角像素点纵坐标
        private double left;//左上角像素点横坐标
        private double height;//矩形框高度
        public void setWidth(double width) {
            this.width = width;
        }
        public double getWidth() {
            return width;
        }

        public void setTop(double top) {
            this.top = top;
        }
        public double getTop() {
            return top;
        }

        public void setLeft(double left) {
            this.left = left;
        }
        public double getLeft() {
            return left;
        }

        public void setHeight(double height) {
            this.height = height;
        }
        public double getHeight() {
            return height;
        }
}
