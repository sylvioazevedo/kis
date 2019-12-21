/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kis.view;

import javax.swing.JOptionPane;

/**
 *
 * @author sazevedo
 */
public class EncodePasswordFrame extends javax.swing.JFrame {
    
    private MainFrame mf;

    /**
     * Creates new form EncodePasswordFrame
     */
    public EncodePasswordFrame() {
        initComponents();
        
        this.setLocationRelativeTo(this.mf);
    }

    public EncodePasswordFrame(MainFrame mf) {
        // call first constructor
        this(); 
        
        // keep references inside
        this.mf = mf;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelAuhtentication = new javax.swing.JPanel();
        jLabelPassword = new javax.swing.JLabel();
        jLabelConfirmation = new javax.swing.JLabel();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jPasswordFieldConfirmation = new javax.swing.JPasswordField();
        jButtonEncode = new javax.swing.JButton();
        jButtonClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelAuhtentication.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Authentication"));

        jLabelPassword.setText("Password");

        jLabelConfirmation.setText("Confirmation");

        jPasswordFieldPassword.setText("jPasswordField1");

        jPasswordFieldConfirmation.setText("jPasswordField2");

        javax.swing.GroupLayout jPanelAuhtenticationLayout = new javax.swing.GroupLayout(jPanelAuhtentication);
        jPanelAuhtentication.setLayout(jPanelAuhtenticationLayout);
        jPanelAuhtenticationLayout.setHorizontalGroup(
            jPanelAuhtenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAuhtenticationLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelAuhtenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelConfirmation)
                    .addComponent(jLabelPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAuhtenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelAuhtenticationLayout.setVerticalGroup(
            jPanelAuhtenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAuhtenticationLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelAuhtenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword)
                    .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAuhtenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelConfirmation)
                    .addComponent(jPasswordFieldConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        jButtonEncode.setText("Encode");
        jButtonEncode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEncodeActionPerformed(evt);
            }
        });

        jButtonClose.setText("Close");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelAuhtentication, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonEncode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonClose)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelAuhtentication, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEncode)
                    .addComponent(jButtonClose)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jButtonEncodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEncodeActionPerformed
        
        String password = new String(this.jPasswordFieldPassword.getPassword());
        String confirmation = new String(this.jPasswordFieldConfirmation.getPassword());

        if (password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "A password is needed.", "Attention", 2);
            this.jPasswordFieldPassword.grabFocus();

            return;
        }

        if (password.length() > 24) {
            JOptionPane.showMessageDialog(this, "The password size must be less than 24 characters.");
            this.jPasswordFieldPassword.grabFocus();

            return;
        }

        if (confirmation == null || confirmation.isEmpty()) {
            JOptionPane.showMessageDialog(this, "A confirmation password is needed.", "Attention", 2);
            this.jPasswordFieldConfirmation.grabFocus();

            return;
        }

        if (!password.equals(confirmation)) {
            JOptionPane.showMessageDialog(this, "The passowrds informed are not the same.", "Attention", 2);
            this.jPasswordFieldConfirmation.setText("");
            this.jPasswordFieldConfirmation.grabFocus();

            return;
        }

        this.mf.encodeFile(password);

        dispose();
        
    }//GEN-LAST:event_jButtonEncodeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonEncode;
    private javax.swing.JLabel jLabelConfirmation;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JPanel jPanelAuhtentication;
    private javax.swing.JPasswordField jPasswordFieldConfirmation;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    // End of variables declaration//GEN-END:variables
}
