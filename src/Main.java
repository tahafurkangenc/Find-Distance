import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
class Point{
    int i;
    int j;
    public Point(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
public class Main {
    public static int surehesapla(ArrayList<Point> points){
        int sure=0;
        for (int i = 1; i < points.size(); i++) {
            int satirFarki = Math.abs(points.get(i).i -points.get(i-1).i);
            int sutunFarki = Math.abs(points.get(i).j - points.get(i-1).j);

            int mesafe = satirFarki + sutunFarki;

            if (satirFarki != 0 && sutunFarki != 0) {
               if(satirFarki==2 && sutunFarki==2){
                   mesafe--;
               }
                mesafe--;
            }
            sure=sure+mesafe;
        }
        return sure;
    }
    public static void main(String[] args) {
        JFrame buttonframe = new JFrame("BUTTON FRAME");
        JFrame frame = new JFrame("Grid Layout");
        JPanel[][] paneller=new JPanel[3][3];
        JLabel[][] labeller=new JLabel[3][3];
        int [][] tustakimi={{6,4,3}, {9,8,2}, {5,7,1}};
        buttonframe.setLayout(new GridLayout(2,1,20,20));
        frame.setLayout(new GridLayout(3,3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                paneller[i][j]=new JPanel();
                labeller[i][j]=new JLabel(Integer.toString(tustakimi[i][j]));
                labeller[i][j].setHorizontalAlignment(JLabel.CENTER);
                labeller[i][j].setVerticalAlignment(JLabel.CENTER);
                paneller[i][j].add(labeller[i][j]);
                if((i+j)%2==0){
                    paneller[i][j].setBackground(Color.blue);
                }
                else {
                    paneller[i][j].setBackground(Color.cyan);
                }
                frame.add(paneller[i][j]);
            }
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        buttonframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonframe.setSize(300, 300);
        JButton yenibelirlebutton = new JButton("Yeni tuş takımı belirle");
        yenibelirlebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] yenituslarstring = JOptionPane.showInputDialog(frame,"yeni tuş takımını sıra ile , ekleyerek giriniz").split(",");
                int sayac=0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        tustakimi[i][j]=Integer.parseInt(yenituslarstring[sayac]);
                        labeller[i][j].setText(yenituslarstring[sayac]);
                        sayac++;
                    }
                }
                System.out.println("YENİ TUSTAKIMI");
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        System.out.print(tustakimi[i][j]+" ");
                    }
                    System.out.println();
                }
            }
        });
        JButton sifregir = new JButton("Şifre gir");
        sifregir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sifrestring= JOptionPane.showInputDialog(frame,"Şifre Giriniz");
                System.out.println(sifrestring.length());
                int[] sifre=new int[sifrestring.length()];
                for (int i = 0; i < sifrestring.length(); i++) {
                    sifre[i]=Integer.parseInt(String.valueOf(sifrestring.charAt(i)));
                }
                for (int i = 0; i <sifre.length ; i++) {
                    System.out.println("sifre:"+sifre[i]);
                }
                ArrayList<Point> points =new ArrayList<>();
                for (int k = 0; k < sifre.length; k++) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if(tustakimi[i][j]==sifre[k]){
                                points.add(new Point(i,j));
                            }
                        }
                    }
                }
                for (int i = 0; i < points.size(); i++) {
                    System.out.println("i -)"+points.get(i).i+" j -)"+points.get(i).j);
                }
                JOptionPane.showMessageDialog(frame, "Şifrenin girilme süresi: "+surehesapla(points)+" saniye", "Sonuçlar", JOptionPane.WARNING_MESSAGE);
            }
        });
        buttonframe.add(yenibelirlebutton);
        buttonframe.add(sifregir);
        frame.setVisible(true);
        buttonframe.setVisible(true);
    }
}