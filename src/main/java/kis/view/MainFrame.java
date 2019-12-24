/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kis.view;

import org.bouncycastle.crypto.CryptoException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kis.controller.FileController;
import kis.controller.Criptography;

/**
 *
 * @author sazevedo
 */
public class MainFrame extends javax.swing.JFrame {
    
    /**
     * Class properties.
     */
    private File file;
    private String lastDirPath;
    private boolean decode;
    

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        
        // center frame in the screen.
        this.setLocationRelativeTo(null);
        
        // set window icon
        try {
            setIconImage(ImageIO.read(getClass().getResource("/kis/image/lock.gif")));
        }
        catch(Exception e) {
            System.err.println("Unable to load application icon");
        }
    }
    
    private void cleanUp() {
        this.file = null;
        this.jTextFieldPath.setText("");
        this.jLabelDataSize.setText("0 bytes.");
        this.jLabelDataType.setText("Unknown.");
        this.jLabelCreated.setText("Unkown.");
        this.jLabelModified.setText("Unkown.");
        this.jLabelStatus.setText("Encode");
        this.jCheckBoxRemoveOldFile.setSelected(false);
    }
    
    public void encodeFile(String password) {
        
        int read = -1;
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            FileInputStream fis = new FileInputStream(this.file);

            while ((read = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, read);
            }
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        String cipherData = null;
        try {
            cipherData = Criptography.encode(baos.toByteArray(), password);
        } catch (CryptoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        String newFileName = ((this.lastDirPath != null) ? this.lastDirPath : "") + FileController.getName(this.file) + ".kis";

        try {
            FileOutputStream fos = new FileOutputStream(newFileName);
            fos.write(FileController.getExtension(this.file).getBytes());
            fos.write("#".getBytes());
            fos.write(cipherData.getBytes());
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (this.jCheckBoxRemoveOldFile.isSelected() && !this.file.delete()) {
            JOptionPane.showMessageDialog(this, "A problem has occurred when deleting file :");
        }

        cleanUp();

        JOptionPane.showMessageDialog(this, "File has been successfully encoded.", "Message", 1);
    }

    public void decodeFile(String password) {
        int read = -1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        

        try {
            FileInputStream fis = new FileInputStream(this.file);
            byte[] buffer = new byte[1024];

            while ((read = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, read);
            }
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String parts[] = new String(baos.toByteArray()).split("#");
        
        String ext = parts[0];
        String ciphered = parts[1];
            

        byte[] plainData = null;
        try {
            plainData = Criptography.decode(ciphered, password);
        } catch (CryptoException ex) {
            JOptionPane.showMessageDialog(this, "File could not be decoded, pehaps a wrong password.", "Attention", 0);

            return;
        }

        String newFileName = ((this.lastDirPath != null) ? this.lastDirPath : "") + FileController.getName(this.file) + "." + ext;

        try {
            FileOutputStream fos = new FileOutputStream(newFileName);
            fos.write(plainData);
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (this.jCheckBoxRemoveOldFile.isSelected()
                && !this.file.delete()) {
            JOptionPane.showMessageDialog(this, "A problem has occurred when deleting file :");
        }

        cleanUp();

        JOptionPane.showMessageDialog(this, "File has been successfully decoded.", "Message", 1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFilePath = new javax.swing.JPanel();
        jTextFieldPath = new javax.swing.JTextField();
        jButtonBrowse = new javax.swing.JButton();
        jPanelFileInfo = new javax.swing.JPanel();
        jLabelSizeText = new javax.swing.JLabel();
        jLabelTypeText = new javax.swing.JLabel();
        jLabelModifeidText = new javax.swing.JLabel();
        jLabelDataSize = new javax.swing.JLabel();
        jLabelDataType = new javax.swing.JLabel();
        jLabelCreatedText = new javax.swing.JLabel();
        jLabelCreated = new javax.swing.JLabel();
        jLabelModified = new javax.swing.JLabel();
        jLabelStatus = new javax.swing.JLabel();
        jCheckBoxRemoveOldFile = new javax.swing.JCheckBox();
        jButtonExecute = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("KIS - Interface");
        setAlwaysOnTop(true);
        setResizable(false);

        jPanelFilePath.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "File path"));

        jButtonBrowse.setText("...");
        jButtonBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFilePathLayout = new javax.swing.GroupLayout(jPanelFilePath);
        jPanelFilePath.setLayout(jPanelFilePathLayout);
        jPanelFilePathLayout.setHorizontalGroup(
            jPanelFilePathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFilePathLayout.createSequentialGroup()
                .addComponent(jTextFieldPath)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelFilePathLayout.setVerticalGroup(
            jPanelFilePathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFilePathLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFilePathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBrowse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanelFileInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "File information"));

        jLabelSizeText.setText("Size");

        jLabelTypeText.setText("Type");

        jLabelModifeidText.setText("Modified");

        jLabelDataSize.setForeground(new java.awt.Color(0, 51, 204));
        jLabelDataSize.setText("0 bytes");

        jLabelDataType.setForeground(new java.awt.Color(0, 51, 204));
        jLabelDataType.setText("Unknown");

        jLabelCreatedText.setText("Created");

        jLabelCreated.setForeground(new java.awt.Color(0, 51, 204));
        jLabelCreated.setText("Unknown");

        jLabelModified.setForeground(new java.awt.Color(0, 51, 204));
        jLabelModified.setText("Unknown");

        javax.swing.GroupLayout jPanelFileInfoLayout = new javax.swing.GroupLayout(jPanelFileInfo);
        jPanelFileInfo.setLayout(jPanelFileInfoLayout);
        jPanelFileInfoLayout.setHorizontalGroup(
            jPanelFileInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFileInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFileInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelFileInfoLayout.createSequentialGroup()
                        .addGroup(jPanelFileInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSizeText)
                            .addComponent(jLabelTypeText))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelFileInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDataType)
                            .addComponent(jLabelDataSize)))
                    .addGroup(jPanelFileInfoLayout.createSequentialGroup()
                        .addComponent(jLabelCreatedText)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelCreated))
                    .addGroup(jPanelFileInfoLayout.createSequentialGroup()
                        .addComponent(jLabelModifeidText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelModified)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelFileInfoLayout.setVerticalGroup(
            jPanelFileInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFileInfoLayout.createSequentialGroup()
                .addGroup(jPanelFileInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSizeText)
                    .addComponent(jLabelDataSize))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFileInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTypeText)
                    .addComponent(jLabelDataType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFileInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCreatedText)
                    .addComponent(jLabelCreated))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFileInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelModifeidText)
                    .addComponent(jLabelModified, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelStatus.setText("Encode");

        jCheckBoxRemoveOldFile.setText("Remove old file");

        jButtonExecute.setText("Execute");
        jButtonExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExecuteActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jLabel4.setText("Operation:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFilePath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelFileInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                .addComponent(jCheckBoxRemoveOldFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExecute)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelFileInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelStatus)
                    .addComponent(jCheckBoxRemoveOldFile)
                    .addComponent(jButtonExecute)
                    .addComponent(jButtonCancel)
                    .addComponent(jLabel4)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBrowseActionPerformed
        
        JFileChooser chooser = new JFileChooser((this.lastDirPath != null) ? this.lastDirPath : System.getProperty("user.dir"));
        
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.jTextFieldPath.setText(chooser.getSelectedFile().getAbsolutePath());
            openFile(chooser.getSelectedFile());
        }
        
        
    }//GEN-LAST:event_jButtonBrowseActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        
        
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Do you really want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION)) {
            this.setVisible(false);
            this.dispose();

            System.exit(0);
        }
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExecuteActionPerformed
        
        if (this.file == null || !this.file.isFile()) {
            JOptionPane.showMessageDialog(this, "No files has been defined. Please inform one.", "Attention", 2);
            this.jTextFieldPath.grabFocus();

            return;
        }
        if (!this.decode) {
            EncodePasswordFrame encodeForm = new EncodePasswordFrame(this);
            encodeForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            encodeForm.setVisible(true);
            encodeForm.requestFocus();
            encodeForm.setAlwaysOnTop(true); 
        } 
        else {
            DecodePasswordFrame decodeForm = new DecodePasswordFrame(this);
            decodeForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            decodeForm.setVisible(true);
            decodeForm.requestFocus();
            decodeForm.setAlwaysOnTop(true); 
        }
    }//GEN-LAST:event_jButtonExecuteActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBrowse;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonExecute;
    private javax.swing.JCheckBox jCheckBoxRemoveOldFile;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelCreated;
    private javax.swing.JLabel jLabelCreatedText;
    private javax.swing.JLabel jLabelDataSize;
    private javax.swing.JLabel jLabelDataType;
    private javax.swing.JLabel jLabelModifeidText;
    private javax.swing.JLabel jLabelModified;
    private javax.swing.JLabel jLabelSizeText;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelTypeText;
    private javax.swing.JPanel jPanelFileInfo;
    private javax.swing.JPanel jPanelFilePath;
    private javax.swing.JTextField jTextFieldPath;
    // End of variables declaration//GEN-END:variables

    private void openFile(File selectedFile) {
        if (!selectedFile.isFile()) {
            this.jLabelStatus.setText("Atention! Invalid file.");

            return;
        } 
    
        this.file = selectedFile;

        if (FileController.isKisFile(this.file)) {
            this.decode = true;
            this.jLabelStatus.setText("Decode");
        } 
        else {

            this.decode = false;
            this.jLabelStatus.setText("Encode");
        } 

        BasicFileAttributes attr;
        try {
            attr = Files.readAttributes(this.file.toPath(), BasicFileAttributes.class);
                    FileTime fileTime = attr.creationTime();
            this.lastDirPath = this.file.getPath().substring(0, this.file.getPath().indexOf(this.file.getName()));


            this.jLabelDataSize.setText(String.valueOf(this.file.length()) + " bytes.");
            this.jLabelDataType.setText(FileController.identifyFile(this.file));
            this.jLabelModified.setText((new SimpleDateFormat("MM/dd/yyyy - HH:mm")).format(new Date(this.file.lastModified())));
            this.jLabelCreated.setText((new SimpleDateFormat("MM/dd/yyyy - HH:mm")).format(new Date(fileTime.toMillis())));
        } 
        catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
