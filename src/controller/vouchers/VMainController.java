package controller.vouchers;

import addons.DragListener;
import addons.ExtraCode;
import controller.MainController;
import java.awt.Image;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import model.access.AccessDetails;
import model.vouchers.VouchersModel;
import model.vouchers.DAOVouchers;
import view.vouchers.VMainView;

public class VMainController extends VMainView implements ActionListener {

    private static VMainController instance;
    private DAOVouchers dao;
    private VouchersModel model;
    private AccessDetails access;

    public VMainController(AccessDetails access) throws Exception {
        super();
        this.registerEvents();
        this.registerObjects(access);
    }

    public static VMainController getInstance(AccessDetails access) {
        try {
            instance = new VMainController(access);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    private void registerEvents() {
        this.jbtExamine.addActionListener(this);
        this.jbtRegister.addActionListener(this);
        this.jbtVouchers.addActionListener(this);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                MainController.getInstance(access);
            }
        });
    }

    private void registerObjects(AccessDetails access) {
        this.access = access;
        this.dao = new DAOVouchers();
        this.model = new VouchersModel();
        new DropTarget(this, new DragListener(jlImage, jlImage));
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jbtExamine == e.getSource()) {
            this.btnExamine();
        } else if (jbtRegister == e.getSource()) {
            this.btnRegister();
        } else if (jbtVouchers == e.getSource()) {
            this.btnVouchers();
        }
    }

    private void btnExamine() {
        JFileChooser jfc = new JFileChooser();
        boolean status = false;
        while (!status) {
            if (jfc.showDialog(this, "Seleccione váucher(JPG, PNG o JPEG)") == 0) {
                File file = jfc.getSelectedFile();
                if (file.canRead()) {
                    try {
                        if (file.getName().endsWith("jpg") || file.getName().endsWith("png") || file.getName().endsWith("PNG") || file.getName().endsWith("JPEG") || file.getName().endsWith("jpeg") || file.getName().endsWith("ico") || file.getName().endsWith("mpeg") || file.getName().endsWith("jfif") || file.getName().endsWith("JFIF")) {
                            this.jlImage.setName(file.getAbsolutePath());
                            BufferedImage bf = ImageIO.read(file);
                            ImageIcon img = new ImageIcon(bf);
                            this.jlImage.setIcon(new ImageIcon(img.getImage().getScaledInstance(jlImage.getWidth(), jlImage.getHeight(), Image.SCALE_SMOOTH)));
                            status = true;
                        } else {
                            ExtraCode.sendMessageError("Error: Archivo seleccionado no admitido, vuelva a intentarlo.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    ExtraCode.sendMessageError("Error: Archivo seleccionado no admitido, vuelva a intentarlo.");
                }
            } else {
                status = true;
            }
        }
    }

    private void btnRegister() {
        String code = jtfCode.getText();
        String customer = jtfName.getText();
        String path = jlImage.getName();
        if (!code.isEmpty()) {
            if (!customer.isEmpty()) {
                if (path != null) {
                    if (!path.isEmpty()) {
                        try {
                            this.model = new VouchersModel(0, code, customer, new FileInputStream(path), ExtraCode.getCurrentDate());
                            this.dao.setModel(model);
                            if (dao.insert()) {
                                ExtraCode.sendMessageSuccessfully("Váucher registrado con éxito.");
                                this.jtfCode.setText(null);
                                this.jtfName.setText(null);
                                this.jlImage.setName(null);
                                this.jlImage.setIcon(null);
                                this.jtfCode.requestFocus();
                            } else {
                                ExtraCode.sendMessageError("Error: No se pudo registrar el váucher, vuelva a intentarlo.");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            ExtraCode.sendMessageError("Error: " + e.getLocalizedMessage());
                        }
                    }else{
                        ExtraCode.sendMessageError("Error: Váucher no seleccionado, vuelva intentarlo.");
                    }
                } else {
                    ExtraCode.sendMessageError("Error: Váucher no seleccionado, vuelva intentarlo.");
                }
            } else {
                ExtraCode.sendMessageError("Error: Nombre de cliente no ingresado, vuelva a intentarlo.");
            }
        } else {
            ExtraCode.sendMessageError("Error: Código no ingresado, vuelva a intentarlo.");
        }
    }

    private void btnVouchers() {
        VManagerController.getInstance(access);
        this.dispose();
    }
}
