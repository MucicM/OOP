import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class Galerija extends JFrame {

    private static final long serialVersionUID = 1L;
    private int i = 0;
    private List<File> images = new ArrayList<>();

    public Galerija() {

        this.setLocation(100, 100);
        setSize(400, 500);
        setTitle("Galerija reloaded");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton back = new JButton("<");
        JButton next = new JButton(">");
        JButton load = new JButton("Load");
        JLabel imgabel = new JLabel();
        JPanel btnpanel = new JPanel();
        JTextField url = new JTextField();
        JPanel urls = new JPanel();

        url.setPreferredSize(new Dimension(200, 30));
        urls.setPreferredSize(new Dimension(450, 50));
        urls.setLayout(new FlowLayout());
        urls.add(url);
        urls.add(load);
        this.add(urls, BorderLayout.NORTH);

        imgabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(imgabel, BorderLayout.CENTER);

        btnpanel.setLayout(new FlowLayout());
        btnpanel.add(back);
        btnpanel.add(next);
        this.add(btnpanel, BorderLayout.SOUTH);

        load.addActionListener(e -> {
            File folder = new File(url.getText());
            //getting all img files
            File[] allfiles = folder.listFiles(pathname -> pathname.getName().endsWith("jpg") || pathname.getName().endsWith("png") || pathname.getName().endsWith("gif"));
            //erasing text field
            url.setText("");
            //add all the files from the array to my list
            if (allfiles != null){
                images = Arrays.asList(allfiles);

                //setting the first image
                ImageIcon img0 = new ImageIcon(images.get(0).toString());
                Dimension d = newsize(img0, imgabel );
                Image newImage = (img0.getImage()).getScaledInstance(d.width, d.height, Image.SCALE_DEFAULT);
                img0 = new ImageIcon(newImage);
                imgabel.setIcon(img0);
            }
        });

        back.addActionListener(e -> {
            if (images.size() != 0){
                ImageIcon img;
                //if image is the first then show last else show the previous one
                if (i == 0){
                    img = new ImageIcon(images.get(images.size() - 1).toString());
                    Dimension d = newsize(img, imgabel );
                    Image newImage = (img.getImage()).getScaledInstance(d.width, d.height, Image.SCALE_DEFAULT);
                    img = new ImageIcon(newImage);
                    i = images.size() - 1;
                } else {
                    i--;
                    img = new ImageIcon(images.get(i).toString());
                    Dimension d = newsize(img, imgabel );
                    Image newImage = (img.getImage()).getScaledInstance(d.width, d.height, Image.SCALE_DEFAULT);
                    img = new ImageIcon(newImage);
                }
                imgabel.setIcon(img);
            }

        });

        next.addActionListener(e -> {
            if (images.size() != 0){
                ImageIcon img;
                //if image is the last show the first else show the next one
                if (i == images.size() - 1){
                    img = new ImageIcon(images.get(0).toString());
                    Dimension d = newsize(img, imgabel );
                    Image newImage = (img.getImage()).getScaledInstance(d.width, d.height, Image.SCALE_DEFAULT);
                    img = new ImageIcon(newImage);
                    i = 0;
                } else {
                    i++;
                    img = new ImageIcon(images.get(i).toString());
                    Dimension d = newsize(img, imgabel );
                    Image newImage = (img.getImage()).getScaledInstance(d.width, d.height, Image.SCALE_DEFAULT);
                    img = new ImageIcon(newImage);
                }
                imgabel.setIcon(img);
            }

        });
    }

    //returns dimension for resizing images so they keep their original aspect ratio

    private static Dimension newsize( ImageIcon img, JLabel cont ) {
        int original_width = img.getIconWidth();
        int original_height = img.getIconHeight();
        int max_width = cont.getWidth();
        int max_height = cont.getHeight();
        int new_width = original_width;
        int new_height = original_height;

        if (original_width > max_width){
            new_width = max_width;
            new_height = (new_width * original_height) / original_width;
        }

        if (new_height > max_height){
            new_height = max_height;
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }


    public static void main( String[] args ) {

        SwingUtilities.invokeLater(() -> {
            JFrame f1 = new Galerija();
            f1.setVisible(true);
        });
    }

}
