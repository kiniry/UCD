package ie.ucd.music.comparison.Interface;


   

    import java.awt.BorderLayout;
    import javax.swing.*;
    import javax.swing.JFrame;
    import com.borland.jbcl.layout.XYLayout;
    import com.borland.jbcl.layout.*;
    import javax.swing.JButton;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import javax.swing.JLabel;
    import java.awt.Dimension;

    /**
     * <p>Title: </p>
     *
     * <p>Description: </p>
     *
     * <p>Copyright: Copyright (c) 2008</p>
     *
     * <p>Company: </p>
     *
     * @author not attributable
     * @version 1.0
     */
    public class Frame3 extends JFrame {
        XYLayout xYLayout1 = new XYLayout();
        JButton Cancel = new JButton();
        JButton Move = new JButton();
        JButton Delete = new JButton();
        JLabel jLabel1 = new JLabel();

        public Frame3() {
            try {
                jbInit();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        private void jbInit() throws Exception {
            getContentPane().setLayout(xYLayout1);
            setSize(new Dimension(400, 300));
            Cancel.setText("Cancel");
            Cancel.addActionListener(new Frame3_Cancel_actionAdapter(this));
            Move.setText("Move");
            Move.addActionListener(new Frame3_Move_actionAdapter(this));
            Delete.addActionListener(new Frame3_Delete_actionAdapter(this));
            jLabel1.setText(
                    "There were duplicates found, what would you like to do with them?");
            Delete.setText("Delete");
            this.getContentPane().add(jLabel1, new XYConstraints(38, 65, 336, 57));
            this.getContentPane().add(Move, new XYConstraints(225, 197, 80, 40));
            this.getContentPane().add(Cancel, new XYConstraints(310, 197, 80, 40));
            this.getContentPane().add(Delete, new XYConstraints(139, 197, 80, 40));
        }

        public void Cancel_actionPerformed(ActionEvent e) {
            System.exit(0);
        }

        public void Move_actionPerformed(ActionEvent e) {
            System.out.println("The Move command was pressed");
            JOptionPane.showMessageDialog(this, "The move button was pressed i will now move the files");
        }



        public void Delete_actionPerformed(ActionEvent e) {
            /*String fileName = "file.txt";
        // A File object to represent the filename
        File f = new File(fileName);

        boolean success = f.delete();

        if (!success)
          throw new IllegalArgumentException("Delete: deletion failed");*/


            System.out.println("The Delete command was pressed");
            JOptionPane.showMessageDialog(this, "The delete button was pressed i will now delete the files");
        }
    }


    class Frame3_Delete_actionAdapter implements ActionListener {
        private Frame3 adaptee;
        Frame3_Delete_actionAdapter(Frame3 adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.Delete_actionPerformed(e);
        }
    }


    class Frame3_Cancel_actionAdapter implements ActionListener {
        private Frame3 adaptee;
        Frame3_Cancel_actionAdapter(Frame3 adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.Cancel_actionPerformed(e);
        }
    }


    class Frame3_Move_actionAdapter implements ActionListener {
        private Frame3 adaptee;
        Frame3_Move_actionAdapter(Frame3 adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.Move_actionPerformed(e);
        }
    }


