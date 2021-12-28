package com.sofn.fdpi.util;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 根据内容生成个表图片
 *
 * @author L
 */
public class ImageUtil {

    public static BufferedImage creatImageBuffer(String titel[], String data[][], String bottom[][]) {
        //标题字号
        int fontTitleSize = 15;
        String fontStyle = "新宋体";
        int totalRow = data.length + 1;
        int totalCol = 0;
        if (data[0] != null) {
            totalCol = data[0].length;
        }
        int imageWidth = 1024;
        int rowHeight = 40;
        int imageHeight = totalRow * rowHeight + 50;
        int startHeight = 10;
        int startWidth = 10;
        int colwidth = (int) ((imageWidth - 20) / totalCol);
        BufferedImage bi = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bi.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);
        graphics.setColor(new Color(220, 240, 240));
        //画横线
        for (int j = 0; j < totalRow; j++) {
            graphics.setColor(Color.BLACK);
            int y1 = startHeight + (j + 1) * rowHeight;
            int x2 = startWidth + colwidth * totalCol;
            int y2 = startHeight + (j + 1) * rowHeight;
            //根据内容  此处处理合并行
            if (j >= 4) {
                x2 = startWidth + colwidth * (totalCol - 1);
            }
            if (j == totalRow - 1) {
                x2 = startWidth + colwidth * totalCol;
            }
            graphics.drawLine(startWidth, y1, x2, y2);
        }
        //画竖线
        for (int k = 0; k < totalCol + 1; k++) {
            graphics.setColor(Color.black);
            int x1 = startWidth + k * colwidth;
            int y1 = startHeight + rowHeight;
            int x2 = startWidth + k * colwidth;
            int y2 = startHeight + rowHeight * totalRow;
            //横向合并 此处处理合并列
            if (k == 2) {
                graphics.drawLine(startWidth + k * colwidth, startHeight + rowHeight, startWidth + k * colwidth, startHeight + rowHeight * k);
                graphics.drawLine(startWidth + k * colwidth, startHeight + rowHeight * (k + 1), startWidth + k * colwidth, startHeight + rowHeight * totalRow);
            } else {
                graphics.drawLine(startWidth + k * colwidth, startHeight + rowHeight, startWidth + k * colwidth, startHeight + rowHeight * totalRow);
            }
        }
        Font font = new Font(fontStyle, Font.BOLD, fontTitleSize);
        graphics.setFont(font);
        //此处暂时写死--金额根据需要修改  标题显示位置在更改x的位置即可
        graphics.drawString(titel[0], startWidth + colwidth + colwidth / 5, startHeight + rowHeight - 10);
        graphics.drawString(titel[1], startWidth + colwidth * 2 + colwidth / 3, startHeight + rowHeight - 10);
        //整合整体数据

        //写入内容
        for (int n = 0; n < data.length; n++) {
            for (int l = 0; l < data[n].length; l++) {
                font = new Font(fontStyle, Font.PLAIN, fontTitleSize);
                graphics.setFont(font);
                graphics.drawString(data[n][l].toString(), startWidth + colwidth * l + 5, startHeight + rowHeight * (n + 2) - 10);
            }
            if (n == data.length - 1) {
                for (int j = 0; j < bottom[0].length; j++) {
                    font = new Font(fontStyle, Font.PLAIN, fontTitleSize);
                    graphics.setFont(font);
                    graphics.drawString(bottom[0][j], startWidth + colwidth * j + 5, startHeight + rowHeight * (n + 3) - 10);
                }
            }
        }
        return bi;
    }

    public static BufferedImage getImage() {
        int width = 820; // 图片宽
        int height = 600;// 图片高
        String titleStr = "公司测试点";
        String zhangdanzhouqiStr = "2019年2月1日至2019年2月28日"; // 账单周期
        String zhangdantianshuStr = "85天"; // 账单天数
        String bengedinggonglvStr = "1000KW";// 泵额定功率
        String bengbianpingjienengyunxingyongdianliangStr = "100度"; // 泵变频节能运行用电量
        String dianjiaStr = "100元/度"; // 电价
        String pingjunjienenglvStr = "50%";// 平均节能率
        String daizhifujingeStr = "1000元"; // 待支付金额
        String bengyunxingzongshichangStr = "200小时"; // 泵运行总时长
        String benggongping运行yongdianliangStr = "99度"; // 泵工频运行用电量
        String jieshengdianliangStr = "100度"; // 节省电量
        String jieshengjineStr = "1000元"; // 节省金额

        // 得到图片缓冲区
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);// INT精确度达到一定,RGB三原色，高度70,宽度150

        // 得到它的绘制环境(这张图片的笔)
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setColor(Color.WHITE); // 设置背景颜色
        g2.fillRect(0, 0, width, height);// 填充整张图片(其实就是设置背景颜色)
        g2.setColor(Color.black);// 设置字体颜色
        g2.setStroke(new BasicStroke(2.0f)); // 边框加粗
        g2.drawRect(1, 1, width - 2, height - 2); // 画边框就是黑边框

        g2.drawLine(0, 80, 820, 80); // 从上到下第二个横线(标题下面横线)
        g2.setStroke(new BasicStroke(0.0f)); // 边框不需要加粗
        g2.drawLine(0, 154, 820, 154); // 从上到下第三个横线(账单周期下面横线)
        g2.drawLine(0, 228, 820, 228); // 从上到下第四个横线(账单天数下面横线)
        g2.drawLine(0, 302, 820, 302); // 从上到下第5个横线(泵额定功率下面横线)
        g2.drawLine(0, 376, 820, 376); // 从上到下第6个横线(泵变频节能运行用电量下面横线)
        g2.drawLine(0, 451, 820, 451); // 从上到下第7个横线(电价下面横线)
        g2.drawLine(0, 525, 820, 525); // 从上到下第8个横线(平均节能率下面横线)

        g2.drawLine(180, 80, 180, 600); // 从左到右第二个竖线
        g2.drawLine(390, 154, 390, 451); // 从左到右第三个竖线
        g2.drawLine(574, 154, 574, 451); // 从左到右第四个竖线

        // 设置标题的字体,字号,大小
        Font titleFont = new Font("宋体", Font.BOLD, 30);
        g2.setFont(titleFont);
        String markNameStr = titleStr;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 抗锯齿
        // 计算文字长度,计算居中的X点坐标
        FontMetrics fm = g2.getFontMetrics(titleFont);
        int titleWidth = fm.stringWidth(markNameStr);
        int titleWidthX = (width - titleWidth) / 2 - 35;// 感觉不居中,向左移动35个单位
        g2.drawString(markNameStr + "节能账单", titleWidthX, 45);

        // 账单周期
        g2.setFont(new Font("宋体", Font.BOLD, 20));
        g2.drawString("账单周期", 33, 125);
        // 账单周期的值
        g2.drawString(zhangdanzhouqiStr, 230, 125);

        // 账单天数
        g2.drawString("账单天数", 33, 200);
        // 账单天数的值
        g2.drawString(zhangdantianshuStr, 230, 200);

        // 泵额定功率
        g2.drawString("泵额定功率", 33, 274);
        // 泵额定功率
        g2.drawString(bengedinggonglvStr, 230, 274);

        // 泵变频节能运行用电量
        g2.drawString("泵变频节能运", 33, 338);
        g2.drawString("行用电量", 33, 360);
        // // 泵变频节能运行用电量的值
        g2.drawString(bengbianpingjienengyunxingyongdianliangStr, 230, 345);

        // 电价
        g2.drawString("电价", 33, 423);
        // 电价的值
        g2.drawString(dianjiaStr, 230, 423);

        // 平均节能率
        g2.drawString("平均节能率", 33, 496);
        // 平均节能率
        g2.drawString(pingjunjienenglvStr, 230, 496);

        // 待支付金额
        g2.drawString("待支付金额", 33, 568);
        // 待支付金额的值
        g2.drawString(daizhifujingeStr, 230, 568);

        // 泵运行总时长
        g2.drawString("泵运行总时长", 420, 200);
        // 泵运行总时长的值
        g2.drawString(bengyunxingzongshichangStr, 630, 200);

        // 泵工频运行用电量
        g2.drawString("泵工频运行用", 420, 265);
        g2.drawString("电量", 420, 287);
        // 泵工频运行用电量的值
        g2.drawString(benggongping运行yongdianliangStr, 630, 274);

        // 节省电量
        g2.drawString("节省电量", 420, 348);
        // 节省电量的值
        g2.drawString(jieshengdianliangStr, 630, 345);

        // 节省金额
        g2.drawString("节省金额", 420, 423);
        // 节省金额的值
        g2.drawString(jieshengjineStr, 630, 423);

        g2.dispose(); // 释放对象
        return bi;
    }

}
