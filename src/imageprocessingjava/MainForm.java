package imageprocessingjava;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.*;
import javax.swing.JOptionPane;

import java.io.IOException;

public class MainForm extends javax.swing.JFrame {

    BufferedImage Image, TempImage, FlipImage;
    File f;

    public MainForm() {
        initComponents();
        Image = new BufferedImage(5185, 3457, BufferedImage.TYPE_INT_RGB);
        //Image=new BufferedImage(853,1280,BufferedImage.TYPE_INT_RGB);
        FlipImage = new BufferedImage(Image.getHeight(), Image.getWidth(), BufferedImage.TYPE_INT_RGB);
        f = new File("D:\\TESTING.bmp\\");
        ///f = new File("D:\\TESTING2.bmp\\");
        //f = new File("/media/afoek/DATA/TESTING.bmp");
        //f = new File("D:\\Temp.bmp\\");
        try {
            Image = ImageIO.read(f);
        } catch (IOException e) {

        }
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        //g.drawImage(FlipImage, 0, 0, Image.getWidth(), Image.getHeight(), this);
        g.drawImage(Image, 0, 0, getWidth(), getHeight(), this); //windows full screen 
        //g.drawImage(Image, 0, 0,this); //Image windowed
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Image Processing Java");
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(5799, 3463));
        setSize(new java.awt.Dimension(5798, 3848));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5799, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34631, 34631)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private BufferedImage CopyBufferedImage(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

        int x, y, z, Color, a, b, alpha = 45, x0, y0, dx, dy, tx, ty, xp, yp, skala = 2;
        double sudut = alpha * Math.PI / 180, sin, cos, D;
        //this.setTitle(evt.getKeyCode()+"");
        switch (evt.getKeyChar()) {
            //flip horizontal
            case '1': {
                for (y = 0; y < Image.getHeight(); y++) {
                    z = Image.getWidth() - 1;
                    for (x = 0; x < Image.getWidth() / 2; x++) {
                        Color = Image.getRGB(x, y);
                        Image.setRGB(x, y, Image.getRGB(z, y));
                        Image.setRGB(z, y, Color);
                        z--;
                    }
                }
                this.repaint();
                break;
            }
            //flip vertikal
            case '2': {
                for (x = 0; x < Image.getWidth(); x++) {
                    for (y = 0; y < Image.getHeight() / 2; y++) {
                        z = Image.getHeight() - y - 1;
                        Color = Image.getRGB(x, y);
                        Image.setRGB(x, y, Image.getRGB(x, z));
                        Image.setRGB(x, z, Color);
                        z--;
                    }
                }
                this.repaint();
                break;
            }
            //Rotate 90 derajat
            case '3': {
                TempImage = CopyBufferedImage(Image);
                Image = new BufferedImage(TempImage.getHeight(), TempImage.getWidth(), BufferedImage.TYPE_INT_RGB);
                for (y = 0; y < TempImage.getHeight(); y++) {
                    for (x = 0; x < TempImage.getWidth(); x++) {
                        b = x;
                        a = TempImage.getHeight() - y - 1;
                        Image.setRGB(a, b, TempImage.getRGB(x, y));
                    }
                }
                this.repaint();
                break;
            }
            //Rotate 180 derajat
            case '4': {
                TempImage = CopyBufferedImage(Image);
                for (y = 0; y < TempImage.getHeight(); y++) {
                    for (x = 0; x < TempImage.getWidth(); x++) {
                        a = TempImage.getWidth() - 1 - x;
                        b = TempImage.getHeight() - 1 - y;
                        Image.setRGB(a, b, TempImage.getRGB(x, y));
                    }
                }
                this.repaint();
                break;
            }
            //Rotate 270 derajat
            case '5': {
                TempImage = CopyBufferedImage(Image);
                Image = new BufferedImage(TempImage.getHeight(), TempImage.getWidth(), BufferedImage.TYPE_INT_RGB);
                for (y = 0; y < TempImage.getHeight(); y++) {
                    for (x = 0; x < TempImage.getWidth(); x++) {
                        a = y;
                        b = TempImage.getWidth() - 1 - x;
                        Image.setRGB(a, b, TempImage.getRGB(x, y));
                    }
                }
                this.repaint();
                break;
            }
            //Rotate 45 derajat 
            case '6': {
                TempImage = CopyBufferedImage(Image);
                if (alpha > 270) {
                    dx = Math.abs((int) (Image.getHeight() * Math.sin(sudut)));
                    dy = Math.abs((int) (Image.getWidth() * Math.sin(sudut)));
                    xp = dx / 2;
                    yp = dy;
                } else if (alpha > 180) {
                    dx = Math.abs((int) (Image.getHeight() * Math.sin(sudut)));
                    dy = Math.abs((int) (Image.getWidth() * Math.sin(sudut)));
                    xp = dx;
                    yp = dy + Image.getHeight();
                } else if (alpha > 90) {
                    dx = Math.abs((int) (Image.getHeight() * Math.sin(sudut)));
                    dy = Math.abs((int) (Image.getWidth() * Math.sin(sudut)));
                    xp = dx + Image.getHeight();
                    yp = dy;
                } else {
                    dx = Math.abs((int) (Image.getHeight() * Math.sin(sudut)));
                    dy = Math.abs((int) (Image.getWidth() * Math.sin(sudut)));
                    xp = dx;
                    yp = 0;
                }
                for (y = 0; y < Image.getHeight(); y++) {
                    for (x = 0; x < Image.getWidth(); x++) {
                        Image.setRGB(x, y, 0);
                    }
                }
                for (y = 0; y < TempImage.getHeight() - 1; y++) {
                    for (x = 0; x < TempImage.getWidth() - 1; x++) {
                        sin = Math.sin(sudut);
                        cos = Math.cos(sudut);
                        D = cos * cos + sin * sin;
                        tx = x - xp;
                        ty = y - yp;
                        x0 = (int) ((tx * cos + ty * sin) / D);
                        y0 = (int) ((ty * cos - tx * sin) / D);
                        if (((x0 >= 0) && (x0 < Image.getWidth()) && ((y0 > 0) && (y0 < Image.getHeight())))) {
                            Image.setRGB(x, y, TempImage.getRGB(x0, y0));
                        }
                    }
                }
                this.repaint();
                break;
            }
            //Zoom 2x
            case '7': {
                TempImage = new BufferedImage(Image.getWidth() * skala, Image.getHeight() * skala, BufferedImage.TYPE_INT_RGB);
                TempImage = CopyBufferedImage(Image);
                for (y = 0; y < TempImage.getHeight() - 1; y++) {
                    y0 = (int) (y / skala);
                    for (x = 0; x < TempImage.getWidth() - 1; x++) {
                        x0 = (int) (x / skala);
                        Image.setRGB(x, y, TempImage.getRGB(x0, y0));
                    }
                }
                this.repaint();
                break;
            }
        }
        //just help windows F1 button
        if (evt.getKeyCode() == 112) {
            JOptionPane.showMessageDialog(this, "Tombol 1 untuk flip horizontal, tombol 2 untuk flip vertikal, tombol 3 untuk rotate 90 derajat, \ntombol 4 untuk rotate 180 derajat, tombol 5 untuk rotate 270 derajat, tombol 6 untuk rotate 45 derajat, \ntombol 7 untuk zoom 2x, tombol esc untuk keluar dari aplikasi", "Help", JOptionPane.INFORMATION_MESSAGE);
        }
        //esc for exit and close programme
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            dispose();
            System.exit(0);
        }

    }//GEN-LAST:event_formKeyPressed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
